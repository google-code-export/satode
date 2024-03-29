package fing.satode.ui.general.data;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class KeyNumeric implements KeyboardListener {

	@Override
	public void onKeyDown(Widget sender, char keyCode, int modifiers) {
		if (!Character.isDigit(keyCode) && (keyCode != (char) KEY_TAB)
		         && (keyCode != (char) KEY_BACKSPACE)
		         && (keyCode != (char) KEY_DELETE) && (keyCode != (char) KEY_ENTER) 
		         && (keyCode != (char) KEY_HOME) && (keyCode != (char) KEY_END)
		         && (keyCode != (char) KEY_LEFT) && (keyCode != (char) KEY_UP)
		         && (keyCode != (char) KEY_RIGHT) && (keyCode != (char) KEY_DOWN)
		         && keyCode != '.') {
		       // TextBox.cancelKey() suppresses the current keyboard event.
			if(Character.isLetter(keyCode)){
				if(keyCode != 'a' && keyCode != 'b' && keyCode != 'c'
						&& keyCode != 'd' && keyCode != 'e' && keyCode != 'f'
						&& keyCode != 'g' && keyCode != 'h' && keyCode != 'i'){
					((TextBox)sender).cancelKey();
				}
			}
		 }
		
		
		
	}

	@Override
	public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		// TODO Auto-generated method stub
		
		
		 //if (!Character.isDigit(keyCode) && (keyCode != (char) KEY_TAB)
		//         && (keyCode != (char) KEY_BACKSPACE)
		//         && (keyCode != (char) KEY_DELETE) && (keyCode != (char) KEY_ENTER) 
		//         && (keyCode != (char) KEY_HOME) && (keyCode != (char) KEY_END)
		//         && (keyCode != (char) KEY_LEFT) && (keyCode != (char) KEY_UP)
		//         && (keyCode != (char) KEY_RIGHT) && (keyCode != (char) KEY_DOWN)) {
		//       // TextBox.cancelKey() suppresses the current keyboard event.
		//       ((TextBox)sender).cancelKey();
		//     }
	}

	@Override
	public void onKeyUp(Widget sender, char keyCode, int modifiers) {
		// TODO Auto-generated method stub
		
	}
}
