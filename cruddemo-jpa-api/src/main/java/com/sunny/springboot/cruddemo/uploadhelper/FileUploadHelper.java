package com.sunny.springboot.cruddemo.uploadhelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	public FileUploadHelper() throws IOException{
		
	}
	
//	public final String UPLOAD_DIR="/home/paritosh/eclipse-spring-boot-workspace/cruddemo-jpa-api/src/main/resources/static/images";
	public final String UPLOAD_DIR= new ClassPathResource("static/images").getFile().getAbsolutePath();
	
	public boolean uploadStatus(MultipartFile multipartFile ) {		
		boolean f = false;		
		try {			
//			InputStream is=multipartFile.getInputStream();
//			byte data[]=new byte[is.available()];
//			is.read(data);			
//			FileOutputStream fos=new FileOutputStream(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename());
//			fos.write(data);			
//			fos.flush();
//			fos.close();			
			Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			f=true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return f;
	}
}
