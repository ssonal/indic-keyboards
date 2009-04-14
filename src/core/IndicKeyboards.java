/** ********************************************************************
 * File:           IndicKeyboards.java 
 * Description:    UI for the Common Keyboard Interface
 * Authors:        Akshay,Abhinava,Revati,Arun 
 * Created:        Mon Oct 20 19:31:25 GMT 2008
 *
 * (C) Copyright 2008, MILE Lab, IISc
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 ** http://www.apache.org/licenses/LICENSE-2.0
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 *
 **********************************************************************/

/*
 * Common Keboard interface module
 *
 * SWT is used for the shell extension.
 * Main part is the interface.
 * 
 * @version 0.1
 */
/**
 * package core is the source package
 * for Common Keyboard Interface
 */
package core;

import java.io.FileNotFoundException;

public class IndicKeyboards implements KeyboardEventListener {

	private Boolean flag = false;
	private long withShiftPressed = 48;
	private Boolean enable = false;

	public static void main(String[] args) throws FileNotFoundException {

		// instantiation of the KeyboardHook class
		KeyboardHook kh = new KeyboardHook();
		kh.addEventListener(new IndicKeyboards());

		System.out.println("Press F12 to enable key logging");

		/*
		 * This is to keep the key monitor in an infite loop BufferedReader br =
		 * new BufferedReader(new InputStreamReader(System.in)); try {
		 * br.readLine(); } catch (IOException ex) { }
		 */

		/*
		 * Creating an instance of the SplashScreenMain class for the Splash
		 * screen.The constructor takes in the number of milliseconds splash
		 * screen will be active.
		 */
		/*
		 * final SplashScreenMain splash = new SplashScreenMain(3000);
		 * splash.SplashScreenShow();
		 * 
		 * The method has been modified to deal with the milliseconds in the
		 * file SplashScreen.java itself
		 */

		SplashScreen s = new SplashScreen();
		s.screen();
		@SuppressWarnings("unused")
		UI iFace = new UI();

	}// end of main

	// Methods to monitor global key presses
	public void GlobalKeyPressed(KeyboardEvent event) {

		if (event.getVirtualKeyCode() == 123) {
			enable = !enable;
			if (enable) {
				System.out.println("Software enabled");
			} else {
				System.out
						.println("Software disabled... Press F12 to re-enable");
			}
		}
		if (enable) {
			if ((event.getVirtualKeyCode() == 16)
					&& (event.getTransitionState())) {
				flag = true;
			}
			if (flag) {
				withShiftPressed = event.getVirtualKeyCode();

				/**
				 * Managing the shift+ key presses.
				 */
				if ((event.getVirtualKeyCode() >= 65)
						&& (event.getVirtualKeyCode() <= 90))
					withShiftPressed = event.getVirtualKeyCode() + 32;

				else {
					switch (event.getVirtualKeyCode()) {

					case 48:
						withShiftPressed = event.getVirtualKeyCode() - 7;
						break;

					case 50:
						withShiftPressed = event.getVirtualKeyCode() + 14;
						break;

					case 54:
						withShiftPressed = event.getVirtualKeyCode() + 40;
						break;

					case 57:
					case 55:
						withShiftPressed = event.getVirtualKeyCode() - 17;
						break;

					case 56:
						withShiftPressed = event.getVirtualKeyCode() - 14;
						break;

					case 49:
					case 51:
					case 52:
					case 53:
						withShiftPressed = event.getVirtualKeyCode() - 16;
						break;

					case 59:
						withShiftPressed = event.getVirtualKeyCode() - 1;
						break;

					case 44:
					case 46:
					case 47:
						withShiftPressed = event.getVirtualKeyCode() + 16;
						break;

					case 45:
						withShiftPressed = event.getVirtualKeyCode() + 50;
						break;

					case 39:
						withShiftPressed = event.getVirtualKeyCode() - 5;
						break;

					case 61:
						withShiftPressed = event.getVirtualKeyCode() - 18;
						break;

					case 91:
					case 92:
					case 93:
						withShiftPressed = event.getVirtualKeyCode() + 32;
						break;

					case 96:
						withShiftPressed = event.getVirtualKeyCode() + 30;
						break;
					}
				}
				if (withShiftPressed == 48 || withShiftPressed == 16) {
					// do not print shift while it is pressed
				} else {
					ParseXML test = new ParseXML();
					String inputChar = new Character((char) event
							.getVirtualKeyCode()).toString().toUpperCase();
					test.getPattern(inputChar);
					System.out.println("Key Pressed with shift: "
							+ withShiftPressed);
				}
			} else {
				ParseXML test = new ParseXML();
				String inputChar = new Character((char) event
						.getVirtualKeyCode()).toString().toLowerCase();
				test.getPattern(inputChar);
				System.out.println("Key Pressed: " + event.getVirtualKeyCode());
			}
		}
	}

	public void GlobalKeyReleased(KeyboardEvent event) {
		if (enable) {
			if ((event.getVirtualKeyCode() == 16)
					&& (!event.getTransitionState())) {
				flag = false;
				if (withShiftPressed == 48) {
					/*
					 * Either do the following or simply leave out shift key
					 * logging System.out.println("Key Released: " +
					 * event.getVirtualKeyCode());
					 */
				} else {
					/*
					 * optional statement. logs key releases of only those where
					 * shift key is pressed. System.out.println("Key Released: "
					 * + withShiftPressed);
					 */
				}
			}
		}
	}

}// end of class CKI
