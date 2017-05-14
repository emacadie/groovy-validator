package info.shelfunit.properties.finality

import validation.ValidFloat
import validation.ConvenientFinalFieldValidator

@ConvenientFinalFieldValidator
class FinalFloatHolder {
    @ValidFloat( minValue = 73.456f, maxValue = 5027.012f, throwEx = true )
    def firstDefFloat
    @ValidFloat( minValue = 73.456f, maxValue = 5027.012f )
    final def finalDefFloat
    @ValidFloat( minValue = 73.456f, maxValue = 5027.012f, throwEx = true )
    Float firstRealFloat
    @ValidFloat( minValue = 73.456f, maxValue = 5027.012f )
    final float finalRealFloat
    
    Float someOtherFloat
    def anotherObject
    
}

