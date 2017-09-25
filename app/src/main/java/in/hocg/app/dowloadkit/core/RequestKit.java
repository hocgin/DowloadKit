package in.hocg.app.dowloadkit.core;


import android.app.DownloadManager;
import android.net.Uri;

import in.hocg.app.dowloadkit.DownloadKit;

/**
 * Created by hocgin on 2017/9/25.
 */

public class RequestKit extends DownloadManager.Request {
	private DownloadKit downloadKit;
	/**
	 * @param uri the HTTP or HTTPS URI to download.
	 */
	public RequestKit(Uri uri, DownloadKit downloadKit) {
		super(uri);
		this.downloadKit = downloadKit;
	}
	
	public ConfigRequestDone done() {
		return downloadKit;
	}
	
	RequestKit getRequest(){
		return this;
	}
	
	DownloadManager getDownloadManager(){
		return downloadKit.getDownloadManager();
	}
}
