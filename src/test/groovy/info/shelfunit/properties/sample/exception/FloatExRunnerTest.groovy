package info.shelfunit.properties.sample.exception

// import info.shelfunit.properties.annotations.GroovyValidatorException
import spock.lang.Specification

class FloatExRunnerTest extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    def "test the no arg constructor"() {
        def fr = new FloatExRunner()
        when:
        fr.firstNum  = 50f
        fr.secondNum = 50f
        fr.thirdNum  = 50f
        then:
        fr.firstNum  == 50f
        fr.secondNum == 50f
        fr.thirdNum  == 50f
        
        when:
        fr.firstNum  = -2f
        fr.secondNum = -2f
        fr.thirdNum  = 9f
        then:
        fr.firstNum  == 50f
        fr.secondNum == 50f
        fr.thirdNum  == 50f
        thrown( Exception )
        
        when:
        fr.firstNum  = 1001f
        fr.secondNum = 1001f
        fr.thirdNum  = 1001f
        then:
        thrown( Exception )

    } // end "test the no arg constructor"
    
    def "test just outside the ranges"() {
        def fr = new FloatExRunner()
        when:
        fr.firstNum  = 50f
        fr.secondNum = 50f
        fr.thirdNum  = 50f
        then:
        fr.firstNum  == 50f
        fr.secondNum == 50f
        fr.thirdNum  == 50f
        
        when:
        fr.firstNum  = -0.1f
        fr.secondNum = -0.1f
        fr.thirdNum  = 9.99f
        then:
        thrown( Exception )
        fr.firstNum  == 50f
        fr.secondNum == 50f
        fr.thirdNum  == 50f
        
        when:
        fr.firstNum  = 1000.1f
        fr.secondNum = 1000.1f
        fr.thirdNum  = 1001f
        then:
        thrown( Exception )

    } // end "test just outside the ranges"
    
    def "test trying another number"() {
        def fr = new FloatExRunner()
        when:
        fr.firstNum  = 50f
        fr.secondNum = 50f
        fr.thirdNum  = 50f
        then:
        fr.firstNum  == 50f
        fr.secondNum == 50f
        fr.thirdNum  == 50f
        
        when:
        fr.firstNum  = 50.3f
        fr.secondNum = 50
        fr.thirdNum  = 'hello'
        then:
        thrown( Exception )
        fr.firstNum  == 50.3f
        fr.secondNum == 50
        fr.thirdNum  == 50.0

    } // end "test just outside the ranges"

}

