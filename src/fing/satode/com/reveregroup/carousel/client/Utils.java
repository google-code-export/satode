package fing.satode.com.reveregroup.carousel.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.Image;

public class Utils {
	public static void preventDrag(Image img) {
		img.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				event.preventDefault();
			}
		});
		preventDragIE(img.getElement());
	}
	
	native private static void preventDragIE(Element element) /*-{
		element.ondragstart = function() { return false; };
	}-*/;
	
	native public static void preventSelection(Element element) /*-{
		element.style.MozUserSelect = 'none';
		element.unselectable = 'on';
		element.onselectstart = function() { return false; };
	}-*/;
	
	native public static void log(String s) /*-{
		if (console) {
			console.log(s);
		}
	}-*/;
	
	public static double distanceForOneTick(double velocity, double acceleration) {
		return velocity * (acceleration - 1) / Math.log(acceleration);
	}
	
	public static double velocityForDistance(double distance, double acceleration, double finalVelocity) {
		if (distance < 0)
			finalVelocity = -finalVelocity;
		return finalVelocity - distance * Math.log(acceleration);
	}
	
	public static double ticksFromStartingVelocity(double velocity, double acceleration, double finalVelocity) {
		if (velocity < 0)
			finalVelocity = -finalVelocity;
		return Math.log(finalVelocity / velocity) / Math.log(acceleration); 
	}
	
	public static double distanceFromStartingVelocity(double velocity, double acceleration, double finalVelocity) {
		if (velocity < 0)
			finalVelocity = -finalVelocity;
		return (finalVelocity - velocity) / Math.log(acceleration);
	}
	
	public static int modulus(int a, int b){
		if(a < 0) {
			a = a % b;
			if (a < 0)
				a += b;
			return a;
		} else if (a == 0) {
			return 0;
		}
		return a % b;
	}
	
	public static double modulus (double a, int b){
		if (a == 0.0)
			return 0.0;
		
		a = a - b * (((int) a) / b);
		if (a < 0.0)
			a += b;
		
		return a;
	}
}
