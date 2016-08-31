package cn.tendata.minzone.manager.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


public abstract class PictureParese {
	
	private  static ServletContext application;




	



	public static String parse(MultipartFile file) throws IOException {
		Date date=new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        
		String picName=file.getOriginalFilename();
		String newPicName=String.format("%s_%s", format.format(date), picName);
		String rootPath=application.getRealPath("/");
		File newFile=new File(String.format("%s/resources/audios/%s", rootPath, newPicName));
		try(InputStream is=file.getInputStream();
			OutputStream os=new FileOutputStream(newFile);) {
			
			byte[] buffer=new byte[1024];
			int count = 0;
			while(-1 !=(count=is.read(buffer))){				
				os.write(buffer, 0, count);
			}						
		} 
		return newPicName;
	}
	

}
