package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode 
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation 

@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class FinalFieldValidatorTransform implements ASTTransformation {
    
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
            ( // ( knownTypes.contains( it.getType().getName() ) ) && 
            ( !it.getName().contains( '$hash$code' ) ) ) 
        } 
        className = annotatedClass.getNameWithoutPackage()
        def minimum
        def maximum
        def packageString = annotatedClass.getPackageName()? "package  ${annotatedClass.getPackageName()}" : " "
        def theString = new StringBuffer()
        def theSwitch
        theString << """
        ${packageString}
        
        class ${annotatedClass.getNameWithoutPackage()} {
                """
                /*
                fields2.each { fieldNode ->
                    if ( fieldNode.isFinal() ) {
                        theString << "\nthis.${fieldNode.getName()} = validMap['${fieldNode.getName()}']\n"
                    } else {
                        def annotationName = fieldNode.getAnnotations()[ 0 ]?.getClassNode()?.getName() ?: "nullx"
                        // println "looking at field ${fieldNode.getName()} which is a ${fieldNode.getType().getName()}, annotationNode: ${annotationName}"
                        def fieldTypeName = fieldNode.getType().getName()
                        theSwitch = ( fieldTypeName != 'java.lang.Object') ? fieldTypeName : annotationName
                        // println "Here is theSwitch: ${theSwitch}"
                        switch ( theSwitch ) {
                        case [ 'java.lang.String', 'validation.ValidString']:
                             theString << "\nthis.set${fieldNode.getName().capitalize()}( validMap['${fieldNode.getName()}'] ?: new String() )\n"
                        break
                        case [ 'double', 'java.lang.Double', 'validation.ValidDouble']:
                            theString << "\nthis.set${fieldNode.getName().capitalize()}( validMap['${fieldNode.getName()}'] ?: 0d )\n"
                        break
                        case [ 'float', 'java.lang.Float', 'validation.ValidFloat']:
                            theString << "\nthis.set${fieldNode.getName().capitalize()}( validMap['${fieldNode.getName()}'] ?: 0f )\n"
                        break
                        case [ 'int', 'java.lang.Integer' , 'validation.ValidInt']:
                            theString << "\nthis.set${fieldNode.getName().capitalize()}( validMap['${fieldNode.getName()}'] ?: 0 )\n"
                        break
                        case [ 'long', 'java.lang.Long', 'validation.ValidLong']:
                            theString << "\nthis.set${fieldNode.getName().capitalize()}( validMap['${fieldNode.getName()}'] ?: 0L )\n"
                        break
                        default:
                            // println "${fieldNode.getName()} is a ${fieldNode.getType().getName()}"
                            theString << "\nthis.set${fieldNode.getName().capitalize()}( validMap['${fieldNode.getName()}'] ?: new Object() )\n"
                        }
                    }
                }
                */
                theString << """            
            // was java.util.HashMap argMap, Boolean validation
            def static createValidatedObject( java.util.HashMap argMap, boolean throwException = false ) {
                java.util.HashMap newMap = [:]
                 ${processFields( fields2 )}
                
                return new ${annotatedClass.getPackageName()}.${annotatedClass.getNameWithoutPackage()}( newMap )
            }
        } // end class 
            """
        // println "Here is the string: ${theString.toString()}"
        try {
            def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, false, theString.toString() )
            // look at block statement
            // look at the class Node
            def someClassNode = ast[ 1 ]
            def constructors = someClassNode.getDeclaredConstructors()
            constructors.each { theCon ->
                annotatedClass.addConstructor( theCon )
            }
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
        def theSwitch
        sb1 << "def val \n def theMatch \n def divSet \n"
        sb1 << "def exceptionStringList = []\n"
        try {
            fields2.each { fieldNode ->
                fieldTypeName = fieldNode.getType().getName()
                def annotationNode = fieldNode.getAnnotations()[ 0 ]
                // println "starting processFields, Here is ${fieldNode.getName()}"
                if ( annotationNode == null ) {
                    sb1 << " newMap[ '${fieldNode.getName()}' ] = argMap[ '${fieldNode.getName()}' ]\n"
                // } else if ( !fieldNode.isFinal() ) {
                    // sb1 << " newMap[ '${fieldNode.getName()}' ] = argMap[ '${fieldNode.getName()}' ]\n"
                } else {
                    def annotationName = annotationNode?.getClassNode()?.getName() ?: "nullx"
                    // println "in processFields, looking at field ${fieldNode.getName()} which is a ${fieldNode.getType().getName()}, annotationNode: ${annotationName}"
                    theSwitch = ( fieldTypeName != 'java.lang.Object') ? fieldTypeName : annotationName
                    // println "in processFields, Here is theSwitch: ${theSwitch} for ${fieldNode.getName()}"
                    switch ( theSwitch ) {
                        case ['java.lang.String', 'validation.ValidString']:
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
                        case [ 'double', 'java.lang.Double', 'validation.ValidDouble' ]:
                            sb1 << handleDoubleAndFloat( fieldNode.getName(), annotationNode, Double.MAX_VALUE, Double.class.name )
                        break
                        case [ 'float', 'java.lang.Float', 'validation.ValidFloat' ]:
                            sb1 << handleDoubleAndFloat( fieldNode.getName(), annotationNode, Float.MAX_VALUE, Float.class.name )
                        break
                        case [ 'int', 'java.lang.Integer', 'validation.ValidInt' ]:
                            sb1 << handleIntAndLong( fieldNode.getName(), annotationNode, new Integer( 0 ) )
                        break
                        case [ 'long', 'java.lang.Long', 'validation.ValidLong' ]:
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
        if ( className.contains( "ImmutableRegExSpec" ) ) {
        println "Here is sb1: ${sb1}"
        }
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
                            // (val == null ) ||
                            sb1 << """
                            if (  ( ( ${minimum} <= val ) && ( val <= ${maximum} ) && ( ${holdSet}.find{ val % it == 0 }  != null ) ) ) {
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
                        println( 'setting ${nodeName} which is a ${numClassName} to ' + val );
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

