package info.shelfunit.properties.sample.exception

import spock.lang.Specification

import validation.AnnotationProcessor

import org.junit.Rule
import org.junit.rules.TestName

class SecondBookSequelTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        // AnnotationProcessor.process( SecondBookSequel, true )
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test the no arg constructor"() {
        
        println "--- Starting test ${name.methodName}"
        def bTest1 = new SecondBookSequel( pages: 100, title: "Some Book", year: 1990 )
        def methodList = SecondBookSequel.getMethods()

        println "bTest1: ${bTest1.toString()}"
        when:
        bTest1.title = "abcdefg"
        then:
        bTest1.title == "abcdefg"
       
        when:
        bTest1.title = "qw" 
        then:
        thrown( Exception )
        bTest1.title == "abcdefg"
        
        when:
        bTest1.title = "qwertyuiopasdfghjklzxcvbnm"
        then:
        bTest1.title == "abcdefg"
        bTest1.pages == 100
        thrown( Exception )
    }
    
    // having problems getting this one to work - come back to it later
    /*
    def "test the map constructor"() {
        println "--- Starting test ${name.methodName}"
        
        try {
        def bTest1 = new SecondBookSequel( pages: 100, title: "S", year: 1990 )
        } catch ( Exception ex ) {
        }
        // bTest1.title == null
        
        println "bTest1: ${bTest1.toString()}"
        
       
        // expect:
        
       
        when:
        bTest1.title = "qwertyuiopasdfghjklzxcvbnm"
        then:
        thrown( Exception )
        bTest1.title == null
        
        when:
        bTest1.title = "abcdefg"
        then:
        bTest1.title == "abcdefg"
        bTest1.pages == 100
        bTest1.year == 1990
        
        println "bTest1: ${bTest1.toString()}"
    }
    */
}

