package info.shelfunit.properties.sample.exception

import info.shelfunit.properties.annotations.GroovyValidatorException
import spock.lang.Specification

class LongExRunnerTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    def "test the no arg constructor"() {
        def lr = new LongExRunner()
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
        final GroovyValidatorException exception = thrown()
        exception.message == "-2 is a long outside the values 0 and 1000"
        println "Here is the exception message: ${exception.message}" 
        lr.firstNum  == 50L
        lr.secondNum == 50L
        lr.thirdNum  == 50L
        
        when:
        lr.thirdNum  = 5L
        lr.firstNum  = 1001L
        lr.secondNum = 1001L
        then:
        final GroovyValidatorException exception2 = thrown()
        println "Here is the exception2 message: ${exception2.message}" 
        exception2.message == "5 is a long outside the values 10 and 9223372036854775807"
        println "lr.firstNum: ${lr.firstNum}, lr.secondNum: ${lr.secondNum}, lr.thirdNum: ${lr.thirdNum}"
        lr.firstNum  == 50L
        lr.secondNum == 50L
        lr.thirdNum  == 50L
        
        when:
        lr.thirdNum  = 1001L
        lr.firstNum  = 1001L
        lr.secondNum = 1001L
        then:
        final GroovyValidatorException exception3 = thrown()
        println "Here is the exception3 message: ${exception3.message}" 
        exception3.message == "1001 is a long outside the values 0 and 1000"
        println "lr.firstNum: ${lr.firstNum}, lr.secondNum: ${lr.secondNum}, lr.thirdNum: ${lr.thirdNum}"
        lr.firstNum  == 50L
        lr.secondNum == 50L
        lr.thirdNum  == 1001L

    } // end "test the no arg constructor"

}

