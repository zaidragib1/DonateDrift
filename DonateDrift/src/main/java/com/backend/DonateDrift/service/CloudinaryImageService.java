package com.backend.DonateDrift.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.DonateDrift.exception.ImageException;
import com.cloudinary.Cloudinary;

@Service
public class CloudinaryImageService {
	
	@Autowired
	private Cloudinary cloudinary;

	public String upload(MultipartFile file)  {
		
		String foldername = "donatedrift";
		try {
          HashMap<Object, Object> options = new HashMap<>();
          options.put("folder", foldername);
          Map data = this.cloudinary.uploader().upload(file.getBytes(),options);
          if(data==null) {
        	  throw new ImageException("file not uploaded");
          }
          String h = "url";
          String url = (String) data.get(h);
		  return url;
		} catch (Exception e) {
			throw new RuntimeException("Image uploading failed");
		}
		
	}
	
}
