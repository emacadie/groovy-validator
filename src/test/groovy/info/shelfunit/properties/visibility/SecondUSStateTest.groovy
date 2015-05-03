package info.shelfunit.properties.visibility

import visibility.VisibilityProcessor

import spock.lang.Specification

import org.junit.Rule
import org.junit.rules.TestName

class SecondUSStateTest extends Specification {
    
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        // VisibilityProcessor.process( USState )
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test with properties"() {
        
        println "--- Starting test ${name.methodName}"
        def il = new SecondUSState( 'Illinois', 'Kaskaskia', 'IL' ) 
        println "il: ${il.toString()}"
        when:
        il.name = "Indiana"
        il.abbrev = "IN"
        then:
        il.name == "Illinois"
        il.abbrev == "IL"
        
        when:
        il.capitalCity = "Vandalia"
        il.name = "LincolnLand"
        il.abbrev = "XX"
        then:
        il.capitalCity == "Vandalia"
        il.name == "Illinois"
        il.abbrev == "IL"
        println "il at the end: ${il.toString()}"
    }
    
    def "test with setters"() {
        
        println "--- Starting test ${name.methodName}"
        def il = new SecondUSState( 'Illinois', 'Kaskaskia', 'IL' ) 
        println "il: ${il.toString()}"
        when:
        il.setName( "Indiana" )
        then:
        il.name == "Illinois"
        
        when:
        il.setCapitalCity( "Vandalia" )
        il.setName( "LincolnLand" )
        il.setAbbrev( 'WI' )
        then:
        il.capitalCity == "Vandalia"
        il.name == "Illinois"
        il.abbrev == "IL"
        println "il at the end: ${il.toString()}"
    }
    
}

