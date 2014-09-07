package info.shelfunit.properties.sample

import spock.lang.Specification

class BookTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    def "test the no arg constructor"() {
        def bTest1 = new Book()
        bTest1.pages = 100
       
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
        def bTest1 = new Book( pages: 100, title: "abcdefg", year: 1979 )
        bTest1.pages = 100
       
        expect:
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
        bTest1.toString() == "info.shelfunit.properties.sample.Book(pages:100, title:abcdefg, year:1979)"
    }

}

