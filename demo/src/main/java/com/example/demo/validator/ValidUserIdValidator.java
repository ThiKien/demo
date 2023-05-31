package com.example.demo.validator;

import jakarta.validation.ConstraintValidator;
import com.example.demo.validator.annotation.ValidUserId;
import jakarta.validation.ConstraintValidatorContext;
import com.example.demo.entity.User;
public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if(user == null)
            return true;
        return user.getId() != null;
    }
}
