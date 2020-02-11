import java.util.Map;

import com.dipanjan.*;

import com.dipanjan.helper.BuildStarter;
import com.dipanjan.helper.FileDownloder;
import com.dipanjan.listener.BuildListener;
import com.dipanjan.listener.DownloadListener;
import com.dipanjan.listener.HudsonBuildListener;
import com.dipanjan.listener.WebsiteResponseListener;



// TODO: Auto-generated Javadoc
/**
 * The Class Start.
 */
public class Start {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]){
		 String BUILD_COPY_PATH = "";
		 String ENV = "emer";
		BuildStarter.startBuild("", new WebsiteResponseListener() {
			
			@Override
			public void _onWebsiteRespondedWithSuccess() {
				System.out.println("Connection established with host!");
				
			}
			
			@Override
			public void _onWebsiteNotResponding() {
				System.exit(0);
				
			}
			
			@Override
			public void _onIncorrectWebsiteURL() {
				System.exit(0);
				
			}

			@Override
			public void _WebsiteConnectionStatus(String msg) {
				System.out.println(msg);
				
			}

			
		});
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HudsonBuildListener hudsonBuildListener= new HudsonBuildListener();
		hudsonBuildListener.setHudsonBuildListener(ENV,"XYZ", hudsonBuildListener, new BuildListener() {
			
			@Override
			public void _onHudsonBuildStated() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void _onHudsonBuildFail() {
				// TODO Auto-generated method stub
				
			}
			
	
			
			@Override
			public void _onHudsonBuildCompleted() {
				FileDownloder.startDownload(ENV,BUILD_COPY_PATH,1, new DownloadListener() {
					
					@Override
					public void _onDownloadStarted() {
						System.out.println("Download Started..");
						
					}
					
					@Override
					public void _onDownloadFailed() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void _onDownloadEnded() {
						System.out.println("Download Completed..");
						
					}
				});
				
			}
			
			@Override
			public void _currentJobFinished(String jobname) {
				System.out.println("Finished : "+jobname);
				
			}

			@Override
			public void _onHudsonBuildCompletionStatusChanged(Map<String, String> buildStatus) {
				// TODO Auto-generated method stub
				
			}
		});
		
}
}
