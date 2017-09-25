package in.hocg.app.dowloadkit.query;

import android.app.DownloadManager;
import android.database.Cursor;

/**
 * Created by hocgin on 2017/9/25.
 */

public class DownloadFileInfo {
	private Long id;
	private String title;
	private String description;
	private String uri;
	private String mediaType;
	/*
	 * 判断是否下载成功，其中状态 status 的值有 5 种:
	 *     DownloadManager.STATUS_SUCCESSFUL:   下载成功
	 *     DownloadManager.STATUS_FAILED:       下载失败
	 *     DownloadManager.STATUS_PENDING:      等待下载
	 *     DownloadManager.STATUS_RUNNING:      正在下载
	 *     DownloadManager.STATUS_PAUSED:       下载暂停
	 */
	private Integer status;
	private String localUri;
	private Long bytesDownloadedSoFar;
	private Long totalSizeBytes;
	private String reason;
	private Long lastModifiedTimestamp;
	private String mediaProviderUri;
	
	protected static DownloadFileInfo Handler(Cursor cursor) {
		DownloadFileInfo downloadFileInfo = null;
		if (cursor.moveToFirst()) {
			downloadFileInfo = new DownloadFileInfo();
			// 下载ID，唯一
			downloadFileInfo.id = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
			// 通知栏标题, 默认""
			downloadFileInfo.title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
			// 通知栏描述, 默认""
			downloadFileInfo.description = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_DESCRIPTION));
			// 下载的uri
			downloadFileInfo.uri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_URI));
			// 媒体类型
			downloadFileInfo.mediaType = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_MEDIA_TYPE));
			// 下载请求的状态，默认下载中
			downloadFileInfo.status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
			// 下载文件在本地保存的路径（Android 7.0 以后 COLUMN_LOCAL_FILENAME 字段被弃用, 需要用 COLUMN_LOCAL_URI 字段来获取本地文件路径的 Uri）
			downloadFileInfo.localUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
			// 已下载的字节大小
			downloadFileInfo.bytesDownloadedSoFar = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
			// 下载文件的总字节大小，默认-1
			downloadFileInfo.totalSizeBytes = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
			// 下载状态的信息
			downloadFileInfo.reason = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_REASON));
			// 最后修改时间 System.currentTimeMillis()
			downloadFileInfo.lastModifiedTimestamp = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_LAST_MODIFIED_TIMESTAMP));
			downloadFileInfo.mediaProviderUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_MEDIAPROVIDER_URI));
		}
		
		cursor.close();
		return downloadFileInfo;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getUri() {
		return uri;
	}
	
	public String getMediaType() {
		return mediaType;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getLocalUri() {
		return localUri;
	}
	
	public Long getBytesDownloadedSoFar() {
		return bytesDownloadedSoFar;
	}
	
	public Long getTotalSizeBytes() {
		return totalSizeBytes;
	}
	
	public String getReason() {
		return reason;
	}
	
	public Long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}
	
	public String getMediaProviderUri() {
		return mediaProviderUri;
	}
}
