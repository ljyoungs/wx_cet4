package cn.chenmuxin.wx_cet4;

import java.applet.AudioClip;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
public class App 
{
	
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
      
    }
}
