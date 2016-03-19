package info.shelfunit.properties.sample

import validation.ValidLong

class LongRunner {
    
    @ValidLong( minValue = 0L, maxValue = 1000L, throwEx = false )
    def firstNum
    @ValidLong( maxValue = 1000L, throwEx = false )
    def secondNum
    @ValidLong( minValue = 10L, throwEx = false )
    def thirdNum
}

