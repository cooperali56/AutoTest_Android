package page;

import driver.DriverAppium;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * 商品详情
 */
public class GmmDetailsBuy {

    private DriverAppium driverGmmDetails;

    public GmmDetailsBuy(DriverAppium driverGmmDetails) {
        this.driverGmmDetails = driverGmmDetails;
    }

    private final Logger logger = Logger.getLogger(GmmDetailsBuy.class);

    public void goodsDetailsBuy(String select, String ins) {
        logger.info("已进入商品详情页");
        if (select.equals("立即购买")) {
            driverGmmDetails.elementClick(driverGmmDetails.findElement("xpath", "//*[@text='立即购买'] | //*[@content-desc='立即购买']"));
        }
        driverGmmDetails.awaitCoerce(1000);
        if (ins.equals("随意") || ins.equals("无保障")) {
            driverGmmDetails.elementClick(driverGmmDetails.findElement("xpath", "//*[@text='保障服务']"));
            if ("随意".equals(ins)) {
                driverGmmDetails.elementClick(driverGmmDetails.findElement("xpath", "//*[@text='30天保障']"));
            } else {
                driverGmmDetails.elementClick(driverGmmDetails.findElement("xpath", "//*[@text='无保障']"));
            }
            driverGmmDetails.elementClick(driverGmmDetails.findElement("xpath", "//*[@text='确定']"));
        }
        WebElement element = driverGmmDetails.findElement("xpath", "//*[contains(@text,'的角色名称')]");
        if (element != null) {
            driverGmmDetails.elementInput(element, "干饭人");
        }
        WebElement element1 = driverGmmDetails.findElement("xpath", "//*[contains(@text,'的角色等级')]");
        if (element1 != null) {
            driverGmmDetails.elementInput(element1, "99");
        }
        driverGmmDetails.awaitCoerce(1000);
        //同意协议
        driverGmmDetails.elementClickNeglect(driverGmmDetails.findElement("id", "com.snda.mhh:id/check_agreement"));
        driverGmmDetails.elementClick(driverGmmDetails.findElement("xpath", "//*[@text='立即购买'] | //*[@content-desc='立即购买']"));
        //支付后跳出首页
        driverGmmDetails.awaitCoerce(5000);
        driverGmmDetails.windowBack();
        driverGmmDetails.awaitCoerce(500);
        driverGmmDetails.elementClick(driverGmmDetails.findElement("xpath", "//*[@text='确定']"));
        driverGmmDetails.awaitCoerce(3000);
        Function function = new Function(driverGmmDetails);
        function.isHome();

    }

}
