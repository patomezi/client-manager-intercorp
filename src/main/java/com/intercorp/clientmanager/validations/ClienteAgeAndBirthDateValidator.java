package com.intercorp.clientmanager.validations;

import com.intercorp.clientmanager.dto.ClientRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ClienteAgeAndBirthDateValidator implements ConstraintValidator<ValidClienteRequest, ClientRequest> {
    public void initialize(ValidClienteRequest constraint) {
    }

    public boolean isValid(ClientRequest clientRequest, ConstraintValidatorContext context) {
        if (clientRequest.getFechaDeNacimiento() == null) return false;

        final ZonedDateTime zonedBirthDate = clientRequest.getFechaDeNacimiento().atZone(ZoneId.systemDefault());
        return  zonedBirthDate.getHour() == 0 &&
                zonedBirthDate.getMinute() == 0 &&
                zonedBirthDate.getSecond() == 0
                && zonedBirthDate.getNano() == 0 &&

                clientRequest.getEdad() - (calculateAge(zonedBirthDate.toLocalDate(), LocalDate.now())) == 0;
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}

