package info.shelfunit.properties.finality

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class CarSpec extends Specification {
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {}   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
     def "first Test"() {
        println "--- Starting test ${name.methodName}"
        def car = new Car( [ miles: 50, year: 2008 ], true )
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        expect:
            car.year == 2008
            car.miles == 50
        
    } // first Test
    
    def "second Test"() {
        println "--- Starting test ${name.methodName}"
        when:
            def car = new Car( [ miles: 50, year: 1987 ], true, true )
        then:
            def ex = thrown( Exception )
            println "Here is ex.message:\n${ex.message}"
            ex.message == "Groovy validation exception:\n" +
            "1987 is a java.lang.Integer outside the range 1990 to 2147483647 or it is not divisible by anything in the set [1]"
            car == null
        
    } // first Test
    
    def "test Exception 001"() {
        println "\n--- Starting test ${name.methodName}"
        def car = new Car( [year: 2008, miles: 10], true )
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        try { 
            car.year = 2010
        } catch ( Exception e ) {
            exceptionThrown = true
        }
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        expect:
            exceptionThrown == true
            car.year == 2008
            car.miles == 10
        
    } // 
    
    def "test different combinations"() {
        println "\n--- Starting test ${name.methodName}"
        when:
            def carA = new Car( [ miles: 5, year: 2010 ], true)
            println "carA: ${carA.toString()}"
        then:
            carA.miles == 0
            carA.year == 2010
            
        when:
            carA.miles = 20
            carA.year = 1900
        then:
            def ex = thrown( Exception )
            ex.message == "Cannot set readonly property: year for class: info.shelfunit.properties.finality.Car"
            carA.miles == 20
            carA.year == 2010
    }
    
    def "test just year in map constructor"() {
        println "\n--- Starting test ${name.methodName}"
        when:
            def carA = new Car( [ year: 2010 ], true)
            println "carA: ${carA.toString()}"
        then:
            carA.miles == 0
            carA.year == 2010
            
        when:
            carA.miles = 20
            carA.year = 1900
        then:
            def ex = thrown( Exception )
            ex.message == "Cannot set readonly property: year for class: info.shelfunit.properties.finality.Car"
            carA.miles == 20
            carA.year == 2010
            
        when:
            carA.miles = 2
            carA.year = 2100
        then:
            def ex2 = thrown( Exception )
            ex2.message == "Cannot set readonly property: year for class: info.shelfunit.properties.finality.Car"
            carA.miles == 20
            carA.year == 2010
            
        when:
            carA.miles = 4
        then:
            carA.miles == 20
            carA.year == 2010
    }

} // end class


