package info.shelfunit.properties.nonmutable

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName
import info.shelfunit.properties.annotations.ImmutableAnnotationProcessor

// @Immutable
class TrulyImmutable001Test extends Specification {
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {
        ImmutableAnnotationProcessor.process( TrulyImmutable001.class ) 
        ImmutableAnnotationProcessor.processGetProp( TrulyImmutable001.class )
    }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    // for some reason the first time you call a class it does not actually process the annotations
    // comment out the lines for the "junk" object and compare
    /*
    def "test the no arg constructor"() {
        println "\n\n--- Starting test ${name.methodName}"
        // println "About to make junk"
        // def junk = new TrulyImmutable001()
        println "About to make throwaway"
        when:
        def throwaway = new TrulyImmutable001( firstString: "Not Junk", secondString: "Goodbye Junk", firstInt: 21, secondInt: 20 )
        throwaway.firstString = "Throwaway"
        then:
        throwaway.firstString == "Not Junk"
        throwaway.firstInt == 0
        throwaway.secondInt == null
        println "Just made throwaway, about to make bTest1"
        when:
        def bTest1 = new TrulyImmutable001( firstString: "Hello1", secondString: "Goodbye", firstInt: 21, secondInt: 200 )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        then:
        bTest1.firstString == "Hello1"
        bTest1.secondInt == 200
        
    } // end "test the no arg constructor"
    */
    
    /*
    def "test bTest2"() {
        println "\n\n--- Starting test ${name.methodName}"
        def bTest2 = new TrulyImmutable001( firstString: "Hello2", secondString: "Goodbye, this is more than 20 characters", firstInt: 22, secondInt: 20 )
        println "In test ${name.methodName}, bTest2: ${bTest2.toString()}"
        expect:
        bTest2.firstString == "Hello2"
    } // end "test bTest2"
    */
    /*
    def "test the no arg constructor again"() {
        println "\n\n--- Starting test ${name.methodName}"
        /*
        def constructors = TrulyImmutable001.class.getConstructors()
        constructors.each { 
            println "Constructor: ${it.toString()}"
        }
        def methods = TrulyImmutable001.metaClass.getMetaMethods()
        methods.each {
            println "MetaMethod: ${it.toString()}"
        }
        * /
        
        def bTest1 = new TrulyImmutable001( firstString: "Hello3", secondString: "Goodbye", secondInt: 401, firstInt: 21  )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        // println "bTest1.firstString: ${bTest1.firstString}, bTest1.secondString: ${bTest1.secondString}"
        bTest1.secondString = "ChumbaWumba"
        println "Still in test ${name.methodName}, bTest1: ${bTest1.toString()}"
        expect:
        bTest1.firstString == "Hello3"
        bTest1.secondInt == null
        
    } // end "test the no arg constructor again"
    */
} // FirstImmutableSample 


