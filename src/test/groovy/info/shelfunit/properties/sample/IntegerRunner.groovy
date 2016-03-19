package info.shelfunit.properties.sample

import validation.ValidInt

class IntegerRunner {
    
    @ValidInt( minValue = 0, maxValue = 1000, throwEx = false )
    def numAsDef
    @ValidInt( minValue = 100, maxValue = 1000, throwEx = false )
    int numAsInt

}

