package info.shelfunit.properties.finality

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class FinalStringHolderSpec extends Specification {
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() { }  // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "first Test"() {
        println "--- Starting test ${name.methodName}"
        when:
            def fshA = new FinalStringHolder( [ firstDefString: "qeeqq", finalDefString: "Groovy ist Wunderbar", firstRealString: "this is a real string", finalRealString: "Groovy ist Wunderbaar", someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
        def ex = thrown( Exception )
        println "Here is ex.message:\n${ex.message}"
        ex.message == "'this is a real string' is a String with a length outside the range of 5 to 10 characters or does not match the regular expression '.*' "
            // fshA.firstDefString == "qeeqq"
            // fshA.finalDefString == "Groovy ist Wunderbar"
            // fshA.firstRealString == "this is a real string"
            // fshA.finalRealString == "Groovy ist Wunderbaar"
            // fshA.someOtherString == "Yo adrian"
        
    } // first Test
    
    def "second Test"() {
        println "--- Starting test ${name.methodName}"
        when:
            def fshA = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy ist Wunderbar", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
        // def ex = thrown( Exception )
        // println "Here is ex.message:\n${ex.message}"
        println "here is fshA: ${fshA.toString()}"
        // ex.message == "'this is a real string' is a String with a length outside the range of 5 to 10 characters or does not match the regular expression '.*' "
            // fshA.firstDefString == "qeeqq"
            // fshA.finalDefString == "Groovy ist Wunderbar"
            // fshA.firstRealString == "this is a real string"
            // fshA.finalRealString == "Groovy ist Wunderbaar"
            // fshA.someOtherString == "Yo adrian"
        
    } // second Test


} // end class


