package info.shelfunit.properties.finality

import groovy.transform.ToString
import validation.ValidFloat
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
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

