package info.shelfunit.properties.sample

import validation.AnnotationProcessor
import validation.FloatAnnotation
import groovy.transform.ToString

@ToString( includeNames = true )
class FloatRunner {
    
    static { 
        AnnotationProcessor.process( FloatRunner.class ) 
    }
    
    @FloatAnnotation( minValue = 0f, maxValue = 1000f )
    def firstNum
    @FloatAnnotation( maxValue = 1000f )
    def secondNum
    @FloatAnnotation( minValue = 10f )
    def thirdNum
    
}

