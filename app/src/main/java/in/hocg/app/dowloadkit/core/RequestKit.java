package in.hocg.app.dowloadkit.core;


import android.app.DownloadManager;
import android.content.Context;
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
	
	@Override
	public RequestKit setDestinationUri(Uri uri) {
		super.setDestinationUri(uri);
		return this;
	}
	
	@Override
	public RequestKit setDestinationInExternalFilesDir(Context context, String dirType, String subPath) {
		super.setDestinationInExternalFilesDir(context, dirType, subPath);
		return this;
	}
	
	@Override
	public RequestKit setDestinationInExternalPublicDir(String dirType, String subPath) {
		super.setDestinationInExternalPublicDir(dirType, subPath);
		return this;
	}
	
	public RequestKit allowScanningByMedia() {
		super.allowScanningByMediaScanner();
		return this;
	}
	
	@Override
	public RequestKit addRequestHeader(String header, String value) {
		super.addRequestHeader(header, value);
		return this;
	}
	
	@Override
	public RequestKit setTitle(CharSequence title) {
		super.setTitle(title);
		return this;
	}
	
	@Override
	public RequestKit setDescription(CharSequence description) {
		super.setDescription(description);
		return this;
	}
	
	@Override
	public RequestKit setMimeType(String mimeType) {
		super.setMimeType(mimeType);
		return this;
	}
	
	@Override
	public RequestKit setNotificationVisibility(int visibility) {
		super.setNotificationVisibility(visibility);
		return this;
	}
	
	@Override
	public RequestKit setAllowedNetworkTypes(int flags) {
		super.setAllowedNetworkTypes(flags);
		return this;
	}
	
	@Override
	public RequestKit setAllowedOverRoaming(boolean allowed) {
		super.setAllowedOverRoaming(allowed);
		return this;
	}
	
	@Override
	public RequestKit setAllowedOverMetered(boolean allow) {
		super.setAllowedOverMetered(allow);
		return this;
	}
	
	@Override
	public RequestKit setRequiresCharging(boolean requiresCharging) {
		super.setRequiresCharging(requiresCharging);
		return this;
	}
	
	@Override
	public RequestKit setRequiresDeviceIdle(boolean requiresDeviceIdle) {
		super.setRequiresDeviceIdle(requiresDeviceIdle);
		return this;
	}
	
	@Override
	public RequestKit setVisibleInDownloadsUi(boolean isVisible) {
		super.setVisibleInDownloadsUi(isVisible);
		return this;
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
