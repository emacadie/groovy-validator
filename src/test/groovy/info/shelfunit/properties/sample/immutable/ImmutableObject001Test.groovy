package info.shelfunit.properties.sample.immutable

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class ImmutableObject001Test extends Specification { 
    
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {
        
    }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
   
    // for some reason the first time you call a class it does not actually process the annotations
    // comment out the lines for the "junk" object and compare
    def "test the no arg constructor"() {
        println "--- Starting test ${name.methodName}"
        // println "About to make junk"
        // def junk = new FirstImmutableSample()
        // println "About to make throwaway"
        def throwaway = new ImmutableObject001( firstString: "Not Junk", firstInt: 21 )
        // throwaway.firstString = "Not Junk"
        println "Just made throwaway, about to make bTest1"
        def bTest1 = new ImmutableObject001( firstString: "Hello1", firstInt: 200 )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        expect:
        bTest1.firstString == "Hello1"
        bTest1.firstInt == 200
        
    } // end "test the no arg constructor"
    
    
    /*
    def "test bTest2"() {
        // println "--- Starting test ${name.methodName}"
        def bTest2 = new FirstImmutableSample( firstString: "Hello2", secondString: "Goodbye, this is more than 20 characters", firstInt: 22, secondInt: 20 )
        // println "In test ${name.methodName}, bTest2: ${bTest2.toString()}"
        expect:
        bTest2.firstString == "Hello2"
    } // end "test bTest2"
    */
    /*
    def "test the no arg constructor again"() {
        // println "--- Starting test ${name.methodName}"
        /*
        def constructors = FirstImmutableSample.class.getConstructors()
        constructors.each { 
            println "Constructor: ${it.toString()}"
        }
        def methods = FirstImmutableSample.metaClass.getMetaMethods()
        methods.each {
            println "MetaMethod: ${it.toString()}"
        }
        // * /
        
            def bTest1 = new FirstImmutableSample( firstString: "Hello3", secondString: "Goodbye", firstInt: 21, secondInt: 401 )
        // println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        expect:
        bTest1.firstString == "Hello3"
        bTest1.secondInt == null
        
    } // end "test the no arg constructor again"
    */
    
        def "trying one with two fields"() {
        println "\n--- Starting test ${name.methodName}"
                given:
        String classString = '''
package info.shelfunit.somepackage

import info.shelfunit.properties.annotations.AstImmutableConstructor
import info.shelfunit.properties.annotations.DoubleAnnotation
import info.shelfunit.properties.annotations.FloatAnnotation
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.LongAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.Immutable

@Immutable
@AstImmutableConstructor
class UVW {
    @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @StringAnnotation( maxLength = 150 )
    String secondString
    @DoubleAnnotation( minValue = 10d, maxValue = 100d )
    double firstDouble
    @FloatAnnotation( minValue = 10f, maxValue = 100f )
    float firstFloat
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int firstInt
    @LongAnnotation( maxValue = 100L )
    long firstLong
}

new UVW(firstString: "hello", firstInt: 222)
'''

        when:
        def instance = new GroovyShell().evaluate( classString )
        Method added = instance.class.declaredMethods.find { it.name == 'added' }

        then:
        !added
    }
    
}

