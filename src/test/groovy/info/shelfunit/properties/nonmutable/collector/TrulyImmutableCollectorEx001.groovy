package info.shelfunit.properties.nonmutable.collector

import validation.ImmutableValidator
import validation.ValidInt
import validation.ValidString

@ImmutableValidator
class TrulyImmutableCollectorEx001 {

    @ValidString( minLength = 5, maxLength = 200 ) // , throwEx = false )
    String firstString
    @ValidString( minLength = 5, maxLength = 20 )
    String secondString
    @ValidInt( minValue = 30, maxValue = 400 )
    int firstInt
    @ValidInt( minValue = 30, maxValue = 400 )
    int secondInt
   
} // TrulyImmutable001


