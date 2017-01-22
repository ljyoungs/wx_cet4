package cn.chenmuxin.wx_cet4.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = { Listen.class },name="listen")
public interface ListenProjection {

	public String getContent();
	
}
