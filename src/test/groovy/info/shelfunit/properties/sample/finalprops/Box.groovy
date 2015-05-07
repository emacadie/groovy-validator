package info.shelfunit.properties.sample.finalprops

import groovy.transform.ToString
import validation.IntAnnotation
import validation.StringAnnotation

@ToString( includeNames = true )
class Box {
    // @IntAnnotation( minValue = 10 )
    final int secondInt
    final int firstInt
    
    // @StringAnnotation( minLength = 5 )
    final String firstString
    final String secondString
}

