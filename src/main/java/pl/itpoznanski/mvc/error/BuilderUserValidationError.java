package pl.itpoznanski.mvc.error;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class BuilderUserValidationError {
    public static UserValidationError fromBindingErrors(Errors errors) {
        UserValidationError error = new UserValidationError("Validation"
                + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}
