package info.shelfunit.properties.visibility

import spock.lang.Specification

import org.junit.Rule
import org.junit.rules.TestName

class AgeHolderSpec extends Specification {
    
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test with properties"() {
        
        println "--- Starting test ${name.methodName}"
        when:
            def ah = new AgeHolder( 50 ) 
        then:
            ah.perceivedAge == 50
        
        when:
            ah.perceivedAge = 40
        then:
            ah.perceivedAge == 50
            
        when:
            ah.visitInLaws()
        then:
            ah.perceivedAge == 51
            
        when:
            ah.visitYogaRetreat()
            ah.visitYogaRetreat()
        then:
            ah.perceivedAge == 49
    }
    
    def "test with setters"() {
        
        println "--- Starting test ${name.methodName}"
        when:
            def ah = new AgeHolder( 50 ) 
        then:
            ah.perceivedAge == 50
        
        when:
            ah.setPerceivedAge( 40 )
        then:
            ah.perceivedAge == 50
            
        when:
            ah.visitInLaws()
        then:
            ah.perceivedAge == 51
            
        when:
            ah.visitYogaRetreat()
            ah.visitYogaRetreat()
        then:
            ah.perceivedAge == 49
    }
    
}

