package chapter20_annotations;

public @interface Uniqueness {
	Constraints constraints() default @Constraints(unique = true);
}
