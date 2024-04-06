package com.backend.DonateDrift.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {
	
	@Bean
	public Cloudinary getCloudinary() {
		
		Map config = new HashMap();
		config.put("cloud_name","dsgmy1rdx");
		config.put("api_key","128264241926168");
		config.put("api_secret","6B9XKvvqXthlcer6JSveO3tOdcs");
		config.put("secure",true);
		
		return new Cloudinary(config);
	}
	
}
