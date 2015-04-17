package visibility

import java.util.regex.Pattern
import groovy.transform.Immutable

/**
<p>This is a class that will process the annotations {@link validation.DoubleAnnotation}, {@link validation.FloatAnnotation}, {@link validation.IntAnnotation}, {@link validation.LongAnnotation} and {@link validation.StringAnnotation}</p>

<p>I never liked that fact that <a href="http://groovy-lang.org/objectorientation.html#properties">Groovy Beans</a> never had any validations for the properties (at least none that I could find). Grails has <a href="http://grails.org/doc/latest/ref/Constraints/Usage.html">constraints</a>. Why not Groovy?</p>

<p>This is pretty simple and a bit limited, but that is the intent. I want to add some easy validation to Groovy Beans. As far as I know, nobody has really done this for Groovy. There is project on Sourceforge called <a href="http://oval.sourceforge.net/">OVal</a>. That does a LOT of stuff, far beyond this project. It has <a href="http://oval.sourceforge.net/dependencies.html">22 dependencies</a>, 3 of them for logging alone. There is also <a href="http://hibernate.org/validator/">Hibernate Validator</a>. It implements some JSR, but when I read the documentation, it said I had to add two or three other JSRs. Honestly I could not get it to work. It also lets you put constraints on method and constructor parameters. If that is what you need, go for it. Those are Java projects, and can be used with Groovy. This is for Groovy only. The goal is to keep this as clean and simple as possible.</p>

<p>So far, this cannot handle fields that are marked as final. If you wish to use the field annotations on an object that is using Groovy's <a href="http://docs.groovy-lang.org/latest/html/gapi/index.html?groovy/transform/Immutable.html">Immutable</a> annotation, use the field annotations with the {@link validation.AstImmutableConstructor} annotation.</p>

*/
class VisibilityProcessor {
    
    /**
    <p>This is the method that actually processes the annotations for mutable objects.</p>
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
    "Hey" is a String with a length outside the range of 5 and 10 or does not match the regular expression /^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}\$/"
    </pre>
    
    <p>If you let the numeric maximums or minimums be the default values, you will get messages like this:</p>
    
    <pre>
    5 is a long outside the range 10 and 9223372036854775807 or it is not divisible by anything in the set [1] 
    </pre>
    
    <p>There is no need to run the process method if you are annotating an immutable object with {@link validation.AstImmutableConstructor}.</p>
    
    @param theClass The class to be transformed and validated
    @param throwException Set this to true to throw an Exception if a field does not validate. This is optional, and is set to false by default.
    */
    static process( Class theClass, boolean throwException = false ) {
        
        def hasImmutableAnn = theClass.getAnnotation( Immutable.class )
        if ( !hasImmutableAnn ) {
            processClass( theClass, throwException )
        } 
        
    } // end process - line 44, 153, 134
    
    def private static processClass( Class theClass, boolean throwException ) {
        theClass.metaClass.setProperty = { String name, arg ->
                
            def field = theClass.getDeclaredField( name )
            def hiddenAnnotation    = field?.getAnnotation( Hidden.class )
            
            // java.lang.reflect.Modifier.FINAL = 16 PUBLIC = 1
                if ( hiddenAnnotation ) {
                    println "In VisibilityProcessor, Processing Hidden for ${field} named ${name}"
                    def currentValue = theClass.metaClass.getMetaProperty( name ).getProperty( delegate )
                    println "currentValue: ${currentValue} and it's a ${currentValue.class.name}, not using ${arg}"
                    // theClass.metaClass.getMetaProperty( name ).setProperty( delegate, currentValue )
                    // theClass.metaClass.getMetaProperty( name ).setProperty( null, null )
                    println "About to try making ${'set' + name.capitalize()}"
                    theClass.metaClass."${'set' + name.capitalize()}" { Object ffff ->
                        delegate.set( name, currentValue )
                    }
                    
                } 
                
        } // end closure
    } // end method processClass
    
} // end class AnnotationProcessor - line 128 - line 157, 150

