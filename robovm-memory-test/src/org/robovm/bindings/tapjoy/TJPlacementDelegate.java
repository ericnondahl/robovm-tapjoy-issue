package org.robovm.bindings.tapjoy;

import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSObjectProtocol;
import org.robovm.objc.annotation.Method;

public interface TJPlacementDelegate extends NSObjectProtocol {

	/**
	 * Called when content for an placement is successfully cached
	 * @param placement The TJPlacement that was sent
	 */
	@Method(selector = "contentIsReady:")
	void contentIsReady(TJPlacement placement);
	
	/**
	 * Called when an error occurs while sending the placement
	 * @param placement The TJPlacement that was sent
	 * @error error code
	 * @return n/a
	 */
	@Method(selector = "requestDidFail:error:")
	void requestDidFail(TJPlacement placement, NSError error);

}
