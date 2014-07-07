package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode 
import org.codehaus.groovy.ast.ConstructorNode
import org.codehaus.groovy.ast.FieldNode

import org.codehaus.groovy.ast.builder.AstBuilder
// import org.codehaus.groovy.control.*
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.*

@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class AstImmutableConstructorTransform implements ASTTransformation {
    
    def knownTypes = [ 'java.lang.Double', 'java.lang.Float', 'java.lang.Integer', 'java.lang.Long', 'java.lang.String', 
    'double', 'float', 'int', 'long' ]
    
    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        println "In AstImmutableConstructorTransform.visit"
        println "Size of astNodes: ${astNodes.size()}"
        println "what is astNodes[ 0 ]?: ${astNodes[ 0 ].class.name}"
        println "what is astNodes[ 1 ]?: ${astNodes[ 1 ].class.name}"
        
        if ( !astNodes ) return
        if ( !astNodes[ 0 ] ) return
        if ( !astNodes[ 1 ] ) return

        ClassNode annotatedClass = astNodes[ 1 ] // ( ClassNode ) astNodes[ 1 ]
        println "Working with annotatedClass ${annotatedClass.getName()}"
        def constructors001 = annotatedClass.getDeclaredConstructors()
        constructors001.each { theCon ->
            println "the constructor: name: ${theCon.getName()} text: ${theCon.getText()}"
        }
        
        def fields = annotatedClass.getFields()
        // fields.each { fieldNode ->
        def fields2 = annotatedClass.getFields().findAll{ 
            ( ( knownTypes.contains( it.getType().getName() ) ) && ( !it.getName().contains( '$hash$code' ) ) ) 
        } 
        fields2.each { fieldNode ->
            println "--- name of field: ${fieldNode.getName()}"
            println "It is a ${fieldNode.getType().getName()}" // getType() returns a ClassNode
            /*
            if ( ( knownTypes.contains( fieldNode.getType().getName() ) ) && ( !fieldNode.getName().contains( '$hash$code' ) ) ) {
                println " Can I get an amen from somebody??"
            }
            */
            def annotations = fieldNode.getAnnotations()
            println "size of annotations for ${fieldNode.getName()}: ${annotations.size()}"
            annotations.each { annotationNode ->
                println "annotationNode is a ${annotationNode.class.name} of type ${annotationNode.getClassNode().getName()}"
                def members = annotationNode.getMembers()
                members.each { k, v ->
                    println "In members, here is key: ${k}, here is value: ${v.toString()}, here is the value of the value: ${v.getValue()} and it is a ${v.getValue().class.name}"
                }
                if ( annotationNode.getClassNode().getName() == "info.shelfunit.properties.annotations.StringAnnotation" ) {
                    println "It's a StringAnnotation"
                    // println "Here is minLength: ${annotationNode.getMember( 'minLength' )}, here is the value: ${annotationNode.getMember(  'minLength' ).getValue()}"
                }
            } // annotations.each
        } // fields2.each
        
        def minimum
        def maximum
        def theString = 
        """
        package  ${annotatedClass.getPackageName()}
        class ${annotatedClass.getNameWithoutPackage()} {
            public ${annotatedClass.getNameWithoutPackage()} ( java.util.LinkedHashMap argMap, boolean validation ) {
                
                this( createValidatingConstructor( argMap, validation ) )
                
            } // end constructor
            
            // was java.util.HashMap argMap, Boolean validation
            def static createValidatingConstructor( java.util.HashMap argMap, boolean validation ) {
            
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
        // println "theString is a ${theString.class.name}"
        // println "Here is theString: ${theString}"
        try {
            def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, false, theString )
            println "Size of ast: ${ast.size()}"
            ast.each { astNode ->
                println "astNode is a ${astNode.class.name}"
            }
            // look at block statement
            println "ast[ 0 ].getText(): ${ast[ 0 ].getText()}"
            println "class node name: ${ast[ 1 ].getName()}"
            // look at the class Node
            def someClassNode = ast[ 1 ]
            def constructors = someClassNode.getDeclaredConstructors()
            constructors.each { theCon ->
                println "the constructor: name: ${theCon.getName()} text: ${theCon.getText()}"
                annotatedClass.addConstructor( theCon )
            }
            println "Methods of the class"
            def methods = ast[ 1 ].methods
            methods.each { theMethod ->
                println "Method name: ${theMethod.name}"
            }
            annotatedClass.addMethod( methods.find { it.name == 'createValidatingConstructor' } )
            def ourMethod = methods.find { it.name == 'createValidatingConstructor' }
            ourMethod.getParameters().each { param ->
                println "our first param is a ${param.getType().getName()}"
            }
        } catch ( Exception e ) {
            println "Some exception occured"
            e.printStackTrace()
        }
        // processFields( fields2 )
        // MethodNode newMethod = makeMethod(annotatedClass)
        // annotatedClass.addMethod(newMethod)
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
                    println "Looking at ${fieldNode.getName()}"
                    sb1 << "val = argMap[ '${fieldNode.getName()}' ]"
                    println "Here is annotationNode.getMember( 'minLength' ): ${annotationNode.getMember( 'minLength' )}"
                    minimum = annotationNode.getMember( 'minLength' ) ? annotationNode.getMember( 'minLength' ).getValue() : 0
                    maximum = annotationNode.getMember( 'maxLength' ) ? annotationNode.getMember( 'maxLength' ).getValue() :  Integer.MAX_VALUE
                    sb1 << """
                    if ( ${minimum} <= val.length() && val.length() <= ${maximum} ) {
                        newMap[ '${fieldNode.getName()}' ] = val
                    } 
                    """
                break
                case [ 'double', 'java.lang.Double' ]:
                    sb1 << "val = argMap[ '${fieldNode.getName()}' ]"
                    minimum = annotationNode.getMember( 'minValue' ) ? annotationNode.getMember( 'minValue' ).getValue() : 0
                    maximum = annotationNode.getMember( 'maxValue' ) ? annotationNode.getMember( 'maxValue' ).getValue() :  Double.MAX_VALUE
                    sb1 << """
                    if ( ( ${minimum} <= val ) && ( val <= ${maximum} ) ) {
                        newMap[ '${fieldNode.getName()}' ] = val
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
                    } 
                    """
                break
                default:
                    sb1 << "newMap[ ${fieldNode.getName()} ] = argMap[ ${fieldNode.getName()} ]\n"
            }
            /*
            println "--- name of field: ${fieldNode.getName()}"
            println "It is a ${fieldNode.getType().getName()}" // getType() returns a ClassNode

            def annotations = fieldNode.getAnnotations()
            println "size of annotations for ${fieldNode.getName()}: ${annotations.size()}"
            annotations.each { annotationNode ->
                println "annotationNode is a ${annotationNode.class.name} of type ${annotationNode.getClassNode().getName()}"
                def members = annotationNode.getMembers()
                members.each { k, v ->
                    println "In members, here is key: ${k}, here is value: ${v.toString()}, here is the value of the value: ${v.getValue()} and it is a ${v.getValue().class.name}"
                }
                if ( annotationNode.getClassNode().getName() == "info.shelfunit.properties.annotations.StringAnnotation" ) {
                    println "It's a StringAnnotation"
                    println "Here is minLength: ${annotationNode.getMember("minLength")}"
                }
            } // annotations.each
            */
        } // fields2.each
        // println "here is sb1: ${sb1}"
        return sb1
    } // end processFields
    
    
} // end class 

