package cn.chenmuxin.wx_cet4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.chenmuxin.wx_cet4.entity.Question;
import cn.chenmuxin.wx_cet4.entity.Read;
import cn.chenmuxin.wx_cet4.entity.SendEntity;
import cn.chenmuxin.wx_cet4.repository.QuestionRepository;
import cn.chenmuxin.wx_cet4.repository.ReadRepository;
import cn.chenmuxin.wx_cet4.repository.SendEntityRepository;

@Controller
public class SendEntityController {

	@Autowired
	private SendEntityRepository sendEntityRepository;

	@RequestMapping("/getSendEntity")
	@ResponseBody
	public Page<SendEntity> getQuestion(Pageable pageable) {

		return sendEntityRepository.findAll(pageable);

	}
}
