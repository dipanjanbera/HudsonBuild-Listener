/*
 * 
 */
package com.dipanjan.listener;

import java.util.Map;

import com.dipanjan.exception.BuildEnvironmentNotFoundException;
import com.dipanjan.helper.HudsonBuildListenerHelper;



// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving hudsonBuild events.
 * The class that is interested in processing a hudsonBuild
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addHudsonBuildListener<code> method. When
 * the hudsonBuild event occurs, that object's appropriate
 * method is invoked.
 *
 * @see HudsonBuildEvent
 */
public class HudsonBuildListener{
	
	
	/** The build successful. */
	public String BUILD_SUCCESSFUL = "success";
	
	/** The build remaining. */
	public String BUILD_REMAINING = "remaining";
	
	/** The build failed. */
	public String BUILD_FAILED = "failed";
	

	/** The build listener. */
	public BuildListener buildListener=null;
	
	
	/**
	 * Instantiates a new hudson build listener.
	 */
	public HudsonBuildListener() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new hudson build listener.
	 *
	 * @param buildListener the build listener
	 */
	public HudsonBuildListener(BuildListener buildListener) {
		super();
		this.buildListener = buildListener;
	}

	/**
	 * Sets the hudson build listener.
	 *
	 * @param environment the environment
	 * @param appName the app name
	 * @param hudsonBuildListener the hudson build listener
	 * @param buildListener the build listener
	 */
	public void setHudsonBuildListener(String environment, String appName,HudsonBuildListener hudsonBuildListener, BuildListener buildListener){
		this.buildListener = buildListener;
		if(HudsonBuildListenerHelper.checkIfWebSiteUp(environment, hudsonBuildListener)){
			HudsonBuildListenerHelper._getHudsonBuildStatus(environment,appName,hudsonBuildListener,this.buildListener);
		}else{
			System.out.println("Website not Responding");
		}
		
	}
	
	/**
	 * Sets the hudson build listener.
	 *
	 * @param environment the environment
	 * @param appName the app name
	 * @param hudsonBuildListener the hudson build listener
	 * @param buildListener the build listener
	 * @param refreshRate the refresh rate
	 */
	public void setHudsonBuildListener(String environment, String appName,HudsonBuildListener hudsonBuildListener, BuildListener buildListener,int refreshRate){
		this.buildListener = buildListener;
		if(HudsonBuildListenerHelper.checkIfWebSiteUp(environment, hudsonBuildListener)){
			HudsonBuildListenerHelper._getHudsonBuildStatus(environment,appName,hudsonBuildListener,this.buildListener,refreshRate);
		}else{
			System.out.println("Website not Responding");
		}
		
	}
	
	
	
	/**
	 * Gets the hudson build URL.
	 *
	 * @param environment the environment
	 * @return the string
	 * @throws BuildEnvironmentNotFoundException the build environment not found exception
	 */
	public String _getHudsonBuildURL(String environment) throws BuildEnvironmentNotFoundException{
		if(environment.equalsIgnoreCase("emer")){
			return AppConstant.BUILD_URL_FOR_MAX_VER.EMERGENCY_EVN_BUILD;
		}
		if(environment.equalsIgnoreCase("trunk")){
			return AppConstant.BUILD_URL_FOR_MAX_VER.TRUNK_EVN_BUILD;
		}
		if(environment.equalsIgnoreCase("uat1")){
			return AppConstant.BUILD_URL_FOR_MAX_VER.UAT1_EVN_BUILD;
		}
		if(environment.equalsIgnoreCase("uat2")){
			return AppConstant.BUILD_URL_FOR_MAX_VER.UAT2_EVN_BUILD;
		}
		if(environment.equalsIgnoreCase("uat3")){
			return AppConstant.BUILD_URL_FOR_MAX_VER.UAT3_EVN_BUILD;
		}
		if(environment.equalsIgnoreCase("uat5")){
			return AppConstant.BUILD_URL_FOR_MAX_VER.UAT5_EVN_BUILD;
		}
		else{
			throw new BuildEnvironmentNotFoundException();
		}
	}
	
}

interface AppConstant{
	
	interface BUILD_URL_FOR_MAX_VER{
	String EMERGENCY_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/Emergency/job/framework_Emergency/";
	String TRUNK_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/Trunk/job/framework_trunk/";
	String UAT1_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/UAT1/job/framework_UAT1/";
	String UAT2_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/UAT2/job/framework_UAT2/";
	String UAT3_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/UAT3/job/framework_UAT3/";
	String UAT5_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/UAT5/job/framework_UAT5/";
	}
}