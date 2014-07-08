package info.shelfunit.properties.sample.other

import groovy.transform.ToString
import info.shelfunit.properties.annotations.IntProcessor
import info.shelfunit.properties.annotations.StringAnnotation

@ToString( includeNames = true )
class Person {
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String firstName
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String lastName
    @IntAnnotation( minValue = 0, maxLength = 100 )
    def age

}

