package com.dipanjan.helper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.dipanjan.exception.BuildEnvironmentNotFoundException;
import com.dipanjan.listener.WebsiteResponseListener;


public class BuildStarter {
	public static void startBuild(String env,WebsiteResponseListener websiteResponseListener) {
		HttpURLConnection con = null;
		URL urlObj;
		
		try {
			urlObj = new URL(_getHudsonBuildURL(env));
			websiteResponseListener._WebsiteConnectionStatus("Connecting....");
			con = (HttpURLConnection) urlObj.openConnection();
		
			int resp = con.getResponseCode();
			if(resp == 200){
				websiteResponseListener._onWebsiteRespondedWithSuccess();
			}
			if (resp != 200) {
				System.out.println("Hudson returned error response code " + resp);
				websiteResponseListener._onWebsiteNotResponding();
			}
		} catch (MalformedURLException e) {
			websiteResponseListener._onIncorrectWebsiteURL();
			e.printStackTrace();
		} catch (BuildEnvironmentNotFoundException e) {
			
			e.printStackTrace();
			websiteResponseListener._onIncorrectWebsiteURL();
		} catch (IOException e) {
			
			e.printStackTrace();
			websiteResponseListener._onIncorrectWebsiteURL();
		}
		
	}

	public static String _getHudsonBuildURL(String environment)
			throws BuildEnvironmentNotFoundException {
		if (environment.equalsIgnoreCase("emer")) {
			return Constants.BuildEnv.emer;
		}
		if (environment.equalsIgnoreCase("trunk")) {
			return Constants.BuildEnv.trunk;
		}
		if (environment.equalsIgnoreCase("uat1")) {
			return Constants.BuildEnv.UAT1;
		}
		if (environment.equalsIgnoreCase("uat2")) {
			return Constants.BuildEnv.UAT2;
		}
		if (environment.equalsIgnoreCase("uat3")) {
			return Constants.BuildEnv.UAT3;
		}
		if (environment.equalsIgnoreCase("uat5")) {
			return Constants.BuildEnv.UAT5;
		} else {
			throw new BuildEnvironmentNotFoundException();
		}
}
}




