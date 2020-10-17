package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode

import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation 

@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class ValidStringTransform implements ASTTransformation {

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
        // println "In String annotation for ${fieldNode.name}"
        def theAnnotation = annotationNode.classNode
        theAnnotation.methods.each { methodNode ->
             // print " ${methodNode.name}, "
        }
        def annotatedClass = fieldNode.getOwner() // the class
        def hasCreateValidatingConstructor = false
        def hasStaticInitializer = false
        def methodToRemove
        annotatedClass.methods.each { mNode ->
            if ( mNode.name == "createValidatingConstructor" ) { hasCreateValidatingConstructor = true }
            if ( mNode.name == "checkForStaticGroovyValidatorInitializer" ) { hasStaticInitializer = true }
            
            if ( mNode.name == "set${fieldNode.name.capitalize()}" ) { methodToRemove = mNode }
        }
        
        if ( !hasCreateValidatingConstructor ) {
        def min = annotationNode.getMember( 'minLength' ) ? annotationNode.getMember( 'minLength' ).getValue() : 0 
        def max = annotationNode.getMember( 'maxLength' ) ? annotationNode.getMember( 'maxLength' ).getValue() :  Integer.MAX_VALUE 
        def throwEx = annotationNode.getMember( 'throwEx' ) ? annotationNode?.getMember( 'throwEx' ).getValue() : true
        def regex = annotationNode.getMember( 'regEx' ) ? "/" + annotationNode?.getMember( 'regEx' )?.getText() + "/" : "\".*\""
        java.util.regex.Pattern myPattern = java.util.regex.Pattern.compile( regex, java.util.regex.Pattern.COMMENTS )
        def patternAsString = myPattern.toString()
        def catchAll = ( regex == '".*"' ) ?: false
        def patternString1 = regex.replace(  "\\", "\\\\" ) 
        if ( patternString1.contains( "\$" ) ) {
            patternString1 = patternString1.substring(0, ( patternString1.length() - 2 ) )
            patternString1 += "DOLLAR_SIGN/"
        }
        def methodString = new StringBuffer()
        // println "About to make method string for ${fieldNode.name}"
        methodString << """
    public void set${fieldNode.name.capitalize()}( Object arg ) {
        /*
        if ( arg.getClass().getName() != "java.lang.String" ) {
            System.out.println( "Method set${fieldNode.name.capitalize()} called with arg " + arg + ", ignoring the love" );
        } else {
            System.out.println( "Method set${fieldNode.name.capitalize()} called with arg " + arg + ", get busy" );
        }
        */
        """
         methodString << """
         java.util.regex.Pattern theMatch = java.util.regex.Pattern.compile( ${regex}, java.util.regex.Pattern.COMMENTS );
        if ( ( ${min} <= arg.length() ) && ( arg.length() <= ${max} ) && ( theMatch.matcher( arg ).matches() ) ) {
            this.${fieldNode.getName()} = arg;
        """
            if ( throwEx ) {
                methodString << """
         } else {
            throw new Exception(
                 "'" + arg + "' is a String with a length outside the range of ${min} to ${max} characters or does not match the regular expression ${patternString1.replaceAll( "\"", "'" )}" )
                 """
            }
        methodString << """
        }
    }
    """
        
            try {
                def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, false, methodString.toString() )
                println "ast[ 0 ] is a ${ast[ 0 ].class.name}, and ast[ 1 ] is a ${ast[ 1 ].class.name}"
                def someClassNode = ast[ 1 ]
                def methods = ast[ 1 ].methods
                annotatedClass.addMethod( methods.find { it.name == "set${fieldNode.name.capitalize()}" } )
            } catch ( Exception e ) {
                println "Some exception occured"
                e.printStackTrace()
            }
        }
        
    } // end method visit
   
} // end class  - line 208, 228, 211, 113

