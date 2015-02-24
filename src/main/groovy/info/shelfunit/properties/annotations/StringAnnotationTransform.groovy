package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode
// import org.codehaus.groovy.ast.ClassNode 

import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation 

// @GroovyASTTransformation( phase = CompilePhase.CLASS_GENERATION )
// @GroovyASTTransformation( phase = CompilePhase.OUTPUT )
@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class StringAnnotationTransform implements ASTTransformation {
    
    /*
    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        
        if ( !astNodes ) { return }
        if ( !astNodes[ 0 ] ) { return }
        if ( !astNodes[ 1 ] ) { return }
        astNodes.eachWithIndex { theNode, x ->
            println "theNode [${x}] is a ${theNode.class.name}"
        }
        def annotationNode = astNodes[ 0 ]
        def fieldNode = astNodes[ 1 ]
        // theNode [0] is a org.codehaus.groovy.ast.AnnotationNode
        // theNode [1] is a org.codehaus.groovy.ast.FieldNode
        println "annotation is for ${annotationNode.classNode.name}"
        println "field is for class ${fieldNode.getOwner().name} and field ${fieldNode.name}, so setter would be set${fieldNode.name.capitalize()}"
        
        
        def theClass = annotationNode.classNode
        println "methods of annotation  ${theClass.name}:"
        theClass.methods.each { methodNode ->
            print " ${methodNode.name}, "
        }
        def annotatedClass = fieldNode.getOwner() // the class
        println "\nmethods of class ${annotatedClass.name}" // look for createValidatingConstructor from AstImmutableConstructorTransform
        def hasCreateValidatingConstructor = false
        annotatedClass.methods.each { mNode ->
            print " ${mNode.name}, "
            if (mNode.name == "createValidatingConstructor" ) { hasCreateValidatingConstructor = true }
        }
        println ",  hasCreateValidatingConstructor: ${hasCreateValidatingConstructor}"
        
        
        def methodString = new StringBuffer()
        methodString << """
        static {
        info.shelfunit.properties.annotations.AnnotationProcessor.process( this, true )
    }
    """
        
        println "here is the method string: ${methodString}"
        if ( !hasCreateValidatingConstructor ) {
            try {
                
                def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, true, methodString.toString() )
                println "Here is ast[ 0 ].class.name: ${ast[ 0 ].class.name}"
                // look at block statement
                // look at the class Node
                def someClassNode = ast[ 1 ]
                def methods = ast[ 1 ].methods
                annotatedClass.addMethod( methods.find { it.name == "set${fieldNode.name.capitalize()}" } )
                
            } catch ( Exception e ) {
                println "Some exception occured"
                e.printStackTrace()
            }
        } // if ( !hasCreateValidatingConstructor )
        
    } // end method visit
    */
    
    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        
        if ( !astNodes ) { return }
        if ( !astNodes[ 0 ] ) { return }
        if ( !astNodes[ 1 ] ) { return }
        astNodes.eachWithIndex { theNode, x ->
            println "theNode [${x}] is a ${theNode.class.name}"
        }
        def annotationNode = astNodes[ 0 ]
        def fieldNode = astNodes[ 1 ]
        // theNode [0] is a org.codehaus.groovy.ast.AnnotationNode
        // theNode [1] is a org.codehaus.groovy.ast.FieldNode
        println "annotation is for ${annotationNode.classNode.name}"
        println "field is for class ${fieldNode.getOwner().name} and field ${fieldNode.name}, so setter would be set${fieldNode.name.capitalize()}"
        
        def theAnnotation = annotationNode.classNode
        println "methods of annotation  ${theAnnotation.name}:"
        theAnnotation.methods.each { methodNode ->
            print " ${methodNode.name}, "
        }
        def annotatedClass = fieldNode.getOwner() // the class
        println "\nmethods of class ${annotatedClass.name}" // look for createValidatingConstructor from AstImmutableConstructorTransform
        def hasCreateValidatingConstructor = false
        annotatedClass.methods.each { mNode ->
            print " ${mNode.name}, "
            if (mNode.name == "createValidatingConstructor" ) { hasCreateValidatingConstructor = true }
        }
        println ",  hasCreateValidatingConstructor: ${hasCreateValidatingConstructor}"
        
        println "Here is annotationNode.getMember('minLength') ${ annotationNode.getMember( 'minLength' ) ? annotationNode.getMember( 'minLength' ).getValue() : 0 }"
        println "Here is annotationNode.getMember('maxLength') ${ annotationNode.getMember( 'maxLength' ) ? annotationNode.getMember( 'maxLength' ).getValue() :  Integer.MAX_VALUE }"
        println "Here is annotationNode.getMember('regEx' ): ${annotationNode.getMember( 'regEx' ) ? "/" + annotationNode?.getMember( 'regEx' )?.getText() + "/" : "\".*\""}" 
        println "\n--------------------------------------\n\n"
        
        def min = annotationNode.getMember( 'minLength' ) ? annotationNode.getMember( 'minLength' ).getValue() : 0 
        def max = annotationNode.getMember( 'maxLength' ) ? annotationNode.getMember( 'maxLength' ).getValue() :  Integer.MAX_VALUE 
        def regex = annotationNode.getMember( 'regEx' ) ? "/" + annotationNode?.getMember( 'regEx' )?.getText() + "/" : "\".*\""
        def catchAll = ( regex == '".*"' ) ?: false
        println "regex ${regex} is catchAll: ${catchAll}  Here is the test: ${( regex == '".*"' )}"
        def patternString1 = regex.replace(  "\\", "\\\\" ) 
        def methodString = new StringBuffer()
        methodString << """
    def set${fieldNode.name.capitalize()}( arg ) {
        println \" calling set${fieldNode.name.capitalize()} with arg \" + arg
    """
        if ( !catchAll ) {
            methodString <<  """
        def theMatch = java.util.regex.Pattern.compile( ${regex}, java.util.regex.Pattern.COMMENTS )
            """ 
        }
        methodString << """
        if ( ( ${min} <= arg?.length() ) && ( arg?.length() <= ${max} ) """
        if ( !catchAll ) {
            methodString << "&& ( theMatch.matcher( arg ).matches() ) "
        }
        methodString << """) {
            this.${fieldNode.getName()} = arg
        } else { 
            throw new Exception(
                 '"' + arg + '" is a String with a length outside the range of ${min} to ${max} characters """
                if ( !catchAll ) { methodString << " or does not match the regular expression ${patternString1} " } 
                methodString << """' 
            )
        }
    }"""
        println "here is the method string: ${methodString}"
        if ( !hasCreateValidatingConstructor ) {
            try {
                def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, false, methodString.toString() )
                // look at block statement
                // look at the class Node
                def someClassNode = ast[ 1 ]
                def methods = ast[ 1 ].methods
                annotatedClass.addMethod( methods.find { it.name == "set${fieldNode.name.capitalize()}" } )
                
            } catch ( Exception e ) {
                println "Some exception occured"
                e.printStackTrace()
            }
        }
        
    } // end method visit
    
} // end class  - line 208, 228

