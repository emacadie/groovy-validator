package info.shelfunit.properties.sample.other

import info.shelfunit.properties.annotations.AnnotationProcessor

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class WithTest001 extends Specification {
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {
        AnnotationProcessor.process( Person ) 
    }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "first Test"() {
        println "--- Starting test ${name.methodName}"
        def person = new Person().with {
            firstName = "Robert"
            lastName = "Lewandowski"
            age = 21
            it
        }
        println "person is a ${person.class.name}"
        println "Here is person: ${person.toString()}"
        expect:
        person.firstName == "Robert"
        person.lastName == "Lewandowski"
        person.age == 21
    } // first Test
    
  
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
 
    
} // end class

