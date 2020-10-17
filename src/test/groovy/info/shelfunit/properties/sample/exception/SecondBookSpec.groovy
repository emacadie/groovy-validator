package info.shelfunit.properties.sample.exception

import spock.lang.Specification

import org.junit.Rule
import org.junit.rules.TestName

class SecondBookSpec extends Specification {

    private String methodName;
    private String className = "SecondBookSpec.";
    @Rule
    TestName name = new TestName()
    def setup() {
        println "\n\n--- Starting test ${name.methodName}"

        // println "Here it is again: ${this.specificationContext.iterationInfo.name}"
    }         // run before every feature method
    def cleanup() {}       // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    def "test the no arg constructor"() {
        methodName = className + Thread.currentThread().getStackTrace()[ 1 ].getMethodName()
        println "At test test the no arg constructor"

        def bTest1 = new SecondBook()
        bTest1.pages = 100
        println( "Here is bTest1: " + bTest1.toString() )
       
        when:
            bTest1.title = "abcdefg"
        then:
            bTest1.title == "abcdefg"
       
        when:
            bTest1.title = "qw"
        println( "Here is bTest1 after trying to set title to qw: " + bTest1.toString() )
        then:
            thrown( Exception )
            bTest1.title == "abcdefg"
        
        when:
            bTest1.title = "qwertyuiopasdfghjklzxcvbnm"
        then:
            thrown( Exception )
            bTest1.title == "abcdefg"
            bTest1.pages == 100
        println( "Here is bTest1: " + bTest1.toString() )
    }

    def "test the no arg constructor with setters"() {

        methodName = className + Thread.currentThread().getStackTrace()[ 1 ].getMethodName()
        println "At test test the no arg constructor with setters"

        def bTest2 = new SecondBook()
        bTest2.pages = 100
        println( "Here is bTest2: " + bTest2.toString() )

        when:
            bTest2.setTitle( "abcdefg" )
        then:
            bTest2.title == "abcdefg"

        when:
            bTest2.setTitle( "qw" )
        then:
            thrown( Exception )
            bTest2.title == "abcdefg"

        when:
            bTest2.setTitle( "qwertyuiopasdfghjklzxcvbnm" )
        then:
            thrown( Exception )
            bTest2.title == "abcdefg"
            bTest2.pages == 100

        println( "Here is bTest2: " + bTest2.toString() )
    }
    
    def "test the map constructor"() {
        methodName = className + Thread.currentThread().getStackTrace()[ 2 ].getMethodName()
        println "At test test the map constructor" + methodName

        def bTest1 = new SecondBook( pages: 100, title: "abcdefg", year: 1979 )
        bTest1.pages = 100
        println( "Here is bTest1: " + bTest1.toString() )
        expect:
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

        println( "Here is bTest1: " + bTest1.toString() )
    }

}


