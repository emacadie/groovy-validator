package info.shelfunit.properties.sample

import validation.DoubleAnnotation

class DoubleRunner {
    
    @DoubleAnnotation( minValue = 0d, maxValue = 1000d, throwEx = false )
    def firstNum
    @DoubleAnnotation( maxValue = 1000d, throwEx = false )
    def secondNum
    @DoubleAnnotation( minValue = 10d, throwEx = false )
    def thirdNum
    
}

