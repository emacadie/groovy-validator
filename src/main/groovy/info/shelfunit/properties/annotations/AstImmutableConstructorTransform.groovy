package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode 
import org.codehaus.groovy.ast.ConstructorNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.ImportNode 

import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation 

import info.shelfunit.properties.annotations.GroovyValidatorException;

@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class AstImmutableConstructorTransform implements ASTTransformation {
    
    def knownTypes = [ 'java.lang.Double', 'java.lang.Float', 'java.lang.Integer', 'java.lang.Long', 'java.lang.String', 
    'double', 'float', 'int', 'long' ]
    
    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        
        if ( !astNodes ) return
        if ( !astNodes[ 0 ] ) return
        if ( !astNodes[ 1 ] ) return

        ClassNode annotatedClass = astNodes[ 1 ] // ( ClassNode ) astNodes[ 1 ]

        def constructors001 = annotatedClass.getDeclaredConstructors()        
        def fields = annotatedClass.getFields()
        def fields2 = annotatedClass.getFields().findAll{ 
            ( ( knownTypes.contains( it.getType().getName() ) ) && 
            ( !it.getName().contains( '$hash$code' ) ) ) 
        } 
        
        def minimum
        def maximum
        def packageString = annotatedClass.getPackageName()? "package  ${annotatedClass.getPackageName()}" : " "
        def theString = 
        """
        ${packageString}
        
        // import info.shelfunit.properties.annotations.GroovyValidatorException
        
        class ${annotatedClass.getNameWithoutPackage()} {
            
            public ${annotatedClass.getNameWithoutPackage()} ( java.util.LinkedHashMap argMap, boolean validation, boolean throwException = false ) {
                
                this( createValidatingConstructor( argMap, validation, throwException ) )
            } // end constructor
            
            // was java.util.HashMap argMap, Boolean validation
            def static createValidatingConstructor( java.util.HashMap argMap, boolean validation, boolean throwException ) {
            
                if ( !validation ) {
                    return argMap
                } else {
                    java.util.HashMap newMap = [:]
                    ${processFields( fields2 )}
                    return newMap
                }
            }
        } // end class 
            """.toString()
        
        try {
            def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, false, theString )
            // look at block statement
            // look at the class Node
            def someClassNode = ast[ 1 ]
            def constructors = someClassNode.getDeclaredConstructors()
            constructors.each { theCon ->
                annotatedClass.addConstructor( theCon )
            }

            def methods = ast[ 1 ].methods
            annotatedClass.addMethod( methods.find { it.name == 'createValidatingConstructor' } )

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
        def fieldTypeName
        sb1 << "def val\n"
        fields2.each { fieldNode ->
            fieldTypeName = fieldNode.getType().getName()
            def annotationNode = fieldNode.getAnnotations()[ 0 ]
            switch ( fieldTypeName ) {
                case 'java.lang.String':
                    sb1 << "val = argMap[ '${fieldNode.getName()}' ]"
                    minimum = annotationNode.getMember( 'minLength' ) ? annotationNode.getMember( 'minLength' ).getValue() : 0
                    maximum = annotationNode.getMember( 'maxLength' ) ? annotationNode.getMember( 'maxLength' ).getValue() :  Integer.MAX_VALUE
                    sb1 << """
                    if ( ${minimum} <= val?.length() && val?.length() <= ${maximum} ) {
                        newMap[ '${fieldNode.getName()}' ] = val
                    } else { 
                        if ( throwException ) {
                            throw new Exception( val + ' is a String with a length outside the values ${minimum} and ${maximum}' )
                        }
                    }
                    """
                    /**
                     } else { 
                        if ( throwException ) { info.shelfunit.properties.annotations.GroovyValidator
                            throw new GroovyValidatorException( '${val.toString()} is is a String with a length outside the values ${minimum} and ${maximum}' )
                        }
                    */
                break
                case [ 'double', 'java.lang.Double' ]:
                    sb1 << "val = argMap[ '${fieldNode.getName()}' ]"
                    minimum = annotationNode.getMember( 'minValue' ) ? annotationNode.getMember( 'minValue' ).getValue() : 0
                    maximum = annotationNode.getMember( 'maxValue' ) ? annotationNode.getMember( 'maxValue' ).getValue() :  Double.MAX_VALUE
                    sb1 << """
                    if ( ( ${minimum} <= val ) && ( val <= ${maximum} ) ) {
                        newMap[ '${fieldNode.getName()}' ] = val
                    } else { 
                        if ( throwException ) {
                            throw new Exception( val + ' is a double outside the values ${minimum} and ${maximum}' )
                        }
                    }
                    """
                break
                case [ 'float', 'java.lang.Float' ]:
                    sb1 << "val = argMap[ '${fieldNode.getName()}' ]"
                    minimum = annotationNode.getMember( 'minValue' ) ? annotationNode.getMember( 'minValue' ).getValue() : 0
                    maximum = annotationNode.getMember( 'maxValue' ) ? annotationNode.getMember( 'maxValue' ).getValue() :  Float.MAX_VALUE
                    sb1 << """
                    if ( ( ${minimum} <= val ) && ( val <= ${maximum} ) ) {
                        newMap[ '${fieldNode.getName()}' ] = val
                    } else { 
                        if ( throwException ) {
                            throw new Exception( val + ' is a float outside the values ${minimum} and ${maximum}' )
                        }
                    }
                    """
                break
                case [ 'int', 'java.lang.Integer' ]:
                    sb1 << "val = argMap[ '${fieldNode.getName()}' ]"
                    minimum = annotationNode.getMember( 'minValue' ) ? annotationNode.getMember( 'minValue' ).getValue() : 0
                    maximum = annotationNode.getMember( 'maxValue' ) ? annotationNode.getMember( 'maxValue' ).getValue() :  Integer.MAX_VALUE
                    sb1 << """
                    if ( ( ${minimum} <= val ) && ( val <= ${maximum} ) ) {
                        newMap[ '${fieldNode.getName()}' ] = val
                    } else { 
                        if ( throwException ) {
                            throw new Exception( val + ' is an integer outside the values ${minimum} and ${maximum}' )
                        }
                    }
                    """
                break
                case [ 'long', 'java.lang.Long' ]:
                    sb1 << "val = argMap[ '${fieldNode.getName()}' ]"
                    minimum = annotationNode.getMember( 'minValue' ) ? annotationNode.getMember( 'minValue' ).getValue() : 0
                    maximum = annotationNode.getMember( 'maxValue' ) ? annotationNode.getMember( 'maxValue' ).getValue() :  Long.MAX_VALUE
                    sb1 << """
                    if ( ( ${minimum} <= val ) && ( val <= ${maximum} ) ) {
                        newMap[ '${fieldNode.getName()}' ] = val
                    } else { 
                        if ( throwException ) {
                            throw new Exception( val + ' is a long outside the values ${minimum} and ${maximum}' )
                        }
                    }
                    """
                break
                default:
                    sb1 << "newMap[ '${fieldNode.getName()}' ] = argMap[ '${fieldNode.getName()}' ]\n"
            }
            
        } // fields2.each
        // println "Here is sb1: ${sb1}"
        return sb1
    } // end processFields

} // end class  - line 208


