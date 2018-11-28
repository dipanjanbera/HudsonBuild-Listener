package com.dipanjan.listener;

import java.util.Map;

public interface BuildListener{
	
	public void _onHudsonBuildCompleted();
	
	public void _onHudsonBuildFail();
	
	public void _onHudsonBuildStated();
	
	public void _currentJobFinished(String jobname);
	
	public void _onHudsonBuildCompletionStatusChanged(Map<String, String> buildStatus);
	
}
