package visibility

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

import org.codehaus.groovy.transform.GroovyASTTransformationClass

/**
The purpose of this annotation is to help you keep your private fields private.
*/

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )
@GroovyASTTransformationClass( [ 'visibility.HiddenTransformation' ] )
public @interface Hidden {
   
}


