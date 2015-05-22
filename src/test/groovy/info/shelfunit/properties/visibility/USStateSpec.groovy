package info.shelfunit.properties.visibility

import spock.lang.Specification

import org.junit.Rule
import org.junit.rules.TestName

class USStateSpec extends Specification {
    
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test with fields"() {
        
        println "--- Starting test ${name.methodName}"
        def il = new USState( 'Illinois', 'Kaskaskia', 'IL' )  // argName: 'Illinois', argCapCity: 'Kaskaskia' )
        println "il: ${il.toString()}"
        when:
            il.name = "Indiana"
            il.abbrev = 'IN'
        then:
            il.name == "Illinois"
            il.capitalCity == "Kaskaskia"
            il.abbrev == 'IL'
        
        when:
            il.capitalCity = "Vandalia"
            il.name = "LincolnLand"
            il.abbrev = "WI"
        then:
            il.capitalCity == "Vandalia"
            il.name == "Illinois"
            il.abbrev == "IL"
            println "il at the end: ${il.toString()}"
    }
    
    
    def "test with setters"() {
        
        println "--- Starting test ${name.methodName}"
        def il = new USState( 'Illinois', 'Kaskaskia', 'IL' )  // argName: 'Illinois', argCapCity: 'Kaskaskia' )
        println "il: ${il.toString()}"
        when:
            il.setName( "Indiana" )
            il.setAbbrev( "IN" )
        then:
            il.getName() == "Illinois"
            il.getAbbrev() == "IL"
        when:
            il.setCapitalCity( "Vandalia" )
            il.setName( "LincolnLand" )
            il.setAbbrev( 'WI' )
        then:
            il.capitalCity == "Vandalia"
            il.name == "Illinois"
            il.getAbbrev() == 'IL'
            println "il at the end: ${il.toString()}"
    }
}

