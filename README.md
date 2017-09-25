## 使用
1. 在 `AndroidManifest.xml` 添加必要权限.
```xml
<manifest>
    <uses-permission android:name="android.permission.INTERNET"/>
    <!-- 如果将下载的文件保存到外部存储器，还需要配置外部存储器的读写权限 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    ...
</manifest>
```

2. 适配 Android N 以及更高的版本, 在 `AndroidManifest.xml` 加入
```xml
    <application>
        ...
        <provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="[注1].fileProvider"
            android:grantUriPermissions="true"
            android:exported="false">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
    </application>
```
并在`res`目录下，创建`xml`文件夹并新增`file_paths.xml`文件，文件内容如下:
```xml
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <external-path
        name="files_root"
        path="Android/data/[注2]/"/>
    <external-path
        name="external_storage_root"
        path="."/>
</paths>
```
> **重要** 注1 和 注2 位置填写`BuildConfig.APPLICATION_ID`的值。

3. (可选) 监听 点击通知 和 下载完成 两种广播, 在 `AndroidManifest.xml` 加入
```xml
    <application>
        ...
        <receiver android:name="具体路径">
            <intent-filter>
                <!-- 配置 点击通知 和 下载完成 两个 action -->
                <action android:name="android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED"/>
                <action android:name="android.intent.action.DOWNLOAD_COMPLETE"/>
            </intent-filter>
        </receiver>
    </application>
```
可选`DownloadManagerReceiver`或`BroadcastReceiver`为父类，具体实现可参照`DownloadReceiver`。

4. 如何使用进行下载  
方案一, 使用默认配置:
```java
DownloadHandle download = DownloadKit.ctx(this).url(url)
					.done()
					.download(..);
// DownloadFileInfo downloadFileInfo = download.query();
```
方案二，使用自定义配置:
```java
DownloadHandle download = DownloadKit.ctx(appContext)
				.url(..)
				.setNotificationVisibility(..)
				.setDescription(..)
				.setTitle(..)
				.setDestinationUri(..)
				.done()
				.download();
```

5. 进行查询下载的状态 或 进度
```java
DownloadFileInfo downloadFileInfo = DownloadQuery.New(((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE)))
				.filter(downloadId)
				.query();
Toast.makeText(this, String.format("下载Id: %s, 状态: %d", downloadFileInfo.getId(), downloadFileInfo.getStatus()), Toast.LENGTH_SHORT).show();
```