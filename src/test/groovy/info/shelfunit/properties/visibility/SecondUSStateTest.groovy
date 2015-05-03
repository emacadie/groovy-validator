package info.shelfunit.properties.visibility

import spock.lang.Specification

import org.junit.Rule
import org.junit.rules.TestName

class SecondUSStateTest extends Specification {
    
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test with properties"() {
        
        println "--- Starting test ${name.methodName}"
        def il = new SecondUSState( 'Illinois', 'Kaskaskia' ) 
        println "il: ${il.toString()}"
        when:
        il.name = "Indiana"
        then:
        il.name == "Illinois"
        
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
        def il = new SecondUSState( 'Illinois', 'Kaskaskia' ) 
        println "il: ${il.toString()}"
        when:
        il.setName( "Indiana" )
        then:
        il.name == "Illinois"
        il.getName( ) == "Illinois"
        
        when:
        il.setCapitalCity( "Vandalia" )
        il.setName( "LincolnLand" )
        then:
        il.capitalCity == "Vandalia"
        il.name == "Illinois"
        println "il at the end: ${il.toString()}"
    }
    
}

