package info.shelfunit.properties.annotations

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
<p>This is an annotation to validate/constrain String fields in Plain Old Groovy Objects.</p>
<p>Here is an example on how to use it:</p>
<pre>
    @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @StringAnnotation( maxLength = 400 )
    def secondString
</pre>
<p>If the field is defined as "String" and it is given a value in the first call to setX that is outside your constraints, then it will be set to a String with length 0. If the field is defined as "def" and it is given a value that is outside your constraints, then it will be set to null. If the field already has a valid value and it is sent an invalid one in a call to setX, the new, invalid value will be ignored.</p>

<p>An application, class or library that uses this annotation must also import {@link info.shelfunit.properties.annotations.AnnotationProcessor}.</p>
<p></p>
*/

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )

public @interface StringAnnotation {
    /**
    The minimum length of the String field. The default is 0.
    */
    public int minLength() default 0
    /**
    The maximum length of the String field. The maximum is 2,147,483,647, which is the same as Integer.MAX_VALUE.
    */
    public int maxLength() default 2147483647
}

