package info.shelfunit.properties.finality

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class FinalDoubleHolderSpec extends Specification {
    def setup() { println " " } // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() { }  // run before the first feature method
    def cleanupSpec() { println " " } // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "first Test"() {
        println "--- Starting test ${name.methodName}"
        when:
            def fdhA = FinalDoubleHolder.createValidatedObject( [ firstDefDouble: 100.02d, finalDefDouble: 1000.1234, firstRealDouble: 725.63d, finalRealDouble: 2025.21d, someOtherDouble: 230d, anotherObject: "DogeDouble: Big numbers, much value" ], true )
        then:
            println "here is fdhA: ${fdhA.toString()}"
            fdhA.toString() == "info.shelfunit.properties.finality.FinalDoubleHolder(firstDefDouble:100.02, finalDefDouble:1000.1234, firstRealDouble:725.63, finalRealDouble:2025.21, someOtherDouble:230.0, anotherObject:DogeDouble: Big numbers, much value)"
                        
        // try to set def final
        when:
            fdhA.anotherObject = 22L
            fdhA.someOtherDouble = 23.04d
            fdhA.firstDefDouble = 501.2d
            fdhA.finalDefDouble = 2222.22d
        then:
            def exA = thrown( Exception )
            println "exA.message: ${exA.message}"
            println "here is fdhA: ${fdhA.toString()}"
            exA.message == "Cannot set readonly property: finalDefDouble for class: info.shelfunit.properties.finality.FinalDoubleHolder"
            fdhA.toString() == "info.shelfunit.properties.finality.FinalDoubleHolder(firstDefDouble:501.2, finalDefDouble:1000.1234, firstRealDouble:725.63, finalRealDouble:2025.21, someOtherDouble:23.04, anotherObject:22)"

        // try to set real mutable to something not divisible by divisor set
        // note: Setting firstRealDouble to 5027.0123 gives this message: 
        // 5027.0123 is a java.lang.Double outside the range 73.456f to 5027.012
        // not too helpful
        when:
            fdhA.firstRealDouble = 5027.013d
            fdhA.finalRealDouble = 241.23d
        then:
            def exB = thrown( Exception )
            println "exB.message: ${exB.message}"
            println "here is fdhA: ${fdhA.toString()}"
            exB.message == "5027.013 is a java.lang.Double outside the range 73.456 to 5027.012"
            fdhA.toString() == "info.shelfunit.properties.finality.FinalDoubleHolder(firstDefDouble:501.2, finalDefDouble:1000.1234, firstRealDouble:725.63, finalRealDouble:2025.21, someOtherDouble:23.04, anotherObject:22)"
            
        // try to set real final
        when:
            fdhA.firstRealDouble = 2027.0123d
            fdhA.finalRealDouble = 932.58d
        then:
            def exC = thrown( Exception )
            println "exC.message: ${exC.message}"
            println "here is fdhA: ${fdhA.toString()}"
            exC.message == "Cannot set readonly property: finalRealDouble for class: info.shelfunit.properties.finality.FinalDoubleHolder"
            fdhA.toString() == "info.shelfunit.properties.finality.FinalDoubleHolder(firstDefDouble:501.2, finalDefDouble:1000.1234, firstRealDouble:2027.0123, finalRealDouble:2025.21, someOtherDouble:23.04, anotherObject:22)"
    } // first Test
    
    def "test bad def inputs"() {
        println "--- Starting test ${name.methodName}"
        // def double too small
        when:
            def fdhA = FinalDoubleHolder.createValidatedObject( [ firstDefDouble: 73.44d, finalDefDouble: 1000.1234, firstRealDouble: 725.63d, finalRealDouble: 2025.21d, someOtherDouble: 230d, anotherObject: "DogeDouble: Big numbers, much value" ], true )
        then:
            def exA = thrown( Exception )
            exA.message == "Groovy validation exception:\n" +
            "73.44 is a java.lang.Double outside the range 73.456 to 5027.012"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fdhA: ${fdhA.toString()}"
            fdhA == null
            
        // def double too big
        when:
            def fLhB = FinalDoubleHolder.createValidatedObject( [ firstDefDouble: 5027.112d, finalDefDouble: 1000.1234, firstRealDouble: 725.63d, finalRealDouble: 2025.21d, someOtherDouble: 230d, anotherObject: "DogeDouble: Big numbers, much value" ], true )
        then:
            def exB = thrown( Exception )
            exB.message == "Groovy validation exception:\n" +
            "5027.112 is a java.lang.Double outside the range 73.456 to 5027.012"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhB: ${fLhB.toString()}"
            fLhB == null
            
        // final def double too small
        when:
            def fLhD = FinalDoubleHolder.createValidatedObject( [ firstDefDouble: 100d, finalDefDouble: 23.99d, firstRealDouble: 725.63d, finalRealDouble: 2025.21d, someOtherDouble: 230d, anotherObject: "DogeDouble: Big numbers, much value" ], true )
        then:
            def exD = thrown( Exception )
            exD.message == "Groovy validation exception:\n" +
            "23.99 is a java.lang.Double outside the range 73.456 to 5027.012"
            println "Here is exD.message:\n${exD.message}"
            println "here is fLhD: ${fLhD.toString()}"
            fLhD == null
        
        // final def double too big
        when:
            def fLhE = FinalDoubleHolder.createValidatedObject( [ firstDefDouble: 73.44d, finalDefDouble: 6000.1234, firstRealDouble: 725.63d, finalRealDouble: 2025.21d, someOtherDouble: 230d, anotherObject: "DogeDouble: Big numbers, much value" ], true )
        then:
            def exE = thrown( Exception )
            exE.message ==  "Groovy validation exception:\n" +
            "73.44 is a java.lang.Double outside the range 73.456 to 5027.012\n" +
            "6000.1234 is a java.lang.Double outside the range 73.456 to 5027.012"
            println "Here is exE.message:\n${exE.message}"
            println "here is fLhE: ${fLhE.toString()}"
            fLhE == null
            
    } // "test bad def inputs"

    def "test bad real inputs"() {
        println "--- Starting test ${name.methodName}"
        // real double too small
        when:
            def fdhA = FinalDoubleHolder.createValidatedObject( [ firstDefDouble: 73.47d, finalDefDouble: 1000.1234, firstRealDouble: 72.63d, finalRealDouble: 2025.21d, someOtherDouble: 230d, anotherObject: "DogeDouble: Big numbers, much value" ], true )
        then:
            def exA = thrown( Exception )
            exA.message == "Groovy validation exception:\n" +
            "72.63 is a java.lang.Double outside the range 73.456 to 5027.012"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fdhA: ${fdhA.toString()}"
            fdhA == null
            
        // real double too big
        when:
            def fLhB = FinalDoubleHolder.createValidatedObject( [ firstDefDouble: 73.47d, finalDefDouble: 1000.1234, firstRealDouble: 7025.63d, finalRealDouble: 2025.21d, someOtherDouble: 230d, anotherObject: "DogeDouble: Big numbers, much value" ], true )
        then:
            def exB = thrown( Exception )
            exB.message == "Groovy validation exception:\n" +
            "7025.63 is a java.lang.Double outside the range 73.456 to 5027.012"
            // println "Here is exC.message:\n${exC.message}"
            println "here is fLhB: ${fLhB.toString()}"
            fLhB == null

        // final real double too small
        when:
            def fLhD = FinalDoubleHolder.createValidatedObject( [ firstDefDouble: 73.47d, finalDefDouble: 1000.1234, firstRealDouble: 725.63d, finalRealDouble: 25.21d, someOtherDouble: 230d, anotherObject: "DogeDouble: Big numbers, much value" ], true )
        then:
            def exD = thrown( Exception )
            exD.message == "Groovy validation exception:\n" +
            "25.21 is a java.lang.Double outside the range 73.456 to 5027.012"
            println "Here is exD.message:\n${exD.message}"
            println "here is fLhD: ${fLhD.toString()}"
            fLhD == null
        
        // final real double too big
        when:
            def fLhE = FinalDoubleHolder.createValidatedObject( [ firstDefDouble: 73.47d, finalDefDouble: 1000.1234, firstRealDouble: 725.63d, finalRealDouble: 8025.21d, someOtherDouble: 230d, anotherObject: "DogeDouble: Big numbers, much value" ], true )
        then:
            def exE = thrown( Exception )
            exE.message == "Groovy validation exception:\n" +
            "8025.21 is a java.lang.Double outside the range 73.456 to 5027.012"
            println "Here is exE.message:\n${exE.message}"
            println "here is fLhE: ${fLhE.toString()}"
            fLhE == null

    } // "test bad real inputs"

} // end class

