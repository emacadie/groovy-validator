package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode

import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation 

@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class ValidationAnnotationTransform implements ASTTransformation {
    
    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        
        if ( !astNodes ) { return }
        if ( !astNodes[ 0 ] ) { return }
        if ( !astNodes[ 1 ] ) { return }
        def annotationNode = astNodes[ 0 ]
        def fieldNode = astNodes[ 1 ]
        
        def theAnnotation = annotationNode.classNode
        def annotatedClass = fieldNode.getOwner() // the class
        
        def hasCreateValidatingConstructor = false
        def hasStaticInitializer = false
        annotatedClass.methods.each { mNode ->
            if ( mNode.name == "createValidatingConstructor" ) { hasCreateValidatingConstructor = true }
            if ( mNode.name == "checkForStaticGroovyValidatorInitializer" ) { hasStaticInitializer = true }
        }
        
        def methodString = new StringBuffer()
    
        methodString << "\n\n"
        methodString << """
        def checkForStaticGroovyValidatorInitializer() {}
        def static {
            info.shelfunit.properties.annotations.AnnotationProcessor.process( ${annotatedClass.name}, true )
        }
        """
        println "here is the method string: ${methodString}"
        if ( !hasCreateValidatingConstructor && !hasStaticInitializer ) {
            try {
                def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, false, methodString.toString() )
                
                // look at block statement
                // look at the class Node
                def someClassNode = ast[ 1 ]
                def methods = ast[ 1 ].methods
                annotatedClass.addMethod( methods.find { it.name == "set${fieldNode.name.capitalize()}" } )
                annotatedClass.addMethod( methods.find { it.name == "checkForStaticGroovyValidatorInitializer" } )
            } catch ( Exception e ) {
                println "Some exception occured"
                // e.printStackTrace()
            }
        }
        
    } // end method visit
    
} // end class  - line 174

