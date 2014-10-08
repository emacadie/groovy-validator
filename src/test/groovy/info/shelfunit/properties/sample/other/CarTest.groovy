package info.shelfunit.properties.sample.other

import info.shelfunit.properties.annotations.AnnotationProcessor

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class CarTest extends Specification {
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {
        AnnotationProcessor.process( Car ) 
    }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
     def "first Test"() {
        println "--- Starting test ${name.methodName}"
        def car = new Car( 2008 )
        car.miles = 5
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        expect:
        
        car.year == 2008
        car.miles == 0
        
    } // first Test
    
    def "second Test"() {
        println "--- Starting test ${name.methodName}"
        def car = new Car( 1987 )
        car.miles = 50
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        expect:
        car.miles == 50
        
    } // first Test
    
    /*
    def "first Test"() {
        println "--- Starting test ${name.methodName}"
        def car = new Car( year: 2008, miles: 0 )
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        try { 
            car.year = 2010
        } catch ( Exception e ) {
            exceptionThrown = true
        }
        
        expect:
        exceptionThrown == true
        car.year == 2008
        car.miles == 0
        
    } // first Test
    */
    /*
    def "second Test"() {
        println "--- Starting test ${name.methodName}"
        def car = new Car( year: 208 )
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        try { 
            car.year = 2010
        } catch ( Exception e ) {
            exceptionThrown = true
        }
        expect:
        exceptionThrown == true
        car.year == 0
        car.miles == 0
        
    } // first Test
    */
    
  /*
    def "test with annotations"() {
        println "--- Starting test ${name.methodName}"
        def person = new Person().with {
            firstName = "Robert"
            lastName = "LewandowskiLewandowski"
            age = 212
            it // got to have "it" here to return object
        }
        println "person is a ${person.class.name}"
        println "Here is person: ${person.toString()}"
        expect:
        person.firstName == "Robert"
        person.lastName == null
        person.age == null
    } // first Test
 
    */
} // end class


