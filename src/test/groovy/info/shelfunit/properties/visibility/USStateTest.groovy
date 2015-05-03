package info.shelfunit.properties.visibility

import visibility.VisibilityProcessor

import spock.lang.Specification

import org.junit.Rule
import org.junit.rules.TestName

class USStateTest extends Specification {
    
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        // VisibilityProcessor.process( USState )
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test with fields"() {
        
        println "--- Starting test ${name.methodName}"
        def il = new USState( 'Illinois', 'Kaskaskia' )  // argName: 'Illinois', argCapCity: 'Kaskaskia' )
        println "il: ${il.toString()}"
        when:
        il.name = "Indiana"
        then:
        il.name == "Illinois"
        il.capitalCity == "Kaskaskia"
        
        when:
        il.capitalCity = "Vandalia"
        il.name = "LincolnLand"
        then:
        il.capitalCity == "Vandalia"
        il.name == "Illinois"
        println "il at the end: ${il.toString()}"
    }
    
    
    def "test with setters"() {
        
        println "--- Starting test ${name.methodName}"
        def il = new USState( 'Illinois', 'Kaskaskia' )  // argName: 'Illinois', argCapCity: 'Kaskaskia' )
        println "il: ${il.toString()}"
        when:
        il.setName( "Indiana" )
        then:
        il.name == "Illinois"
        
        when:
        il.setCapitalCity( "Vandalia" )
        il.setName( "LincolnLand" )
        then:
        il.capitalCity == "Vandalia"
        il.name == "Illinois"
        println "il at the end: ${il.toString()}"
    }
}

