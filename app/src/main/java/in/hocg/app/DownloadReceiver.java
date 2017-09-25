package in.hocg.app;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

import in.hocg.app.dowloadkit.BuildConfig;
import in.hocg.app.dowloadkit.DownloadManagerReceiver;
import in.hocg.app.dowloadkit.query.DownloadQuery;

/**
 * Created by hocgin on 2017/9/25.
 */

public class DownloadReceiver extends DownloadManagerReceiver {
	@Override
	public void notificationClicked(long[] downloadIds) {
		
	}
	
	@Override
	public void downloadComplete(long id) {
		String uri = DownloadQuery.New((DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE))
				.filter(id)
				.query().getLocalUri();
		Intent intent = new Intent();
		Uri contentUri;
		// 判断是否是AndroidN以及更高的版本
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			contentUri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".fileProvider", new File(Uri.parse(uri).getPath()));
		} else {
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			contentUri = Uri.parse(uri);
		}
		intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
		getContext().startActivity(intent);
	}
}
