package info.shelfunit.properties.nonmutable.collector

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class TrulyImmutableCollectorEx001Test extends Specification {
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {
    }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
   
    def "two immutable objects with validation, trying to change the first"() {
        println "\n\n--- Starting test ${name.methodName}"
        println "About to make throwaway"
        when:
        def throwaway = new TrulyImmutableCollector001( [ firstString: "Not Junk", secondString: "Goodbye Junk", firstInt: 21, secondInt: 20 ], true, true )
        boolean exceptionThrown = false
        try {
            throwaway.firstString = "Throwaway"
        } catch ( Exception e ) {
            exceptionThrown = true
        }
        then:
        def ex1 = thrown( Exception )
        ex1.message == "Groovy validation exception: \n" +
        "21 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] \n" +
        "20 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] "
        exceptionThrown == false
        println "Just made throwaway, about to make bTest1"
        
        when:
        def bTest1 = new TrulyImmutableCollector001( [ firstString: "Hello1", secondString: "Goodbye", firstInt: 21, secondInt: 200 ], true, true )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        then:
        def ex2 = thrown ( Exception )
        ex2.message == "Groovy validation exception: \n" +
        "21 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] "
        
    } // end "two immutable objects with validation, trying to change the first"
    
    def "test bTest2"() {
        println "\n\n--- Starting test ${name.methodName}"
        when:
        def bTest2 = new TrulyImmutableCollector001( [ firstString: "Hello2", secondString: "Goodbye, this is more than 20 characters", firstInt: 22, secondInt: 20 ], true, true )
        println "In test ${name.methodName}, bTest2: ${bTest2.toString()}"
        then:
        def exTest2 = thrown( Exception )
        exTest2.message == "Groovy validation exception: \n" +
        "\"Goodbye, this is more than 20 characters\" is a String with a length outside the range of 5 to 20 characters \n" +
        "22 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] \n" +
        "20 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] "
    } // end "test bTest2"
    
    def "third test"() {
        println "\n\n--- Starting test ${name.methodName}"
        when:
        boolean exceptionThrown = false
        def bTest1 = new TrulyImmutableCollector001( [ firstString: "Hello3", secondString: "Goodbye", secondInt: 401, firstInt: 21 ], true, true )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        
        try {
            bTest1.secondString = "ChumbaWumba"
        } catch ( Exception e ) {
            exceptionThrown = true
        }
        println "Still in test ${name.methodName}, bTest1: ${bTest1.toString()}"
        then:
        def exThirdTest = thrown( Exception )
        exThirdTest.message == "Groovy validation exception: \n" +
        "21 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] \n" +
        "401 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] "
        exceptionThrown == false
    } // end "third test
   
} // TrulyImmutableCollectorEx001Test 


