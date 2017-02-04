package cn.chenmuxin.wx_cet4.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cn.chenmuxin.wx_cet4.entity.Listen;
import cn.chenmuxin.wx_cet4.entity.Option;
import cn.chenmuxin.wx_cet4.entity.Question;
import cn.chenmuxin.wx_cet4.entity.Read;
import cn.chenmuxin.wx_cet4.entity.Word;
import cn.chenmuxin.wx_cet4.entity.Writing;

/**
 * 
 * rest暴露id和rest根路径两个配置
 * 
 * @author zo
 *
 */

@Configuration
public class RestConfiguration extends RepositoryRestMvcConfiguration {

	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Listen.class,Option.class,Question.class,Read.class,Word.class,Writing.class);
		config.setBasePath("data");
	}
	//patch 
	
//	@Override
//	protected void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
//		// TODO Auto-generated method stub
//		validatingListener.addValidator("beforeSave", new ListenValidate());
//	}
}
