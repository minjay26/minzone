package cn.tendata.minzone.manager.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import cn.tendata.minzone.manager.bind.annotation.CurrentUser;
import cn.tendata.minzone.manager.model.entity.Blog;
import cn.tendata.minzone.manager.model.entity.User;
import cn.tendata.minzone.manager.service.BlogService;

@Controller
@RequestMapping("/user_blog")
public class BLogController implements ServletContextAware{
	
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public String submit(@RequestParam("content") String content,final @CurrentUser  User user) {
        Blog blog=new Blog();
        blog.setContent(content);
		this.blogService.addBlog(blog);
		return "success";
	}

	@RequestMapping("/imageUpload")
	public String imageUpload(@CurrentUser final User user,HttpServletRequest request,HttpServletResponse response) throws IOException, FileUploadException {
//		Date date = new Date();
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		String imageName = imageFile.getOriginalFilename();
//		String newImageName = String.format("%s_%s", format.format(date), imageName);
//		
//		String rootPath=this.servletContext.getRealPath("/");
//		File fileImage=new File(String.format("%s/resources/uploadImages/%s", rootPath, newImageName));
//		
//		try(InputStream is=imageFile.getInputStream();
//			OutputStream os=new FileOutputStream(fileImage)){
//			byte[] bytes=new byte[1024];
//			int count=0;
//			while (-1!=(count=is.read(bytes))) {
//				os.write(bytes, 0, count);
//			}
//		}
//		
//		UploadImage image=new UploadImage();
//		image.setFilePath(newImageName);
//		image.setUpLoadUser(user);
//		this.blogService.upload(image);
		
		String savePath=this.servletContext.getRealPath("/")+"attached/";
		String saveUrl=request.getContextPath()+"attached/";
		
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		long maxSize = 1000000;

		response.setContentType("text/html; charset=UTF-8");
		
		if(!ServletFileUpload.isMultipartContent(request)){
			
			return "";
		}
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			//out.println(getError("上传目录不存在。"));
			return "";
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			//out.println(getError("上传目录没有写权限。"));
			return "";
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			//out.println(getError("目录名不正确。"));
			return "";
		}
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				//检查文件大小
				if(item.getSize() > maxSize){
					//out.println(getError("上传文件大小超过限制。"));
					return "";
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					//out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return "";
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				try{
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				}catch(Exception e){
					//out.println(getError("上传文件失败。"));
					return "";
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				//out.println(obj.toJSONString());
			}
		}
		return "";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
		
	}

//	@Override
//	public void setServletContext(ServletContext servletContext) {
//		this.servletContext=servletContext;
//		
//	}

	

}
