package info.shelfunit.properties.sample

import groovy.transform.ToString
import validation.StringAnnotation

@ToString( includeNames = true )
class StringRunner {
    
    @StringAnnotation( minLength = 0, maxLength = 20, regEx = /^.*ee.*$/, throwEx = false )
    def stringAsDef
    @StringAnnotation( minLength = 0, maxLength = 20, regEx = /^.*oo.*$/, throwEx = false )
    String stringAsString

}

