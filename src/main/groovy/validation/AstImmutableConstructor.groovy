package validation

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
<p>This is an annotation that can be used to validate fields in immutable objects. It is intended to be used with the <a href="http://docs.groovy-lang.org/latest/html/gapi/index.html?groovy/transform/Immutable.html">Immutable</a> annotation at the class level. The fields you wish to validate must be annotated with the following annotations: {@link validation.DoubleAnnotation}, {@link validation.FloatAnnotation}, {@link validation.IntAnnotation}, {@link validation.LongAnnotation} and {@link validation.StringAnnotation}.</p>

<p>The {@link validation.ImmutableValidator} annotation does the same thing, with a few less lines of code. It is a meta-annotation that wraps this one. If you use this annotation, you MUST also annotate your class with Groovy's <a href="http://docs.groovy-lang.org/latest/html/gapi/index.html?groovy/transform/Immutable.html">Immutable</a> annotation. {@link validation.ImmutableValidator}, on the other hand, is a meta-annotation using <a href="http://docs.groovy-lang.org/latest/html/gapi/index.html?groovy/transform/AnnotationCollector.html">AnnotationCollector</a> to combine this annotation with <a href="http://docs.groovy-lang.org/latest/html/gapi/index.html?groovy/transform/Immutable.html">Immutable</a> and <a href="http://docs.groovy-lang.org/latest/html/gapi/index.html?groovy/transform/ToString.html">ToString</a> into one annotation. </p>

<p>The fields must be of a type that can be in an object annotated with the <a href="http://docs.groovy-lang.org/latest/html/gapi/index.html?groovy/transform/Immutable.html">Immutable</a> annotation. Unlike using {@link validation.DoubleAnnotation}, {@link validation.FloatAnnotation}, {@link validation.IntAnnotation}, {@link validation.LongAnnotation} and/or {@link validation.StringAnnotation} in mutable objects, you cannot have fields declared as "def" with this annotation. Those annotations have their own transform classes, but if they detect that this annotation has been used, they stop processing.</p>

<p>
There is a bit of a bug: It will set String, double, float, int and long fields within the default constraints in the annotations listed in the previous paragraph even if the fields are not annotated. They are pretty broad, but the default for the numbers is to set the minimum equal to 0. So if you have no annotation for an int, and you try to give it a value below 0, it will be set to 0. However, this might be a "bug" that really is a feature.
</p>


<p>This is an annotation has a "throwException" field. The annotations {@link validation.DoubleAnnotation}, {@link validation.FloatAnnotation}, {@link validation.IntAnnotation}, {@link validation.LongAnnotation} and {@link validation.StringAnnotation} all have "throwEx". I have not tested what happens if "throwException" is set to true, and "throwEx" for a particular field is false, or vice versa. I will leave this for a later date.</p>
<p>
Here is an example class:
</p>

<pre>
package info.shelfunit.properties.sample.immutable

import validation.AstImmutableConstructor
import validation.IntAnnotation
import validation.LongAnnotation
import validation.StringAnnotation
import groovy.transform.Immutable
import groovy.transform.ToString

{@code @ToString( includeNames = true )}
{@code @AstImmutableConstructor}
{@code @Immutable}
class ImmutableObject002 {

    @StringAnnotation( minLength = 5, maxLength = 10, regex = /\d{4}?-\d\d-\d\d/ )
    String firstString
    @IntAnnotation( minValue = 10, maxValue = 100, divisorSet = [ 2, 3 ] )
    int firstInt
    @LongAnnotation( maxValue = 100L )
    long firstLong
}

</pre>

<p>
You could set this with a Map like any other immutable object in Groovy, or with the fields, but it will not trigger validation:
</p>

<pre>
def firstImObject = new ImmutableObject002( firstString: "Hello", firstInt: 55, firstLong: 44L )
</pre>

<p>
To get the annotation to actually process, you should send two parameters to the constructor: a Map for the fields, and a boolean for whether or not you want the fields to be validated. This boolean is called "validation" in the AST Transformer code. If validation is set to false, the effect is the same as if you simply sent the fields as a Map.
</p>

<pre>
def secondImObject = new ImmutableObject002( [ firstString: "Hi Again", firstInt: 11, firstLong: 22L ], true )
</pre>

<p>You could also send a second boolean (called "throwException" in the AST Transformer) after the Map to throw an Exception if one of the fields does not validate. It is false by default.
</p>

<pre>
def thirdImObject = new ImmutableObject002( [ firstString: "Hi Once Again", firstInt: 123456789, firstLong: 22L ], true, true )
</pre>

<p>If throwException is not set to true and a field does not validate, it will be set to "null" if it is a String and 0 for a number. If throwException is set to true and a field does not validate, an Exception is thrown and the object is not created.</p>

<p>There is a corner case I have not been able to solve. I made an object with two String fields and a few numbers. If I did not provide a value for the second String, it would be null if throwException was set to false, but it would be set to "null" if throwException was set to true.</p>

<p>If validation is set to false and throwException is set to true, then no exceptions are thrown.</p>

<p>The message of the exception is a pseudo-list of messages, one for each that did not pass the validation. They are separated by newline characters. Here is an example:</p>

<pre>
Groovy validation exception: 
"eeeeeeeeeee" is a String with a length outside the range of 5 to 10 characters or does not match the regular expression ".*"
"NNNNNNNNNNNNNNNN" is a String with a length outside the range of 0 to 15 characters or does not match the regular expression /^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}\$/
101.0 is a double outside the range 10.0 to 100.0 
101.0 is a float outside the range 10.0 to 100.0
101 is an integer outside the range 10 to 100 or it is not divisible by anything in the set [1] 
101 is a long outside the range 0 to 100 or it is not divisible by anything in the set [5, 7] 
</pre>

*/
@Retention( RetentionPolicy.SOURCE )
@Target( [ ElementType.TYPE ] )
@GroovyASTTransformationClass( [ 'validation.AstImmutableConstructorTransform' ] )
public @interface AstImmutableConstructor {
}

