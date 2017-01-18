package cn.chenmuxin.wx_cet4.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cet4_word")
public class Word extends SendEntity {

	private String word;
	
	private String explanation;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	
}
