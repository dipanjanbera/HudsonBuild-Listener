/*
 * 
 */
package com.dipanjan.helper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import com.dipanjan.exception.BuildEnvironmentNotFoundException;
import com.dipanjan.listener.DownloadListener;

// TODO: Auto-generated Javadoc
/**
 * The Class FileDownloder.
 */
public class FileDownloder {
    
    /**
     * Start download.
     *
     * @param env the env
     * @param buildCopyPath the build copy path
     * @param DOWNLOAD_MODE the download mode
     * @param downloadListener the download listener
     */
    public static void startDownload(String env,String buildCopyPath,int DOWNLOAD_MODE,DownloadListener downloadListener) {
    	String PORTAL_URL=null;
    	String LOSAPP_URL=null;
    	
		try {
			PORTAL_URL = getPortalURL(env);
		
		    LOSAPP_URL = getBaseProductURL(env);
		} catch (BuildEnvironmentNotFoundException e) {
			downloadListener._onDownloadFailed();
			e.printStackTrace();
		}
        String BUILD_COPY_PATH=buildCopyPath;
        String folder_path = createFolder(BUILD_COPY_PATH);
    	switch (DOWNLOAD_MODE) {
		case 1:
			downloadUsingStream(PORTAL_URL,folder_path+"//"+getFileNameFromURL(PORTAL_URL),downloadListener);
			break;
		case 2:
			downloadUsingStream(LOSAPP_URL,folder_path+"//"+getFileNameFromURL(LOSAPP_URL),downloadListener);
			break;
			
		case 3:
			downloadUsingStream(PORTAL_URL,folder_path+"//"+getFileNameFromURL(PORTAL_URL),downloadListener);
			downloadUsingStream(LOSAPP_URL,folder_path+"//"+getFileNameFromURL(LOSAPP_URL),downloadListener);
			break;
			
		default:
			break;
		}
    }
    
    /**
     * Start download.
     *
     * @param env the env
     * @param DOWNLOAD_MODE the download mode
     * @param PORTAL_URL the portal url
     * @param LOSAPP_URL the losapp url
     * @param BUILD_COPY_PATH the build copy path
     * @param downloadListener the download listener
     */
    public static void startDownload(String env,int DOWNLOAD_MODE,String PORTAL_URL,String LOSAPP_URL,String BUILD_COPY_PATH,DownloadListener downloadListener){
        String folder_path = createFolder(BUILD_COPY_PATH);
    	switch (DOWNLOAD_MODE) {
		case 1:
			downloadUsingStream(PORTAL_URL,folder_path+"//"+getFileNameFromURL(PORTAL_URL),downloadListener);
			break;
		case 2:
			downloadUsingStream(LOSAPP_URL,folder_path+"//"+getFileNameFromURL(LOSAPP_URL),downloadListener);
			break;
			
		case 3:
			downloadUsingStream(PORTAL_URL,folder_path+"//"+getFileNameFromURL(PORTAL_URL),downloadListener);
			downloadUsingStream(LOSAPP_URL,folder_path+"//"+getFileNameFromURL(LOSAPP_URL),downloadListener);
			break;
			
		default:
			break;
		}
    }
    
    /**
     * Gets the file name from URL.
     *
     * @param url the url
     * @return the file name from URL
     */
    private static String getFileNameFromURL(String url){
    	return url.substring(url.lastIndexOf("/")+1);
    }

    /**
     * Download using stream.
     *
     * @param urlStr the url str
     * @param file the file
     * @param downloadListener the download listener
     */
    private static void downloadUsingStream(String urlStr, String file,DownloadListener downloadListener){
        URL url;
		try {
			url = new URL(urlStr);
		
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        downloadListener._onDownloadStarted();
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
		} catch (MalformedURLException e) {
			downloadListener._onDownloadFailed();
			e.printStackTrace();
		} catch (IOException e) {
			downloadListener._onDownloadFailed();
		}
        downloadListener._onDownloadEnded();
    }

    /**
     * List all files.
     *
     * @param filePath the file path
     */
    private static void listAllFiles(String filePath){
    	File folder = new File(filePath);
    	File[] listOfFiles = folder.listFiles();

    	    for (int i = 0; i < listOfFiles.length; i++) {
    	      if (listOfFiles[i].isFile()) {
    	        
    	      } else if (listOfFiles[i].isDirectory()) {
    	        System.out.println("Directory " + listOfFiles[i].getName());
    	      }
    	    }
    	
    }
    
    /**
     * Creates the folder.
     *
     * @param path the path
     * @return the string
     */
    private static String createFolder(String path){
    	File folder = new File(path+"BUILD_"+getMaxBuildVersion(path));
    	if (!folder.exists()) {
             if (folder.mkdir()) {
                 System.out.println("Directory is created! "+folder.getAbsolutePath());
             } else {
                 System.out.println("Failed to create directory!");
             }
         }
    	return folder.getAbsolutePath();
    }
    
    /**
     * Gets the max build version.
     *
     * @param path the path
     * @return the max build version
     */
    private static int getMaxBuildVersion(String path){
    	ArrayList<Integer> arrList = new ArrayList<Integer>();
    	File folder = new File(path);
    	File[] listOfFiles = folder.listFiles();
    	for (int i = 0; i < listOfFiles.length; i++) {
    		if (listOfFiles[i].isDirectory()) {
    			if(listOfFiles[i].getName().indexOf("BUILD_")>-1){
    				arrList.add(Integer.parseInt(listOfFiles[i].getName().substring(listOfFiles[i].getName().indexOf("_")+1)));
    			}
    	        
    	      }
    	  }
    	return Collections.max(arrList)+1;
    }
    

    /**
     * Gets the portal URL.
     *
     * @param environment the environment
     * @return the portal URL
     * @throws BuildEnvironmentNotFoundException the build environment not found exception
     */
    public static String getPortalURL(String environment) throws BuildEnvironmentNotFoundException{
    	if (environment.equalsIgnoreCase("emer")) {
    		return PORTALURL.EMER_PORTAL_URL;
    	}
    	if (environment.equalsIgnoreCase("trunk")) {
    		return PORTALURL.TRUNK_PORTAL_URL;
    	}
    	if (environment.equalsIgnoreCase("uat1")) {
    		return PORTALURL.UAT1_PORTAL_URL;
    	}
    	if (environment.equalsIgnoreCase("uat2")) {
    		return PORTALURL.UAT2_PORTAL_URL;
    	}
    	if (environment.equalsIgnoreCase("uat3")) {
    		return PORTALURL.UAT3_PORTAL_URL;
    	}
    	if (environment.equalsIgnoreCase("uat5")) {
    		return PORTALURL.UAT5_PORTAL_URL;
    	} else {
    		throw new BuildEnvironmentNotFoundException();
    	}
    }


/**
 * Gets the base product URL.
 *
 * @param environment the environment
 * @return the base product URL
 * @throws BuildEnvironmentNotFoundException the build environment not found exception
 */
public static String getBaseProductURL(String environment) throws BuildEnvironmentNotFoundException{
	if (environment.equalsIgnoreCase("emer")) {
		return LOS_WEB_APP_URL.EMER_URL;
	}
	if (environment.equalsIgnoreCase("trunk")) {
		return LOS_WEB_APP_URL.TRUNK_URL;
	}
	if (environment.equalsIgnoreCase("uat1")) {
		return LOS_WEB_APP_URL.UAT1_URL;
	}
	if (environment.equalsIgnoreCase("uat2")) {
		return LOS_WEB_APP_URL.UAT2_URL;
	}
	if (environment.equalsIgnoreCase("uat3")) {
		return LOS_WEB_APP_URL.UAT3_URL;
	}
	if (environment.equalsIgnoreCase("uat5")) {
		return LOS_WEB_APP_URL.UAT5_URL;
	} else {
		throw new BuildEnvironmentNotFoundException();
	}
}
}

	
interface PORTALURL{
	String EMER_PORTAL_URL = "";
	String UAT1_PORTAL_URL = "";
	String UAT2_PORTAL_URL = "";
	String UAT3_PORTAL_URL = "";
	String UAT5_PORTAL_URL = "";
	String TRUNK_PORTAL_URL = "";
}

interface LOS_WEB_APP_URL{
	String EMER_URL = "";
	String UAT1_URL = "";
	String UAT2_URL = "";
	String UAT3_URL = "";
	String UAT5_URL = "";
	String TRUNK_URL = "";
}




