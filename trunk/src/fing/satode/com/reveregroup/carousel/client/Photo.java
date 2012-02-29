package fing.satode.com.reveregroup.carousel.client;

public class Photo{
	private String url;
	private String caption;
	private int height;
	private int width;
	private int photoId;
	
	public Photo() {
	}
	
	public Photo(String url){
		this.url = url;
	}
	public Photo(String url,String caption, int height,int width){
		this.url = url;
		this.caption = caption;
		this.height = height;
		this.width = width;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public int getPhotoId() {
		return photoId;
	}
	
	
}