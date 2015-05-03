package info.shelfunit.properties.nonmutable

import validation.AstImmutableConstructor
import validation.IntAnnotation
import validation.StringAnnotation
import groovy.transform.Immutable
import groovy.transform.ToString

@AstImmutableConstructor
@Immutable
@ToString( includeNames = true )
class TrulyImmutable001 {

    @StringAnnotation( minLength = 5, maxLength = 200 ) //, throwEx = false )
    String firstString
    @StringAnnotation( minLength = 5, maxLength = 20 ) // , throwEx = false )
    String secondString
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int firstInt
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int secondInt
   
} // TrulyImmutable001


