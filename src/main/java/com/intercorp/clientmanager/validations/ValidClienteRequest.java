package com.intercorp.clientmanager.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClienteAgeAndBirthDateValidator.class)
public @interface ValidClienteRequest {
    String message() default "Error validando el cliente. La fecha de nacimiento es incorrecta";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
