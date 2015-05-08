package info.shelfunit.properties.sample

import spock.lang.Specification

import org.junit.Rule
import org.junit.rules.TestName

class BookSequelTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() { }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test the no arg constructor"() {
        
        println "--- Starting test ${name.methodName}"
        def bTest1 = new BookSequel( pages: 100, title: "Some Book", year: 1990 )
        println "bTest1: ${bTest1.toString()}"
        when:
        bTest1.title = "abcdefg"
        then:
        bTest1.title == "abcdefg"
       
        when:
        bTest1.title = "qw"
        then:
        bTest1.title == "abcdefg"
        
        when:
        bTest1.title = "qwertyuiopasdfghjklzxcvbnm"
        then:
        bTest1.title == "abcdefg"
        bTest1.pages == 100
    }
    
    def "test the map constructor"() {
        println "--- Starting test ${name.methodName}"
        
        def bTest1 = new BookSequel( pages: 100, title: "S", year: 1990 )
        
        println "bTest1: ${bTest1.toString()}"
        
        expect:
        bTest1.title == null
       
        when:
        bTest1.title = "qwertyuiopasdfghjklzxcvbnm"
        then:
        bTest1.title == null
        
        when:
        bTest1.title = "abcdefg"
        then:
        bTest1.title == "abcdefg"
        bTest1.pages == 100
        bTest1.year == 1990
        
        println "bTest1: ${bTest1.toString()}"
    }

}

