package info.shelfunit.properties.sample.immutable.exception

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class ImmutableExObjectColl001Spec extends Specification { 
    
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {
        
    }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
   
    // for some reason the first time you call a class it does not actually process the annotations
    // comment out the lines for the "junk" object and compare
    def "test without validation"() {
        println "--- Starting test ${name.methodName}"
        def throwaway = new ImmutableExObjectColl001( firstString: "Not Junk", firstInt: 21 )
        println "Just made throwaway, about to make bTest1"
        def bTest1 = new ImmutableExObjectColl001( firstString: "Hello1", firstInt: 200 )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        expect:
            bTest1.firstString == "Hello1"
            bTest1.firstInt == 200
        
    } // end "test without validation"
    
        def "trying one with two fields"() {
        println "\n--- Starting test ${name.methodName}"
                given:
        String classString = '''
package info.shelfunit.somepackage

import validation.ImmutableValidator
import validation.ValidDouble
import validation.ValidFloat
import validation.ValidInt
import validation.ValidLong
import validation.ValidString

@ImmutableValidator
class UVW {
    @ValidString( minLength = 5, maxLength = 10 )
    String firstString
    @ValidString( maxLength = 150 )
    String secondString
    @ValidDouble( minValue = 10d, maxValue = 100d )
    double firstDouble
    @ValidFloat( minValue = 10f, maxValue = 100f )
    float firstFloat
    @ValidInt( minValue = 10, maxValue = 100 )
    int firstInt
    @ValidLong( maxValue = 100L )
    long firstLong
}

new UVW(firstString: "hello", firstInt: 222)
'''

        when:
            def instance = new GroovyShell().evaluate( classString )
            Method added = instance.class.declaredMethods.find { it.name == 'added' }

        then:
            !added
    }
    
}

