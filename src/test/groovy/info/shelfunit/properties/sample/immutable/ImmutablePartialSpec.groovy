package info.shelfunit.properties.sample.immutable

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class ImmutablePartialSpec extends Specification { 
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
        def res = new ImmutablePartial( [ stringWithAnn: "this is the string with ann", stringWithoutAnn: "Hello" ], true )
        then:
        res.stringWithAnn == "this is the string with ann"
        res.stringWithoutAnn == "Hello"

        println "res: ${res.toString()}"
        
    } // end "test date regex"
    
} // ImmutablePartialSpec


