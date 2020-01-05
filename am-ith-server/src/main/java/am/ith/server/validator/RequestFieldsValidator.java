package am.ith.server.validator;

import am.ith.service.error.ErrorFieldResponseDto;
import am.ith.service.error.ValidationError;
import am.ith.service.exception.BaseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestFieldsValidator {

    public void validate(Errors errors) {
        if (errors.hasErrors()) {
            List<ErrorFieldResponseDto> errorList = new ArrayList<>();

            errors.getAllErrors().forEach(error -> {
                ErrorFieldResponseDto fieldError = new ErrorFieldResponseDto();
                fieldError.setField(error.getObjectName());
                fieldError.setMessage(error.getDefaultMessage());

                errorList.add(fieldError);
            });
            throw new BaseException(ValidationError.VALIDATION_ERROR, errorList);
        }
    }

}
