package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class AstIntAnnotationTransformer implements ASTTransformation {

    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        println "In AstIntAnnotationTransformer.visit"
        println "Size of astNodes: ${astNodes.size()}"
        println "what is astNodes[ 0 ]?: ${astNodes[ 0 ].class.name}"
        println "what is astNodes[ 1 ]?: ${astNodes[ 1 ].class.name}"
        def members = astNodes[ 0 ].getMembers()
        members.each { k, v ->
            println "In members, here is key: ${k}, here is value: ${v.toString()}, here is the value of the value: ${v.getValue()} and it is a ${v.getValue().class.name}"
        }
        if ( !astNodes ) return
        if ( !astNodes[ 0 ] ) return
        if ( !astNodes[ 1 ] ) return
        if ( !( astNodes[ 0 ] instanceof AnnotationNode ) ) return
        // if ( astNodes[ 0 ].classNode?.name != Marker.class.name ) return

        // ClassNode annotatedClass = ( ClassNode ) astNodes[ 1 ]
        // MethodNode newMethod = makeMethod( annotatedClass )
        // annotatedClass.addMethod( newMethod )
    } // end visit
}



