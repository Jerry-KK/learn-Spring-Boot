package cn.lethekk.chapter4.validator.impl;

import cn.lethekk.chapter4.pojo.User;
import cn.lethekk.chapter4.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validate(User user) {
        System.out.println("引入新的接口： " + UserValidator.class.getSimpleName());
        return user != null;
    }
}
