package com.intercorp.clientmanager.validations;

import com.intercorp.clientmanager.dto.NewClientRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ClienteAgeAndBirthDateValidator implements ConstraintValidator<ValidClienteRequest, NewClientRequest> {
    public void initialize(ValidClienteRequest constraint) {
    }

    public boolean isValid(NewClientRequest newClientRequest, ConstraintValidatorContext context) {
        if (newClientRequest.getFechaDeNacimiento() == null) return false;
        final ZonedDateTime zonedBirthDate = newClientRequest.getFechaDeNacimiento().atZone(ZoneOffset.UTC);
        return  newClientRequest.getEdad() - (calculateAge(zonedBirthDate.toLocalDate(), LocalDate.now())) == 0;
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}

