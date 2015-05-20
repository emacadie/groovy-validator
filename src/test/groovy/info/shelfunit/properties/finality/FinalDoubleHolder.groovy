package info.shelfunit.properties.finality

import groovy.transform.ToString
import validation.DoubleAnnotation
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
class FinalDoubleHolder {
    @DoubleAnnotation( minValue = 73.456d, maxValue = 5027.012d, throwEx = true )
    def firstDefDouble
    @DoubleAnnotation( minValue = 73.456d, maxValue = 5027.012d )
    final def finalDefDouble
    @DoubleAnnotation( minValue = 73.456d, maxValue = 5027.012d, throwEx = true )
    Double firstRealDouble
    @DoubleAnnotation( minValue = 73.456d, maxValue = 5027.012d )
    final double finalRealDouble
    
    Double someOtherDouble
    def anotherObject
    
}

