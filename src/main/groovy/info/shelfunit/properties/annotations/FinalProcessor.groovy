package info.shelfunit.properties.annotations

class FinalProcessor {
    static process( Class theClass, boolean throwException = false ) {
        theClass.metaClass.properties.each { theProp ->
            println "theProp is a ${theProp.class.name}"
            println "-- Prop name is ${theProp.name}"
            
        }
        println "\n\n"
        theClass.declaredFields.each { theField ->
            println "theField is a ${theField.class.name}, with name ${theField.name}"
        }
        /*
        metaClass.constructor = { Map argMap ->
            
        }
        */
    } // end process
}

