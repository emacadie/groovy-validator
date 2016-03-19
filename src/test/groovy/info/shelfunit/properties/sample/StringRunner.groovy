package info.shelfunit.properties.sample

import groovy.transform.ToString
import validation.ValidString

@ToString( includeNames = true )
class StringRunner {
    
    @ValidString( minLength = 0, maxLength = 20, regEx = /^.*ee.*$/, throwEx = false )
    def stringAsDef
    @ValidString( minLength = 0, maxLength = 20, regEx = /^.*oo.*$/, throwEx = false )
    String stringAsString

}

