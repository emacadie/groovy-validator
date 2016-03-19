package info.shelfunit.properties.finality

import groovy.transform.ToString
import validation.ValidInt
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
class Car {
    @ValidInt( minValue = 10, throwEx = false )
    int miles
    @ValidInt( minValue = 1990 )
    final int year
}

