package info.shelfunit.properties.sample.divisor

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName
import info.shelfunit.properties.annotations.AnnotationProcessor

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
        def rid = new ImmutableIntDivisor( [ intWithDiv: 15, intWithDiv002: 14 ], true, true )
        then:
        rid.intWithDiv == 15
        rid.intWithDiv002 == 14
        println "rid.toString(): ${rid.toString()}"
        
        when:
        def rid2 = new ImmutableIntDivisor( [ intWithDiv: 5, intWithDiv002: 13 ], true, true )
        println "rid2.toString(): ${rid2.toString()}"
        then:
        def ex = thrown( Exception )
        ex.message == "Groovy validation exception: \n" +
        "5 is an integer outside the range 10 to 2147483647 or it is not divisible by anything in the set [5] \n" +
        "13 is an integer outside the range 0 to 2147483647 or it is not divisible by anything in the set [7] "
        // rid2.intWithDiv == 15
        // rid2.intWithDiv002 == 0
        
    } // end "test both int fields"
    
    /*
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
    */
} // ImmutableIntDivisorSpec

