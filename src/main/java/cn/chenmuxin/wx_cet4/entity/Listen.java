package cn.chenmuxin.wx_cet4.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cet4_listen")
public class Listen extends SendEntity {

	private String audio;
	
	@OneToMany
	private List<Question> questions;

	private String question;
	
	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	
}
