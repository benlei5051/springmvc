package org.java.controller;


import org.java.entity.Info;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Info entity = new Info();
        entity.setAge(12);
        entity.setName("admin");
        Set<ConstraintViolation<Info>> constraintViolations = validator.validate(entity);
        for (ConstraintViolation<Info> constraintViolation : constraintViolations) {
            System.out.println("对象属性:" + constraintViolation.getPropertyPath());
            System.out.println("国际化key:" + constraintViolation.getMessageTemplate());
            System.out.println("错误信息:" + constraintViolation.getMessage());
        }
    }

}
