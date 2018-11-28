/*
 * 
 */
package com.dipanjan.listener;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving download events.
 * The class that is interested in processing a download
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addDownloadListener<code> method. When
 * the download event occurs, that object's appropriate
 * method is invoked.
 *
 * @see DownloadEvent
 */
public interface DownloadListener {
	
		/**
		 * On download started.
		 */
		public void _onDownloadStarted();
		
		/**
		 * On download ended.
		 */
		public void _onDownloadEnded();
		
		/**
		 * On download failed.
		 */
		public void _onDownloadFailed();
	
}
