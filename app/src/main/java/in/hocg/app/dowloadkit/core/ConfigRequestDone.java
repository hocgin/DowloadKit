package in.hocg.app.dowloadkit.core;

import java.io.File;

import in.hocg.app.dowloadkit.query.DownloadHandle;

/**
 * Created by hocgin on 2017/9/25.
 */

public interface ConfigRequestDone {
	DownloadHandle download();
	
	DownloadHandle download(String noticeTitle,
	                        String noticeDescription);
	
	DownloadHandle download(String noticeTitle,
	                        String noticeDescription,
	                        File destinationFile);
	
}
