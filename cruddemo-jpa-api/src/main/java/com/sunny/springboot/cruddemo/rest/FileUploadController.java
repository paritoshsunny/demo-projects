package com.sunny.springboot.cruddemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sunny.springboot.cruddemo.uploadhelper.FileUploadHelper;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadHelper fileuploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

		try {
	//	validations for file		
		if(file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid file");
		}
		
	// file upload code
		
		boolean f=fileuploadHelper.uploadStatus(file);
		
		if(f==true) {
//			return ResponseEntity.ok("File uploaded successfully");
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
		}
		
		
		} catch(Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid file");
		
		
	}
	
	
	
	
	
	
}
