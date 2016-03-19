package info.shelfunit.properties.sample.immutable

import validation.ImmutableValidator
import validation.ValidString

@ImmutableValidator
class ImmutableRegEx {
   
    @ValidString( minLength = 10, regEx = /^.*?[Gg]roovy.*$/ ) 
    String groovyString
    @ValidString( regEx = /\d{4}?-\d\d-\d\d/  ) // 
    String yearWithDay
    @ValidString( minLength = 6, maxLength = 10, regEx = /^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}$/ )
    String password
}

