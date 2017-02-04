package cn.pengshengyang.wx_cet4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pengshengyang.wx_cet4.entity.Word;
import cn.pengshengyang.wx_cet4.repository.WordRepository;

@Controller
public class WordController {

	@Autowired
	private WordRepository wordRepository;

	@RequestMapping("/getWord")
	@ResponseBody
	public Page<Word> getQuestion(Pageable pageable) {

		return wordRepository.findAll(pageable);

	}
}
