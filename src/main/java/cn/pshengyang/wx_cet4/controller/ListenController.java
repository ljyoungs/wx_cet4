package cn.chenmuxin.wx_cet4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.chenmuxin.wx_cet4.entity.Listen;
import cn.chenmuxin.wx_cet4.repository.ListenRepository;

@Controller
@RequestMapping("/wxman")
public class ListenController {
	
	@Autowired
	private ListenRepository listenRepository;
	
	@RequestMapping(value = "/listen",method={RequestMethod.GET})
	public String listenPage(){
		return "listen/index";
	}
	
	@RequestMapping("/getListen")
	public @ResponseBody  Page<Listen> getListen(Pageable pageable) {
		
		return  listenRepository.findAll(pageable);
	
	}
	
	@RequestMapping(value="/get/{id}")
	public @ResponseBody Listen getLis(@PathVariable String id){
		
		Listen listen=new Listen();
		listen.setId(id);
		
		return listen;
		
	}

}
