package cn.chenmuxin.wx_cet4.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.chenmuxin.wx_cet4.serializer.DateSerializer;
@MappedSuperclass
public class SendEntity {

	@Id
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@GeneratedValue(generator="system-uuid")
	private String id;
	
	@Type(type="text")
	private String content;
	
	private String core;
	
	private String answer;
	
	@ColumnDefault(value = "false")
	private boolean isPushed;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonSerialize(using=DateSerializer.class)
	private Date createTime;
	@JsonSerialize(using=DateSerializer.class)
	private Date pushTime;

	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public String getCore() {
		return core;
	}

	public void setCore(String core) {
		this.core = core;
	}

	public boolean getIsPushed() {
		return isPushed;
	}

	public void setIsPushed(boolean isPushed) {
		this.isPushed = isPushed;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	
}
