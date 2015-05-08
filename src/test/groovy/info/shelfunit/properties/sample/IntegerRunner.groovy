package info.shelfunit.properties.sample

import validation.IntAnnotation

class IntegerRunner {
    
    @IntAnnotation( minValue = 0, maxValue = 1000, throwEx = false )
    def numAsDef
    @IntAnnotation( minValue = 100, maxValue = 1000, throwEx = false )
    int numAsInt

}

