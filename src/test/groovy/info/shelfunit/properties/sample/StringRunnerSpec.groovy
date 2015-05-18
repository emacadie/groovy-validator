package info.shelfunit.properties.sample

import spock.lang.Specification

class StringRunnerSpec extends Specification { 
    def setup() {}        // run before every feature method
    def cleanup() {}      // run after every feature method
    def setupSpec() {}    // run before the first feature method
    def cleanupSpec() {}  // run after the last feature method
    
    def "first test"() {
        def sr = new StringRunner()
        when:
            sr.stringAsDef = "heello"
            sr.stringAsString = "This is groovy"
        then:
            println "sr: ${sr.toString()}"
            sr.stringAsDef == "heello"
            sr.stringAsString == "This is groovy"
        
        when:
            sr.stringAsDef = "haallo"
        then:
            println "sr: ${sr.toString()}"
            sr.stringAsDef == "heello"
            sr.stringAsString == "This is groovy"
        
        when:
            sr.stringAsString = "this is something else"
        then:
            println "sr: ${sr.toString()}"
            sr.stringAsDef == "heello"
            sr.stringAsString == "This is groovy"
        
        when:
            sr.setStringAsString( "this is something else" )
            sr.setStringAsDef( "haallo" )
        then:
            println "sr: ${sr.toString()}"
            sr.stringAsDef == "heello"
            sr.stringAsString == "This is groovy"
    } // end "first test"

}

