package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ClassHelper 
import org.codehaus.groovy.ast.ClassNode 

import java.lang.annotation.Annotation
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
<p>This is a class that will process the annotations {@link info.shelfunit.properties.annotations.DoubleAnnotation}, {@link info.shelfunit.properties.annotations.FloatAnnotation}, {@link info.shelfunit.properties.annotations.IntAnnotation}, {@link info.shelfunit.properties.annotations.LongAnnotation} and {@link info.shelfunit.properties.annotations.StringAnnotation}</p>

<p>I never liked that fact that <a href="http://groovy.codehaus.org/Groovy+Beans">Groovy Beans</a> never had any validations for the properties (at least none that I could find). Grails has <a href="http://grails.org/doc/latest/ref/Constraints/Usage.html">constraints</a>. Why not Groovy?</p>

<p>This is pretty simple and a bit limited, but that is the intent. I want to add some easy validation to Groovy Beans. As far as I know, nobody has really done this for Groovy. There is project on Sourceforge called <a href="http://oval.sourceforge.net/">OVal</a>. That does a LOT of stuff, far beyond this project. It has <a href="http://oval.sourceforge.net/dependencies.html">22 dependencies</a>, 3 of them for logging alone. There is also <a href="http://hibernate.org/validator/">Hibernate Validator</a>. It implements some JSR, but when I read the documentation, it said I had to add two or three other JSRs. Honestly I could not get it to work. It also lets you put constraints on method and constructor parameters. If that is what you need, go for it. Those are Java projects, and can be used with Groovy. This is for Groovy only. The goal is to keep this as clean and simple as possible.</p>

<p>So far, this cannot handle fields that are marked as final. If you wish to use the field annotations on an object that is using Groovy's <a href="http://beta.groovy-lang.org/docs/groovy-2.3.0/html/gapi/index.html?groovy/transform/Immutable.html">Immutable</a> annotation, use the field annotations with the {@link info.shelfunit.properties.annotations.AstImmutableConstructor} annotation.</p>

*/
class AnnotationProcessor {
    
    /**
    <p>This is the method that actually processes the annotations.</p>
    <p>Suppose you made a class called "Book" that used some of the annotations this class processes. Somewhere in your code, you
    will need to do this:</p>
    <pre>
    AnnotationProcessor.process( Book.class ) 
    </pre>
    <p>I think you could also do:</p>
    <pre>
    AnnotationProcessor.process( Book ) 
    </pre>
    <p>You could call it in a static method in the class itself, like this:</p>
    <pre>
    static { 
        AnnotationProcessor.process( Book.class ) 
    }
    </pre>
    <p>That is fine, but there is one corner case: If you use a map-based constructor the first time you instantiate the class, then the annotations will not be run on that object. However, they will be run for subsequent objects.</p>
    
    <p>You can also have it throw an Exception if the argument does not meet the constraints. If you are setting a value for the first time, it will be null or 0. If you are setting a variable to a different value, it will retain its previous value. You can enable that by calling process like this:</p>
    
    <pre>
    AnnotationProcessor.process( Book, true ) 
    </pre>
    
    <p>If the exception is thrown, you will get a message like this:</p>
    
    <pre>
    "Hey" is a String with a length outside the range of 5 and 10"
    </pre>
    
    <p>If you let the numeric maximums or minimums be the default values, you will get messages like this:</p>
    
    <pre>
    5 is a long outside the range 10 and 9223372036854775807
    </pre>
    
    <p>There is no need to run the process method if you are annotating an immutable object with {@link info.shelfunit.properties.annotations.AstImmutableConstructor}.</p>
    
    @param theClass The class to be transformed and validated
    @param throwException Set this to true to throw an Exception if a field does not validate. This is optional, and is set to false by default.
    */
    static process( Class theClass, boolean throwException = false ) {
        
        theClass.metaClass.setProperty = { String name, arg ->
            
            def field = theClass.getDeclaredField( name )
            def intAnnotation    = field?.getAnnotation( IntAnnotation.class )
            def stringAnnotation = field?.getAnnotation( StringAnnotation.class )
            def doubleAnnotation = field?.getAnnotation( DoubleAnnotation.class )
            def floatAnnotation  = field?.getAnnotation( FloatAnnotation.class )
            def longAnnotation   = field?.getAnnotation( LongAnnotation.class )
            def divSet
            
            if ( intAnnotation ) {
                divSet = intAnnotation.divisor() as Set
                divSet.remove( 0 )
                if ( divSet.size() == 0 ) { println "removing 0 from divSet"; divSet.add( 1 ) }
                println "here is divSet: ${divSet} with arg ${arg}"
                if ( ( arg instanceof Integer ) && 
                    ( divSet.find{ arg % it == 0 }  != null ) &&
                    ( arg >= intAnnotation.minValue() ) &&
                    ( arg <= intAnnotation.maxValue() ) &&
                    ( arg >= Integer.MIN_VALUE ) &&
                    ( arg <= Integer.MAX_VALUE ) ) {
                    theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg )
                } else { 
                    if ( throwException ) {
                        throw new Exception( "${arg} is an integer outside the range ${intAnnotation.minValue()} to ${intAnnotation.maxValue()}" )
                    }
                }
            } else if ( stringAnnotation ) {
                // println "Here is arg for string: ${arg}, and delegate is a ${delegate.class.name}"
                def theMatch = Pattern.compile( stringAnnotation.regEx(), Pattern.COMMENTS )
                if ( ( arg.length() >= stringAnnotation.minLength() ) &&
                    ( arg.length() <= stringAnnotation.maxLength() ) && 
                     ( theMatch.matcher( arg ).matches() ) ) {
                    theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg.toString() )
                } else { 
                    if ( throwException ) {
                        throw new Exception( "\"${arg}\" is a String with a length outside the range of ${stringAnnotation.minLength()} to ${stringAnnotation.maxLength()}" )
                    }
                }
            } else if ( doubleAnnotation ) {
                if ( ( arg instanceof Double ) && 
                    ( arg >= doubleAnnotation.minValue() ) &&
                    ( arg <= doubleAnnotation.maxValue() ) &&
                    ( arg >= Double.MIN_VALUE ) &&
                    ( arg <= Double.MAX_VALUE ) ) {
                    theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg )
                } else { 
                    if ( throwException ) {
                        throw new Exception( "${arg} is a double outside the range ${doubleAnnotation.minValue()} to ${doubleAnnotation.maxValue()}" )
                    }
                }
            } else if ( floatAnnotation ) {
                if ( ( arg instanceof Float ) && 
                    ( arg >= floatAnnotation.minValue() ) &&
                    ( arg <= floatAnnotation.maxValue() ) &&
                    ( arg >= Float.MIN_VALUE ) &&
                    ( arg <= Float.MAX_VALUE ) ) {
                    theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg )
                } else { 
                    if ( throwException ) {
                        throw new Exception( "${arg} is a float outside the range ${floatAnnotation.minValue()} to ${floatAnnotation.maxValue()}" )
                    }
                }
            } else if ( longAnnotation ) {
                divSet = longAnnotation.divisor() as Set
                divSet.remove( 0L )
                if ( divSet.size() == 0L ) { divSet.add( 1L ) }
                if ( ( arg instanceof Long ) && 
                    ( divSet.find{ arg % it == 0 }  != null   ) &&
                    ( arg >= longAnnotation.minValue() ) &&
                    ( arg <= longAnnotation.maxValue() ) &&
                    ( arg >= Long.MIN_VALUE ) &&
                    ( arg <= Long.MAX_VALUE ) ) {
                    theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg )
                } else { 
                    if ( throwException ) {
                        throw new Exception( "${arg} is a long outside the range ${longAnnotation.minValue()} to ${longAnnotation.maxValue()}" )
                    }
                }
            } else {
                theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg ) // this works
            }
        }
        
    } // end process - line 44
    
} // end class AnnotationProcessor - line 128

