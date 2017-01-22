package cn.chenmuxin.wx_cet4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.chenmuxin.wx_cet4.entity.Question;
import cn.chenmuxin.wx_cet4.entity.Read;
import cn.chenmuxin.wx_cet4.repository.QuestionRepository;
import cn.chenmuxin.wx_cet4.repository.ReadRepository;

@Controller
@RequestMapping("/wxman")
public class ReadController {

	@Autowired
	private ReadRepository readRepository;

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readPage() {

		return "read/index";

	}

	@RequestMapping("/getRead")
	@ResponseBody
	public Page<Read> getQuestion(Pageable pageable) {

		return readRepository.findAll(pageable);

	}
}
