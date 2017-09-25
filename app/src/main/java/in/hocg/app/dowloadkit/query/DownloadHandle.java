package in.hocg.app.dowloadkit.query;

import android.app.DownloadManager;
import android.net.Uri;

import in.hocg.app.dowloadkit.core.RequestKit;

/**
 * Created by hocgin on 2017/9/25.
 */

public class DownloadHandle {
	private DownloadManager downloadManager;
	private long downloadId;
	public DownloadHandle(DownloadManager downloadManager, RequestKit request) {
		this.downloadManager = downloadManager;
		this.downloadId = downloadManager.enqueue(request);
	}
	
	/**
	 * 获取 下载ID号
	 * @return
	 */
	public long getDownloadId() {
		return downloadId;
	}
	
	/**
	 * 获取文件下载的 Uri
	 */
	public Uri getUriForDownloadedFile() {
		return downloadManager.getUriForDownloadedFile(downloadId);
	}
	
	/**
	 * 获取下载文件的媒体类型
	 * @return
	 */
	public String getMimeTypeForDownloadedFile() {
		return downloadManager.getMimeTypeForDownloadedFile(downloadId);
	}
	
	/**
	 * 取消下载
	 */
	public void cancel() {
		downloadManager.remove(downloadId);
	}
	
	/**
	 * 查询下载信息
	 * @return
	 */
	public DownloadFileInfo query() {
		return DownloadQuery.New(downloadManager)
				.filter(downloadId)
				.query();
	}
}
