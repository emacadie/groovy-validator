package info.shelfunit.properties.sample.immutable

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
   
    def "test date regex"() {
        println "--- Starting test ${name.methodName}"
        
        when:
        def res = new ImmutableRegEx( [ yearWithDay: "2012-04-12", groovyString: "I like groovy", password: "p4ssw0rd" ], true )
        then:
        res.yearWithDay == "2012-04-12"
      
        println "res: ${res.toString()}"
        
    } // end "test date regex"
    
    def "test groovy regex"() {
        println "--- Starting test ${name.methodName}"
       
        when:
        def res = new ImmutableRegEx( [ groovyString: "I like groovy" ] , true )
        then:
        res.groovyString == "I like groovy"
        println "res: ${res.toString()}"
        
    } // end "test groovy regex"
    
    def "test password regex"() {
        println "--- Starting test ${name.methodName}"
        
        when:
        def res = new ImmutableRegEx( [ password: "p4ssw0rd" ] , true )
        // res.password = "p4ssw0rd"
        then:
        res.password == "p4ssw0rd"

        println "res: ${res.toString()}"
        
    } // end "test password regex"
    
    def "test regex failures"() {
        println "--- Starting test ${name.methodName}"
       
        when:
        def res = new ImmutableRegEx( [ groovyString: "I like grooooovy" ] , true, true )
        then:
        def ex1 = thrown( Exception )
        ex1.message == "Groovy validation exception: \n" +
        "\"I like grooooovy\" is a String with a length outside the range of 10 to 2147483647 characters or does not match the regular expression /^.*?[Gg]roovy.*\$/ \n" +
        "\"null\" is a String with a length outside the range of 0 to 2147483647 characters or does not match the regular expression /\\d{4}?-\\d\\d-\\d\\d/ \n" +
        "\"null\" is a String with a length outside the range of 6 to 10 characters or does not match the regular expression /^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}\$/ "
        
        when:
        def res2 = new ImmutableRegEx( [ yearWithDay: "2012-04-12_12.11.05", groovyString: "I like groovy", password: "p4ssw0rd" ] , true, true )
        then:
        def ex2 = thrown( Exception )
        ex2.message == "Groovy validation exception: \n" +
        "\"2012-04-12_12.11.05\" is a String with a length outside the range of 0 to 2147483647 characters or does not match the regular expression /\\d{4}?-\\d\\d-\\d\\d/ "
        
    } // end "test groovy regex"
    
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


