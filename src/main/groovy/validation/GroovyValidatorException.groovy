package validation

/**
This is a class that will catch an exception in the validating classes. If it is enabled, it will give messages like
<pre>
5 is a long outside the values 10 and 9223372036854775807
</pre>
*/

class GroovyValidatorException extends Exception {
    GroovyValidatorException() {
        super()
    }
    /**
    */
    GroovyValidatorException( String message ) {
        super( message )
    }
}

