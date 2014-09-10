package info.shelfunit.properties.nonmutable.exception

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class TrulyImmutableEx001Test extends Specification {
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {
    }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    // for some reason the first time you call a class it does not actually process the annotations
    // comment out the lines for the "junk" object and compare
   
    def "two immutable objects with validation, trying to change the first"() {
        println "\n\n--- Starting test ${name.methodName}"
        // println "About to make junk"
        // def junk = new TrulyImmutable001()
        println "About to make throwaway"
        when:
        def throwaway = new TrulyImmutableEx001( [ firstString: "Not Junk", secondString: "Goodbye Junk", firstInt: 21, secondInt: 20 ], true, true )
        boolean exceptionThrown = false
        /*
        try {
            throwaway.firstString = "Throwaway"
        } catch ( Exception e ) {
            exceptionThrown = true
        }
        */
        then:
        def ex = thrown( Exception )
        ex.message == "Groovy validation exception: \n" +
        "21 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] \n" +
        "20 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] "
        // throwaway.firstString == "Not Junk"
        // throwaway.firstInt == 0
        // throwaway.secondInt == 0
        // exceptionThrown == true
        println "Just made throwaway, about to make bTest1"
        
        when:
        def bTest1 = new TrulyImmutableEx001( [ firstString: "Hello1", secondString: "Goodbye", firstInt: 21, secondInt: 200 ], true, true )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        then:
        def ex2 = thrown( Exception )
        ex2.message == "Groovy validation exception: \n" +
        "21 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] "
        // bTest1.firstString == "Hello1"
        // bTest1.secondInt == 200
        // bTest1.firstInt == 0
        
    } // end "test the no arg constructor"
    
    def "test bTest2"() {
        println "\n\n--- Starting test ${name.methodName}"
        when:
        def bTest2 = new TrulyImmutableEx001( [ firstString: "Hello2", secondString: "Goodbye, this is more than 20 characters", firstInt: 22, secondInt: 20 ], true, true )
        println "In test ${name.methodName}, bTest2: ${bTest2.toString()}"
        then:
        def ex = thrown( Exception )
        ex.message == "Groovy validation exception: \n" +
            "\"Goodbye, this is more than 20 characters\" is a String with a length outside the range of 5 to 20 characters \n" +
            "22 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] \n" +
            "20 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] "
        // bTest2.firstString == "Hello2"
        // bTest2.secondString == null
        // bTest2.firstInt == 0
        // bTest2.secondInt == 0
    } // end "test bTest2"
    
    def "third test"() {
        println "\n\n--- Starting test ${name.methodName}"
        when:
        def testString = "hello, this is a test"
        // boolean exceptionThrown = false
        def bTest1 = new TrulyImmutableEx001( [ firstString: "Hello3", secondString: "Goodbye", secondInt: 401, firstInt: 21 ], true, true )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        // println "bTest1.firstString: ${bTest1.firstString}, bTest1.secondString: ${bTest1.secondString}"
        /*
        try {
            bTest1.secondString = "ChumbaWumba"
        } catch ( Exception e ) {
            exceptionThrown = true
        }
        */
        println "Still in test ${name.methodName}, bTest1: ${bTest1.toString()}"
        then:
        def exThirdTest = thrown( Exception )
        exThirdTest.message == "Groovy validation exception: \n" +
        "21 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] \n" +
        "401 is an integer outside the range 30 to 400 or it is not divisible by anything in the set [1] "
        testString == "hello, this is a test"
        testString.length() == 21
        println "Here is testString.length(): ${testString.length()}"
        // bTest1.firstString == "Hello3"
        // bTest1.secondString == "Goodbye"
        // bTest1.firstInt == 0
        // bTest1.secondInt == 0
        // exceptionThrown == true
    } // end "test the no arg constructor again"
   
} // TrulyImmutableEx001Test 


