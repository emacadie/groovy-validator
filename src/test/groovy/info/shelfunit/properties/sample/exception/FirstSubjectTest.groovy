package info.shelfunit.properties.sample.exception

import spock.lang.Specification

class FirstSubjectTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    def "test the no arg constructor"() {
        def fs001 = new FirstSubject()
        when:
        fs001.firstNum = 100
        then:
        fs001.firstNum == 100
        
        when:
        fs001.firstNum = 10
        then:
        thrown( Exception )
        fs001.firstNum == 100
        
        when:
        fs001.firstNum = 10000
        then:
        thrown( Exception )
        fs001.firstNum == 100
    }
    
    def "testing with map constructor"() {
        when:
        def fsWithMap = new FirstSubject(firstNum: 40)
        println "fsWithMap.firstNum: ${fsWithMap.firstNum}"
        then:
        fsWithMap.firstNum == 40
        
        when:
        def fsWithMap2 = new FirstSubject(firstNum: 20)
        // println "fsWithMap2.firstNum: ${fsWithMap2.firstNum}"
        then:
        thrown( Exception )
        // fsWithMap2.firstNum == null
    }
}

