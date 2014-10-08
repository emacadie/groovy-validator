package info.shelfunit.properties.sample.exception

import spock.lang.Specification

class DoubleExRunnerTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    def "test the no arg constructor"() {
        def dr = new DoubleExRunner()
        when:
        dr.firstNum  = 50d
        dr.secondNum = 50d
        dr.thirdNum  = 50d
        then:
        dr.firstNum  == 50d
        dr.secondNum == 50d
        dr.thirdNum  == 50d
        
        when:
        dr.firstNum  = -2d
        dr.secondNum = -2d
        dr.thirdNum  = 9d
        then:
        dr.firstNum  == 50d
        dr.secondNum == 50d
        dr.thirdNum  == 50d
        thrown( Exception )
        
        when:
        dr.firstNum  = 1001d
        dr.secondNum = 1001d
        dr.thirdNum  = 1001d
        then:
        thrown( Exception )
        dr.firstNum  == 50d
        dr.secondNum == 50d
        dr.thirdNum  == 50d
        

    } // end "test the no arg constructor"
    
    def "test just outside the ranges"() {
        def dr = new DoubleExRunner()
        when:
        dr.firstNum  = 50d
        dr.secondNum = 50d
        dr.thirdNum  = 50d
        then:
        dr.firstNum  == 50d
        dr.secondNum == 50d
        dr.thirdNum  == 50d
        
        when:
        dr.firstNum  = -0.1d
        dr.secondNum = -0.1d
        dr.thirdNum  = 9.99d
        then:
        thrown( Exception )
        dr.firstNum  == 50d
        dr.secondNum == 50d
        dr.thirdNum  == 50d
        
        when:
        dr.firstNum  = 1000.1d
        dr.secondNum = 1000.1d
        dr.thirdNum  = 1001d
        then:
        dr.firstNum  == 50d
        dr.secondNum == 50d
        dr.thirdNum  == 50d
        thrown( Exception )

    } // end "test just outside the ranges"

}

