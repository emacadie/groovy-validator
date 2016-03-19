package info.shelfunit.properties.sample

import validation.ValidFloat
import groovy.transform.ToString

@ToString( includeNames = true )
class FloatRunner {
    
    @ValidFloat( minValue = 0f, maxValue = 1000f, throwEx = false )
    def firstNum
    @ValidFloat( maxValue = 1000f, throwEx = false )
    def secondNum
    @ValidFloat( minValue = 10f, throwEx = false )
    def thirdNum
    
}

