package info.shelfunit.properties.sample.finalprops

import validation.FinalProcessor

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class BoxSpec extends Specification {
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        FinalProcessor.process( Box )
    } // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "first test"() {
        println "Hello"
        // def box = new Box
    }
} // BoxSpec

