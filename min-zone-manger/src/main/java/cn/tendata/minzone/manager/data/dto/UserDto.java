package cn.tendata.minzone.manager.data.dto;

public class UserDto {
    
	private String username;
	
	private String headPicture;
	
	private long countFocus;
	
	private long countFans;
	
	private long countBlogs;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}

	public long getCountFocus() {
		return countFocus;
	}

	public void setCountFocus(long countFocus) {
		this.countFocus = countFocus;
	}

	public long getCountFans() {
		return countFans;
	}

	public void setCountFans(long countFans) {
		this.countFans = countFans;
	}

	public long getCountBlogs() {
		return countBlogs;
	}

	public void setCountBlogs(long countBlog) {
		this.countBlogs = countBlog;
	}
	
	
}
