package info.shelfunit.properties.sample.other

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class BuilderTest001 extends Specification {
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {}   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "first Test"() {
        println "--- Starting test ${name.methodName}"
        def message = Message
            .builder()  // New internal helper class.
            .from('mrhaki@mrhaki.com')  // Method per property
            .to('mail@host.nl')
            .subject('Sample mail')
            .body('Groovy rocks!')
            .build()  // Create instance of Message
            println "Here is message: ${message.toString()}"
        expect:
        message.body == 'Groovy rocks!'
        message.from == 'mrhaki@mrhaki.com'
        message.subject == 'Sample mail'
        message.to == 'mail@host.nl'
    } // first Test
    
    def "test with annotations"() {
        println "--- Starting test ${name.methodName}"
        def message = Message
            .builder()  // New internal helper class.
            .from('mrhaki@mrhaki.com')  // Method per property
            .to('mail@host.nl')
            .subject('mail')
            .body('Groovy rocks! Groovy rocks!')
            .build()  // Create instance of Message
        println "Here is message: ${message.toString()}"
        expect:
        message.body == null
        message.from == 'mrhaki@mrhaki.com'
        message.subject == null
        message.to == 'mail@host.nl'
    } // first Test
    
} // end class

