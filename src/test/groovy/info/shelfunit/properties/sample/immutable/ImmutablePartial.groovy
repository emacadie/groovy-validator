package info.shelfunit.properties.sample.immutable

import validation.ImmutableValidator
import validation.IntAnnotation
import validation.StringAnnotation

@ImmutableValidator(includePackage = false)
class ImmutablePartial {

    // @StringAnnotation( minLength = 10 ) 
    String stringWithAnn
    
    String stringWithoutAnn
    
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int intWithAnn
    
    int intWithoutAnn
} // ImmutablePartial

