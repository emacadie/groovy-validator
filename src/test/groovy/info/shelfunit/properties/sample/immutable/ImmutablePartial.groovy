package info.shelfunit.properties.sample.immutable

import info.shelfunit.properties.annotations.ImmutableValidator
// import info.shelfunit.properties.annotations.DoubleAnnotation
// import info.shelfunit.properties.annotations.FloatAnnotation
// import info.shelfunit.properties.annotations.IntAnnotation
// import info.shelfunit.properties.annotations.LongAnnotation
import info.shelfunit.properties.annotations.StringAnnotation

@ImmutableValidator
class ImmutablePartial {

    @StringAnnotation( minLength = 10 ) 
    String stringWithAnn
    
    String stringWithoutAnn
}

