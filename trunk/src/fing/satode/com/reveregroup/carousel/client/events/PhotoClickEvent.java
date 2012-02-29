package fing.satode.com.reveregroup.carousel.client.events;

import com.google.gwt.event.shared.GwtEvent;
import fing.satode.com.reveregroup.carousel.client.Photo;


public class PhotoClickEvent extends GwtEvent<PhotoClickHandler> {
	private static final Type<PhotoClickHandler> TYPE = new Type<PhotoClickHandler>();	
	
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
	protected void dispatch(PhotoClickHandler handler) {
		handler.photoClicked(this);
	}
	@Override
	public Type<PhotoClickHandler> getAssociatedType() {
		return TYPE;
	}
	public static Type<PhotoClickHandler> getType(){
		return TYPE;
	}
}
