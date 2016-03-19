package info.shelfunit.properties.sample.exception

import validation.ValidLong

class LongExRunner {
    
    @ValidLong( minValue = 0L, maxValue = 1000L )
    def firstNum
    @ValidLong( maxValue = 1000L )
    def secondNum
    @ValidLong( minValue = 2147483647L ) // was 10
                                
    def thirdNum
}

