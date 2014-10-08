package info.shelfunit.properties.sample.divisor

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
    
    def "test with zero divisor"() {
        println "--- Starting test ${name.methodName}"
        
        def rid = new LongDivisor(  )
        when:
        rid.longWithDivArray = 12L
        rid.longWithZeroDiv = 35L
        println "rid: ${rid.toString()}"
        then:
        // def ex = thrown( Exception )
        // println "ex.message: ${ex.message}"
        rid.longWithDivArray == 12L
        rid.longWithZeroDiv == 35L
        
        when:
        rid.longWithDivArray = 13L
        then:
        rid.longWithDivArray == 12L
        rid.longWithZeroDiv == 35L
        
        when:
        rid.longWithZeroDiv == 55L
        rid.longWithDivArray = 9L
        then:
        rid.longWithZeroDiv == 35L
        rid.longWithDivArray == 9L
        
        def rid2 = new LongDivisor(  )
        when:
        rid2.longWithZeroDiv == 55L
        rid2.longWithDivArray = 16L
        then:
        rid2.longWithZeroDiv == 0L
        rid2.longWithDivArray == 16L
        
    } // end test with zero divisor
    
} // LongDivisorSpec

