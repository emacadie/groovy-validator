package info.shelfunit.properties.sample.immutable

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class ImmutableRegExSpec extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    /*
    def "test date regex"() {
        println "--- Starting test ${name.methodName}"
        
        when:
        def res = new ImmutableRegEx( [ yearWithDay: "2012-04-12", groovyString: "I like groovy", password: "p4ssw0rd" ], true )
        then:
        res.yearWithDay == "2012-04-12"
      
        println "res: ${res.toString()}"
        
    } // end "test date regex"
    */
    /*
    def "test groovy regex"() {
        println "--- Starting test ${name.methodName}"
       
        when:
        def res = new ImmutableRegEx( [ groovyString: "I like groovy" ] , true )
        then:
        res.groovyString == "I like groovy"
        println "res: ${res.toString()}"
        
    } // end "test groovy regex"
    */
    /*
    def "test password regex"() {
        println "--- Starting test ${name.methodName}"
        
        when:
        def res = new ImmutableRegEx( [ password: "p4ssw0rd" ] , true )
        // res.password = "p4ssw0rd"
        then:
        res.password == "p4ssw0rd"
        when:
        res.password = "p45sword"
        then:
        res.password == "p45sword" 
        when:
        res.password = "password"
        then:
        res.password == "p45sword"
        when:
        res.password = "p4ssword"
        then:
        res.password == "p45sword"
        
        when:
        res.password = "p45s"
        then:
        res.password == "p45sword"
        when:
        res.password = "p45swordp45sword"
        then:
        res.password == "p45sword"
        println "res: ${res.toString()}"
        
    } // end "test password regex"
    */
    
    // until I can get comments to work in reg ex, this test is commented out
    /*
    def "test passwordWithComment regex"() {
        println "--- Starting test ${name.methodName}"
        def res = new RegExSubject()
        when:
        res.passwordWithComment = "p4ssw0rd"
        then:
        res.passwordWithComment == "p4ssw0rd"
        when:
        res.passwordWithComment = "p45sword"
        then:
        res.passwordWithComment == "p45sword" 
        when:
        res.passwordWithComment = "password"
        then:
        res.passwordWithComment == "p45sword"
        when:
        res.passwordWithComment = "p4ssword"
        then:
        res.passwordWithComment == "p45sword"
        
        when:
        res.passwordWithComment = "p45s"
        then:
        res.passwordWithComment == "p45sword"
        when:
        res.passwordWithComment = "p45swordp45sword"
        then:
        res.passwordWithComment == "p45sword"
        println "res: ${res.toString()}"
        
    } // end "test passwordWithComment regex"
    */
    
} // ImmutableRegExSpec


