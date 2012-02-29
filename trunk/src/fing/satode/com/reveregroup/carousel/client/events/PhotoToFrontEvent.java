package fing.satode.com.reveregroup.carousel.client.events;

import com.google.gwt.event.shared.GwtEvent;
import fing.satode.com.reveregroup.carousel.client.Photo;


public class PhotoToFrontEvent extends GwtEvent<PhotoToFrontHandler> {
	private static final Type<PhotoToFrontHandler> TYPE = new Type<PhotoToFrontHandler>();	
	
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
	protected void dispatch(PhotoToFrontHandler handler) {
		handler.photoToFront(this);
	}
	@Override
	public Type<PhotoToFrontHandler> getAssociatedType() {
		return TYPE;
	}
	public static Type<PhotoToFrontHandler> getType(){
		return TYPE;
	}
	
}
