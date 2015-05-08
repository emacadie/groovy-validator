package info.shelfunit.properties.sample.exception

// import validation.AnnotationProcessor
import validation.FloatAnnotation
import groovy.transform.ToString

@ToString( includeNames = true )
class FloatExRunner {
    
    static { 
        // AnnotationProcessor.process( FloatExRunner, true ) 
    }
    
    @FloatAnnotation( minValue = 0f, maxValue = 1000f )
    def firstNum
    @FloatAnnotation( maxValue = 1000f )
    def secondNum
    @FloatAnnotation( minValue = 10f )
    def thirdNum
}

