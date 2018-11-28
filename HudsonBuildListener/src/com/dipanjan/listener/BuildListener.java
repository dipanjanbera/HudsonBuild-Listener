/*
 * 
 */
package com.dipanjan.listener;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving build events.
 * The class that is interested in processing a build
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addBuildListener<code> method. When
 * the build event occurs, that object's appropriate
 * method is invoked.
 *
 * @see BuildEvent
 */
public interface BuildListener{
	
	/**
	 * On hudson build completed.
	 */
	public void _onHudsonBuildCompleted();
	
	/**
	 * On hudson build fail.
	 */
	public void _onHudsonBuildFail();
	
	/**
	 * On hudson build stated.
	 */
	public void _onHudsonBuildStated();
	
	/**
	 * Current job finished.
	 *
	 * @param jobname the jobname
	 */
	public void _currentJobFinished(String jobname);
	
	/**
	 * On hudson build completion status changed.
	 *
	 * @param buildStatus the build status
	 */
	public void _onHudsonBuildCompletionStatusChanged(Map<String, String> buildStatus);
	
}
