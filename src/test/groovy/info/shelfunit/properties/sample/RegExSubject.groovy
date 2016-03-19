package info.shelfunit.properties.sample

import validation.ValidString
import groovy.transform.ToString

@ToString( includeNames = true )
class RegExSubject {
    
    @ValidString( minLength = 10, regEx = /^.*?[Gg]roovy.*$/, throwEx = false ) // "^.*?[Gg]roovy.*\$" okay, ~/^.*?[Gg]roovy.*\$/ did not work
    String groovyString
    @ValidString( regEx = /\d{4}?-\d\d-\d\d/, throwEx = false ) // "\\d{4}?-\\d\\d-\\d\\d"
    String yearWithDay
    @ValidString( minLength = 6, maxLength = 10, regEx = "^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}\$" , throwEx = false )
    String password
    @ValidString( minLength = 6, maxLength = 10, regEx = "([A-Za-z]+)\\s+([A-Z]\\.)?\\s+([A-Za-z]+)", throwEx = false )
    String passwordWithComment
}

