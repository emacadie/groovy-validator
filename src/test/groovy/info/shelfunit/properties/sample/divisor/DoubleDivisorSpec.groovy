package info.shelfunit.properties.sample.divisor

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName
import info.shelfunit.properties.annotations.AnnotationProcessor

class DoubleDivisorSpec extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        AnnotationProcessor.process( DoubleDivisor )
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test both int fields"() {
        println "--- Starting test ${name.methodName}"
        
        def rid = new DoubleDivisor(  )
        when:
        rid.dWithDiv = 15.0d
        rid.dWithDiv002 = 14.0d
        
        then:
        rid.dWithDiv == 15.0d
        rid.dWithDiv002 == 14.0d
        
        when:
        rid.dWithDiv = 5
        rid.dWithDiv002 = 13
        
        then:
        rid.dWithDiv == 15
        rid.dWithDiv002 == 14
        
    } // end "test both double fields"

    
} // DoubleDivisorSpec

