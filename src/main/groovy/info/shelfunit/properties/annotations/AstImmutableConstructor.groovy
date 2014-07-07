package info.shelfunit.properties.annotations

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.*

@Retention( RetentionPolicy.SOURCE )
@Target( [ ElementType.TYPE ] )
@GroovyASTTransformationClass( [ 'info.shelfunit.properties.annotations.AstImmutableConstructorTransform' ] )
public @interface AstImmutableConstructor {
}

