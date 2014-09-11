package info.shelfunit.properties.sample.exception

// import info.shelfunit.properties.annotations.GroovyValidatorException
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
        final Exception exception = thrown()
        exception.message == "Groovy validation exception: \n" +
        "-2 is a long outside the range 0 to 1000 or it is not divisible by anything in the set [1] "
        println "Here is the exception message: ${exception.message}" 
        lr.firstNum  == 50L
        lr.secondNum == 50L
        lr.thirdNum  == 50L
        
        when:
        lr.thirdNum  = 5L
        lr.firstNum  = 1001L
        lr.secondNum = 1001L
        then:
        final Exception exception2 = thrown()
        println "Here is the exception2 message: ${exception2.message}" 
        exception2.message == "Groovy validation exception: \n" +
        "5 is a long outside the range 10 to 9223372036854775807 or it is not divisible by anything in the set [1] "
        println "lr.firstNum: ${lr.firstNum}, lr.secondNum: ${lr.secondNum}, lr.thirdNum: ${lr.thirdNum}"
        lr.firstNum  == 50L
        lr.secondNum == 50L
        lr.thirdNum  == 50L
        
        when:
        lr.thirdNum  = 1001L
        lr.firstNum  = 1001L
        lr.secondNum = 1001L
        then:
        final Exception exception3 = thrown()
        println "Here is the exception3 message: ${exception3.message}" 
        exception3.message == "Groovy validation exception: \n" +
        "1001 is a long outside the range 0 to 1000 or it is not divisible by anything in the set [1] "
        println "lr.firstNum: ${lr.firstNum}, lr.secondNum: ${lr.secondNum}, lr.thirdNum: ${lr.thirdNum}"
        lr.firstNum  == 50L
        lr.secondNum == 50L
        lr.thirdNum  == 1001L

    } // end "test the no arg constructor"

}

