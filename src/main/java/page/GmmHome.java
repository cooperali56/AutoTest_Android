package page;

import driver.DriverAppium;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

/**
 * 首页
 */
public class GmmHome {

    private DriverAppium driverHome;

    public GmmHome(DriverAppium driverHome) {
        this.driverHome = driverHome;
    }

    private final Logger logger = Logger.getLogger(GmmHome.class);

    /**
     * 首次安装允许权限
     */
    public void initApp() {
        logger.info("首次进入app初始化~~~");
        driverHome.elementClickNeglect(driverHome.findElement("xpath", "//*[@text='始终允许']"));
        driverHome.elementClickNeglect(driverHome.findElement("xpath", "//*[@text='始终允许']"));
        driverHome.elementClickNeglect(driverHome.findElement("xpath", "//*[@text='允许']"));
        driverHome.elementClickNeglect(driverHome.findElement("xpath", "//*[@text='允许']"));
        driverHome.awaitCoerce(1000);
        driverHome.slide("左");
        //立即体验
        driverHome.elementClickNeglect(driverHome.findElement("id", "com.snda.mhh:id/ivBtn"));
        //同意
        driverHome.elementClickNeglect(driverHome.findElement("id", "com.snda.mhh:id/btn_update_cancel"));
        //更新
        driverHome.elementClickNeglect(driverHome.findElement("id", "com.snda.mhh:id/agree_button"));
        driverHome.elementClickNeglect(driverHome.findElement("id", "com.snda.mhh:id/btn_update_cancel"));
    }

    /**
     * 主动登录
     *
     * @param account phone
     */
    public void login(String account) {
        logger.info("开始登录~~~");
        //底部我
        driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/persona_tab_new"));
        //顶部登录
        driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/btn_login_sign"));
        driverHome.awaitCoerce(1000);
        driverHome.elementInput(driverHome.findElement("id", "com.snda.mhh:id/edit_login_phone"), account);
        //点击验证码
        driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/btn_login_getcode"));
        driverHome.awaitCoerce(2000);
        //滑动获取
        driverHome.slideElement(driverHome.findElement("id", "com.snda.mhh:id/sb_drag"));
        driverHome.awaitCoerce(1000);
        driverHome.elementInput(driverHome.findElement("id", "com.snda.mhh:id/edit_login_smscode"), "201961");
        driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/btn_login_submit"));
        WebElement userName = driverHome.findElement("id", "com.snda.mhh:id/nickname");
        logger.info("欢迎登录gmmApp:" + driverHome.elementGetText(userName));
        driverHome.awaitCoerce(1000);
    }

    /**
     * 首页底部出售前选择
     *
     * @param gameName
     * @param gameType
     */
    public void sellSelect(String gameName, String gameType) {
        Function function = new Function(driverHome);
        function.isHome();
        logger.info("开始出售流程:" + gameName + "," + gameType + "类型");
        //底部出售
        driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/sell_tab_new"));
        driverHome.awaitCoerce(1000);
        //选择游戏名和类型
        if (!driverHome.isElementExist(By.xpath("//*[contains(@text,'请输入游戏')]"))) {
            WebElement element = driverHome.findElement("xpath", "//*[contains(@text,'请输入您的账号')]");
            driverHome.elementClick(element);
            String s = element.getText();
            driverHome.clearText(s);
        }
        driverHome.elementInput(driverHome.findElement("xpath", "//*[contains(@text,'请输入游戏')]"), gameName);
        driverHome.awaitCoerce(1000);
        driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/arrow_img"));
        driverHome.awaitCoerce(1000);
        driverHome.elementClick(driverHome.findElement("xpath", "//*[@text='" + gameType + "']"));
        function.sms();
        driverHome.awaitCoerce(1000);
        //确认按钮
        WebElement elementOK = null;
        //继续上架
        WebElement elementGoon = null;
        //通信证列表
        List<WebElement> txzList = driverHome.findElements("id", "com.snda.mhh:id/item_all");
        if (txzList == null) {
            throw new NullPointerException("此账户下没有通信证");
        }
        for (int i = 0; i < txzList.size(); i++) {
            //List<WebElement> txzListText = driverHome.findElements("id", "com.snda.mhh:id/accountName");
            //String txzText = driverHome.elementGetText(txzListText.get(i));
            //点击通信证
            txzList.get(i).click();
            driverHome.awaitCoerce(1000);
            //判断底部按钮可操作
            elementOK = driverHome.findElement("id", "com.snda.mhh:id/confirm");
            if (elementOK.isEnabled()) {
                driverHome.elementClick(elementOK);
                driverHome.awaitCoerce(1000);
                elementGoon = driverHome.findElement("xpath", "//*[@text='继续上架']");
                driverHome.elementClick(elementGoon);
                driverHome.awaitCoerce(1000);
                //？？？获取提示
                WebElement elementTitle = driverHome.findElement("id", "com.snda.mhh:id/mc_title");
                if (driverHome.elementGetText(elementTitle).contains("商品信息")) {
                    //logger.info("即将上架通信证=" + txzText);
                    break;
                }
                if (driverHome.elementGetText(elementTitle).contains("角色列表")) {
                    elementOK = driverHome.findElement("id", "com.snda.mhh:id/confirm");
                    if (elementOK.isEnabled()) {
                        driverHome.elementClick(elementOK);
                        break;
                    } else {
                        logger.info("该商品不需要详细角色信息");
                        elementOK.click();
                    }
                    //判断角色列表
                    driverHome.awaitCoerce(1000);
                    if (driverHome.isElementExist(By.id("com.snda.mhh:id/item_all"))) {
                        List<WebElement> roleList = driverHome.findElements("id", "com.snda.mhh:id/item_all");
                        for (int j = 0; j < roleList.size(); j++) {
                            //选择角色
                            roleList.get(j).click();
                            List<WebElement> roleListText = driverHome.findElements("id", "com.snda.mhh:id/roleName");
                            //logger.info("即将上架通信证角色简介=" + txzText + ":" + roleListText.get(j).getText());
                            driverHome.awaitCoerce(1000);
                            //判断底部按钮可操作
                            elementOK = driverHome.findElement("id", "com.snda.mhh:id/confirm");
                            if (elementOK.isEnabled()) {
                                elementOK.click();
                                //成功后进入填写商品资料页面
                                driverHome.awaitCoerce(1000);
                                break;
                            } else {
                                logger.info(roleListText + ";此选角色无法出售");
                                driverHome.windowBack();
                            }
                        }
                        elementTitle = driverHome.findElement("id", "com.snda.mhh:id/mc_title");
                        if (driverHome.elementGetText(elementTitle).contains("商品信息")) {
                            break;
                        }
                    } else {
                        driverHome.windowBack();
                    }
                }
                if (driverHome.elementGetText(elementTitle).contains("出售游戏角色")) {
                    logger.info("开始出售手游角色");
                    //手游
                }
                if (driverHome.elementGetText(elementTitle).contains("发布商品")) {
                    break;
                }
            } else {
                logger.info("所选通信证无法出售");
            }
        }
    }

    /**
     * 首页购买类型选择
     *
     * @param gameName
     * @param gameType
     */
    public void buySelect(String gameName, String gameType, String ins) {
        Function function = new Function(driverHome);
        function.isHome();
        logger.info("开始购买流程:" + gameName + "," + gameType + "类型");
        driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/home_tab_new"));
        driverHome.elementClick(driverHome.findElement("xpath", "//*[@text='" + gameType + "']"));
        driverHome.awaitCoerce(500);
        //重复类型初始选择
        if (!driverHome.isElementExist(By.xpath("//*[contains(@text,'请输入游戏')]"))) {
            driverHome.elementClickNeglect(driverHome.findElement("id", "com.snda.mhh:id/mc_title_layout"));
        }
        driverHome.awaitCoerce(500);
        driverHome.elementInput(driverHome.findElement("xpath", "//*[contains(@text,'请输入游戏')]"), gameName);
        driverHome.awaitCoerce(500);
        driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/arrow_img"));
        driverHome.awaitCoerce(500);
        if (!ins.equals("没有")) {
            driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/tvFilter"));
            driverHome.awaitCoerce(500);
            driverHome.elementClick(driverHome.findElement("xpath", "//*[@text='综合']"));
            if (ins.equals("保障")) {
                driverHome.elementClick(driverHome.findElement("xpath", "//*[@text='有保障服务']"));
            } else {
                driverHome.elementClick(driverHome.findElement("xpath", "//*[@text='无保障服务']"));
            }
            driverHome.elementClick(driverHome.findElement("id", "com.snda.mhh:id/done"));
            driverHome.awaitCoerce(500);
        }
        //商品列表 com.snda.mhh:id/vItemGood
        String s = "//*[@resource-id='com.snda.mhh:id/vItemGood']|//*[@resource-id='android:id/list']/android.widget.FrameLayout";
        List<WebElement> goodsList = driverHome.findElements("xpath", s);
        Random random = new Random();
        driverHome.elementClick(goodsList.get(random.nextInt(goodsList.size())));
    }

}
