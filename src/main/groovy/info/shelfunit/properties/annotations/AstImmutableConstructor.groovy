package info.shelfunit.properties.annotations

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
<p>This is an annotation that can be used to validate fields in immutable objects. It is intended to be used with the <a href="http://beta.groovy-lang.org/docs/groovy-2.3.0/html/gapi/index.html?groovy/transform/Immutable.html">Immutable</a> annotation at the class level, although I think it will also work with mutable POGOs as well. The fields can be annotated with the following annotations: {@link info.shelfunit.properties.annotations.DoubleAnnotation}, {@link info.shelfunit.properties.annotations.FloatAnnotation}, {@link info.shelfunit.properties.annotations.IntAnnotation}, {@link info.shelfunit.properties.annotations.LongAnnotation} and {@link info.shelfunit.properties.annotations.StringAnnotation}. You do not need to run the {@link info.shelfunit.properties.annotations.AnnotationProcessor} for this to work.</p>

<p>
There is a bit of a bug: It will set String, double, float, int and long fields within the default constraints in the annotations listed in the previous paragraph even if the fields are not annotated. They are pretty broad, but the default for the numbers is to set the minimum equal to 0. So if you have no annotation for an int, and you try to give it a value below 0, it will be set to 0.
</p>

<p>
Here is an example class:
</p>

<pre>
package info.shelfunit.properties.sample.immutable

import info.shelfunit.properties.annotations.AstImmutableConstructor
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.LongAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.Immutable
import groovy.transform.ToString

{@code @ToString( includeNames = true )}
{@code @AstImmutableConstructor}
{@code @Immutable}
class ImmutableObject002 {

    @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @IntAnnotation( minValue = 10, maxValue = 100 )
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
To get the annotation to actually process, you should send two parameters to the constructor: a Map for the fields, and a boolean for whether or not you want the fields to be validated. If the validation is set to false, the effect is the same as if you simply sent the fields as a Map.
</p>

<pre>
def secondImObject = new ImmutableObject002( [ firstString: "Hi Again", firstInt: 11, firstLong: 22L ], true )
</pre>

*/
@Retention( RetentionPolicy.SOURCE )
@Target( [ ElementType.TYPE ] )
@GroovyASTTransformationClass( [ 'info.shelfunit.properties.annotations.AstImmutableConstructorTransform' ] )
public @interface AstImmutableConstructor {
}

