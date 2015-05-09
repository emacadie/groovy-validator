package validation

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
<p>This is an annotation to validate/constrain String fields in Plain Old Groovy Objects.</p>
<p>Here is an example on how to use it:</p>
<pre>
    @StringAnnotation( minLength = 5, maxLength = 10, throwEx = false )
    String firstString
    @StringAnnotation( maxLength = 400 )
    def secondString
    @StringAnnotation( minLength = 10, regEx = /^.*?[Gg]roovy.*$/ ) 
    String groovyString
</pre>
<p>If the field is defined as "String" and it is given a value in the first call to setX that is outside your constraints, then it will be set to a String with length 0. If the field is defined as "def" and it is given a value that is outside your constraints, then it will be set to null. If the field already has a valid value and it is sent an invalid one in a call to setX, the new, invalid value will be ignored.</p>

<p>If you choose to throw exceptions for constraint violations and your regular expression ends with a dollar sign, the message will print DOLLAR_SIGN in the message. I know it's pretty lame, but it's the only way I could get it to work. So a string with a regular expression constraint like this:
<pre>
/^.*?[Gg]roovy.*$/
</pre>
will throw an exception like this:
<pre>
java.lang.Exception: 'I like PHP' is a String with a length outside the range of 10 to 2147483647 characters or does not match the regular expression /^.*?[Gg]roovy.*DOLLAR_SIGN/ 
</pre>

</p>

<p>An application, class or library that uses this annotation must also import {@link validation.ImmutableValidator} to use this in an immutable object.</p>
<p></p>
*/

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )
@GroovyASTTransformationClass( [ 'info.shelfunit.properties.annotations.StringAnnotationTransform' ] )
public @interface StringAnnotation {
    /**
    The minimum length of the String field. The default is 0.
    */
    public int minLength() default 0
    /**
    The maximum length of the String field. The maximum is 2,147,483,647, which is the same as Integer.MAX_VALUE.
    */
    public int maxLength() default 2147483647
    /**
    <p>A regular expression the String must match. By default, it is the catch-all ".*"</p>
    <p>You can delineate a string with slashes, like this (for use with {@link validation.ImmutableValidator} or {@link validation.AnnotationProcessor}):</p>
    <pre>/^.*?[Gg]roovy.*$/</pre>
    <p>or as a Java string like this (for use with {@link validation.AnnotationProcessor} only):</p>
    <pre>"^.*?[Gg]roovy.*\$"</pre>
    <p>See the javadoc for the <a href="http://docs.oracle.com/javase/7/docs/api/?java/util/regex/Pattern.html">Pattern</a> class for details.</p>
    <p>If you are going to use this annotation with {@link validation.ImmutableValidator}, you MUST use slashes. If you are going to use it with {@link validation.AnnotationProcessor}, you can use either way.</p>
    <p>You cannot use comments to document the regular expression. It must be unbroken on one line.</p>
    */
    public String regEx() default ".*"
    
    /**
    A boolean declaring if an exception should be thrown if any of the contraints are violated. Defaults to true.
    */
    public boolean throwEx() default true
}

