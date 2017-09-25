package in.hocg.app.dowloadkit.query;

/**
 * Created by hocgin on 2017/9/25.
 */

public interface AddFilter {
	AddFilter filter(int flags);
	
	AddFilter filter(long... downloadId);
	
	DownloadFileInfo query();
}
