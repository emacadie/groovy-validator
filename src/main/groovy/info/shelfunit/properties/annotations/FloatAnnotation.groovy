package info.shelfunit.properties.annotations

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
<p>This is an annotation to validate/constrain floating number fields in Plain Old Groovy Objects.</p>
<p>Here is an example on how to use it:</p>
<pre>
    @FloatAnnotation( minValue = 0f, maxValue = 1000f )
    def firstNum
    @FloatAnnotation( maxValue = 1000f )
    float secondNum
</pre>
<p>If the field is defined as "float" and it is given a value in the first call to setX that is outside your constraints, then it will be set to 0. If the field is defined as "def" and it is given a value that is outside your constraints, then it will be set to null. If the field already has a valid value and it is sent an invalid one in a call to setX, the new, invalid value will be ignored.</p>

<p>An application, class or library that uses this annotation must also import {@link info.shelfunit.properties.annotations.AnnotationProcessor}.</p>
<p></p>
*/

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )

public @interface FloatAnnotation {
    /**
    The lowest value you want this field to hold. The default is 0.0f. It could go as low as Float.MIN_VALUE.
    */
  public float minValue() default 0.0f
  /**
  The highest value you want this field to hold. The default is 3.4x10^38, which is the same as Float.MAX_VALUE.
  */
  public float maxValue() default 3.4028235E38f 
}

