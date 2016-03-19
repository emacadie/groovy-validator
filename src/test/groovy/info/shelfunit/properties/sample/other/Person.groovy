package info.shelfunit.properties.sample.other

import groovy.transform.ToString
import validation.ValidInt
import validation.ValidString

@ToString( includeNames = true )
class Person {
    @ValidString( minLength = 5, maxLength = 20 , throwEx = false )
    String firstName
    @ValidString( minLength = 5, maxLength = 20, throwEx = false )
    String lastName
    @ValidInt( minValue = 0, maxValue = 100, throwEx = false )
    def age

}

