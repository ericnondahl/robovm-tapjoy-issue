package com.perblue.test;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.uikit.*;
import org.robovm.bindings.tapjoy.TJPlacement;
import org.robovm.bindings.tapjoy.TJPlacementDelegateAdapter;

public class MyViewController extends UIViewController {
    private final UIButton button;
    private final UILabel label;
    private int clickCount;
    private boolean tapjoyConnected;
    private TJPlacement placement;
    
    public MyViewController() {
        // Get the view of this view controller.
        UIView view = getView();

        // Setup background.
        view.setBackgroundColor(UIColor.white());

        // Setup label.
        label = new UILabel(new CGRect(20, 250, 280, 44));
        label.setFont(UIFont.getSystemFont(24));
        label.setTextAlignment(NSTextAlignment.Center);
        view.addSubview(label);

        // Setup button.
        button = UIButton.create(UIButtonType.RoundedRect);
        button.setFrame(new CGRect(110, 150, 100, 40));
        button.setTitle("Click me!", UIControlState.Normal);
        button.getTitleLabel().setFont(UIFont.getBoldSystemFont(22));

        button.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            @Override
            public void onTouchUpInside (UIControl control, UIEvent event) {
            	System.gc();
            	
            	System.out.println(label.getText());
                label.setText("Click Nr. " + (++clickCount));
                
                if(tapjoyConnected) {
                	launchTapjoy();
                }
                else {
                	System.out.println("tapjoy not connected yet");
                }

            }
        });
        view.addSubview(button);
    }
    
    public void setTapjoyConnected(boolean connected) {
    	this.tapjoyConnected = connected;
    }
    
    private void launchTapjoy() {
    	placement = new TJPlacement(RoboVMLauncher.TAPJOY_OFFERWALL_PLACEMENT, new TJPlacementDelegateAdapter() {
			
			@Override
			public void contentIsReady(TJPlacement placement) {
				placement.showContent(UIApplication.getSharedApplication().getKeyWindow().getRootViewController());
			}
			
			@Override
			public void requestDidFail(TJPlacement placement, NSError error) {
				System.out.println("could not load tapjoy content");
			}
		});
		System.out.println("loading tapjoy");
		placement.requestContent();
    }
}
