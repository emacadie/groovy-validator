package info.shelfunit.properties.sample.divisor

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName
import info.shelfunit.properties.annotations.AnnotationProcessor

class ImmutableLongDivisorSpec extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test both long fields"() {
        println "--- Starting test ${name.methodName}"
        
        when:
        def rid = new ImmutableLongDivisor( [ longWithDiv: 15L, longWithDiv002: 14L ], true, true )
        then:
        rid.longWithDiv == 15L
        rid.longWithDiv002 == 14L
        println "rid.toString(): ${rid.toString()}"
        
        when:
        def rid2 = new ImmutableLongDivisor( [ longWithDiv: 5L, longWithDiv002: 13L ], true, true )
        println "rid2.toString(): ${rid2.toString()}"
        then:
        def ex = thrown( Exception )
        ex.message == "Groovy validation exception: \n" +
        "5 is a long outside the range 10 to 9223372036854775807 or it is not divisible by anything in the set [5] \n" +
        "13 is a long outside the range 0 to 9223372036854775807 or it is not divisible by anything in the set [7] "
        // rid2.longWithDiv == 15
        // rid2.longWithDiv002 == 0
        
    } // end "test both long fields"
    
    /*
    def "test with divisor array"() {
        println "--- Starting test ${name.methodName}"
        
        def rid = new LongDivisor(  )
        when:
        rid.longWithDivArray = 12
        then:
        rid.longWithDivArray == 12
        
        when:
        rid.longWithDivArray = 13
        then:
        rid.longWithDivArray == 12
        
        when:
        rid.longWithDivArray = 9
        then:
        rid.longWithDivArray == 9
        
        when:
        rid.longWithDivArray = 16
        then:
        rid.longWithDivArray == 16
        
        when:
        rid.longWithDivArray = 55
        then:
        rid.longWithDivArray == 16
    } // end test with divisor array
    */
} // ImmutableLongDivisorSpec

