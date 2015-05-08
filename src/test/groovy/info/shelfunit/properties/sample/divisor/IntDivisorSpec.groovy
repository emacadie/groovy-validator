package info.shelfunit.properties.sample.divisor

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class IntDivisorSpec extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test both int fields"() {
        println "--- Starting test ${name.methodName}"
        
        def rid = new IntDivisor(  )
        when:
        rid.intWithDiv = 15
        rid.intWithDiv002 = 14
        println "rid.toString(): ${rid.toString()}"
        then:
        rid.intWithDiv == 15
        rid.intWithDiv002 == 14
        
        when:
        rid.intWithDiv = 5
        rid.intWithDiv002 = 13
        println "rid.toString(): ${rid.toString()}"
        then:
        rid.intWithDiv == 15
        rid.intWithDiv002 == 14
        
    } // end "test both int fields"
    
    def "test with divisor array"() {
        println "--- Starting test ${name.methodName}"
        
        def rid = new IntDivisor(  )
        when:
        rid.intWithDivArray = 12
        then:
        rid.intWithDivArray == 12
        
        when:
        rid.intWithDivArray = 13
        then:
        rid.intWithDivArray == 12
        
        when:
        rid.intWithDivArray = 9
        then:
        rid.intWithDivArray == 9
        
        when:
        rid.intWithDivArray = 16
        then:
        rid.intWithDivArray == 16
        
        when:
        rid.intWithDivArray = 55
        then:
        rid.intWithDivArray == 16
    } // end test with divisor array
    
    def "test with zero divisor"() {
        println "--- Starting test ${name.methodName}"
        
        def rid = new IntDivisor(  )
        when:
        rid.intWithDivArray = 12
        rid.intWithZeroDiv = 35
        println "rid: ${rid.toString()}"
        then:
        // def ex = thrown( Exception )
        // println "ex.message: ${ex.message}"
        rid.intWithDivArray == 12
        rid.intWithZeroDiv == 35
        
        when:
        rid.intWithDivArray = 13
        then:
        rid.intWithDivArray == 12
        rid.intWithZeroDiv == 35
        
        when:
        rid.intWithZeroDiv == 55
        rid.intWithDivArray = 9
        then:
        rid.intWithZeroDiv == 35
        rid.intWithDivArray == 9
        
        def rid2 = new IntDivisor(  )
        when:
        rid2.intWithZeroDiv == 55
        rid2.intWithDivArray = 16
        then:
        rid2.intWithZeroDiv == 0
        rid2.intWithDivArray == 16
        
    } // end test with zero divisor
    
} // IntDivisorSpec

