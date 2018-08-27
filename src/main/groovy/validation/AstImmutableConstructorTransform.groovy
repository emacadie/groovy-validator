package validation

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode 

import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation 

@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class AstImmutableConstructorTransform implements ASTTransformation {
    
    def knownTypes = [ 'java.lang.Double', 'java.lang.Float', 'java.lang.Integer', 'java.lang.Long', 'java.lang.String', 
    'double', 'float', 'int', 'long' ]
    
    def className
    
    String toString() {
        "AstImmutableConstructorTransform"
    }
    
    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        
        if ( !astNodes ) { return }
        if ( !astNodes[ 0 ] ) { return }
        if ( !astNodes[ 1 ] ) { return }

        ClassNode annotatedClass = astNodes[ 1 ] // ( ClassNode ) astNodes[ 1 ]

        def constructors001 = annotatedClass.getDeclaredConstructors()        
        def fields = annotatedClass.getFields()
        def fields2 = annotatedClass.getFields().findAll{ 
            ( ( knownTypes.contains( it.getType().getName() ) ) && 
            ( !it.getName().contains( '$hash$code' ) ) ) 
        } 
        className = annotatedClass.getNameWithoutPackage()
        // println "Here is className: ${className} and it is a ${className.class.name}"
        def minimum
        def maximum
        def packageString = annotatedClass.getPackageName()? "package  ${annotatedClass.getPackageName()}" : " "
        def theString = 
        """
        ${packageString}
        
        class ${annotatedClass.getNameWithoutPackage()} {
            def static createValidatedObject( java.util.HashMap argMap, boolean throwException = false ) {
                java.util.HashMap newMap = [:]
                ${processFields( fields2 )}
                return new ${annotatedClass.getPackageName()}.${annotatedClass.getNameWithoutPackage()}( newMap )
            }
        } // end class 
            """.toString()
        // println "Here is theString: ${theString}"
        try {
            def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, false, theString )
            // look at block statement
            // look at the class Node

            def someClassNode = ast[ 1 ]

            def methods = ast[ 1 ].methods
            annotatedClass.addMethod( methods.find { it.name == 'createValidatedObject' } )

        } catch ( Exception e ) {
            println "Some exception occured"
            e.printStackTrace()
        }
        
    } // end method visit
    
    /** This method uses string interpolation to create a new HashMap constructor. Go through the fields. If it has a validation annotation, examine it. If it meets the validation requirements, pass it to a new 
    constructor that will be passed on. Otherwise leave it out. If the field does not have a validation annotation, just pass it thought.
    */
    def processFields( fields2 ) {
        def sb1= new StringBuffer()
        def minimum
        def maximum
        def regExp
        def fieldTypeName
        sb1 << "def val \n def theMatch \n def divSet \n"
        sb1 << "def exceptionStringList = []\n"
        try {
            fields2.each { fieldNode ->
                fieldTypeName = fieldNode.getType().getName()
                def annotationNode = fieldNode.getAnnotations()[ 0 ]
                if ( annotationNode == null ) {
                    sb1 << " newMap[ '${fieldNode.getName()}' ] = argMap[ '${fieldNode.getName()}' ]\n"
                } else {
                    switch ( fieldTypeName ) {
                        
                        case 'java.lang.String':
                            sb1 << "val = argMap[ '${fieldNode.getName()}' ]"
                            def throwEx = annotationNode.getMember( 'throwEx' ) ? annotationNode?.getMember( 'throwEx' ).getValue() : true
                            minimum = annotationNode.getMember( 'minLength' ) ? annotationNode.getMember( 'minLength' ).getValue() : 0
                            maximum = annotationNode.getMember( 'maxLength' ) ? annotationNode.getMember( 'maxLength' ).getValue() :  Integer.MAX_VALUE
                            if ( minimum < 0 ) { minimum = 0 }
                            regExp = annotationNode.getMember( 'regEx' ) ? "/" + annotationNode?.getMember( 'regEx' )?.getText() + "/" : "\".*\"" 
                            def patternString1 = regExp.replace(  "\\", "\\\\" ) 

                            sb1 << """
                            theMatch = java.util.regex.Pattern.compile( ${regExp}, java.util.regex.Pattern.COMMENTS )
                            if ( ( ${minimum} <= val?.length() ) && ( val?.length() <= ${maximum} ) && ( theMatch.matcher( val ).matches() ) ) {
                                newMap[ '${fieldNode.getName()}' ] = val
                            } else { 
                                if ( throwException ) {
                                    exceptionStringList.add( '"' + val + '" is a String with a length outside the range of ${minimum} to ${maximum} characters or does not match the regular expression ${patternString1}' )
                                }
                            }
                            """
                        break
                        case [ 'double', 'java.lang.Double' ]:
                            sb1 << handleDoubleAndFloat( fieldNode.getName(), annotationNode, Double.MAX_VALUE, Double.class.name )
                        break
                        case [ 'float', 'java.lang.Float' ]:
                            sb1 << handleDoubleAndFloat( fieldNode.getName(), annotationNode, Float.MAX_VALUE, Float.class.name )
                        break
                        case [ 'int', 'java.lang.Integer' ]:
                            sb1 << handleIntAndLong( fieldNode.getName(), annotationNode, new Integer( 0 ) )
                        break
                        case [ 'long', 'java.lang.Long' ]:
                            sb1 << handleIntAndLong( fieldNode.getName(), annotationNode, new Long( 0 ) )
                        break
                        default:
                            sb1 << "newMap[ '${fieldNode.getName()}' ] = argMap[ '${fieldNode.getName()}' ]\n"
                    } // end switch( fieldTypeName )
                } // if ( annotationNode == null ) / else
                
            } // fields2.each
        } catch ( Exception e ) {
            e.printStackTrace()
        }
        sb1 << """
        if ( throwException && ( exceptionStringList.size() > 0 ) ) {
            def exMessage = exceptionStringList.join( System.lineSeparator() )
            throw new Exception( 'Groovy validation exception:' + System.lineSeparator() + exMessage  )
        }
        """

        return sb1
    } // end processFields, 226
    
    def private handleIntAndLong( nodeName, annotationNode, zeroNum ) {
        
        def sb1 = new StringBuffer()
        def holdSet = [] as Set
        sb1 << "val = argMap[ '${nodeName}' ]"
                            def minimum = annotationNode.getMember( 'minValue' ) ? annotationNode.getMember( 'minValue' ).getValue() : 0
                            def maximum = annotationNode.getMember( 'maxValue' ) ? annotationNode.getMember( 'maxValue' ).getValue() :  zeroNum.MAX_VALUE
                            try {
                                annotationNode.getMember( 'divisorSet' ).getExpressions().each { member ->
                                    if ( zeroNum instanceof java.lang.Integer ) { holdSet.add( new Integer( member.getValue() ) ) }
                                    else if ( zeroNum instanceof java.lang.Long ) { holdSet.add( new Long( member.getValue() ) ) }
                                }
                            } catch ( Exception e ) { }
                            holdSet.remove( zeroNum )
                            if ( holdSet.size() == zeroNum ) { holdSet.add( ++zeroNum ) }
                            sb1 << """
                            if ( (val == null ) || ( ( ${minimum} <= val ) && ( val <= ${maximum} ) && ( ${holdSet}.find{ val % it == 0 }  != null ) ) ) {
                                newMap[ '${nodeName}' ] = val
                            } else { 
                                if ( throwException ) {
                                exceptionStringList.add( val + ' is a ${zeroNum.class.name} outside the range ${minimum} to ${maximum} or it is not divisible by anything in the set ${holdSet}' )
                                }
                            }
                            """
        return sb1
    } // handleIntAndLong
    
    def private handleDoubleAndFloat( nodeName, annotationNode, maxValue, numClassName ) {
        def sb1 = new StringBuffer()
        sb1 << "val = argMap[ '${nodeName}' ]"
                    def minimum = annotationNode.getMember( 'minValue' ) ? annotationNode.getMember( 'minValue' ).getValue() : 0
                    def maximum = annotationNode.getMember( 'maxValue' ) ? annotationNode.getMember( 'maxValue' ).getValue() :  maxValue
                    sb1 << """
                    if ( ( ${minimum} <= val ) && ( val <= ${maximum} ) ) {
                        newMap[ '${nodeName}' ] = val
                    } else { 
                        if ( throwException ) {
                        exceptionStringList.add( val + ' is a ${numClassName} outside the range ${minimum} to ${maximum}' )
                        }
                    }
                    """
        return sb1

    } // handleDoubleAndFloat

} // end class  - line 208, 228

