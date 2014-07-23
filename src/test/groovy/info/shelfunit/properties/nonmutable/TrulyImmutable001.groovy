package info.shelfunit.properties.nonmutable

import info.shelfunit.properties.annotations.AstImmutableConstructor
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.Immutable
import groovy.transform.ToString

@AstImmutableConstructor
@Immutable
@ToString( includeNames = true )
class TrulyImmutable001 {

    @StringAnnotation( minLength = 5, maxLength = 200 )
    String firstString
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String secondString
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int firstInt
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int secondInt
   
} // TrulyImmutable001


