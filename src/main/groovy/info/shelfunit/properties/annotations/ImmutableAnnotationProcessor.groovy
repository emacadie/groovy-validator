package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ClassHelper 
import org.codehaus.groovy.ast.ClassNode 

import static org.codehaus.groovy.transform.ToStringASTTransformation.createToString

import java.lang.annotation.Annotation
import org.codehaus.groovy.runtime.NullObject

/**
<p>This is a class that will process the annotations {@link info.shelfunit.properties.annotations.DoubleAnnotation}, {@link info.shelfunit.properties.annotations.FloatAnnotation}, {@link info.shelfunit.properties.annotations.IntAnnotation}, {@link info.shelfunit.properties.annotations.LongAnnotation} and {@link info.shelfunit.properties.annotations.StringAnnotation}</p>

<p>I never liked that fact that <a href="http://groovy.codehaus.org/Groovy+Beans">Groovy Beans</a> never had any validations for the properties (at least none that I could find). Grails has <a href="http://grails.org/doc/latest/ref/Constraints/Usage.html">constraints</a>. Why not Groovy?</p>

<p>This is pretty simple and a bit limited, but that is the intent. I want to add some easy validation to Groovy Beans. As far as I know, nobody has really done this for Groovy. There is project on Sourceforge called <a href="http://oval.sourceforge.net/">OVal</a>. That does a LOT of stuff, far beyond this project. It has <a href="http://oval.sourceforge.net/dependencies.html">22 dependencies</a>, 3 of them for logging alone. There is also <a href="http://hibernate.org/validator/">Hibernate Validator</a>. It implements some JSR, but when I read the documentation, it said I had to add two or three other JSRs. If that is what you need, go for it. Those are Java projects. This is for Groovy beans. The goal is to keep this as clean and simple as possible.</p>

*/
class ImmutableAnnotationProcessor {
    
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
    <p>You could make it a static method in the class itself, like this:</p>
    <pre>
    static { 
        AnnotationProcessor.process( Book.class ) 
    }
    </pre>
    <p>That is fine, but there is one corner case: If you use a map-based constructor the first time you instantiate the class, then the annotations will not be run on that object. however, they will be run for subsequent objects.</p>
    
    @param theClass The class to be transformed and validated
    */
    static process( Class theClass ) {
        println "Just got called for class ${theClass.getName()}"
        def imAnnotation = theClass.getAnnotation( ImmutableAnnotation )
        if ( imAnnotation ) {
            println "${theClass.name} has ImmutableAnnotation"
            def classNode = ClassHelper.make( theClass )
            println "classNode is a ${classNode.class.name} and its name is ${classNode.getUnresolvedName()}"
        } else {
            println "${theClass.name} does not have ImmutableAnnotation"
        }
     
        theClass.metaClass.constructor = { Map theMap ->
            // println "theMap[ secondString ]: ${ theMap[ "secondString" ]}"
            def instance = theClass.newInstance()
            println "instance is a ${instance.class.name}  and delegate is a ${delegate.class.name}"
            delegate = instance
            println "instance is a ${instance.class.name}  and now delegate is a ${delegate.class.name}"
          
            // http://burtbeckwith.com/blog/?p=1003
          
            theMap.each { entry ->
                
                def field = theClass.getDeclaredField( entry.key )
                def intAnnotation    = field?.getAnnotation( IntAnnotation.class )
                def stringAnnotation = field?.getAnnotation( StringAnnotation.class )
                def doubleAnnotation = field?.getAnnotation( DoubleAnnotation.class )
                def floatAnnotation  = field?.getAnnotation( FloatAnnotation.class )
                def longAnnotation   = field?.getAnnotation( LongAnnotation.class )
                    
                if ( ( entry.value instanceof Integer ) && ( intAnnotation ) ) {
                    println "Here is arg for int in constructor: ${entry.value} and it is a ${entry.value.class.name}"
                    println "instance is a ${instance.class.name}, delegate is a ${delegate.class.name}, owner is a ${owner.class.name}"
                    
                    if ( ( entry.value instanceof Integer ) && 
                        ( entry.value >= intAnnotation.minValue() ) &&
                        ( entry.value <= intAnnotation.maxValue() ) &&
                        ( entry.value >= Integer.MIN_VALUE ) &&
                        ( entry.value <= Integer.MAX_VALUE ) ) {
                        theClass.metaClass.getMetaProperty( entry.key ).setProperty( instance, entry.value )
                        
                    } else {
                        def currentVal = instance."${entry.key}"
                        println "${entry.value} did not pass the tests"
                    }
                } else if ( ( stringAnnotation ) && ( entry.value instanceof String ) ) {
                    println "setting the String method"
                    def metaMethod = theClass.metaClass.getMetaMethod( "${'set' + entry.key.capitalize()}",  entry.value )
                    if ( ( entry.value.length() >= stringAnnotation.minLength() ) &&
                        ( entry.value.length() <= stringAnnotation.maxLength() ) ) {
                            def qq = entry.value + entry.value
                            theClass.metaClass.getMetaProperty( entry.key ).setProperty( instance, entry.value )
                            println "calling invoke for the String method"
                    } else {
                        def currentVal = instance."${entry.key}"
                        println "${entry.value} did not pass the tests, and currentVal is ${currentVal}"
                    }
                }  else if ( doubleAnnotation ) {
                    if ( ( entry.val instanceof Double ) && 
                        ( entry.val >= doubleAnnotation.minValue() ) &&
                        ( entry.val <= doubleAnnotation.maxValue() ) &&
                        ( entry.val >= Double.MIN_VALUE ) &&
                        ( entry.val <= Double.MAX_VALUE ) ) {
                        theClass.metaClass.getMetaProperty( name ).setProperty( instance, entry.val )
                    }
                } else if ( floatAnnotation ) {
                    if ( ( entry.val instanceof Float ) && 
                        ( entry.val >= floatAnnotation.minValue() ) &&
                        ( entry.val <= floatAnnotation.maxValue() ) &&
                        ( entry.val >= Float.MIN_VALUE ) &&
                        ( entry.val <= Float.MAX_VALUE ) ) {
                        theClass.metaClass.getMetaProperty( name ).setProperty( instance, entry.val )
                    }
                } else if ( longAnnotation ) {
                    if ( ( arg instanceof Long ) && ( arg >= longAnnotation.minValue() ) &&
                        ( entry.val <= longAnnotation.maxValue() ) &&
                        ( entry.val >= Long.MIN_VALUE ) &&
                        ( entry.val <= Long.MAX_VALUE ) ) {
                        theClass.metaClass.getMetaProperty( name ).setProperty( instance, entry.val )
                    }
                } else {
                    println "in the else for ${entry.key} with val ${entry.val}"
                    theClass.metaClass.getMetaProperty( entry.key ).setProperty( instance, entry.val ) // this works
                } 
                
            } // map.each
            
            instance
        } // constructor

        theClass.metaClass.setProperty = { String name, arg ->
            println " In set property for ${theClass.getName()} for property ${name} with arg ${arg},  and delegate is a ${delegate.class.name}"
            println "totally skipping it"
        }
    } // end process - line 44
    
} // end class AnnotationProcessor - line 128, 295

