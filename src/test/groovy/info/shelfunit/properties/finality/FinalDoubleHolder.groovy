package info.shelfunit.properties.finality

import groovy.transform.ToString
import validation.ValidDouble
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
class FinalDoubleHolder {
    @ValidDouble( minValue = 73.456d, maxValue = 5027.012d, throwEx = true )
    def firstDefDouble
    @ValidDouble( minValue = 73.456d, maxValue = 5027.012d )
    final def finalDefDouble
    @ValidDouble( minValue = 73.456d, maxValue = 5027.012d, throwEx = true )
    Double firstRealDouble
    @ValidDouble( minValue = 73.456d, maxValue = 5027.012d )
    final double finalRealDouble
    
    Double someOtherDouble
    def anotherObject
    
}

