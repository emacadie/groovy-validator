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
            ex.message == "'this is a real string' is a String with a length outside the range of 5 to 10 characters or does not match the regular expression '.*'"
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
        // def string too short
        when:
            def fshA = new FinalStringHolder( [ firstDefString: 'qqq', finalDefString: "Groovy ist Wunderbar", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exA = thrown( Exception )
            exA.message == "'qqq' is a String with a length outside the range of 5 to 20 characters or does not match the regular expression /^.*ee.*DOLLAR_SIGN/"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fshA: ${fshA.toString()}"
            fshA == null
            
        // def string too long
        when:
            def fshB = new FinalStringHolder( [ firstDefString: 'qeeqq blah blah blah blah blah blah blah blah blah', finalDefString: "Groovy ist Wunderbar", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exB = thrown( Exception )
            exB.message == "'qeeqq blah blah blah blah blah blah blah blah blah' is a String with a length outside the range of 5 to 20 characters or does not match the regular expression /^.*ee.*DOLLAR_SIGN/"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fshB: ${fshB.toString()}"
            fshB == null
            
        // def string regEx
        when:
            def fshC = new FinalStringHolder( [ firstDefString: 'qaaqqq', finalDefString: "Groovy ist Wunderbar", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exC = thrown( Exception )
            exC.message == "'qaaqqq' is a String with a length outside the range of 5 to 20 characters or does not match the regular expression /^.*ee.*DOLLAR_SIGN/"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fshC: ${fshC.toString()}"
            fshC == null
            
        // def final string too short            
        when:
            def fshD = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Grvy", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exD = thrown( Exception )
            exD.message == "Groovy validation exception:\n" +
            "\"Grvy\" is a String with a length outside the range of 5 to 20 characters or does not match the regular expression /^.*?oo.*\$/"
            println "here is fshD: ${fshD.toString()}"
            fshD == null
            
        // def final string too long
        when:
            def fshE = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy gets me mooving in the moorning", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exE = thrown( Exception )
            exE.message == "Groovy validation exception:\n" +
            "\"Groovy gets me mooving in the moorning\" is a String with a length outside the range of 5 to 20 characters or does not match the regular expression /^.*?oo.*\$/"
            println "here is fshE: ${fshE.toString()}"
            fshE == null
            
        // def final string bad reg ex
        when:
            def fshF = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Gruuvy ist Wunderbar", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exF = thrown( Exception )
            exF.message == "Groovy validation exception:\n" +
            "\"Gruuvy ist Wunderbar\" is a String with a length outside the range of 5 to 20 characters or does not match the regular expression /^.*?oo.*\$/"
            println "here is fshF: ${fshF.toString()}"
            fshF == null
            
    } // "test bad def inputs"

    def "test bad real inputs"() {
        println "--- Starting test ${name.methodName}"
        // test reg ex for finalRealString
        when:
            def fshA = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy ist Wunderbar", firstRealString: "realString", finalRealString: 'Groovy ist Wunderbiir', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exA = thrown( Exception )
            println "Here is exA.message:\n${exA.message}"
            exA.message == "Groovy validation exception:\n\"Groovy ist Wunderbiir\" is a String with a length outside the range of 5 to 30 characters or does not match the regular expression /^.*?aa.*\$/"
            println "here is fshA: ${fshA.toString()}"
            fshA == null

        // final real string too short            
        when:
            def fshB = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy", firstRealString: "realString", finalRealString: 'Grvy', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exB = thrown( Exception )
            println "Here is exB.message:\n${exB.message}"
            exB.message == "Groovy validation exception:\n" +
            "\"Grvy\" is a String with a length outside the range of 5 to 30 characters or does not match the regular expression /^.*?aa.*\$/"
            println "here is fshB: ${fshB.toString()}"
            fshB == null
            
        // final real string too long            
        when:
            def fshC = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy", firstRealString: "realString", finalRealString: 'Groovy is wunderbaar from Maars to Haarlem', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exC = thrown( Exception )
            println "Here is exC.message:\n${exC.message}"
            exC.message == "Groovy validation exception:\n" +
            "\"Groovy is wunderbaar from Maars to Haarlem\" is a String with a length outside the range of 5 to 30 characters or does not match the regular expression /^.*?aa.*\$/"
            println "here is fshC: ${fshC.toString()}"
            fshC == null
            
        // test min length for firstRealString
        when:
            def fshD = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy ist Wunderbar", firstRealString: "real", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exD = thrown( Exception )
            println "Here is exD.message:\n${exD.message}"
            exD.message == "'real' is a String with a length outside the range of 5 to 10 characters or does not match the regular expression '.*'"
            println "here is fshD: ${fshD.toString()}"
            fshD == null
            
        // test max length for firstRealString
        when:
            def fshE = new FinalStringHolder( [ firstDefString: 'qeeqq', finalDefString: "Groovy ist Wunderbar", firstRealString: "really long string will not pass", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exE = thrown( Exception )
            println "Here is exE.message:\n${exE.message}"
            exE.message == "'really long string will not pass' is a String with a length outside the range of 5 to 10 characters or does not match the regular expression '.*'"
            println "here is fshE: ${fshE.toString()}"
            fshE == null
    } // "test bad real inputs"
    
    def "test multiple bad inputs"() {
        println "--- Starting test ${name.methodName}"
        // the exception only catches the final fields, because those are processed first
        when:
            def fshA = new FinalStringHolder( [ firstDefString: 'qwwqq', finalDefString: "Gruuvy ist Wunderbar", firstRealString: "realStringwqerypweioyrpweurou", finalRealString: 'Groovy ist Wunderbiir', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exA = thrown( Exception )
            println "Here is exA.message:\n${exA.message}"
            exA.message == "Groovy validation exception:\n" +
            "\"Gruuvy ist Wunderbar\" is a String with a length outside the range of 5 to 20 characters or does not match the regular expression /^.*?oo.*\$/\n" +
            "\"Groovy ist Wunderbiir\" is a String with a length outside the range of 5 to 30 characters or does not match the regular expression /^.*?aa.*\$/"
            println "here is fshA: ${fshA.toString()}"
            fshA == null
        
        // with non-final fields, only the first invalid one will throw an exception 
        when:
            def fshB = new FinalStringHolder( [ firstDefString: 'qwwqq', finalDefString: "Groovy ist Wunderbar", firstRealString: "realStringwqerypweioyrpweurou", finalRealString: 'Groovy ist Wunderbaar', someOtherString: "Yo adrian", anotherObject: "jsjsjjsjsjs" ], true, true )
        then:
            def exB = thrown( Exception )
            println "Here is exB.message:\n${exB.message}"
            exB.message == "'qwwqq' is a String with a length outside the range of 5 to 20 characters or does not match the regular expression /^.*ee.*DOLLAR_SIGN/"
            println "here is fshA: ${fshB.toString()}"
            fshB == null

    } // "test multiple bad inputs"()

} // end class


