package info.shelfunit.properties.annotations

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.TYPE )
public @interface ImmutableAnnotation {
  // public long minValue() default 0L
  // public long maxValue() default 9223372036854775807L // Long.MAX_VALUE 
}

