package info.shelfunit.properties.sample.other

import groovy.transform.ToString
import validation.IntAnnotation
import validation.StringAnnotation

@ToString( includeNames = true )
class Person {
    @StringAnnotation( minLength = 5, maxLength = 20 ) // , throwEx = false )
    String firstName
    @StringAnnotation( minLength = 5, maxLength = 20 ) // , throwEx = false )
    String lastName
    @IntAnnotation( minValue = 0, maxValue = 100 )
    def age

}

