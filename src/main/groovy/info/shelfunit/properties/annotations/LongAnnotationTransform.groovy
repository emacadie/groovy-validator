package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode

import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation 

@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class LongAnnotationTransform implements ASTTransformation {
    
    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        
        if ( !astNodes ) { return }
        if ( !astNodes[ 0 ] ) { return }
        if ( !astNodes[ 1 ] ) { return }
        astNodes.eachWithIndex { theNode, x ->
            // println "theNode [${x}] is a ${theNode.class.name}"
        }
        def annotationNode = astNodes[ 0 ]
        def fieldNode = astNodes[ 1 ]
        if ( fieldNode.isFinal() ) { return }
        def theAnnotation = annotationNode.classNode

        theAnnotation.methods.each { methodNode ->
            // print " ${methodNode.name}, "
        }
        def annotatedClass = fieldNode.getOwner() // the class
        def hasCreateValidatingConstructor = false
        def methodToRemove
        annotatedClass.methods.each { mNode ->
            if ( mNode.name == "createValidatingConstructor" ) { hasCreateValidatingConstructor = true }
            if ( mNode.name == "set${fieldNode.name.capitalize()}" ) { methodToRemove = mNode }
        }
        
        def minimum = annotationNode.getMember( 'minValue' ) ? annotationNode.getMember( 'minValue' ).getValue() : 0
        def maximum = annotationNode.getMember( 'maxValue' ) ? annotationNode.getMember( 'maxValue' ).getValue() :  java.lang.Long.MAX_VALUE
        def throwEx = annotationNode.getMember( 'throwEx' ) ? annotationNode?.getMember( 'throwEx' ).getValue() : true
        def holdSet = [] as Set
        try {
            annotationNode.getMember( 'divisorSet' ).getExpressions().each { member ->
                holdSet.add( new Long( member.getValue() ) )
            }
        } catch ( Exception e ) { }
        holdSet.remove( 0L )
        if ( holdSet.size() == 0L ) { holdSet.add( 1L ) }

        def methodString = new StringBuffer()
        methodString << """
    public void set${fieldNode.name.capitalize()}( Object arg ) {
        if ( arg.getClass().getName() == "java.lang.Long" ) {
            // System.out.println( "Method set${fieldNode.name.capitalize()} called with arg " + arg + ", ignoring the love" );
        }
        """
         methodString << """
         if ( ( arg == null ) || ( ( ${minimum} <= arg ) && ( arg <= ${maximum} ) && ( ${holdSet}.find{ arg % it == 0 }  != null ) ) ) {
             this.${fieldNode.getName()} = arg;
        """
            if ( throwEx ) {
                methodString << """
         } else {
            throw new Exception(
                 arg + " is a java.lang.Long outside the range ${minimum} to ${maximum} or it is not divisible by anything in the set ${holdSet}" )
                 """
            }
        methodString << """
        }
    }
    """

        if ( !hasCreateValidatingConstructor ) {
            try {
                def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, false, methodString.toString() )
                def someClassNode = ast[ 1 ]
                def methods = ast[ 1 ].methods
                annotatedClass.addMethod( methods.find { it.name == "set${fieldNode.name.capitalize()}" } )
            } catch ( Exception e ) {
                println "Some exception occured"
                e.printStackTrace()
            }
        }
        
    } // end method visit
    
} // end class  - line 174, 103

