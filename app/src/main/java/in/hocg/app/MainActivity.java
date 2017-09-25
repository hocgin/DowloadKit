package in.hocg.app;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import in.hocg.app.dowloadkit.DownloadKit;
import in.hocg.app.dowloadkit.R;
import in.hocg.app.dowloadkit.query.DownloadFileInfo;
import in.hocg.app.dowloadkit.query.DownloadHandle;
import in.hocg.app.dowloadkit.query.DownloadQuery;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	Button button;
	Button button2;
	DownloadHandle download;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button);
		button2 = (Button) findViewById(R.id.button2);
		button.setOnClickListener(this);
		button2.setOnClickListener(this);
		
		//		必要的权限申请
		new RxPermissions(this).request(
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.CAMERA,
				Manifest.permission.READ_EXTERNAL_STORAGE)
				.subscribe(new Consumer<Boolean>() {
					@Override
					public void accept(@NonNull Boolean aBoolean) throws Exception {
						
					}
				});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button:
				download = DownloadKit.ctx(this).url("https://downapp.baidu.com/baidutieba/AndroidPhone/8.8.8.0/1/1019960r/20170904163649/baidutieba_AndroidPhone_8-8-8-0_1019960r.apk")
						.done()
						.download("百度贴吧下载", "测试");
				break;
			case R.id.button2:
				if (download == null) {
					Toast.makeText(this, "请先进行下载", Toast.LENGTH_SHORT).show();
					return;
				}
				DownloadFileInfo downloadFileInfo = DownloadQuery.New(((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE)))
						.filter(download.getDownloadId())
						.query();
				Toast.makeText(this, String.format("下载Id: %s, 状态: %d", downloadFileInfo.getId(), downloadFileInfo.getStatus()), Toast.LENGTH_SHORT).show();
				break;
		}
	}
}
