package info.shelfunit.properties.sample.exception

import info.shelfunit.properties.annotations.AnnotationProcessor
import info.shelfunit.properties.annotations.FloatAnnotation

class FloatExRunner {
    
    static { 
        AnnotationProcessor.process( FloatExRunner, true ) 
    }
    
    @FloatAnnotation( minValue = 0f, maxValue = 1000f )
    def firstNum
    @FloatAnnotation( maxValue = 1000f )
    def secondNum
    @FloatAnnotation( minValue = 10f )
    def thirdNum
}

