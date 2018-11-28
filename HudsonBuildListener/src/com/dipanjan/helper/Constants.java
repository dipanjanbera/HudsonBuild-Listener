package com.dipanjan.helper;


public interface Constants {
	public interface BUILD_URL_FOR_MAX_VER {
		String EMERGENCY_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/Emergency/job/framework_Emergency/";
		String TRUNK_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/Trunk/job/framework_trunk/";
		String UAT1_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/UAT1/job/framework_UAT1/";
		String UAT2_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/UAT2/job/framework_UAT2/";
		String UAT3_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/UAT3/job/framework_UAT3/";
		String UAT5_EVN_BUILD = "http://3.209.152.38:7504/hudson/view/UAT5/job/framework_UAT5/";
	}

	public interface BuildEnv {
		String emer = "http://3.209.152.38:7504/hudson/view/Emergency/job/framework_Emergency/build?delay=0sec";
		String trunk = "http://3.209.152.38:7504/hudson/view/Trunk/job/framework_trunk/build?delay=0sec";
		String UAT1 = "http://3.209.152.38:7504/hudson/view/UAT1/job/framework_UAT1/build?delay=0sec";
		String UAT2 = "http://3.209.152.38:7504/hudson/view/UAT2/job/framework_UAT2/build?delay=0sec";
		String UAT3 = "http://3.209.152.38:7504/hudson/view/UAT3/job/framework_UAT3/build?delay=0sec";
		String UAT5 = "http://3.209.152.38:7504/hudson/view/UAT5/job/framework_UAT5/build?delay=0sec";

	}
}

