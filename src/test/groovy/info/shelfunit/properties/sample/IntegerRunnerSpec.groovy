package info.shelfunit.properties.sample

import spock.lang.Specification

class IntegerRunnerTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    def "test the no arg constructor"() {
        def dr = new IntegerRunner()
        when:
        dr.numAsDef = 50
        dr.numAsInt = 500
        then:
        dr.numAsDef == 50
        dr.numAsInt == 500
        
        when:
        dr.numAsDef = -2d
        dr.numAsInt = -2d
        then:
        dr.numAsDef == 50
        dr.numAsInt == 500
        
        when:
        dr.numAsDef = 1001
        dr.numAsInt = 1001
        then:
        dr.numAsDef == 50
        dr.numAsInt == 500

    } // end "test the no arg constructor"
    
    def "test just outside the ranges"() {
        def dr = new IntegerRunner()
        when:
        dr.numAsDef = 50
        dr.numAsInt = 500
        then:
        dr.numAsDef == 50
        dr.numAsInt == 500
        
        when:
        dr.numAsDef = -1
        dr.numAsInt = 99
        then:
        dr.numAsDef == 50
        dr.numAsInt == 500
        
        when:
        dr.numAsDef = 1001
        dr.numAsInt = 1001
        then:
        dr.numAsDef == 50
        dr.numAsInt == 500

    } // end "test just outside the ranges"

}

