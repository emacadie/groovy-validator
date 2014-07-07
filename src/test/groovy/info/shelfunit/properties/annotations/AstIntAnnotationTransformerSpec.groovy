package info.shelfunit.properties.annotations

import java.lang.reflect.Method
import spock.lang.Specification

import org.junit.Rule
import org.junit.rules.TestName

class AstIntAnnotationTransformerSpec extends Specification {
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method

    @Rule 
    TestName name = new TestName()
    
    def "first test"() {
        println "\n--- Starting test ${name.methodName}"
        println "Hello again"
        expect:
        1 == 1
    }

    def 'transform adds method'() {
        println "\n--- Starting test ${name.methodName}"
        given:
        String classString = '''
import info.shelfunit.properties.annotations.*

class XYZ { 
    @AstIntAnnotation( minValue = 14 )
    int firstNum
}

new XYZ()
'''

        when:
        def instance = new GroovyShell().evaluate(classString)
        Method added = instance.class.declaredMethods.find { it.name == 'added' }

        then:
        !added
    }
    
    def "trying one with two fields"() {
        println "\n--- Starting test ${name.methodName}"
                given:
        String classString = '''
import info.shelfunit.properties.annotations.*

class UVW { 
    @AstIntAnnotation( minValue = 14 )
    int firstNum
    @AstIntAnnotation( minValue = 2, maxValue = 12 )
    int secondNum
}

new UVW()
'''

        when:
        def instance = new GroovyShell().evaluate(classString)
        Method added = instance.class.declaredMethods.find { it.name == 'added' }

        then:
        !added
    }
    
} // AstIntAnnotationTransformerSpec 

