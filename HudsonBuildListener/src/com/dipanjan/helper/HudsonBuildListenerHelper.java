/*
 * 
 */
package com.dipanjan.helper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dipanjan.exception.BuildEnvironmentNotFoundException;
import com.dipanjan.listener.BuildListener;
import com.dipanjan.listener.HudsonBuildListener;

// TODO: Auto-generated Javadoc
/**
 * The Class HudsonBuildListenerHelper.
 */
public class HudsonBuildListenerHelper { 
	
	/** The web refresh rate. */
	static int _webRefreshRate = 400;
	
   /**
    * Gets the hudson build status.
    *
    * @param environment the environment
    * @param appName the app name
    * @param hudsonBuildListener the hudson build listener
    * @param hunsonBuildStatus the hunson build status
    * @param refreshRate the refresh rate
    */
   public static void _getHudsonBuildStatus(String environment,String appName,HudsonBuildListener hudsonBuildListener, BuildListener hunsonBuildStatus,int refreshRate){
		 
	   if(refreshRate!=0){
		   _webRefreshRate = refreshRate;
	   }
	   _getHudsonBuildStatus(environment,appName,hudsonBuildListener,hunsonBuildStatus);
    }
	
    /**
     * Gets the hudson build status.
     *
     * @param environment the environment
     * @param appName the app name
     * @param hudsonBuildListener the hudson build listener
     * @param hunsonBuildStatus the hunson build status
     */
    public static void _getHudsonBuildStatus(String environment,String appName,HudsonBuildListener hudsonBuildListener, BuildListener hunsonBuildStatus){
    	
    	 Document doc;
         boolean flag = true;
         LinkedList<String> completedJobList = new LinkedList<String>();
         Map<String,String> buildStatus = new HashMap<String,String>();
         boolean skipOnHudsonBuildStatedListener = false;
         int previousJobListCount = 0;
         try {
         	
         	while(flag){
         		
         		_setDefaultBuildStatus(buildStatus);
         		
         		 String url = hudsonBuildListener._getHudsonBuildURL(environment)+_getMaxBuildVersion(environment,hudsonBuildListener)+"/downstreambuildview/";
                 doc = Jsoup.connect(url).get();
                 if(doc!=null){
                	 if(!skipOnHudsonBuildStatedListener)
                	 hunsonBuildStatus._onHudsonBuildStated();
                	 skipOnHudsonBuildStatedListener = true;
                 }
                 
                
                 Element element = doc.getElementById("main-panel");
                 Elements tds = element.getElementsByTag("span");
                 for (Element td : tds) {
                 	if(td.text().indexOf("SUCCESS")>0){
                 		completedJobList.add(td.text().substring(0, td.text().indexOf(" ")));
                 		
                 	}
                 	
                 }
                 
                 int currentBuild = completedJobList.size()-previousJobListCount;  
                 if(currentBuild>0){
                	for(int index =completedJobList.size();index>previousJobListCount ; index--){
                		hunsonBuildStatus._currentJobFinished(completedJobList.get(index-1));
                	}
                 }
                
               
                 
                 Elements redPngs = doc.select("img[src$=red.png]");
                 if(redPngs.size()>0){
                	 hunsonBuildStatus._onHudsonBuildFail();
                	 flag=false;
                	 break;
                 }
                 
                 Elements gifs = doc.select("img[src$=grey_anime.gif]");
                 
                 
                 buildStatus.put(hudsonBuildListener.BUILD_SUCCESSFUL,""+completedJobList.size());
                 buildStatus.put(hudsonBuildListener.BUILD_REMAINING,""+gifs.size());
                 buildStatus.put(hudsonBuildListener.BUILD_FAILED,""+redPngs.size());
                 
                 hunsonBuildStatus._onHudsonBuildCompletionStatusChanged(buildStatus);
                 previousJobListCount = completedJobList.size();
                 completedJobList.clear();
                 
				if (appName.equals("PORTAL")) {
					if (gifs.size() == 1) {
						flag = false;
						hunsonBuildStatus._onHudsonBuildCompleted();
					}
				} else {
					if (gifs.size() == 0) {
						flag = false;
						hunsonBuildStatus._onHudsonBuildCompleted();
					}
				}
                
                 
                 try {
 					Thread.sleep(_webRefreshRate);
 				} catch (InterruptedException e) {
 					e.printStackTrace();
 				}
         	}
            
         	
         } catch (IOException | BuildEnvironmentNotFoundException e) {
             e.printStackTrace();
         }

    	
    }
    
    /**
     * Sets the count to zero.
     *
     * @param count the count
     * @return the int
     */
    public static int _setCountToZero(int count){
    	count=0;
    	return count;
    }
    
    /**
     * Sets the default build status.
     *
     * @param buildStatus the build status
     */
    public static void _setDefaultBuildStatus(Map<String,String> buildStatus){
    	if(buildStatus!=null)buildStatus.clear();
    }
    
    /**
     * Check if web site up.
     *
     * @param environment the environment
     * @param hudsonBuildListener the hudson build listener
     * @return true, if successful
     */
    public static boolean checkIfWebSiteUp(String environment,HudsonBuildListener hudsonBuildListener ){
    	 Document doc=null;
    	 String url;
    	 int count=0;
		try {
			url = hudsonBuildListener._getHudsonBuildURL(environment)+_getMaxBuildVersion(environment,hudsonBuildListener)+"/downstreambuildview/";
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				count++;
				try {
					if(count<10){
						Thread.sleep(1000);
						checkIfWebSiteUp(environment, hudsonBuildListener );
					}else{
						return false;
					}
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (BuildEnvironmentNotFoundException e) {
			
			e.printStackTrace();
			return false;
		}
		if(doc!=null){
			return true;
		}
		return false;
         
    } 
    
	/**
	 * Gets the max build version.
	 *
	 * @param environment the environment
	 * @param hudsonBuildListener the hudson build listener
	 * @return the int
	 */
	public static int _getMaxBuildVersion(String environment,HudsonBuildListener hudsonBuildListener) {

		Document doc;
		int maxBuildVersion = 0;
		try {
			doc = Jsoup
					.connect(
							hudsonBuildListener._getHudsonBuildURL(environment))
					.get();
			Element table = doc.getElementById("buildHistory");
			Elements tds = table.getElementsByTag("td");
			Set<Integer> buiSet = new HashSet<Integer>();
			for (Element td : tds) {
				Element element = td.firstElementSibling();
				if (element != null) {
					String html = element.html();
					if (html.substring(html.indexOf("#") + 1) != null) {
						try {
							int a = Integer.parseInt(html.substring(html.indexOf("#") + 1));
							buiSet.add(a);
						} catch (NumberFormatException numberFormatException) {
							
						}

					}
				}

			}
			maxBuildVersion = Collections.max(buiSet);
			
		} catch (IOException | BuildEnvironmentNotFoundException e) {
			e.printStackTrace();
		}
		return maxBuildVersion;
	}
	
	
	
}