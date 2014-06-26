package info.shelfunit.properties.annotations

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )

public @interface DoubleAnnotation {
  public double minValue() default 0.0d
  public double maxValue() default 1.7976931348623157E308d // Double.MAX_VALUE 
}


