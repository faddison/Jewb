package com.jewb.control;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListenerExample implements NativeKeyListener {
	
	static boolean shouldRun = false;
	
	public boolean getShouldRun()
	{
		return this.shouldRun;
	}
	
    public void nativeKeyPressed(NativeKeyEvent e) {
            System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

            switch (e.getKeyCode())
            {
	            case NativeKeyEvent.VK_ESCAPE:
	            	GlobalScreen.unregisterNativeHook();
	                System.exit(0);
	                break;
	            case NativeKeyEvent.VK_S:
	            	if (!shouldRun)
	            		shouldRun = true;
	            	break;
	            case NativeKeyEvent.VK_X:
	            	if (shouldRun)
	            		shouldRun = false;
	            	break;
	            default:
	            	System.out.println("Unknown key");
            }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
            System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
            System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }
}