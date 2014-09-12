package info.shelfunit.properties.sample.divisor

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName
import info.shelfunit.properties.annotations.AnnotationProcessor

class ImmutableIntDivisorSpec extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test both int fields"() {
        println "--- Starting test ${name.methodName}"
        
        when:
        def rid = new ImmutableIntDivisor( [ intWithDiv: 15, intWithDiv002: 14 ], true, true )
        then:
        rid.intWithDiv == 15
        rid.intWithDiv002 == 14
        println "rid.toString(): ${rid.toString()}"
        
        when:
        def rid2 = new ImmutableIntDivisor( [ intWithDiv: 5, intWithDiv002: 13 ], true, true )
        println "rid2.toString(): ${rid2.toString()}"
        then:
        def ex = thrown( Exception )
        ex.message == "Groovy validation exception: \n" +
        "5 is an integer outside the range 10 to 2147483647 or it is not divisible by anything in the set [5] \n" +
        "13 is an integer outside the range 0 to 2147483647 or it is not divisible by anything in the set [7] "
        
    } // end "test both int fields"
    
    
    def "test with divisor array"() {
        println "--- Starting test ${name.methodName}"
        when:
        def rid = new ImmutableIntDivisor( [ intWithDivArray: 12 ], true, true )
        then:
        rid.intWithDivArray == 12
        
        when:
        rid.intWithDiv = 15
        then:
        def ex = thrown( Exception )
        ex.message == "Cannot set readonly property: intWithDiv for class: info.shelfunit.properties.sample.divisor.ImmutableIntDivisor"
        
        when:
        def rid2 = new ImmutableIntDivisor( [ intWithDivArray: 13 ], true, true )
        then:
        def ex2 = thrown( Exception )
        ex2.message == "Groovy validation exception: \n" +
        "13 is an integer outside the range 0 to 40 or it is not divisible by anything in the set [3, 4] "
        
        when:
        def rid3 = new ImmutableIntDivisor( [ intWithDivArray: 9 ], true, true )
        then:
        rid3.intWithDivArray == 9
       
        when:
        def rid4 = new ImmutableIntDivisor( [ intWithDivArray: 16 ], true, true )
        then:
        rid4.intWithDivArray == 16
        
        when:
        def rid5 = new ImmutableIntDivisor( [ intWithDivArray: 55 ], true, true )
        then:
        def ex5 = thrown( Exception )
        ex5.message == "Groovy validation exception: \n" +
        "55 is an integer outside the range 0 to 40 or it is not divisible by anything in the set [3, 4] "
        
    } // end test with divisor array
    
    
    def "test with zero divisor"() {
        println "--- Starting test ${name.methodName}"
        when:
        def rid = new ImmutableIntDivisor( [ intWithDivArray: 12, intWithZeroDiv: 35 ], true, true )
        // println "rid: ${rid.toString()}"
        then:
        rid.intWithDivArray == 12
        rid.intWithZeroDiv == 35
        
        when:
        def rid2 = new ImmutableIntDivisor( [ intWithDivArray: 9, intWithZeroDiv: 55 ], true, true )
        then:
        def ex = thrown( Exception )
        ex.message == "Groovy validation exception: \n" +
        "55 is an integer outside the range 0 to 40 or it is not divisible by anything in the set [1] "

    } // end test with zero divisor
   
} // ImmutableIntDivisorSpec

