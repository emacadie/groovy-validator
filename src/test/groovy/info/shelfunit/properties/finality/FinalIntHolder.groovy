package info.shelfunit.properties.finality

import groovy.transform.ToString
import validation.IntAnnotation
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
class FinalIntHolder {
    @IntAnnotation( minValue = 50, maxValue = 2000, divisorSet= [ 3, 5 ], throwEx = true )
    def firstDefInt
    @IntAnnotation( minValue = 50, maxValue = 2000, divisorSet= [ 3, 5 ] )
    final def finalDefInt
    @IntAnnotation( minValue = 50, maxValue = 2000, divisorSet= [ 3, 5 ], throwEx = true )
    int firstRealInt
    @IntAnnotation( minValue = 50, maxValue = 2000, divisorSet= [ 3, 5 ] )
    final int finalRealInt
    
    int someOtherInt
    def anotherObject
    
}

