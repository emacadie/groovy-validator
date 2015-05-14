package info.shelfunit.properties.finality

import groovy.transform.ToString
import groovy.transform.TupleConstructor 
import validation.IntAnnotation
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
@TupleConstructor
class Car {
    @IntAnnotation( minValue = 10, throwEx = false )
    int miles
    @IntAnnotation( minValue = 1990 )
    final int year
    
    // Car( theYear ) { year = theYear }
}

