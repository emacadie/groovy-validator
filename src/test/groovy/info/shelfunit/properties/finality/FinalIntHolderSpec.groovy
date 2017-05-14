package info.shelfunit.properties.finality

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class FinalIntHolderSpec extends Specification {
    def setup() { println " " } // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() { }  // run before the first feature method
    def cleanupSpec() { println " " } // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "first Test"() {
        println "--- Starting test ${name.methodName}"
        when:
            def fihA = FinalIntHolder.createValidatedObject( [ firstDefInt: 102, finalDefInt: 100, firstRealInt: 100, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            println "here is fihA: ${fihA.toString()}"
            fihA.toString() == "info.shelfunit.properties.finality.FinalIntHolder(firstDefInt:102, finalDefInt:100, firstRealInt:100, finalRealInt:100, someOtherInt:100, anotherObject:hello)"
            
        // try to set def final
        when:
            fihA.firstDefInt = 105
            fihA.finalDefInt = 200
        then:
            def exA = thrown( Exception )
            println "exA.message: ${exA.message}"
            println "here is fihA: ${fihA.toString()}"
            exA.message == "Cannot set readonly property: finalDefInt for class: info.shelfunit.properties.finality.FinalIntHolder"
            fihA.toString() == "info.shelfunit.properties.finality.FinalIntHolder(firstDefInt:105, finalDefInt:100, firstRealInt:100, finalRealInt:100, someOtherInt:100, anotherObject:hello)"

        // try to set real mutable to something not divisible by divisor set
        when:
            fihA.firstRealInt = 206
            fihA.finalRealInt = 200
        then:
            def exB = thrown( Exception )
            println "exB.message: ${exB.message}"
            println "here is fihA: ${fihA.toString()}"
            exB.message == "206 is an integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            fihA.toString() == "info.shelfunit.properties.finality.FinalIntHolder(firstDefInt:105, finalDefInt:100, firstRealInt:100, finalRealInt:100, someOtherInt:100, anotherObject:hello)"
            
        // try to set real final
        when:
            fihA.firstRealInt = 207
            fihA.finalRealInt = 200
        then:
            def exC = thrown( Exception )
            println "exC.message: ${exC.message}"
            println "here is fihA: ${fihA.toString()}"
            exC.message == "Cannot set readonly property: finalRealInt for class: info.shelfunit.properties.finality.FinalIntHolder"
            fihA.toString() == "info.shelfunit.properties.finality.FinalIntHolder(firstDefInt:105, finalDefInt:100, firstRealInt:207, finalRealInt:100, someOtherInt:100, anotherObject:hello)"
    } // first Test
    
    def "test bad def inputs"() {
        println "--- Starting test ${name.methodName}"
        // def int too small
        when:
            def fihA = FinalIntHolder.createValidatedObject( [ firstDefInt: 10, finalDefInt: 100, firstRealInt: 100, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exA = thrown( Exception )
            exA.message == "Groovy validation exception:\n" +
            "10 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fihA: ${fihA.toString()}"
            fihA == null
            
        // def int too big
        when:
            def fihB = FinalIntHolder.createValidatedObject( [ firstDefInt: 10000, finalDefInt: 100, firstRealInt: 100, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exB = thrown( Exception )
            exB.message == "Groovy validation exception:\n" +
            "10000 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fihB: ${fihB.toString()}"
            fihB == null

        // def int not in divisor set
        when:
            def fihC = FinalIntHolder.createValidatedObject( [ firstDefInt: 301, finalDefInt: 100, firstRealInt: 100, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exC = thrown( Exception )
            exC.message == "Groovy validation exception:\n" +
            "301 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fihC: ${fihC.toString()}"
            fihC == null
            
        // final def int too small
        when:
            def fihD = FinalIntHolder.createValidatedObject( [ firstDefInt: 100, finalDefInt: 10, firstRealInt: 100, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exD = thrown( Exception )
            exD.message == "Groovy validation exception:\n" +
            "10 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            println "Here is exD.message:\n${exD.message}"
            println "here is fihD: ${fihD.toString()}"
            fihD == null
        
        // final def int too big
        when:
            def fihE = FinalIntHolder.createValidatedObject( [ firstDefInt: 100, finalDefInt: 10000, firstRealInt: 100, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exE = thrown( Exception )
            exE.message ==  "Groovy validation exception:\n" +
            "10000 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            println "Here is exE.message:\n${exE.message}"
            println "here is fihE: ${fihE.toString()}"
            fihE == null
            
        // final def int not in divisor set
        when:
            def fihF = FinalIntHolder.createValidatedObject( [ firstDefInt: 300, finalDefInt: 307, firstRealInt: 100, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exF = thrown( Exception )
            exF.message == "Groovy validation exception:\n" +
            "307 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            println "Here is exF.message:\n${exF.message}"
            println "here is fihF: ${fihF.toString()}"
            fihF == null
            
    } // "test bad def inputs"

    def "test bad real inputs"() {
        println "--- Starting test ${name.methodName}"
        // real int too small
        when:
            def fihA = FinalIntHolder.createValidatedObject( [ firstDefInt: 100, finalDefInt: 100, firstRealInt: 11, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exA = thrown( Exception )
            exA.message == "Groovy validation exception:\n" +
            "11 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fihA: ${fihA.toString()}"
            fihA == null
            
        // real int too big
        when:
            def fihB = FinalIntHolder.createValidatedObject( [ firstDefInt: 100, finalDefInt: 100, firstRealInt: 10002, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exB = thrown( Exception )
            exB.message == "Groovy validation exception:\n" +
            "10002 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fihB: ${fihB.toString()}"
            fihB == null

        // real int not in divisor set
        when:
            def fihC = FinalIntHolder.createValidatedObject( [ firstDefInt: 300, finalDefInt: 100, firstRealInt: 304, finalRealInt: 100, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exC = thrown( Exception )
            exC.message == "Groovy validation exception:\n" +
            "304 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fihC: ${fihC.toString()}"
            fihC == null

        // final real int too small
        when:
            def fihD = FinalIntHolder.createValidatedObject( [ firstDefInt: 100, finalDefInt: 100, firstRealInt: 100, finalRealInt: 10, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exD = thrown( Exception )
            exD.message == "Groovy validation exception:\n" +
            "10 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            println "Here is exD.message:\n${exD.message}"
            println "here is fihD: ${fihD.toString()}"
            fihD == null
        
        // final real int too big
        when:
            def fihE = FinalIntHolder.createValidatedObject( [ firstDefInt: 100, finalDefInt: 100, firstRealInt: 100, finalRealInt: 2005, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exE = thrown( Exception )
            exE.message ==  "Groovy validation exception:\n" +
            "2005 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            println "Here is exE.message:\n${exE.message}"
            println "here is fihE: ${fihE.toString()}"
            fihE == null

        // final real int not in divisor set
        when:
            def fihF = FinalIntHolder.createValidatedObject( [ firstDefInt: 300, finalDefInt: 300, firstRealInt: 100, finalRealInt: 313, someOtherInt: 100, anotherObject: 'hello' ], true )
        then:
            def exF = thrown( Exception )
            exF.message == "Groovy validation exception:\n" +
            "313 is a java.lang.Integer outside the range 50 to 2000 or it is not divisible by anything in the set [3, 5]"
            println "Here is exF.message:\n${exF.message}"
            println "here is fihF: ${fihF.toString()}"
            fihF == null

    } // "test bad real inputs"

} // end class

