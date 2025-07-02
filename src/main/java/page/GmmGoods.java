package page;

import driver.DriverAppium;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 商品详情
 */
public class GmmGoods {

    private DriverAppium driverGmmGoods;

    public GmmGoods(DriverAppium driverGmmGoods) {
        this.driverGmmGoods = driverGmmGoods;
    }

    private final Logger logger = Logger.getLogger(GmmGoods.class);

    public void goodsDetails(String select) {
        if (driverGmmGoods.isElementExist(By.xpath("//*[@text='" + select + "']"))) {
            driverGmmGoods.elementClick(driverGmmGoods.findElement("xpath", "//*[@text='" + select + "']"));
        } else {
            driverGmmGoods.elementClick(driverGmmGoods.findElement("xpath", "//*[@content-desc='" + select + "']"));
        }
        driverGmmGoods.awaitCoerce(1000);
        if (select.equals("下架")) {
            WebElement element = null;
            if (driverGmmGoods.isElementExist(By.id("com.snda.mhh:id/tv_state"))) {
                element = driverGmmGoods.findElement("id", "com.snda.mhh:id/tv_state");
                if (element.getText().equals("已下架")) {
                    logger.info("商品下架成功");
                }
            } else {
                logger.info(driverGmmGoods.findElement("xpath", "//*[@content-desc='已下架']").getText());
            }
        }
    }

}
