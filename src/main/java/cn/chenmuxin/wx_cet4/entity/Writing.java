package cn.chenmuxin.wx_cet4.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cet4_writing")
public class Writing extends SendEntity {

	//图片列表，| 分隔开每张照片路径
	private String images;

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}
