package cn.chenmuxin.wx_cet4.entity;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@RepositoryEventHandler(SendEntity.class)
@Component()
public class SendEntityEventHandler {
	

	@HandleBeforeCreate
	public void beforeCreate(SendEntity sendEntity){
		if (sendEntity instanceof Listen) {
			Listen listen = (Listen)sendEntity;
			listen.setContent("555");
			System.out.println(listen.toString());
		}
	}
	
}
