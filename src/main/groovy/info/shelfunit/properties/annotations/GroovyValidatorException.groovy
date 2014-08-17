package info.shelfunit.properties.annotations

/**
This is a class that will catch an exception in the validating classes.
*/

class GroovyValidatorException extends Exception {
    GroovyValidatorException() {
        super()
    }
    
    GroovyValidatorException( String message ) {
        super( message )
    }
}

