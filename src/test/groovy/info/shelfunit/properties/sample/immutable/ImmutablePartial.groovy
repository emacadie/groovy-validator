package info.shelfunit.properties.sample.immutable

import info.shelfunit.properties.annotations.ImmutableValidator
// import info.shelfunit.properties.annotations.DoubleAnnotation
// import info.shelfunit.properties.annotations.FloatAnnotation
import info.shelfunit.properties.annotations.IntAnnotation
// import info.shelfunit.properties.annotations.LongAnnotation
import info.shelfunit.properties.annotations.StringAnnotation

@ImmutableValidator(includePackage = false)
class ImmutablePartial {

    @StringAnnotation( minLength = 10 ) 
    String stringWithAnn
    
    String stringWithoutAnn
    
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int intWithAnn
    
    int intWithoutAnn
} // ImmutablePartial

