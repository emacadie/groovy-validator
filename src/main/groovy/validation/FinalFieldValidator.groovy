package validation

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
<p>This is an annotation to validate/constrain floating number fields in Plain Old Groovy Objects.</p>
<p>Here is an example on how to use it:</p>
<pre>
    @FloatAnnotation( minValue = 0f, maxValue = 1000f, throwEx = false )
    def firstNum
    @FloatAnnotation( maxValue = 1000f )
    float secondNum
</pre>
<p>If the field is defined as "float" and it is given a value in the first call to setX that is outside your constraints, then it will be set to 0. If the field is defined as "def" and it is given a value that is outside your constraints, then it will be set to null. If the field already has a valid value and it is sent an invalid one in a call to setX, the new, invalid value will be ignored.</p>

<p>An application, class or library that uses this annotation must also import {@link validation.ImmutableValidator} to use this in an immutable object.</p>

<p>You must append the "f" at the end of the number. If you set "throwException" to true for {@link validation.ImmutableValidator} and an exception is thrown, the "f" will not be printed as part of the number in the message.</p>

<p></p>
*/

@Retention( RetentionPolicy.SOURCE )
@Target( [ ElementType.TYPE ] )
@GroovyASTTransformationClass( [ 'info.shelfunit.properties.annotations.FinalFieldValidatorTransform' ] )
public @interface FinalFieldValidator {}

