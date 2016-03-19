package info.shelfunit.properties.sample.exception

import validation.ValidDouble

class DoubleExRunner {
    
    @ValidDouble( minValue = 0d, maxValue = 1000d )
    def firstNum
    @ValidDouble( maxValue = 1000d )
    def secondNum
    @ValidDouble( minValue = 10d )
    def thirdNum
    
}

