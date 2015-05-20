package info.shelfunit.properties.finality

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class FinalFloatHolderSpec extends Specification {
    def setup() { println " " } // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() { }  // run before the first feature method
    def cleanupSpec() { println " " } // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "first Test"() {
        println "--- Starting test ${name.methodName}"
        when:
            def ffhA = new FinalFloatHolder( [ firstDefFloat: 100.02f, finalDefFloat: 1000.1234, firstRealFloat: 725.63f, finalRealFloat: 2025.21f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            println "here is ffhA: ${ffhA.toString()}"
            ffhA.toString() == "info.shelfunit.properties.finality.FinalFloatHolder(firstDefFloat:100.02, finalDefFloat:1000.1234, firstRealFloat:725.63, finalRealFloat:2025.21, someOtherFloat:230.0, anotherObject:DogeFloat: Big numbers, much value)"
            
        // try to set def final
        when:
            ffhA.anotherObject = 22L
            ffhA.someOtherFloat = 23.04f
            ffhA.firstDefFloat = 501.2f
            ffhA.finalDefFloat = 2222.22f
        then:
            def exA = thrown( Exception )
            println "exA.message: ${exA.message}"
            println "here is ffhA: ${ffhA.toString()}"
            exA.message == "Cannot set readonly property: finalDefFloat for class: info.shelfunit.properties.finality.FinalFloatHolder"
            ffhA.toString() == "info.shelfunit.properties.finality.FinalFloatHolder(firstDefFloat:501.2, finalDefFloat:1000.1234, firstRealFloat:725.63, finalRealFloat:2025.21, someOtherFloat:23.04, anotherObject:22)"

        // try to set real mutable to something not divisible by divisor set
        // note: Setting firstRealFloat to 5027.0123 gives this message: 
        // 5027.0123 is a java.lang.Float outside the range 73.456f to 5027.012
        // not too helpful
        when:
            ffhA.firstRealFloat = 5027.013f
            ffhA.finalRealFloat = 241.23f
        then:
            def exB = thrown( Exception )
            println "exB.message: ${exB.message}"
            println "here is ffhA: ${ffhA.toString()}"
            exB.message == "5027.013 is a java.lang.Float outside the range 73.456 to 5027.012"
            ffhA.toString() == "info.shelfunit.properties.finality.FinalFloatHolder(firstDefFloat:501.2, finalDefFloat:1000.1234, firstRealFloat:725.63, finalRealFloat:2025.21, someOtherFloat:23.04, anotherObject:22)"
            
        // try to set real final
        when:
            ffhA.firstRealFloat = 2027.0123f
            ffhA.finalRealFloat = 932.58f
        then:
            def exC = thrown( Exception )
            println "exC.message: ${exC.message}"
            println "here is ffhA: ${ffhA.toString()}"
            exC.message == "Cannot set readonly property: finalRealFloat for class: info.shelfunit.properties.finality.FinalFloatHolder"
            ffhA.toString() == "info.shelfunit.properties.finality.FinalFloatHolder(firstDefFloat:501.2, finalDefFloat:1000.1234, firstRealFloat:2027.0123, finalRealFloat:2025.21, someOtherFloat:23.04, anotherObject:22)"
    } // first Test
    
    def "test bad def inputs"() {
        println "--- Starting test ${name.methodName}"
        // def long too small
        when:
            def ffhA = new FinalFloatHolder( [ firstDefFloat: 999f, finalDefFloat: 100000000f, firstRealFloat: 700000f, finalRealFloat: 700002f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exA = thrown( Exception )
            exA.message == "999 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is ffhA: ${ffhA.toString()}"
            ffhA == null
            
        // def long too big
        when:
            def fLhB = new FinalFloatHolder( [ firstDefFloat: 1000000005, finalDefFloat: 100000000f, firstRealFloat: 700000f, finalRealFloat: 700002f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exB = thrown( Exception )
            exB.message == "1000000005 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhB: ${fLhB.toString()}"
            fLhB == null

        // def long not in divisor set
        when:
            def fLhC = new FinalFloatHolder( [ firstDefFloat: 1001, finalDefFloat: 100000000f, firstRealFloat: 700000f, finalRealFloat: 700002f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exC = thrown( Exception )
            exC.message == "1001 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhC: ${fLhC.toString()}"
            fLhC == null
            
        // final def long too small
        when:
            def fLhD = new FinalFloatHolder( [ firstDefFloat: 10000, finalDefFloat: 100f, firstRealFloat: 700000f, finalRealFloat: 700002f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exD = thrown( Exception )
            exD.message == "Groovy validation exception: \n" +
            "100 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exD.message:\n${exD.message}"
            println "here is fLhD: ${fLhD.toString()}"
            fLhD == null
        
        // final def long too big
        when:
            def fLhE = new FinalFloatHolder( [ firstDefFloat: 10000, finalDefFloat: 1000000006, firstRealFloat: 700000f, finalRealFloat: 700002f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exE = thrown( Exception )
            exE.message ==  "Groovy validation exception: \n" +
            "1000000006 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exE.message:\n${exE.message}"
            println "here is fLhE: ${fLhE.toString()}"
            fLhE == null
            
        // final def long not in divisor set
        when:
            def fLhF = new FinalFloatHolder( [ firstDefFloat: 10000, finalDefFloat: 10000001, firstRealFloat: 700000f, finalRealFloat: 700002f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exF = thrown( Exception )
            exF.message == "Groovy validation exception: \n" +
            "10000001 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exF.message:\n${exF.message}"
            println "here is fLhF: ${fLhF.toString()}"
            fLhF == null
            
    } // "test bad def inputs"

    def "test bad real inputs"() {
        println "--- Starting test ${name.methodName}"
        // real long too small
        when:
            def ffhA = new FinalFloatHolder( [ firstDefFloat: 700000f, finalDefFloat: 100000000, firstRealFloat: 70f, finalRealFloat: 700002f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exA = thrown( Exception )
            exA.message == "70 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is ffhA: ${ffhA.toString()}"
            ffhA == null
            
        // real long too big
        when:
            def fLhB = new FinalFloatHolder( [ firstDefFloat: 700000f, finalDefFloat: 100000000, firstRealFloat: 1000000600f, finalRealFloat: 700002f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exB = thrown( Exception )
            exB.message == "1000000600 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhB: ${fLhB.toString()}"
            fLhB == null

        // real long not in divisor set
        when:
            def fLhC = new FinalFloatHolder( [ firstDefFloat: 700000f, finalDefFloat: 100000000, firstRealFloat: 10000007f, finalRealFloat: 700002f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exC = thrown( Exception )
            exC.message == "10000007 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhC: ${fLhC.toString()}"
            fLhC == null

        // final real long too small
        when:
            def fLhD = new FinalFloatHolder( [ firstDefFloat: 700000f, finalDefFloat: 100000000, firstRealFloat: 700002f, finalRealFloat: 703f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exD = thrown( Exception )
            exD.message == "Groovy validation exception: \n" +
            "703 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exD.message:\n${exD.message}"
            println "here is fLhD: ${fLhD.toString()}"
            fLhD == null
        
        // final real long too big
        when:
            def fLhE = new FinalFloatHolder( [ firstDefFloat: 700000f, finalDefFloat: 100000000, firstRealFloat: 700002f, finalRealFloat: 1000000004f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exE = thrown( Exception )
            exE.message ==  "Groovy validation exception: \n" +
            "1000000004 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exE.message:\n${exE.message}"
            println "here is fLhE: ${fLhE.toString()}"
            fLhE == null

        // final real long not in divisor set
        when:
            def fLhF = new FinalFloatHolder( [ firstDefFloat: 700000f, finalDefFloat: 100000000, firstRealFloat: 700002f, finalRealFloat: 700004f, someOtherFloat: 230f, anotherObject: "DogeFloat: Big numbers, much value" ], true, true )
        then:
            def exF = thrown( Exception )
            exF.message == "Groovy validation exception: \n" +
            "700004 is a java.lang.Float outside the range 1000 to 1000000000 or it is not divisible by anything in the set [3, 5] "
            println "Here is exF.message:\n${exF.message}"
            println "here is fLhF: ${fLhF.toString()}"
            fLhF == null

    } // "test bad real inputs"

} // end class

