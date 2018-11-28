package com.dipanjan.listener;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving websiteResponse events.
 * The class that is interested in processing a websiteResponse
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addWebsiteResponseListener<code> method. When
 * the websiteResponse event occurs, that object's appropriate
 * method is invoked.
 *
 * @see WebsiteResponseEvent
 */
public interface WebsiteResponseListener {
	
	/**
	 * On website responded with success.
	 */
	public void _onWebsiteRespondedWithSuccess();
	
	/**
	 * On website not responding.
	 */
	public void _onWebsiteNotResponding();
	
	/**
	 * On incorrect website URL.
	 */
	public void _onIncorrectWebsiteURL();
}
