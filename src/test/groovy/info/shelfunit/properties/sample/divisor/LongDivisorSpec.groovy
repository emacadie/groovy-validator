package info.shelfunit.properties.sample.divisor

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName
import info.shelfunit.properties.annotations.AnnotationProcessor

class LongDivisorSpec extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        AnnotationProcessor.process( LongDivisor )
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test both long fields"() {
        println "--- Starting test ${name.methodName}"
        
        def rid = new LongDivisor(  )
        when:
        rid.longWithDiv = 15L
        rid.longWithDiv002 = 14L
        
        then:
        rid.longWithDiv == 15L
        rid.longWithDiv002 == 14L
        
        when:
        rid.longWithDiv = 5L
        rid.longWithDiv002 = 13L
        
        then:
        rid.longWithDiv == 15L
        rid.longWithDiv002 == 14L
        
    } // end "test both long fields"
    
    def "test with divisor array"() {
        println "--- Starting test ${name.methodName}"
        
        def rid = new LongDivisor(  )
        when:
        rid.longWithDivArray = 12L
        then:
        rid.longWithDivArray == 12L
        
        when:
        rid.longWithDivArray = 13L
        then:
        rid.longWithDivArray == 12L
        
        when:
        rid.longWithDivArray = 9L
        then:
        rid.longWithDivArray == 9L
        
        when:
        rid.longWithDivArray = 16L
        then:
        rid.longWithDivArray == 16L
        
        when:
        rid.longWithDivArray = 55L
        then:
        rid.longWithDivArray == 16L
    } // end test with divisor array
    
} // LongDivisorSpec

