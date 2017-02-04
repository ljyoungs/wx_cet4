package cn.chenmuxin.wx_cet4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.chenmuxin.wx_cet4.entity.Question;
import cn.chenmuxin.wx_cet4.repository.QuestionRepository;

@Controller
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;

	@RequestMapping("/getQuestion")
	@ResponseBody
	public Page<Question> getQuestion(Pageable pageable) {

		return questionRepository.findAll(pageable);

	}
}
