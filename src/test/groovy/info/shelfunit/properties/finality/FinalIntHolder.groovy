package info.shelfunit.properties.finality

import validation.ValidInt
import validation.ConvenientFinalFieldValidator

@ConvenientFinalFieldValidator
class FinalIntHolder {
    @ValidInt( minValue = 50, maxValue = 2000, divisorSet= [ 3, 5 ], throwEx = true )
    def firstDefInt
    @ValidInt( minValue = 50, maxValue = 2000, divisorSet= [ 3, 5 ] )
    final def finalDefInt
    @ValidInt( minValue = 50, maxValue = 2000, divisorSet= [ 3, 5 ], throwEx = true )
    int firstRealInt
    @ValidInt( minValue = 50, maxValue = 2000, divisorSet= [ 3, 5 ] )
    final int finalRealInt
    
    int someOtherInt
    def anotherObject
    
}

