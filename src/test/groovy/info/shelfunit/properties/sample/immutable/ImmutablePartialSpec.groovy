package info.shelfunit.properties.sample.immutable

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
            def res = ImmutablePartial.createValidatedObject( [ stringWithAnn: "this is the string with ann", stringWithoutAnn: "Hello" ] )
        then:
            res.stringWithAnn == "this is the string with ann"
            res.stringWithoutAnn == "Hello"
            res.toString() == "ImmutablePartial(stringWithAnn:this is the string with ann, stringWithoutAnn:Hello, intWithAnn:0, intWithoutAnn:0)"
            println "res: ${res.toString()}"
        
        when:
            def res2 = ImmutablePartial.createValidatedObject( [ stringWithAnn: "this is the string with ann again" ] )
        then:
            res2.stringWithAnn == "this is the string with ann again"
            res2.toString() == "ImmutablePartial(stringWithAnn:this is the string with ann again, stringWithoutAnn:null, intWithAnn:0, intWithoutAnn:0)"
            println "res2: ${res2.toString()}"
        
        when:
            def res3 = ImmutablePartial.createValidatedObject( [ stringWithoutAnn: "this is the string with ann again" ] )
        then:
            res3.stringWithAnn == null
            res3.toString() == "ImmutablePartial(stringWithAnn:null, stringWithoutAnn:this is the string with ann again, intWithAnn:0, intWithoutAnn:0)"
            println "res3: ${res3.toString()}"
        
        // now, make stringWithoutAnn shorter than minumum length
        when:
            def res4 = ImmutablePartial.createValidatedObject( [ stringWithoutAnn: "Hello", stringWithoutAnn: "this is the string with ann again" ] )
        then:
            res4.stringWithAnn == null
            res4.toString() == "ImmutablePartial(stringWithAnn:null, stringWithoutAnn:this is the string with ann again, intWithAnn:0, intWithoutAnn:0)"
            println "res4: ${res4.toString()}"
    } // end "test both string fields"
    
    def "test both int fields"() {
        println "--- Starting test ${name.methodName}"
        
        when:
            def res = ImmutablePartial.createValidatedObject( [ intWithAnn: 55, intWithoutAnn: 22 ] )
        then:
            res.intWithAnn == 55
            res.intWithoutAnn == 22
            res.toString() == "ImmutablePartial(stringWithAnn:null, stringWithoutAnn:null, intWithAnn:55, intWithoutAnn:22)"
            println "res: ${res.toString()}"
        
        when:
            def res2 = ImmutablePartial.createValidatedObject( [ intWithAnn: 55 ] )
        then:
            res2.stringWithAnn == null
            res2.toString() == "ImmutablePartial(stringWithAnn:null, stringWithoutAnn:null, intWithAnn:55, intWithoutAnn:0)"
            println "res2: ${res2.toString()}"
        
        when:
            def res3 = ImmutablePartial.createValidatedObject( [ intWithoutAnn: 22 ] )
        then:
            res3.stringWithAnn == null
            res3.toString() == "ImmutablePartial(stringWithAnn:null, stringWithoutAnn:null, intWithAnn:0, intWithoutAnn:22)"
            println "res3: ${res3.toString()}"
        
        // make intWithAnn larger than max value of 100
        when:
            def res4 = ImmutablePartial.createValidatedObject( [  intWithoutAnn: 222, intWithoutAnn: 22 ] )
        then:
            res4.stringWithAnn == null
            res4.toString() == "ImmutablePartial(stringWithAnn:null, stringWithoutAnn:null, intWithAnn:0, intWithoutAnn:22)"
            println "res4: ${res4.toString()}"
    } // end "test both int fields"
    
} // ImmutablePartialSpec
