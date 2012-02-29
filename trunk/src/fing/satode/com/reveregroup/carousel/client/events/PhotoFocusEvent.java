package fing.satode.com.reveregroup.carousel.client.events;

import com.google.gwt.event.shared.GwtEvent;
import fing.satode.com.reveregroup.carousel.client.Photo;


public class PhotoFocusEvent extends GwtEvent<PhotoFocusHandler> {
	private static final Type<PhotoFocusHandler> TYPE = new Type<PhotoFocusHandler>();	
	
	private Photo photo;
	private int photoIndex;
	
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public int getPhotoIndex() {
		return photoIndex;
	}
	public void setPhotoIndex(int photoIndex) {
		this.photoIndex = photoIndex;
	}	
	@Override
	protected void dispatch(PhotoFocusHandler handler) {
		handler.photoFocused(this);
	}
	@Override
	public Type<PhotoFocusHandler> getAssociatedType() {
		return TYPE;
	}
	public static Type<PhotoFocusHandler> getType(){
		return TYPE;
	}
	
}
