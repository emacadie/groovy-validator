package info.shelfunit.properties.sample.divisor

import validation.ValidInt
import validation.ImmutableValidator 

@ImmutableValidator
class ImmutableIntDivisor {
    
    @ValidInt( minValue = 10 , divisorSet = [ 5 ] )
    int intWithDiv
    
    @ValidInt( divisorSet = [ 7 ] )
    int intWithDiv002
    
    @ValidInt( maxValue = 40, divisorSet = [ 3, 4 ] )
    int intWithDivArray
    
    @ValidInt( maxValue = 40, divisorSet = [ 0 ] )
    int intWithZeroDiv
}

/*
package info.shelfunit.properties.sample.divisor

import validation.ValidInt as ValidInt
import validation.ImmutableValidator as ImmutableValidator

@groovy.transform.EqualsAndHashCode
// @groovy.transform.ImmutableBase
// @groovy.transform.KnownImmutable
// @groovy.transform.MapConstructor
@groovy.transform.TupleConstructor
@groovy.transform.ToString(includeNames = true)
// @validation.AstImmutableConstructor
public final class  ImmutableIntDivisor extends Object implements groovy.lang.GroovyObject {

    // @validation.ValidInt(minValue = 10, divisorSet = [5])
    private final int intWithDiv
    // @validation.ValidInt(divisorSet = [7])
    private final int intWithDiv002
    // @validation.ValidInt(maxValue = 40, divisorSet = [3, 4])
    private final int intWithDivArray
    // @validation.ValidInt(maxValue = 40, divisorSet = [0])
    private final int intWithZeroDiv
    private static org.codehaus.groovy.reflection.ClassInfo $staticClassInfo
    public static transient boolean __$stMC
    private transient groovy.lang.MetaClass metaClass

    // @groovy.transform.Generated
    public ImmutableIntDivisor(Map args) {
        // metaClass = / * BytecodeExpression * /
        if ( args == null) {
            args = [:]
        }
        println "here is args: ${args}"
        org.codehaus.groovy.transform.ImmutableASTTransformation.checkPropNames(this, args)
        if (args.containsKey('intWithDiv')) {
            this .intWithDiv = args.get('intWithDiv')
        }
        if (args.containsKey('intWithDiv002')) {
            this .intWithDiv002 = args.get('intWithDiv002')
        }
        if (args.containsKey('intWithDivArray')) {
            this .intWithDivArray = args.get('intWithDivArray')
        }
        if (args.containsKey('intWithZeroDiv')) {
            this .intWithZeroDiv = args.get('intWithZeroDiv')
        }
    }

    // @groovy.transform.Generated
    public ImmutableIntDivisor(int intWithDiv, int intWithDiv002, int intWithDivArray, int intWithZeroDiv) {
        // metaClass = / * BytecodeExpression * /
                this .intWithDiv = intWithDiv
        this .intWithDiv002 = intWithDiv002
        this .intWithDivArray = intWithDivArray
        this .intWithZeroDiv = intWithZeroDiv
    }

    // @groovy.transform.Generated
    public ImmutableIntDivisor(int intWithDiv, int intWithDiv002, int intWithDivArray) {
        // this((( intWithDiv ) as int), (( intWithDiv002 ) as int), (( intWithDivArray ) as int), ((0) as int))
        this( intWithDiv, intWithDiv002, intWithDivArray, 0 )
    }

    // @groovy.transform.Generated
    public ImmutableIntDivisor(int intWithDiv, int intWithDiv002) {
        // this((( intWithDiv ) as int), (( intWithDiv002 ) as int), ((0) as int), ((0) as int))
        this( intWithDiv, intWithDiv002, 0, 0 )
    }

    // @groovy.transform.Generated
    public ImmutableIntDivisor(int intWithDiv) {
        // this ((( intWithDiv ) as int), ((0) as int), ((0) as int), ((0) as int))
        this( intWithDiv, 0 , 0, 0  )
    }

    // @groovy.transform.Generated
    public ImmutableIntDivisor() {
        this (((0) as int), ((0) as int), ((0) as int), ((0) as int))
    }

    // @groovy.transform.Generated
    public int hashCode() {
        Object _result = org.codehaus.groovy.util.HashCodeHelper.initHash()
        if (this.getIntWithDiv() !== this ) {
            _result = org.codehaus.groovy.util.HashCodeHelper.updateHash(_result, this.getIntWithDiv())
        }
        if (this.getIntWithDiv002() !== this ) {
            _result = org.codehaus.groovy.util.HashCodeHelper.updateHash(_result, this.getIntWithDiv002())
        }
        if (this.getIntWithDivArray() !== this ) {
            _result = org.codehaus.groovy.util.HashCodeHelper.updateHash(_result, this.getIntWithDivArray())
        }
        if (this.getIntWithZeroDiv() !== this ) {
            _result = org.codehaus.groovy.util.HashCodeHelper.updateHash(_result, this.getIntWithZeroDiv())
        }
        return _result
    }

    // @groovy.transform.Generated
    public boolean canEqual( Object other) {
        return other instanceof ImmutableIntDivisor
    }

    // @groovy.transform.Generated
    public boolean equals( Object other) {
        if ( other == null) {
            return false
        }
        if (this.is(other)) {
            return true
        }
        if (!( other instanceof ImmutableIntDivisor)) {
            return false
        }
        // ImmutableIntDivisor otherTyped = (( other ) as ImmutableIntDivisor)
        ImmutableIntDivisor otherTyped = ( ImmutableIntDivisor ) other
        if (!(otherTyped.canEqual( this ))) {
            return false
        }
        if (!(this.getIntWithDiv() == otherTyped.getIntWithDiv())) {
            return false
        }
        if (!(this.getIntWithDiv002() == otherTyped.getIntWithDiv002())) {
            return false
        }
        if (!(this.getIntWithDivArray() == otherTyped.getIntWithDivArray())) {
            return false
        }
        if (!(this.getIntWithZeroDiv() == otherTyped.getIntWithZeroDiv())) {
            return false
        }
        return true
    }

    // @groovy.transform.Generated
    public String toString() {
        Object _result = new StringBuilder()
        Object $toStringFirst = true
        _result.append('info.shelfunit.properties.sample.divisor.ImmutableIntDivisor(')
        if ( $toStringFirst ) {
            $toStringFirst = false
        } else {
            _result.append(', ')
        }
        _result.append('intWithDiv:')
        _result.append(org.codehaus.groovy.runtime.InvokerHelper.toString(this.getIntWithDiv()))
        if ( $toStringFirst ) {
            $toStringFirst = false
        } else {
            _result.append(', ')
        }
        _result.append('intWithDiv002:')
        _result.append(org.codehaus.groovy.runtime.InvokerHelper.toString(this.getIntWithDiv002()))
        if ( $toStringFirst ) {
            $toStringFirst = false
        } else {
            _result.append(', ')
        }
        _result.append('intWithDivArray:')
        _result.append(org.codehaus.groovy.runtime.InvokerHelper.toString(this.getIntWithDivArray()))
        if ( $toStringFirst ) {
            $toStringFirst = false
        } else {
            _result.append(', ')
        }
        _result.append('intWithZeroDiv:')
        _result.append(org.codehaus.groovy.runtime.InvokerHelper.toString(this.getIntWithZeroDiv()))
        _result.append(')')
        return _result.toString()
    }

    public static Object createValidatedObject( HashMap argMap, boolean throwException) {
        HashMap newMap = [:]
        Object val
        Object theMatch
        Object divSet
        Object exceptionStringList = []
        val = argMap [ 'intWithDiv']
        if ( val == null || 10 <= val && val <= 2147483647 && [5].find({
            return val % it == 0
        }) != null) {
            newMap [ 'intWithDiv'] = val
        } else {
            if ( throwException ) {
                exceptionStringList.add( val + ' is a java.lang.Integer outside the range 10 to 2147483647 or it is not divisible by anything in the set [5]')
            }
        }
        val = argMap [ 'intWithDiv002']
        if ( val == null || 0 <= val && val <= 2147483647 && [7].find({
            return val % it == 0
        }) != null) {
            newMap [ 'intWithDiv002'] = val
        } else {
            if ( throwException ) {
                exceptionStringList.add( val + ' is a java.lang.Integer outside the range 0 to 2147483647 or it is not divisible by anything in the set [7]')
            }
        }
        val = argMap [ 'intWithDivArray']
        if ( val == null || 0 <= val && val <= 40 && [3, 4].find({
            return val % it == 0
        }) != null) {
            newMap [ 'intWithDivArray'] = val
        } else {
            if ( throwException ) {
                exceptionStringList.add( val + ' is a java.lang.Integer outside the range 0 to 40 or it is not divisible by anything in the set [3, 4]')
            }
        }
        val = argMap [ 'intWithZeroDiv']
        if ( val == null || 0 <= val && val <= 40 && [1].find({
            return val % it == 0
        }) != null) {
            newMap [ 'intWithZeroDiv'] = val
        } else {
            if ( throwException ) {
                exceptionStringList.add( val + ' is a java.lang.Integer outside the range 0 to 40 or it is not divisible by anything in the set [1]')
            }
        }
        if ( throwException && exceptionStringList.size() > 0) {
            Object exMessage = exceptionStringList.join( System.lineSeparator())
            throw new Exception('Groovy validation exception:' + System.lineSeparator() + exMessage )
        }
        return new ImmutableIntDivisor(newMap)
    }

    // @groovy.transform.Generated
    public static Object createValidatedObject( HashMap argMap) {
        // return this.createValidatedObject((( argMap ) as HashMap), ((false) as boolean))
        return this.createValidatedObject( argMap, false )
    }
/* -- lots of groovy stuff commented out
    protected groovy.lang.MetaClass $getStaticMetaClass() {
    }

    // @groovy.transform.Generated
    // @groovy.transform.Internal
    public groovy.lang.MetaClass getMetaClass() {
    }

    // @groovy.transform.Generated
    // @groovy.transform.Internal
    public void setMetaClass(groovy.lang.MetaClass mc) {
    }

    // @groovy.transform.Generated
    public final int getIntWithDiv() {
    }

    // @groovy.transform.Generated
    public final int getIntWithDiv002() {
    }

    // @groovy.transform.Generated
    public final int getIntWithDivArray() {
    }

    // @groovy.transform.Generated
    public final int getIntWithZeroDiv() {
    }

    public int super$1$hashCode() {
    }

    public String super$1$toString() {
    }

    public boolean super$1$equals( Object param0) {
    }
-- end commenting groovy stuff * /
}
*/


