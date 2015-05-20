package info.shelfunit.properties.finality

import groovy.transform.ToString
import validation.FloatAnnotation
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
class FinalFloatHolder {
    @FloatAnnotation( minValue = 73.456f, maxValue = 5027.012f, throwEx = true )
    def firstDefFloat
    @FloatAnnotation( minValue = 73.456f, maxValue = 5027.012f )
    final def finalDefFloat
    @FloatAnnotation( minValue = 73.456f, maxValue = 5027.012f, throwEx = true )
    Float firstRealFloat
    @FloatAnnotation( minValue = 73.456f, maxValue = 5027.012f )
    final float finalRealFloat
    
    Float someOtherFloat
    def anotherObject
    
}

