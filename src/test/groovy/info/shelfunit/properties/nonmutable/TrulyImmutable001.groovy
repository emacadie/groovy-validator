package info.shelfunit.properties.nonmutable

import validation.AstImmutableConstructor
import validation.ValidInt
import validation.ValidString
import groovy.transform.Immutable
import groovy.transform.ToString

@AstImmutableConstructor
@Immutable
@ToString( includeNames = true )
class TrulyImmutable001 {

    @ValidString( minLength = 5, maxLength = 200 ) //, throwEx = false )
    String firstString
    @ValidString( minLength = 5, maxLength = 20 ) // , throwEx = false )
    String secondString
    @ValidInt( minValue = 30, maxValue = 400 )
    int firstInt
    @ValidInt( minValue = 30, maxValue = 400 )
    int secondInt
   
} // TrulyImmutable001


