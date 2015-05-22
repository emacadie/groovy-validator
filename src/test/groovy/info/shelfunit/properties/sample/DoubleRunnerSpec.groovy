package info.shelfunit.properties.sample

import spock.lang.Specification

class DoubleRunnerSpec extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    def "test the no arg constructor"() {
        def dr = new DoubleRunner()
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
        
        when:
            dr.firstNum  = 1001d
            dr.secondNum = 1001d
            dr.thirdNum  = 1001d
        then:
            dr.firstNum  == 50d
            dr.secondNum == 50d
            dr.thirdNum  == 1001d

    } // end "test the no arg constructor"
    
    def "test just outside the ranges"() {
        def dr = new DoubleRunner()
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
            dr.thirdNum  == 1001d

    } // end "test just outside the ranges"
    
    def "test some more"() {
        def fr = new DoubleRunner()
        when:
            fr.firstNum  = 50.3d
            fr.secondNum = 50d
            fr.thirdNum  = 'hello'
        then:
            fr.firstNum  == 50.3d
            fr.secondNum == 50d
            fr.thirdNum  == null
    }

}

