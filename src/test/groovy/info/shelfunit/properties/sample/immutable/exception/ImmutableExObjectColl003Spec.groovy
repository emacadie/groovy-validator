package info.shelfunit.properties.sample.immutable.exception

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class ImmutableExObjectColl003Test extends Specification { 
    
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {
        
    }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test without validation"() {
        println "\n--- Starting test ${name.methodName}"
        given:
        def throwaway = new ImmutableExObjectColl003( firstString: "Not Junk", firstInt: 21 )
        println "In test ${name.methodName}, throwaway: ${throwaway.toString()}"
        println "Just made throwaway, about to make bTest1"
        def bTest1 = new ImmutableExObjectColl003( firstString: "Hello1", firstInt: 200 )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        expect:
        bTest1.firstString == "Hello1"
        bTest1.firstInt == 200
        
    } // end "test without validation"
    
    def "test try to change something"() {
        println "\n--- Starting test ${name.methodName}"
        
        def throwaway = new ImmutableExObjectColl003( firstString: "Not Junk", firstInt: 21 )
        println "In test ${name.methodName}, throwaway: ${throwaway.toString()}"
        println "Just made throwaway, about to make bTest1"
        def bTest1 = new ImmutableExObjectColl003( firstString: "Hello1", firstInt: 200 )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        try {
            bTest1.firstString = "Hello again"
        } catch ( Exception e ) {
            println "Got exception: ${e.class.name}"
        }
        expect:
        bTest1.firstString == "Hello1"
        bTest1.firstInt == 200
        
    } // end "test try to change something"
    
    def "test below the ranges without boolean"() {
        println "\n--- Starting test ${name.methodName}"
        
        def bTest1 = new ImmutableExObjectColl003( firstString: "HH", secondString: "No min length", firstDouble: 5d, firstFloat: 5f, firstInt: 5, firstLong: 5L )
        println "In test ${name.methodName} bTest1: ${bTest1.toString()}"
        
        expect:
        bTest1.firstString == "HH"
        bTest1.firstInt == 5
        
    } // end "test below the ranges without boolean"
    
    def "test below the ranges with validation false and throw exception true"() {
        println "\n--- Starting test ${name.methodName}"
        boolean valid = true
        
        when:
        def bTest1 = new ImmutableExObjectColl003( [ firstString: "HH", secondString: "No min length", firstDouble: 5d, firstFloat: 5f, firstInt: 5, firstLong: 5L ], false, true )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        
        then:
        bTest1.firstString == "HH"
        bTest1.secondString == "No min length"
        bTest1.firstDouble == 5d
        bTest1.firstFloat == 5f
        bTest1.firstInt == 5
        bTest1.firstLong == 5L
        
    } // end "test below the ranges with boolean"
    
    def "test within the ranges with boolean"() {
        println "\n--- Starting test ${name.methodName}"
        boolean valid = true
        
        def bTest1 = new ImmutableExObjectColl003( [ firstString: "Hello You", secondString: "No min length", firstDouble: 50d, firstFloat: 50f, firstInt: 50, firstLong: 50L ], false, true )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        
        expect:
        bTest1.firstString == "Hello You"
        bTest1.secondString == "No min length"
        bTest1.firstInt == 50
        bTest1.firstDouble == 50d
        bTest1.firstFloat == 50f
        bTest1.firstLong == 50L
        
    } // end "test within the ranges with boolean"
    
    def "test beyond the ranges with validation set to false and throwException set to true"() {
        println "\n--- Starting test ${name.methodName}"
        boolean valid = true
        
        when:
        def bTest1 = new ImmutableExObjectColl003( [ firstString: "e" * 11, secondString: "N" * 16, firstDouble: 101d, firstFloat: 101f, firstInt: 101, firstLong: 101L ], false, true )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        
        then:
        bTest1.firstString == "eeeeeeeeeee"
        bTest1.secondString == "NNNNNNNNNNNNNNNN"
        bTest1.firstInt == 101
        bTest1.firstDouble == 101d
        bTest1.firstFloat == 101f
        bTest1.firstLong == 101L
        
    } // end "test beyond the ranges with boolean"
    
} // end class ImmutableExObjectColl003Test

