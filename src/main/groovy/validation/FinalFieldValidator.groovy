package validation

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
<p>This is an annotation to validate/constrain final fields in Plain Old Groovy Objects. The fields you wish to validate must be annotated with the following annotations: {@link validation.DoubleAnnotation}, {@link validation.FloatAnnotation}, {@link validation.IntAnnotation}, {@link validation.LongAnnotation} and {@link validation.StringAnnotation}. This annotation must be applied at the class level.</p>

<p>A class can mix final and mutable fields with the field validation annotations. If the field is not final, the field will be processed by the transformation class for that field type. Likewise, those field annotation processors will skip those fields and let the processor for this validation handle them.</p>

<p>To get the annotation to actually process, you should send two parameters to the constructor: a Map for the fields, and a boolean for whether or not you want the fields to be validated. This boolean is called "validation" in the AST Transformer code. If validation is set to false, the effect is the same as if you simply sent the fields as a Map. Here is an example on how to use it:</p>
<pre>
import groovy.transform.ToString
import validation.IntAnnotation
import validation.FinalFieldValidator

@ToString( includeNames = true )
{@code @FinalFieldValidator}
class Car {
    @IntAnnotation( minValue = 10, throwEx = false )
    int miles
    @IntAnnotation( minValue = 1990 )
    final int year
}

</pre>

<p>Here is an example of how to instantiate the object:</p>

<pre>
def carA = new Car( [ miles: 5, year: 2010 ], true)
</pre>

<p>You can also use a constructor with a second boolean to control if the final fields will throw an exception if the validation fails. If the validation fails, the object will not be created.</p>

<pre>
def carA = new Car( [ miles: 5, year: 2010 ], true, true)
</pre>

<p>The "throwEx" argument for an annotation on a final field is ignored.</p>


<p></p>
*/

@Retention( RetentionPolicy.SOURCE )
@Target( [ ElementType.TYPE ] )
@GroovyASTTransformationClass( [ 'info.shelfunit.properties.annotations.FinalFieldValidatorTransform' ] )
public @interface FinalFieldValidator {}

