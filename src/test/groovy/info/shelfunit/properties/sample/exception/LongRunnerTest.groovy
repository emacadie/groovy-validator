package info.shelfunit.properties.sample.exception

import spock.lang.Specification

class LongRunnerTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    def "test the no arg constructor"() {
        def lr = new LongRunner()
        when:
        lr.firstNum  = 50L
        lr.secondNum = 50L
        lr.thirdNum  = 50L
        then:
        lr.firstNum  == 50L
        lr.secondNum == 50L
        lr.thirdNum  == 50L
        
        when:
        lr.firstNum  = -2L
        lr.secondNum = -2L
        lr.thirdNum  = 9L
        then:
        lr.firstNum  == 50L
        lr.secondNum == 50L
        lr.thirdNum  == 50L
        
        when:
        lr.firstNum  = 1001L
        lr.secondNum = 1001L
        lr.thirdNum  = 1001L
        then:
        lr.firstNum  == 50L
        lr.secondNum == 50L
        lr.thirdNum  == 1001L

    } // end "test the no arg constructor"

}

