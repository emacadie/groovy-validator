package info.shelfunit.properties.finality

import groovy.transform.ToString
import validation.LongAnnotation
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
class FinalLongHolder {
    @LongAnnotation( minValue = 1000L, maxValue = 1000000000L, divisorSet= [ 3L, 5L ], throwEx = true )
    def firstDefLong
    @LongAnnotation( minValue = 1000L, maxValue = 1000000000L, divisorSet= [ 3L, 5L ] )
    final def finalDefLong
    @LongAnnotation( minValue = 1000L, maxValue = 1000000000L, divisorSet= [ 3L, 5L ], throwEx = true )
    long firstRealLong
    @LongAnnotation( minValue = 1000L, maxValue = 1000000000L, divisorSet= [ 3L, 5L ] )
    final long finalRealLong
    
    long someOtherLong
    def anotherObject
    
}

