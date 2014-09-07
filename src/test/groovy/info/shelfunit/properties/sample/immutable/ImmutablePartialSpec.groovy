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
    
    def "test both string fields"() {
        println "--- Starting test ${name.methodName}"
        
        when:
        def res = new ImmutablePartial( [ stringWithAnn: "this is the string with ann", stringWithoutAnn: "Hello" ], true )
        then:
        res.stringWithAnn == "this is the string with ann"
        res.stringWithoutAnn == "Hello"
        res.toString() == "ImmutablePartial(stringWithAnn:this is the string with ann, stringWithoutAnn:Hello, intWithAnn:0, intWithoutAnn:0)"
        println "res: ${res.toString()}"
        
        when:
        def res2 = new ImmutablePartial( [ stringWithAnn: "this is the string with ann again" ], true )
        then:
        res2.stringWithAnn == "this is the string with ann again"
        res2.toString() == "ImmutablePartial(stringWithAnn:this is the string with ann again, stringWithoutAnn:null, intWithAnn:0, intWithoutAnn:0)"
        println "res2: ${res2.toString()}"
        
        when:
        def res3 = new ImmutablePartial( [ stringWithoutAnn: "this is the string with ann again" ], true )
        then:
        res3.stringWithAnn == null
        res3.toString() == "ImmutablePartial(stringWithAnn:null, stringWithoutAnn:this is the string with ann again, intWithAnn:0, intWithoutAnn:0)"
        println "res2: ${res2.toString()}"
    } // end "test both string fields"
    
    def "test both int fields"() {
        println "--- Starting test ${name.methodName}"
        
        when:
        def res = new ImmutablePartial( [ intWithAnn: 55, intWithoutAnn: 22 ], true )
        then:
        res.intWithAnn == 55
        res.intWithoutAnn == 22
        res.toString() == "ImmutablePartial(stringWithAnn:null, stringWithoutAnn:null, intWithAnn:55, intWithoutAnn:22)"
        println "res: ${res.toString()}"
        
        when:
        def res2 = new ImmutablePartial( [ stringWithAnn: "this is the string with ann again" ], true )
        then:
        res2.stringWithAnn == "this is the string with ann again"
        res2.toString() == "ImmutablePartial(stringWithAnn:this is the string with ann again, stringWithoutAnn:null, intWithAnn:0, intWithoutAnn:0)"
        println "res2: ${res2.toString()}"
        
        when:
        def res3 = new ImmutablePartial( [ stringWithoutAnn: "this is the string with ann again" ], true )
        then:
        res3.stringWithAnn == null
        res3.toString() == "ImmutablePartial(stringWithAnn:null, stringWithoutAnn:this is the string with ann again, intWithAnn:0, intWithoutAnn:0)"
        println "res2: ${res2.toString()}"
    } // end "test both int fields"
    
} // ImmutablePartialSpec


