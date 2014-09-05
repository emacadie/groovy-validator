package info.shelfunit.properties.sample

import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.ToString

@ToString( includeNames = true )
class RegExSubject {
    
    @StringAnnotation( minLength = 10, regEx = "^.*?[Gg]roovy.*\$" )
    String groovyString
    @StringAnnotation( regEx = "\\d{4}?-\\d\\d-\\d\\d" )
    String yearWithDay
    @IntAnnotation( minValue = 0, maxValue = 1000 )
    int firstInt
    @IntAnnotation( minValue = 1000, maxValue = 2000 )
    int secondInt
}

