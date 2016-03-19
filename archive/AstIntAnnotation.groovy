package info.shelfunit.properties.annotations

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import org.codehaus.groovy.transform.GroovyASTTransformationClass

/**
<p>This is an annotation to validate/constrain integer fields in Plain Old Groovy Objects.</p>
<p>Here is an example on how to use it:</p>
<pre>
    @ValidInt( minValue = 30, maxValue = 400 )
    int firstInt
    @ValidInt( maxValue = 400 )
    def secondInt
</pre>
<p>If the field is defined as "int" and it is given a value that is outside your constraints, then it will be set to 0. If the field is defined as "def" and it is given a value that is outside your constraints, then it will be set to null.</p>

<p>An application, class or library that uses this annotation must also import {@link info.shelfunit.properties.annotations.AnnotationProcessor}.</p>
<p></p>
*/

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )
@GroovyASTTransformationClass(['info.shelfunit.properties.annotations.AstValidIntTransformer'])
public @interface AstValidInt {
    /**
    The lowest value you want this field to hold. The default is 0.
    */
  public int minValue() default 0
  /**
  The highest value you want this field to hold. The default is 2,147,483,647, which is the same as Integer.MAX_VALUE.
  */
  public int maxValue() default 2147483647
}

