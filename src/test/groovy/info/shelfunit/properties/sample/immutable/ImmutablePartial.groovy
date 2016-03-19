package info.shelfunit.properties.sample.immutable

import validation.ImmutableValidator
import validation.ValidInt
import validation.ValidString

@ImmutableValidator(includePackage = false)
class ImmutablePartial {

    @ValidString( minLength = 10 ) 
    String stringWithAnn
    
    String stringWithoutAnn
    
    @ValidInt( minValue = 10, maxValue = 100 )
    int intWithAnn
    
    int intWithoutAnn
} // ImmutablePartial

