package info.shelfunit.properties.sample.exception

import info.shelfunit.properties.annotations.AnnotationProcessor
import info.shelfunit.properties.annotations.DoubleAnnotation

class DoubleRunner {
    
    static { 
        AnnotationProcessor.process( DoubleRunner, true ) 
    }
    
    @DoubleAnnotation( minValue = 0d, maxValue = 1000d )
    def firstNum
    @DoubleAnnotation( maxValue = 1000d )
    def secondNum
    @DoubleAnnotation( minValue = 10d )
    def thirdNum
    
}

