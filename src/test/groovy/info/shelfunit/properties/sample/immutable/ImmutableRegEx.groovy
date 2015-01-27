package info.shelfunit.properties.sample.immutable

import info.shelfunit.properties.annotations.ImmutableValidator
import info.shelfunit.properties.annotations.StringAnnotation

@ImmutableValidator
class ImmutableRegEx {
   
    // @StringAnnotation( minLength = 10, regEx = /^.*?[Gg]roovy.*$/ ) 
    String groovyString
    // @StringAnnotation( regEx = /\d{4}?-\d\d-\d\d/  ) // 
    String yearWithDay
    // @StringAnnotation( minLength = 6, maxLength = 10, regEx = /^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}$/ )
    String password
}

