package vn.hoidanit.laptopshop.service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.DTO.RegisterDTO;
import vn.hoidanit.laptopshop.service.UserService;

public class RegisterDTOValidator implements ConstraintValidator<RegisterDTOCheck, RegisterDTO> {
    private final UserService userService;

    public RegisterDTOValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDTO userRegister, ConstraintValidatorContext context) {
        boolean valid = true;
        // Check password co giong confirmPass
        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Password is not match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Check Email
        if (this.userService.checkExistEmail(userRegister.getEmail())) {
            context.buildConstraintViolationWithTemplate("Existed Email")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
