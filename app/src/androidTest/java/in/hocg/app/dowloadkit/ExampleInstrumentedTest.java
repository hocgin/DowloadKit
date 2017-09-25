package in.hocg.app.dowloadkit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import in.hocg.app.dowloadkit.query.DownloadFileInfo;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
	@Test
	public void useAppContext() throws Exception {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getTargetContext();
		DownloadFileInfo query = DownloadKit.ctx(appContext).url("https://downapp.baidu.com/baidutieba/AndroidPhone/8.8.8.0/1/1019960r/20170904163649/baidutieba_AndroidPhone_8-8-8-0_1019960r.apk")
				.done()
				.download("百度贴吧下载", "测试").query();
		
		assertEquals("in.hocg.app.dowloadkit", appContext.getPackageName());
	}
}
