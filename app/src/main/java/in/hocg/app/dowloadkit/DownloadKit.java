package in.hocg.app.dowloadkit;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

import in.hocg.app.dowloadkit.core.ConfigRequestDone;
import in.hocg.app.dowloadkit.core.RequestKit;
import in.hocg.app.dowloadkit.core.SetUrl;
import in.hocg.app.dowloadkit.query.DownloadHandle;

/**
 * Created by hocgin on 2017/9/25.
 */

//		来源 http://blog.csdn.net/xietansheng/article/details/52513624
public class DownloadKit implements SetUrl,
		ConfigRequestDone {
	private RequestKit request;
	private Context context;
	private DownloadManager manager;
	
	private DownloadKit(Context context) {
		this.context = context;
	}
	
	
	public static SetUrl ctx(Context context) {
		return new DownloadKit(context);
	}
	
	public RequestKit url(String downloadUrl) {
		request = new RequestKit(Uri.parse(downloadUrl), this);
		return request;
	}
	
	/**
	 * 根据 RequestKit 的配置进行下载
	 * @return
	 */
	@Override
	public DownloadHandle download() {
		return _download(getDownloadManager(), request);
	}
	
	/**
	 * 使用部分默认配置进行下载
	 * @param noticeTitle 通知标题
	 * @param noticeDescription 通知描述
	 * @param destinationFile 保存到本地的文件路径
	 * @return
	 */
	@Override
	public DownloadHandle download(String noticeTitle,
	                               String noticeDescription,
	                               File destinationFile) {
		RequestKit request = getRequest();
		/*
		 * 设置在通知栏是否显示下载通知(下载进度), 有 3 个值可选:
		 *    VISIBILITY_VISIBLE:                   下载过程中可见, 下载完后自动消失 (默认)
		 *    VISIBILITY_VISIBLE_NOTIFY_COMPLETED:  下载过程中和下载完成后均可见
		 *    VISIBILITY_HIDDEN:                    始终不显示通知
		 */
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		
		// 设置通知的标题和描述
		request.setTitle(noticeTitle);
		request.setDescription(noticeDescription);

		/*
		 * 设置允许使用的网络类型, 可选值:
		 *     NETWORK_MOBILE:      移动网络
		 *     NETWORK_WIFI:        WIFI网络
		 *     NETWORK_BLUETOOTH:   蓝牙网络
		 * 默认为所有网络都允许
		 */
		// request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
		
		// 添加请求头
		// request.addRequestHeader("User-Agent", "Chrome Mozilla/5.0");
		
		// 设置下载文件的保存位置
		request.setDestinationUri(Uri.fromFile(destinationFile));
		
		// 将下载请求加入下载队列, 返回一个下载ID
		return _download(getDownloadManager(), request);
		
		// 如果中途想取消下载, 可以调用remove方法, 根据返回的下载ID取消下载, 取消下载后下载保存的文件将被删除
		// manager.remove(downloadId);
	}
	
	/**
	 * 使用部分默认配置进行下载
	 * @param noticeTitle 通知标题
	 * @param noticeDescription 通知描述
	 * @return
	 */
	@Override
	public DownloadHandle download(String noticeTitle,
	                               String noticeDescription) {
		File saveFile = new File(Environment.getExternalStorageDirectory(), "update.apk");
		return download(noticeTitle, noticeDescription, saveFile);
	}
	
	
	private DownloadHandle _download(DownloadManager downloadManager, RequestKit request) {
		return new DownloadHandle(downloadManager, request);
	}
	
	/**
	 * 获取 RequestKit
	 * @return
	 */
	public RequestKit getRequest() {
		return request;
	}
	
	/**
	 * 获取 DownloadManager 服务
	 *
	 * @return
	 */
	public DownloadManager getDownloadManager() {
		if (manager == null) {
			manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
		}
		return manager;
	}
}
