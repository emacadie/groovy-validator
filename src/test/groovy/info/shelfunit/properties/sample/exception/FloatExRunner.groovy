package info.shelfunit.properties.sample.exception

import validation.ValidFloat
import groovy.transform.ToString

@ToString( includeNames = true )
class FloatExRunner {

    @ValidFloat( minValue = 0f, maxValue = 1000f )
    def firstNum
    @ValidFloat( maxValue = 1000f )
    def secondNum
    @ValidFloat( minValue = 10f )
    def thirdNum
}

