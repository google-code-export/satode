package fing.satode.ui.base;

public class EntryPointBase {

	public static native void setWindowHref(String url)/*-{
    	$wnd.location.href = url;
	}-*/; 
}
