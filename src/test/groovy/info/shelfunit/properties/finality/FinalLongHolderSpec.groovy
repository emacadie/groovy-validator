package info.shelfunit.properties.finality

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class FinalLongHolderSpec extends Specification {
    def setup() { println " " } // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() { }  // run before the first feature method
    def cleanupSpec() { println " " } // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "first Test"() {
        println "--- Starting test ${name.methodName}"
        when:
            def fLhA = new FinalLongHolder( [ firstDefLong: 10000L, finalDefLong: 100000000, firstRealLong: 700000L, finalRealLong: 700002L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            println "here is fLhA: ${fLhA.toString()}"
            fLhA.toString() == "info.shelfunit.properties.finality.FinalLongHolder(firstDefLong:10000, finalDefLong:100000000, firstRealLong:700000, finalRealLong:700002, someOtherLong:230, anotherObject:DogeLong: Big numbers, much value)"
            
        // try to set def final
        when:
            fLhA.firstDefLong = 100005
            fLhA.finalDefLong = 200000
        then:
            def exA = thrown( Exception )
            println "exA.message: ${exA.message}"
            println "here is fLhA: ${fLhA.toString()}"
            exA.message == "Cannot set readonly property: finalDefLong for class: info.shelfunit.properties.finality.FinalLongHolder"
            fLhA.toString() == "info.shelfunit.properties.finality.FinalLongHolder(firstDefLong:100005, finalDefLong:100000000, firstRealLong:700000, finalRealLong:700002, someOtherLong:230, anotherObject:DogeLong: Big numbers, much value)"

        // try to set real mutable to something not divisible by divisor set
        when:
            fLhA.firstRealLong = 200000006
            fLhA.finalRealLong = 200000
        then:
            def exB = thrown( Exception )
            println "exB.message: ${exB.message}"
            println "here is fLhA: ${fLhA.toString()}"
            exB.message == "200000006 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            fLhA.toString() == "info.shelfunit.properties.finality.FinalLongHolder(firstDefLong:100005, finalDefLong:100000000, firstRealLong:700000, finalRealLong:700002, someOtherLong:230, anotherObject:DogeLong: Big numbers, much value)"
            
        // try to set real final
        when:
            fLhA.firstRealLong = 200000007
            fLhA.finalRealLong = 200000
        then:
            def exC = thrown( Exception )
            println "exC.message: ${exC.message}"
            println "here is fLhA: ${fLhA.toString()}"
            exC.message == "Cannot set readonly property: finalRealLong for class: info.shelfunit.properties.finality.FinalLongHolder"
            fLhA.toString() == "info.shelfunit.properties.finality.FinalLongHolder(firstDefLong:100005, finalDefLong:100000000, firstRealLong:200000007, finalRealLong:700002, someOtherLong:230, anotherObject:DogeLong: Big numbers, much value)"
    } // first Test
    
    def "test bad def inputs"() {
        println "--- Starting test ${name.methodName}"
        // def long too small
        when:
            def fLhA = new FinalLongHolder( [ firstDefLong: 999L, finalDefLong: 100000000L, firstRealLong: 700000L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exA = thrown( Exception )
            exA.message == "999 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhA: ${fLhA.toString()}"
            fLhA == null
            
        // def long too big
        when:
            def fLhB = new FinalLongHolder( [ firstDefLong: 1000000005, finalDefLong: 100000000L, firstRealLong: 700000L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exB = thrown( Exception )
            exB.message == "1000000005 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhB: ${fLhB.toString()}"
            fLhB == null

        // def long not in divisor set
        when:
            def fLhC = new FinalLongHolder( [ firstDefLong: 1001, finalDefLong: 100000000L, firstRealLong: 700000L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exC = thrown( Exception )
            exC.message == "1001 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhC: ${fLhC.toString()}"
            fLhC == null
            
        // final def long too small
        when:
            def fLhD = new FinalLongHolder( [ firstDefLong: 10000, finalDefLong: 100L, firstRealLong: 700000L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exD = thrown( Exception )
            exD.message == "Groovy validation exception: \n" +
            "100 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exD.message:\n${exD.message}"
            println "here is fLhD: ${fLhD.toString()}"
            fLhD == null
        
        // final def long too big
        when:
            def fLhE = new FinalLongHolder( [ firstDefLong: 10000, finalDefLong: 1000000006, firstRealLong: 700000L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exE = thrown( Exception )
            exE.message ==  "Groovy validation exception: \n" +
            "1000000006 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exE.message:\n${exE.message}"
            println "here is fLhE: ${fLhE.toString()}"
            fLhE == null
            
        // final def long not in divisor set
        when:
            def fLhF = new FinalLongHolder( [ firstDefLong: 10000, finalDefLong: 10000001, firstRealLong: 700000L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exF = thrown( Exception )
            exF.message == "Groovy validation exception: \n" +
            "10000001 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exF.message:\n${exF.message}"
            println "here is fLhF: ${fLhF.toString()}"
            fLhF == null
            
    } // "test bad def inputs"

    def "test bad real inputs"() {
        println "--- Starting test ${name.methodName}"
        // real long too small
        when:
            def fLhA = new FinalLongHolder( [ firstDefLong: 700000L, finalDefLong: 100000000, firstRealLong: 70L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exA = thrown( Exception )
            exA.message == "70 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhA: ${fLhA.toString()}"
            fLhA == null
            
        // real long too big
        when:
            def fLhB = new FinalLongHolder( [ firstDefLong: 700000L, finalDefLong: 100000000, firstRealLong: 1000000600L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exB = thrown( Exception )
            exB.message == "1000000600 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhB: ${fLhB.toString()}"
            fLhB == null

        // real long not in divisor set
        when:
            def fLhC = new FinalLongHolder( [ firstDefLong: 700000L, finalDefLong: 100000000, firstRealLong: 10000007L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exC = thrown( Exception )
            exC.message == "10000007 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhC: ${fLhC.toString()}"
            fLhC == null

        // final real long too small
        when:
            def fLhD = new FinalLongHolder( [ firstDefLong: 700000L, finalDefLong: 100000000, firstRealLong: 700003L, finalRealLong: 703L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exD = thrown( Exception )
            exD.message == "Groovy validation exception: \n" +
            "703 is a java.lang.Long outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exD.message:\n${exD.message}"
            println "here is fLhD: ${fLhD.toString()}"
            fLhD == null
        
        // final real long too big
        when:
            def fLhE = new FinalLongHolder( [ firstDefLong: 700000L, finalDefLong: 100000000, firstRealLong: 700003L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exE = thrown( Exception )
            exE.message ==  "Groovy validation exception: \n" +
            "2005 is a java.lang.Integer outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exE.message:\n${exE.message}"
            println "here is fLhE: ${fLhE.toString()}"
            fLhE == null

        // final real int not in divisor set
        when:
            def fLhF = new FinalLongHolder( [ firstDefLong: 700000L, finalDefLong: 100000000, firstRealLong: 700003L, finalRealLong: 700003L, someOtherLong: 230L, anotherObject: "DogeLong: Big numbers, much value" ], true, true )
        then:
            def exF = thrown( Exception )
            exF.message == "Groovy validation exception: \n" +
            "313 is a java.lang.Integer outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exF.message:\n${exF.message}"
            println "here is fLhF: ${fLhF.toString()}"
            fLhF == null

    } // "test bad real inputs"

} // end class

