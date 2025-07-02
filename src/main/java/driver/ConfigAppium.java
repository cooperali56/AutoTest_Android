package driver;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * 配置Appium连接手机
 */
public class ConfigAppium {

    //无参
    public ConfigAppium() {
    }

    //声明对象
    private static AndroidDriver androidDriver;

    //有参
    public ConfigAppium(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    //get方法
    public static AndroidDriver getAndroidDriver() {
        return androidDriver;
    }

    //日志
    private final Logger logger = Logger.getLogger(ConfigAppium.class);

    /**
     * AndroidDriver参数配置
     *
     * @param deviceName 设备序列号
     * @param app        安装包地址
     * @param versions   安卓系统版本
     * @return
     */
    public AndroidDriver androidConfig(String deviceName, String app, String versions) {
        System.out.println("开始创建设备连接···");
        //选择本地sdk安装包地址
        File relative = new File("");
        String appSdk = relative.getAbsolutePath() + File.separator + "apps\\";
        //设置自动化相关参数（设置的过程中只需更改value值）
        DesiredCapabilities cap = new DesiredCapabilities();
        //使用哪个自动化引擎
        cap.setCapability("automationName", "Appium");
        //设置测试的平台是IOS/Android/FirefoxOS
        cap.setCapability("platformName", "Android");
        //设置测试的安卓系统版本
        cap.setCapability("platformVersion", versions);
        //设置设备的序列号
        cap.setCapability("deviceName", deviceName);
        //设置apk路径
        cap.setCapability("app", appSdk + app);
        //设置apk的包名
        cap.setCapability("appPackage", "com.snda.mhh");
        //设置启动Activity
        cap.setCapability("appActivity", "com.snda.mhh.business.welcome.WelcomeActivity");
        //使用resetKeyboard输入 测试完之后恢复默认
        cap.setCapability("resetKeyboard", true);
        //使用 Unicode 输入法 ，支持中文输入
        cap.setCapability("unicodeKeyboard", true);
        //重置输入法到原有状态
        cap.setCapability("resetKeyboard", true);
        //每次启动时覆盖session(会议)否则第二次后运行会报错不能新建session
        cap.setCapability("sessionOverride", true);
        //没命令多少秒退出
        cap.setCapability("newCommandTimeout", 30);
        //获取toast消息
        cap.setCapability("automationName", "UiAutomator1");
        //防止重安装app
        cap.setCapability("noReset", false);
        //把所有配置参数传入appiumService
        try {
            androidDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //隐式等待
        androidDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return androidDriver;
    }

}
