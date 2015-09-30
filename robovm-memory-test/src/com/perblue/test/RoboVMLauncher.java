package com.perblue.test;

import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.bindings.tapjoy.Tapjoy;
import org.robovm.objc.block.VoidBlock1;

public class RoboVMLauncher extends UIApplicationDelegateAdapter {
    private UIWindow window;
    private MyViewController rootViewController;
	
    public static final String TAPJOY_SDK_KEY = "{Put Key Here}";
	public static final String TAPJOY_OFFERWALL_PLACEMENT = "Offerwall";
	
    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        // Set up the view controller.
        rootViewController = new MyViewController();

        // Create a new window at screen size.
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        // Set the view controller as the root controller for the window.
        window.setRootViewController(rootViewController);
        // Make the window visible.
        window.makeKeyAndVisible();
        
        //tapjoy
        NSNotificationCenter.getDefaultCenter().addObserver(new NSString("TJC_Connect_Success"), 
				null, null, new VoidBlock1<NSNotification>() {

			@Override
			public void invoke(NSNotification notif) {
				System.out.println("tapjoy connected");
				rootViewController.setTapjoyConnected(true);
			}

		});
		NSNotificationCenter.getDefaultCenter().addObserver(new NSString("TJC_Connect_Failed"), 
				null, null, new VoidBlock1<NSNotification>() {

			@Override
			public void invoke(NSNotification notif) {
				System.out.println("tapjoy connect failed");
				rootViewController.setTapjoyConnected(false);
			}

		});
		Tapjoy.setDebugEnabled(true);
		Tapjoy.connect(TAPJOY_SDK_KEY);

        return true;
    }

    public static void main(String[] args) {
        try (NSAutoreleasePool pool = new NSAutoreleasePool()) {
            UIApplication.main(args, null, RoboVMLauncher.class);
        }
    }
}
