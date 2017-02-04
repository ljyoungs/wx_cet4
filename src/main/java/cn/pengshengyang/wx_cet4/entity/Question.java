package cn.pengshengyang.wx_cet4.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name="cet4_question")
public class Question {

	@Id
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@GeneratedValue(generator="system-uuid")
	private String id;
	
	@Type(type="text")
	private String title;
	
	private int core;
	
	private boolean isPushed;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createTime;
	
	@ColumnDefault(value="false")
	private boolean isRoot;
	
	@ColumnDefault(value="false")
	private boolean isMultiple;
	
	@OneToMany
	private List<Option> options;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCore() {
		return core;
	}

	public void setCore(int core) {
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

	public boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean getIsMultiple() {
		return isMultiple;
	}

	public void setIsMultiple(boolean isMultiple) {
		this.isMultiple = isMultiple;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	
}
