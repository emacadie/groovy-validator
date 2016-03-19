package info.shelfunit.properties.sample

import validation.ValidDouble

class DoubleRunner {
    
    @ValidDouble( minValue = 0d, maxValue = 1000d, throwEx = false )
    def firstNum
    @ValidDouble( maxValue = 1000d, throwEx = false )
    def secondNum
    @ValidDouble( minValue = 10d, throwEx = false )
    def thirdNum
    
}

