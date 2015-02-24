package info.shelfunit.properties.sample

import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.ToString

@ToString( includeNames = true )
class RegExSubject {
    
    // @StringAnnotation( minLength = 10, regEx = /^.*?[Gg]roovy.*$/ ) // "^.*?[Gg]roovy.*\$" okay, ~/^.*?[Gg]roovy.*\$/ did not work
    String groovyString
    // @StringAnnotation( regEx = /\d{4}?-\d\d-\d\d/ ) // "\\d{4}?-\\d\\d-\\d\\d"
    String yearWithDay
    // @StringAnnotation( minLength = 6, maxLength = 10, regEx = "^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}\$" )
    String password
    // @StringAnnotation( minLength = 6, maxLength = 10, regEx = "([A-Za-z]+)\\s+([A-Z]\\.)?\\s+([A-Za-z]+)" )
    // String passwordWithComment
}

