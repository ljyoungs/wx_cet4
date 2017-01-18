package cn.chenmuxin.wx_cet4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.chenmuxin.wx_cet4.entity.Option;
import cn.chenmuxin.wx_cet4.repository.OptionRepository;

@Controller
@RequestMapping("/wxman")
public class OptionController  {
	
	@Autowired
	private OptionRepository optionRepository;
	
	@RequestMapping(value="/option",method=RequestMethod.GET)
	public String optionPage(){
		
		return "option/index";
		
	}
	
	
	@RequestMapping("/getOption")
	@ResponseBody
	public  Page<Option> getOption(Pageable pageable){
		
		return optionRepository.findAll(pageable) ;
		
	}
	

}
