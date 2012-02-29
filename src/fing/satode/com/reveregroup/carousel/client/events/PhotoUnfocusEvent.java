package fing.satode.com.reveregroup.carousel.client.events;

import com.google.gwt.event.shared.GwtEvent;
import fing.satode.com.reveregroup.carousel.client.Photo;


public class PhotoUnfocusEvent extends GwtEvent<PhotoUnfocusHandler> {
	private static final Type<PhotoUnfocusHandler> TYPE = new Type<PhotoUnfocusHandler>();	
	
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
	protected void dispatch(PhotoUnfocusHandler handler) {
		handler.photoUnfocused(this);
	}
	@Override
	public Type<PhotoUnfocusHandler> getAssociatedType() {
		return TYPE;
	}
	public static Type<PhotoUnfocusHandler> getType(){
		return TYPE;
	}
	
}
