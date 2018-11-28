package com.dipanjan.listener;

import java.util.Map;

import com.dipanjan.exception.BuildEnvironmentNotFoundException;
import com.dipanjan.helper.HudsonBuildListenerHelper;



public class HudsonBuildListener{
	
	
	public String BUILD_SUCCESSFUL = "success";
	public String BUILD_REMAINING = "remaining";
	public String BUILD_FAILED = "failed";
	

	public BuildListener buildListener=null;
	
	
	public HudsonBuildListener() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HudsonBuildListener(BuildListener buildListener) {
		super();
		this.buildListener = buildListener;
	}

	public void setHudsonBuildListener(String environment, String appName,HudsonBuildListener hudsonBuildListener, BuildListener buildListener){
		this.buildListener = buildListener;
		if(HudsonBuildListenerHelper.checkIfWebSiteUp(environment, hudsonBuildListener)){
			HudsonBuildListenerHelper._getHudsonBuildStatus(environment,appName,hudsonBuildListener,this.buildListener);
		}else{
			System.out.println("Website not Responding");
		}
		
	}
	
	public void setHudsonBuildListener(String environment, String appName,HudsonBuildListener hudsonBuildListener, BuildListener buildListener,int refreshRate){
		this.buildListener = buildListener;
		if(HudsonBuildListenerHelper.checkIfWebSiteUp(environment, hudsonBuildListener)){
			HudsonBuildListenerHelper._getHudsonBuildStatus(environment,appName,hudsonBuildListener,this.buildListener,refreshRate);
		}else{
			System.out.println("Website not Responding");
		}
		
	}
	
	
	
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