package info.shelfunit.properties.sample.exception

import spock.lang.Specification

class IntegerRunnerTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    /*
    def "test the no arg constructor"() {
        def dr = new IntegerRunner()
        when:
        dr.firstNum  = 50
        dr.secondNum = 50
        dr.thirdNum  = 50
        then:
        dr.firstNum  == 50
        dr.secondNum == 50
        dr.thirdNum  == 50
        
        when:
        dr.firstNum  = -2
        dr.secondNum = -2
        dr.thirdNum  = 9
        then:
        dr.firstNum  == 50
        dr.secondNum == 50
        dr.thirdNum  == 50
        
        when:
        dr.firstNum  = 1001
        dr.secondNum = 1001
        dr.thirdNum  = 1001
        then:
        dr.firstNum  == 50
        dr.secondNum == 50
        dr.thirdNum  == 1001

    } // end "test the no arg constructor"
    */
    /*
    def "test just outside the ranges"() {
        def dr = new IntegerRunner()
        when:
        dr.firstNum  = 50
        dr.secondNum = 50
        dr.thirdNum  = 50
        then:
        dr.firstNum  == 50
        dr.secondNum == 50
        dr.thirdNum  == 50
        
        when:
        dr.firstNum  = -0.1
        dr.secondNum = -0.1
        dr.thirdNum  = 9.99
        then:
        dr.firstNum  == 50
        dr.secondNum == 50
        dr.thirdNum  == 50
        
        when:
        dr.firstNum  = 1000.1
        dr.secondNum = 1000.1
        dr.thirdNum  = 1001d
        then:
        dr.firstNum  == 50
        dr.secondNum == 50
        dr.thirdNum  == 1001

    } // end "test just outside the ranges"
    */
}

