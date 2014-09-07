package info.shelfunit.properties.sample.immutable

import info.shelfunit.properties.annotations.ImmutableValidator
import info.shelfunit.properties.annotations.StringAnnotation

@ImmutableValidator
class ImmutableRegEx {
   
    // /^.*?[Gg]roovy.*$/
    // "^.*?[Gg]roovy.*" - works w/adding quotes in AST
    @StringAnnotation( minLength = 10 ) // , regEx = /^.*?[Gg]roovy.*/ ) 
    String groovyString
    // /\d{4}?-\d\d-\d\d/ 
    // "\\\\d{4}?-\\\\d\\\\d-\\\\d\\\\d" - works w/adding quotes in AST
    @StringAnnotation( regEx = /\d{4}?-\d\d-\d\d/  ) // 
    // @StringAnnotation( minLength = 6, maxLength = 10 )
    String yearWithDay
    // "^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}\$"
    // /^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}$/
    // @StringAnnotation( minLength = 6, maxLength = 10, regEx = "^(?=.*[0-9].*[0-9])[0-9a-zA-Z]\\\\{8,12\\\\}" )
    @StringAnnotation( minLength = 6, maxLength = 10 )
    String password
   
}

