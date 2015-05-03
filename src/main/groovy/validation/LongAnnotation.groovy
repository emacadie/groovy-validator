package validation

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
<p>This is an annotation to validate/constrain long fields in Plain Old Groovy Objects.</p>
<p>Here is an example on how to use it:</p>
<pre>
    @LongAnnotation( minValue = 0L, maxValue = 1000L )
    long firstLong
    @LongAnnotation( minValue = 2147483648L )
    def secondLong
    @LongAnnotation( minValue = 10L, divisorSet = [ 3L, 5L ] )
    long dasFizzbuzz
</pre>
<p>If the field is defined as "long" and it is given a value in the first call to setX that is outside your constraints, then it will be set to 0. If the field is defined as "def" and it is given a value that is outside your constraints, then it will be set to null. If the field already has a valid value and it is sent an invalid one in a call to setX, the new, invalid value will be ignored.</p>

<p>An application, class or library that uses this annotation must also import {@link validation.AnnotationProcessor} (for a POGO) or {@link validation.ImmutableValidator} (for an immutable object).</p>

<p>You must append the "L" at the end of the number. If you set "throwException" to true for {@link validation.AnnotationProcessor} or {@link validation.ImmutableValidator} and an exception is thrown, the "L" will not be printed as part of the number in the message.</p>

<p></p>
*/
@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )
@GroovyASTTransformationClass( [ 'info.shelfunit.properties.annotations.ValidationAnnotationTransform' ] )
public @interface LongAnnotation {
        /**
    The lowest value you want this field to hold. The default is 0. It could go as low as Long.MIN_VALUE.
    */
  public long minValue() default 0L
    /**
  The highest value you want this field to hold. The default is 9,223,372,036,854,775,807, which is the same as Long.MAX_VALUE.
  */
  public long maxValue() default 9223372036854775807L
    
  /**
  A number that your variable can be divided by. If you want a number to be even, then would select [ 2L ]. If you wanted to do fizzbuzz, you would set it to [ 3L, 5L ]. The default is [1L]. This must be specified using the list notation. If you simply provide a number things will not work as expected. And, yes, you need the "L" at the end.
  */
  public long[] divisorSet() default [1L]
}


