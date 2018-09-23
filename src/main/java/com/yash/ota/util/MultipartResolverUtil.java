package com.yash.ota.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Component
public class MultipartResolverUtil {

	 @Bean(name = "multipartResolver")
	    public CommonsMultipartResolver commonsMultipartResolver() {
	    	CommonsMultipartResolver cmr = new CommonsMultipartResolver();
	    	cmr.setMaxUploadSize(10000000);
	    	return cmr;
	    }
}
