package com.local.util;

import com.local.service.km.webservice.IKmReviewWebserviceService;
import lombok.Data;
import org.apache.catalina.Context;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 解析配置文件
 * 
 */

@Configuration
@Data
public class FMWebServiceConfig {

	// Web服务的URL
	@Value("km.address:http://192.168.2.220/sys/webservice/kmReviewWebserviceService")
	private String address;
	
	// Web服务接口
	private Class serviceClass;
	
	// Web服务标识
	@Value("km.service_bean:kmReviewWebserviceService")
	private String serviceBean;
	
	// 用户
	@Value("km.user:01344")
	private String user;
	
	// 密码
	@Value("km.password:8e8d6f3196a7e8a308101955b8b96c23")
	private String password;

	public FMWebServiceConfig getWebServiceConfig() {
		FMWebServiceConfig config=new FMWebServiceConfig();
		config.setAddress(this.address);
		config.setUser(this.user);
		config.setPassword(this.password);
		config.setServiceBean(this.serviceBean);
		config.setServiceClass(IKmReviewWebserviceService.class);
		return config;
	}

    @Bean
	public Object JaxWsProxyFactoryBean () throws Exception{
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		// 记录入站消息
		//factory.getInInterceptors().add(new LoggingInInterceptor());

		// 记录出站消息
		//factory.getOutInterceptors().add(new LoggingOutInterceptor());

		// 添加消息头验证信息。如果服务端要求验证用户密码，请加入此段代码
		// factory.getOutInterceptors().add(new AddSoapHeader());
		factory.setServiceClass(IKmReviewWebserviceService.class);
		factory.setAddress(address);

		// 使用MTOM编码处理消息。如果需要在消息中传输文档附件等二进制内容，请加入此段代码
		// Map<String, Object> props = new HashMap<String, Object>();
		// props.put("mtom-enabled", Boolean.TRUE);
		// factory.setProperties(props);

		// 创建服务代理并返回
		return factory.create();
	}


}
