package info.shelfunit.properties.annotations

import java.util.regex.Pattern
import groovy.transform.Immutable

/**
<p>This is a class that will process the annotations {@link info.shelfunit.properties.annotations.DoubleAnnotation}, {@link info.shelfunit.properties.annotations.FloatAnnotation}, {@link info.shelfunit.properties.annotations.IntAnnotation}, {@link info.shelfunit.properties.annotations.LongAnnotation} and {@link info.shelfunit.properties.annotations.StringAnnotation}</p>

<p>I never liked that fact that <a href="http://groovy.codehaus.org/Groovy+Beans">Groovy Beans</a> never had any validations for the properties (at least none that I could find). Grails has <a href="http://grails.org/doc/latest/ref/Constraints/Usage.html">constraints</a>. Why not Groovy?</p>

<p>This is pretty simple and a bit limited, but that is the intent. I want to add some easy validation to Groovy Beans. As far as I know, nobody has really done this for Groovy. There is project on Sourceforge called <a href="http://oval.sourceforge.net/">OVal</a>. That does a LOT of stuff, far beyond this project. It has <a href="http://oval.sourceforge.net/dependencies.html">22 dependencies</a>, 3 of them for logging alone. There is also <a href="http://hibernate.org/validator/">Hibernate Validator</a>. It implements some JSR, but when I read the documentation, it said I had to add two or three other JSRs. Honestly I could not get it to work. It also lets you put constraints on method and constructor parameters. If that is what you need, go for it. Those are Java projects, and can be used with Groovy. This is for Groovy only. The goal is to keep this as clean and simple as possible.</p>

<p>So far, this cannot handle fields that are marked as final. If you wish to use the field annotations on an object that is using Groovy's <a href="http://beta.groovy-lang.org/docs/groovy-2.3.0/html/gapi/index.html?groovy/transform/Immutable.html">Immutable</a> annotation, use the field annotations with the {@link info.shelfunit.properties.annotations.AstImmutableConstructor} annotation.</p>

*/
class AnnotationProcessor {
    
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
    
    <p>There is no need to run the process method if you are annotating an immutable object with {@link info.shelfunit.properties.annotations.AstImmutableConstructor}.</p>
    
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
        def useStringBlock = true
        theClass.metaClass.setProperty = { String name, arg ->
                
            def field = theClass.getDeclaredField( name )
            def intAnnotation    = field?.getAnnotation( IntAnnotation.class )
            def stringAnnotation = field?.getAnnotation( StringAnnotation.class )
            def doubleAnnotation = field?.getAnnotation( DoubleAnnotation.class )
            def floatAnnotation  = field?.getAnnotation( FloatAnnotation.class )
            def longAnnotation   = field?.getAnnotation( LongAnnotation.class )
            def divSet
            
            // if ( theClass.metaClass.getMetaProperty( name ).getModifiers() != 17 ) {
            // java.lang.reflect.Modifier.FINAL = 16 PUBLIC = 1
            if ( field.getModifiers() != 17 ) {
                if ( doubleAnnotation ) {
                    handleDoubleAndFloat( arg, new Double( 0 ), doubleAnnotation, name, theClass, delegate, throwException )
                } else if ( floatAnnotation ) {
                    handleDoubleAndFloat( arg, new Float( 0 ), floatAnnotation, name, theClass, delegate, throwException )
                } else if ( intAnnotation ) {
                    handleIntAndLong( arg, new Integer( 0 ), intAnnotation, theClass, name, delegate, throwException )
                } else if ( longAnnotation ) {
                    handleIntAndLong( arg, new Long( 0 ), longAnnotation, theClass, name, delegate, throwException )                
                } else if ( stringAnnotation ) {
                    println "Processing the string in AnnotationProcessor for ${theClass.getName()}"
                    def theMatch = Pattern.compile( stringAnnotation.regEx(), Pattern.COMMENTS )
                    def minimum = stringAnnotation.minLength()
                    if ( minimum < 0 ) { minimum = 0 }
                    if ( ( arg.length() >= minimum ) &&
                        ( arg.length() <= stringAnnotation.maxLength() ) && 
                         ( theMatch.matcher( arg ).matches() ) ) {
                        theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg.toString() )
                    } else { 
                        if ( throwException ) {
                            throw new Exception( "Groovy validation exception: \n" +
                            "\"${arg}\" is a String with a length outside the range of ${stringAnnotation.minLength()} to ${stringAnnotation.maxLength()} characters or does not match the regular expresstion ${stringAnnotation.regEx()}" )
                        }
                    }
                    
                } else {

                    println "-----\tHere is theClass.metaClass.getMetaProperty( name ).getModifiers(): ${theClass.metaClass.getMetaProperty( name ).getModifiers()}" 
                    theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg ) // this works
                }
            } // if ( !fieldIsFinal )
        } // end closure
    } // end method processClass
   
    // theNumber must be 0 - it is used to prevent division by 0
    def private static handleIntAndLong( arg, theNumber, annotation, theClass, name, delegate, throwException ) {
        def divSet = annotation.divisorSet() as Set
        println "in int and long with arg ${arg}"
        divSet.remove( theNumber )
        if ( divSet.size() == 0 ) { divSet.add( ++theNumber ) }
        if ( ( arg.class.name == theNumber.class.name ) && 
            ( divSet.find{ arg % it == 0 }  != null ) &&
            ( arg >= annotation.minValue() ) &&
            ( arg <= annotation.maxValue() ) &&
            ( arg >= theNumber.MIN_VALUE ) &&
            ( arg <= theNumber.MAX_VALUE ) ) {
            theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg )
        } else { 
            if ( throwException ) {
                throw new Exception( "Groovy validation exception: \n" +
                "${arg} is a ${theNumber.class.name} outside the range ${annotation.minValue()} to ${annotation.maxValue()} or it is not divisible by anything in the set ${divSet} " )
            }
        }
    } // handleIntAndLong

    def private static handleDoubleAndFloat( arg, numClass, annotation, name, theClass, delegate, throwException ) {
        if ( ( arg.class.name == numClass.class.name ) && 
            ( arg >= annotation.minValue() ) &&
            ( arg <= annotation.maxValue() ) &&
            ( arg >= numClass.MIN_VALUE ) &&
            ( arg <= numClass.MAX_VALUE ) ) {
            theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg )
        } else { 
            if ( throwException ) {
                throw new Exception( "Groovy validation exception: \n" +
                "${arg} is a ${numClass.name} outside the range ${annotation.minValue()} to ${annotation.maxValue()}" )
            }
        }
    } // handleDoubleAndFloat
    
} // end class AnnotationProcessor - line 128 - line 157, 150

