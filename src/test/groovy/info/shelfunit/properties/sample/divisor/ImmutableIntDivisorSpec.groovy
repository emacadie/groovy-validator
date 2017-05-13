package info.shelfunit.properties.sample.divisor

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class ImmutableIntDivisorSpec extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test both int fields"() {
        println "--- Starting test ${name.methodName}"
        
        when:
            def rid = ImmutableIntDivisor.createValidatedObject( [ intWithDiv: 15, intWithDiv002: 14 ], true )
        then:
            rid.intWithDiv == 15
            rid.intWithDiv002 == 14
            println "rid.toString(): ${rid.toString()}"
        
        when:
            def rid2 = ImmutableIntDivisor.createValidatedObject( [ intWithDiv: 5, intWithDiv002: 13 ], true )
            println "rid2.toString(): ${rid2.toString()}"
        then:
            def ex = thrown( Exception )
            ex.message == "Groovy validation exception:\n" +
            "5 is a java.lang.Integer outside the range 10 to 2147483647 or it is not divisible by anything in the set [5]\n" +
            "13 is a java.lang.Integer outside the range 0 to 2147483647 or it is not divisible by anything in the set [7]"
        
    } // end "test both int fields"
    
    
    def "test with divisor array"() {
        println "--- Starting test ${name.methodName}"
        when:
            def rid = ImmutableIntDivisor.createValidatedObject( [ intWithDivArray: 12 ], true )
        then:
            rid.intWithDivArray == 12
        
        when:
            rid.intWithDiv = 15
        then:
            def ex = thrown( Exception )
            ex.message == "Cannot set readonly property: intWithDiv for class: info.shelfunit.properties.sample.divisor.ImmutableIntDivisor"
        
        when:
            def rid2 = ImmutableIntDivisor.createValidatedObject( [ intWithDivArray: 13 ], true )
        then:
            def ex2 = thrown( Exception )
            ex2.message == "Groovy validation exception:\n" +
            "13 is a java.lang.Integer outside the range 0 to 40 or it is not divisible by anything in the set [3, 4]"
        
        when:
            def rid3 = ImmutableIntDivisor.createValidatedObject( [ intWithDivArray: 9 ], true )
        then:
            rid3.intWithDivArray == 9
       
        when:
            def rid4 = ImmutableIntDivisor.createValidatedObject( [ intWithDivArray: 16 ], true )
        then:
            rid4.intWithDivArray == 16
        
        when:
            def rid5 = ImmutableIntDivisor.createValidatedObject( [ intWithDivArray: 55 ], true )
        then:
            def ex5 = thrown( Exception )
            ex5.message == "Groovy validation exception:\n" +
            "55 is a java.lang.Integer outside the range 0 to 40 or it is not divisible by anything in the set [3, 4]"
        
    } // end test with divisor array
    
    
    def "test with zero divisor"() {
        println "--- Starting test ${name.methodName}"
        when:
            def rid = ImmutableIntDivisor.createValidatedObject( [ intWithDivArray: 12, intWithZeroDiv: 35 ], true )
            // println "rid: ${rid.toString()}"
        then:
            rid.intWithDivArray == 12
            rid.intWithZeroDiv == 35
        
        when:
            def rid2 = ImmutableIntDivisor.createValidatedObject( [ intWithDivArray: 9, intWithZeroDiv: 55 ], true )
        then:
            def ex = thrown( Exception )
            ex.message == "Groovy validation exception:\n" +
            "55 is a java.lang.Integer outside the range 0 to 40 or it is not divisible by anything in the set [1]"

    } // end test with zero divisor
   
} // ImmutableIntDivisorSpec

