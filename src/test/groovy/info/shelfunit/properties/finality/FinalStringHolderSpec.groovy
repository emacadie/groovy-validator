package info.shelfunit.properties.finality

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class FinalStringHolderSpec extends Specification {
    def setup() { println " " }       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() { }  // run before the first feature method
    def cleanupSpec() { println " " } // run after the last feature method
    
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
            fshA.firstDefString == "qeeqq"
            fshA.finalDefString == "Groovy ist Wunderbar"
            fshA.firstRealString == "realString"
            fshA.finalRealString == "Groovy ist Wunderbaar"
            fshA.someOtherString == "Yo adrian"
            fshA.anotherObject == "jsjsjjsjsjs"
            
        when:
            fshA.finalDefString = "Loosy goosey"
        then:
            def exB = thrown( Exception )
            println "Here is exB.message:\n${exB.message}"
            fshA.toString() == "info.shelfunit.properties.finality.FinalStringHolder(firstDefString:qeeqq, finalDefString:Groovy ist Wunderbar, firstRealString:realString, finalRealString:Groovy ist Wunderbaar, someOtherString:Yo adrian, anotherObject:jsjsjjsjsjs)"
        
        when:
            fshA.finalRealString = "Paak your car"
        then:
            def exC = thrown( Exception )
            println "Here is exC.message:\n${exC.message}"
            fshA.toString() == "info.shelfunit.properties.finality.FinalStringHolder(firstDefString:qeeqq, finalDefString:Groovy ist Wunderbar, firstRealString:realString, finalRealString:Groovy ist Wunderbaar, someOtherString:Yo adrian, anotherObject:jsjsjjsjsjs)"
            
    } // second Test
    
    def "test bad def inputs"() {
        println "--- Starting test ${name.methodName}"
        when:
            def fshA = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Gruuvy ist Wunderbar", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            // def exA = thrown( Exception )
            // println "Here is exC.message:\n${exC.message}"
            println "here is fshA: ${fshA.toString()}"

        // def final string too short            
        when:
            def fshB = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Grvy", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            // def exB = thrown( Exception )
            // println "Here is exB.message:\n${exB.message}"
            println "here is fshB: ${fshB.toString()}"
    } // "test bad def inputs"

    def "test bad real inputs"() {
        println "--- Starting test ${name.methodName}"
        // test reg ex for finalRealString
        when:
            def fshA = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy ist Wunderbar", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbiir', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exA = thrown( Exception )
            println "Here is exA.message:\n${exA.message}"
            exA.message == "Groovy validation exception: \n\"Groovy ist Wunderbiir\" is a String with a length outside the range of 5 to 30 characters or does not match the regular expression /^.*?aa.*\$/ "
            println "here is fshA: ${fshA.toString()}"
            fshA == null

        // final real string too short            
        when:
            def fshB = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy", firstRealString: "realString", finalRealString: 'Grvy', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exB = thrown( Exception )
            println "Here is exB.message:\n${exB.message}"
            exB.message == "Groovy validation exception: \n" +
            "\"Grvy\" is a String with a length outside the range of 5 to 30 characters or does not match the regular expression /^.*?aa.*\$/ "
            println "here is fshB: ${fshB.toString()}"
            fshB == null
            
        // final real string too long            
        when:
            def fshC = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy", firstRealString: "realString", finalRealString: 'Groovy is wunderbaar from Maars to Haarlem', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exC = thrown( Exception )
            println "Here is exC.message:\n${exC.message}"
            exC.message == "Groovy validation exception: \n" +
            "\"Groovy is wunderbaar from Maars to Haarlem\" is a String with a length outside the range of 5 to 30 characters or does not match the regular expression /^.*?aa.*\$/ "
            println "here is fshC: ${fshC.toString()}"
            fshC == null
    } // "test bad real inputs"

} // end class


