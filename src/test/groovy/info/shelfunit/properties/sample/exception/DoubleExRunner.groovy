package info.shelfunit.properties.sample.exception

import validation.DoubleAnnotation

class DoubleExRunner {
    
    @DoubleAnnotation( minValue = 0d, maxValue = 1000d )
    def firstNum
    @DoubleAnnotation( maxValue = 1000d )
    def secondNum
    @DoubleAnnotation( minValue = 10d )
    def thirdNum
    
}

