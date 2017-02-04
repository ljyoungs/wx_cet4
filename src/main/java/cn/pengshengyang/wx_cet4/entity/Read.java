package cn.pengshengyang.wx_cet4.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="cet4_read")
public class Read extends SendEntity {

	//区分 选词填空 和 阅读理解
	private String type;
	
	@OneToMany	
	private List<Question> questions;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
}
