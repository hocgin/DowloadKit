package in.hocg.app.dowloadkit.query;

import android.app.DownloadManager;
import android.database.Cursor;

/**
 * Created by hocgin on 2017/9/25.
 */

public class DownloadQuery implements AddFilter {
	private DownloadManager downloadManager;
	
	private DownloadManager.Query query;
	
	private Cursor cursor;
	
	private DownloadQuery(DownloadManager downloadManager) {
		this.downloadManager = downloadManager;
		this.query = new DownloadManager.Query();
	}
	
	public static AddFilter New(DownloadManager downloadManager) {
		return new DownloadQuery(downloadManager);
	}
	
	/**
	 * 根据 下载ID 过滤结果
	 * @param downloadId 下载ID
	 * @return
	 */
	@Override
	public AddFilter filter(long... downloadId) {
		query.setFilterById(downloadId);
		return this;
	}
	
	/**
	 * 还可以根据状态过滤结果
	 * @param flags 状态(DownloadManager.STATUS_**)
	 * @return
	 */
	@Override
	public AddFilter filter(int flags) {
		query.setFilterByStatus(flags);
		return this;
	}
	
	@Override
	public DownloadFileInfo query() {
		cursor = downloadManager.query(query);
		return DownloadFileInfo.Handler(cursor);
	}
}
