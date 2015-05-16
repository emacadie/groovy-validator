package info.shelfunit.properties.visibility

import visibility.Hidden
import groovy.transform.ToString 

@ToString( includeNames = true, includeFields = true )
class AgeHolder {
    @Hidden
    int perceivedAge
     
    AgeHolder( argAge ) {
        this.perceivedAge = argAge
    }
     
    def visitYogaRetreat() {
        perceivedAge--
        null
    }
     
    def visitInLaws() {
        perceivedAge++
        null
    }
}

