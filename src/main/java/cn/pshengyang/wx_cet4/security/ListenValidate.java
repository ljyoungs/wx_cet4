package cn.chenmuxin.wx_cet4.security;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cn.chenmuxin.wx_cet4.entity.Listen;

public class ListenValidate implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Listen listen = (Listen)target;
		if(listen.getContent() == null || listen.getContent().equals("")){
			errors.rejectValue("content", null, "Password is empty."); 
		}
	}

}
