package org.robovm.bindings.tapjoy;

import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.annotation.NotImplemented;

public class TJPlacementDelegateAdapter extends NSObject implements
		TJPlacementDelegate {

	@NotImplemented("contentIsReady:")
	public void contentIsReady(TJPlacement placement) {}

	@NotImplemented("requestDidFail:error:")
	public void requestDidFail(TJPlacement placement, NSError error) {}

}
