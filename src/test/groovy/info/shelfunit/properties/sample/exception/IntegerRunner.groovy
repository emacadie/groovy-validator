package info.shelfunit.properties.sample.exception

import validation.IntAnnotation

class IntegerRunner {
    
    @IntAnnotation( minValue = 0, maxValue = 1000 )
    def numAsDef
    @IntAnnotation( minValue = 100, maxValue = 1000 )
    int numAsInt

}

