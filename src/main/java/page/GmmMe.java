package page;

import driver.DriverAppium;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * 个人中心
 */
public class GmmMe {

    private DriverAppium driverMe;

    public GmmMe(DriverAppium driverMe) {
        this.driverMe = driverMe;
    }

    private final Logger logger = Logger.getLogger(GmmMe.class);

    /**
     * 检查是否有已上架或下单
     */
    public void examine() {
        logger.info("是否有已上架商品");
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/persona_tab_new"));
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/my_goods"));
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/mc_title_layout"));
        driverMe.awaitCoerce(500);
        for (int i = 0; i < driverMe.findElements("id", "android:id/content").size(); i++) {
            driverMe.elementClick(driverMe.findElements("id", "android:id/content").get(i));
            driverMe.awaitCoerce(500);
            driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/btnOnSell"));
            driverMe.awaitCoerce(500);
            if (driverMe.isElementExist(By.xpath("//*[@text='下架']"))) {
                for (int j = 0; j < driverMe.findElements("xpath", "//*[@text='下架']").size(); j++) {
                    driverMe.elementClick(driverMe.findElements("xpath", "//*[@text='下架']").get(j));
                }
            }
            driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/mc_title_layout"));
            driverMe.awaitCoerce(500);
        }
        driverMe.windowBack();
        driverMe.windowBack();
        driverMe.awaitCoerce(500);
        logger.info("是否有已生成订单");
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/buy_order"));
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/mc_title_layout"));
        driverMe.awaitCoerce(500);
        for (int i = 0; i < driverMe.findElements("id", "android:id/content").size(); i++) {
            driverMe.elementClick(driverMe.findElements("id", "android:id/content").get(i));
            driverMe.awaitCoerce(500);
            if (driverMe.isElementExist(By.id("com.snda.mhh:id/label"))) {
                driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/label"));
            } else {
                driverMe.elementClick(driverMe.findElement("xpath", "//*[@text='待付款']"));
            }
            driverMe.awaitCoerce(500);
            if (driverMe.isElementExist(By.xpath("//*[@text='取消订单']"))) {
                for (int j = 0; j < driverMe.findElements("xpath", "//*[@text='取消订单']").size(); j++) {
                    driverMe.elementClick(driverMe.findElements("xpath", "//*[@text='取消订单']").get(j));
                    driverMe.awaitCoerce(500);
                    driverMe.elementClick(driverMe.findElement("xpath", "//*[@resource-id='com.snda.mhh:id/list']/android.widget.FrameLayout[2]"));
                    driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/layout"));
                }
            }
            driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/mc_title_layout"));
            driverMe.awaitCoerce(500);
        }
        Function function = new Function(driverMe);
        function.isHome();
    }

    /**
     * 出售流程我发布的
     */
    public void meIssueSell(String gameType) {
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/persona_tab_new"));
        driverMe.awaitCoerce(500);
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/my_goods"));
        driverMe.awaitCoerce(500);
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/mc_title_layout"));
        driverMe.awaitCoerce(500);
        driverMe.elementClick(driverMe.findElement("xpath", "//*[contains(@text,'" + gameType + "')]"));
        driverMe.awaitCoerce(500);
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/btnOnSell"));
        driverMe.awaitCoerce(500);
        if (gameType.equals("账号")) {
            driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/vItemGood"));
        } else {
            driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/vItem"));
        }
        driverMe.awaitCoerce(500);
    }

    /**
     * 购买流程我买到的
     */
    public void meBuy(String gameType){
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/persona_tab_new"));
        driverMe.awaitCoerce(500);
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/buy_order"));
        driverMe.awaitCoerce(500);
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/mc_title_layout"));
        driverMe.awaitCoerce(500);
        driverMe.elementClick(driverMe.findElement("xpath", "//*[contains(@text,'" + gameType + "')]"));
        driverMe.awaitCoerce(500);
        driverMe.elementClick(driverMe.findElement("xpath", "//*[@text='交易中']|//*[@text='待付款']"));
        driverMe.awaitCoerce(500);
        String s = "//*[@resource-id='com.snda.mhh:id/vItemGood']|//*[@resource-id='android:id/list']/android.widget.FrameLayout";
        driverMe.elementClick(driverMe.findElement("xpath", s));
        driverMe.awaitCoerce(500);
        logger.info("订单状态:"+driverMe.elementGetText(driverMe.findElement("id","com.snda.mhh:id/trade_status_prompt")));
        logger.info("订单信息:"+driverMe.elementGetText(driverMe.findElement("xpath","//*[@resource-id='com.snda.mhh:id/trade_info']//android.widget.TextView")));
        driverMe.elementClick(driverMe.findElement("xpath","//*[@text='取消订单']"));
        driverMe.awaitCoerce(500);
        driverMe.elementClick(driverMe.findElement("xpath", "//*[@resource-id='com.snda.mhh:id/list']/android.widget.FrameLayout[2]"));
        driverMe.elementClick(driverMe.findElement("id", "com.snda.mhh:id/layout"));
        driverMe.awaitCoerce(500);
        String state = driverMe.elementGetText(driverMe.findElement("id","com.snda.mhh:id/trade_status_prompt"));
        Assert.assertEquals(state,"交易关闭","断言是否交易订单是否关闭");
        logger.info("订单取消成功");
    }

}
