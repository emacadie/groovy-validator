package info.shelfunit.properties.annotations

import java.util.regex.Pattern
import validation.ValidDouble
import validation.ValidFloat
import validation.ValidInt
import validation.ValidLong
import validation.ValidString

/**
<p>This is a class that will process the annotations {@link info.shelfunit.properties.annotations.DoubleAnnotation}, {@link info.shelfunit.properties.annotations.FloatAnnotation}, {@link info.shelfunit.properties.annotations.IntAnnotation}, {@link info.shelfunit.properties.annotations.LongAnnotation} and {@link info.shelfunit.properties.annotations.StringAnnotation}</p>

<p>I never liked that fact that <a href="http://groovy.codehaus.org/Groovy+Beans">Groovy Beans</a> never had any validations for the properties (at least none that I could find). Grails has <a href="http://grails.org/doc/latest/ref/Constraints/Usage.html">constraints</a>. Why not Groovy?</p>

<p>This is pretty simple and a bit limited, but that is the intent. I want to add some easy validation to Groovy Beans. As far as I know, nobody has really done this for Groovy. There is project on github called <a href="https://github.com/sebthom/oval">OVal</a> (you can find the user guide at <a href="https://sebthom.github.io/oval/USERGUIDE.html">this link</a>). That does a LOT of stuff, far beyond this project. It has 22 dependencies, 3 of them for logging alone. And JRuby. Hard pass. There is also <a href="http://hibernate.org/validator/">Hibernate Validator</a>. It implements some JSR, but when I read the documentation, it said I had to add two or three other JSRs. Honestly I could not get it to work. It also lets you put constraints on method and constructor parameters. If that is what you need, go for it. Those are Java projects, and can be used with Groovy. This is for Groovy only. The goal is to keep this as clean and simple as possible.</p>

<p>So far, this cannot handle fields that are marked as final. If you wish to use the field annotations on an object that is using Groovy's <a href="http://beta.groovy-lang.org/docs/groovy-2.3.0/html/gapi/index.html?groovy/transform/Immutable.html">Immutable</a> annotation, use the field annotations with the {@link info.shelfunit.properties.annotations.AstImmutableConstructor} annotation.</p>

*/
class StaticAnnotationProcessor {
    
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
        
        theClass.metaClass.setProperty = { String name, arg ->
            
            def field = theClass.getDeclaredField( name )
            println( "In StaticAnnotationProcessor.process, name: " + name + ", field: " + field + ", arg: " + arg )

            def annotations = field?.getDeclaredAnnotations()
            annotations.every {
                println( "Here is annotation for ${name}: " + it.toString()  )
                println( "here is class of annotation: " + it.getClass().getName().toString() )
                // println( "type: " + it.asType().getClass().toString() )
            }
            def intAnnotation    = field?.getAnnotation( ValidInt.class )
            println( "intAnnotation: " + intAnnotation )
            def stringAnnotation = field?.getAnnotation( ValidString.class )
            println( "stringAnnotation: " + stringAnnotation  )
            def doubleAnnotation = field?.getAnnotation( ValidDouble.class )
            def floatAnnotation  = field?.getAnnotation( ValidFloat.class )
            def longAnnotation   = field?.getAnnotation( ValidLong.class )
            def divSet
            
            if ( doubleAnnotation ) {
                handleDoubleAndFloat( arg, new Double( 0 ), doubleAnnotation.minValue(), doubleAnnotation.maxValue(), name, theClass, delegate, throwException )
            } else if ( floatAnnotation ) {
                handleDoubleAndFloat( arg, new Float( 0 ), floatAnnotation.minValue(), floatAnnotation.maxValue(), name, theClass, delegate, throwException )
            } else if ( intAnnotation ) {
                handleIntAndLong( arg, intAnnotation.divisorSet() as Set, new Integer( 0 ), intAnnotation.minValue(), intAnnotation.maxValue(), theClass, name, delegate, throwException )
            } else if ( longAnnotation ) {
                handleIntAndLong( arg, longAnnotation.divisorSet() as Set, new Long( 0 ), longAnnotation.minValue(), longAnnotation.maxValue(), theClass, name, delegate, throwException )                
            
            } else if ( stringAnnotation ) {
                println "stringAnnotation is not null,  throwException: " +  stringAnnotation.throwEx()
                def theMatch = Pattern.compile( stringAnnotation.regEx(), Pattern.COMMENTS )
                def minimum = stringAnnotation.minLength()
                if ( minimum < 0 ) { minimum = 0 }
                if ( ( arg.length() >= minimum ) &&
                    ( arg.length() <= stringAnnotation.maxLength() ) && 
                     ( theMatch.matcher( arg ).matches() ) ) {
                    theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg.toString() )
                } else { 
                    if ( stringAnnotation.throwEx() ) {
                        throw new Exception( "Groovy validation exception: \n" +
                        "\"${arg}\" is a String with a length outside the range of ${stringAnnotation.minLength()} to ${stringAnnotation.maxLength()} characters or does not match the regular expresstion ${stringAnnotation.regEx()}" )
                    }
                }
            
            } else {
                // java.lang.reflect.Modifier.FINAL = 16 PUBLIC = 1
                if ( theClass.metaClass.getMetaProperty( name ).getModifiers() != 17 ) {
                    println "-----\tHere is theClass.metaClass.getMetaProperty( name ).getModifiers(): ${theClass.metaClass.getMetaProperty( name ).getModifiers()}" 
                    theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg ) // this works
                }
            }
        }
        
    } // end process - line 44, 153, 134
    
    // theNumber must be 0
    def private static handleIntAndLong( arg, divSet, theNumber, annMinValue, annMaxValue, theClass, name, delegate, throwException ) {
        println "in int and long with arg ${arg}"
        divSet.remove( theNumber )
        if ( divSet.size() == 0 ) { divSet.add( ++theNumber ) }
        if ( ( arg.class.name == theNumber.class.name ) && 
            ( divSet.find{ arg % it == 0 }  != null ) &&
            ( arg >= annMinValue ) &&
            ( arg <= annMaxValue ) &&
            ( arg >= theNumber.MIN_VALUE ) &&
            ( arg <= theNumber.MAX_VALUE ) ) {
            theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg )
        } else { 
            if ( throwException ) {
                throw new Exception( "Groovy validation exception: " +
                "${arg} is a ${theNumber.class.name} outside the range ${annMinValue} to ${annMaxValue} or it is not divisible by anything in the set ${divSet} " )
            }
        }
    } // handleIntAndLong

    def private static handleDoubleAndFloat( arg, numClass, annMinValue, annMaxValue, name, theClass, delegate, throwException ) {

        if ( ( arg.class.name == numClass.class.name ) && 
            ( arg >= annMinValue ) &&
            ( arg <= annMaxValue ) &&
            ( arg >= numClass.MIN_VALUE ) &&
            ( arg <= numClass.MAX_VALUE ) ) {
            theClass.metaClass.getMetaProperty( name ).setProperty( delegate, arg )
        } else { 
            if ( throwException ) {
                throw new Exception( "Groovy validation exception: \n" +
                "${arg} is a ${numClass.name} outside the range ${annMinValue} to ${annMaxValue}" )
            }
        }
    } // handleDoubleAndFloat

    static processFirstTime( Class theClass, List myFields, boolean throwException = false  ) {
        println "\n\nin processFirstTime"
        println "class name: " + theClass.getName().toString()

        def dFields = theClass.getDeclaredFields()
        println "dFields.length: ${dFields.length} and it's a ${dFields.getClass().name}"
        dFields.every {
            try {
                println "name of dFields: " + it.getName()
                /*
                if (it.getAnnotations().length > 0) {
                    println it.getName() + " has annotations"
                }
                */
            } catch ( Exception e ) {
                println "exception in dFields.every for ${it.name}"
                e.printStackTrace()
            }
        }
        for ( def i = 0; i < dFields.length; i++  ) {
            println "name dFields[${i}]: ${dFields[ i ].name}"
            if ( dFields[ i ].getAnnotations().length > 0 ) {
                println dFields[ i ].getName() + " has annotations"
            }
            def stringAnnotation = dFields[ i ].getAnnotation( ValidString.class )
            println "does ${dFields[ i ]} have string annotation: " + stringAnnotation
            if ( stringAnnotation ) {
                println "dFields[i] has a string annotation"
                try {
                    theClass.metaClass.set${ dFields[i].name.capitalize() } { String arg ->
                        println "In new method set${dFields[i].name}"
                        theClass.metaClass.getMetaProperty(name).setProperty(delegate, arg.reverse())

                    }
                } catch ( Exception e ) {
                    e.printStackTrace()
                }
            }
        }

        def fields = theClass.getFields()
        println "fields.length" + fields.length
        fields.every {
            println "name of field: " + it.getName()
            if ( it.getAnnotations().length > 0 ) {
                println it.getName() + " has annotations"
            }

        }
        myFields.every{ my ->
            try {
                println "field name is: ${my}"
                // theField = theClass.getDeclaredField( my.toString() )
                // println "Here is theField: " + theField.getName()

                /*
                theClass.metaClass."set${my.toString().capitalize()}" = {

                }
                */

            } catch ( Exception e ) {
                e.printStackTrace()
            }
        }
        println "ending processFirstTime\n\n"
    } // processFirstTime

} // end class AnnotationProcessor - line 128 - line 157, 150

