package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.PropertyNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

// order: parsing, conversion, semantic analysis, canonicalization, intstruction selection, class generation, output, finalization
@GroovyASTTransformation( phase = CompilePhase.CLASS_GENERATION ) // SEMANTIC_ANALYSIS ) // INSTRUCTION_SELECTION )
class Transform implements ASTTransformation {
    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        if ( !astNodes ) return
        if ( !astNodes[ 0 ] ) return
        if ( !astNodes[ 1 ] ) return
        if ( !( astNodes[ 0 ] instanceof AnnotationNode ) ) return
        // if (astNodes[0].classNode?.name != Marker.class.name) return

        // ClassNode annotatedClass = (ClassNode) astNodes[1]
        // MethodNode newMethod = makeMethod(annotatedClass)
        // annotatedClass.addMethod(newMethod)
        // PropertyNode theNode = ( PropertyNode ) astNodes[ 1 ] // does not work
        FieldNode theNode = ( FieldNode ) astNodes[ 1 ]
        
        def classNode = theNode.getDeclaringClass()
        // see http://stackoverflow.com/questions/8100376/class-forname-vs-classloader-loadclass-which-to-use-for-dynamic-loading
        // def classToTransform = Class.forName( classNode.name )
        def classToTransform = ClassLoader.getSystemClassLoader().loadClass( classNode.name )
        transformClass( classToTransform )
    }
    
    def transformClass( classToTransform ) {
        classToTransform.metaClass.setProperty = { String name, arg ->
            println "name in setProperty is ${name}"
            def field = classToTransform.getDeclaredField( name )
            def intAnnotation = field?.getAnnotation( ValidInt.class )
            println "-- Here is stringAnnotation: ${stringAnnotation}"
            if ( intAnnotation ) {
                println "Looking at Book.set${name.capitalize()}"
                if ( !( arg < intAnnotation.min() ) &&
                    !( arg > intAnnotation.max() ) ) {
                    classToTransform.class.metaClass.getMetaProperty( name ).setProperty( delegate, arg.toString() )
                } else {
                    println "Cannot call set${name.capitalize()}"
                }
            } else {
                def mName = "set${name.capitalize()}"
                def propClassName = classToTransform.class.metaClass.getMetaProperty( name ).getType().getName()
                println "LET'S TRY CALLING ${mName}, it's a ${propClassName}"
                classToTransform.class.metaClass.getMetaProperty( name ).setProperty( delegate, arg ) // this works
            }
        }
    } // transformClass

} // end class Transform

