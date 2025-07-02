package page;

import driver.DriverAppium;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * 功能集合
 */
public class Function {

    private DriverAppium driverFun;

    public Function(DriverAppium driverFun) {
        this.driverFun = driverFun;
    }

    private final Logger logger = Logger.getLogger(Function.class);

    /**
     * 判断是否在首页
     */
    public void isHome(){
        boolean homeElement = driverFun.isElementExist(By.id("com.snda.mhh:id/home_tab_new"));
        while (!homeElement){
            driverFun.windowBack();
            driverFun.awaitCoerce(1000);
            if (driverFun.isElementExist(By.id("com.snda.mhh:id/home_tab_new"))){
                homeElement = true;
            }
        }
    }

    /**
     * 短信
     */
    public void sms(){
        if (driverFun.isElementExist(By.xpath("//*[@text='发送验证码']"))){
            driverFun.elementClick(driverFun.findElement("xpath","//*[@text='发送验证码']"));
            driverFun.elementInput(driverFun.findElement("xpath","//*[@text='请输入验证码']"),"201961");
            driverFun.awaitCoerce(1000);
            driverFun.elementClick(driverFun.findElement("id","com.snda.mhh:id/confirm_btn"));
        }
    }

    /**
     * 发布操作
     */
    public void issue(){
        if (driverFun.isElementExist(By.xpath("//*[@text='发布']"))){
            driverFun.elementClick(driverFun.findElement("xpath","//*[@text='发布']"));
            //打印确认出售提示详细信息 com.snda.mhh:id/btn_confirm
            logger.info(driverFun.elementGetText(driverFun.findElement("id","com.snda.mhh:id/dialog_content")));
            driverFun.elementClickNeglect(driverFun.findElement("id","com.snda.mhh:id/check_agreement"));
            driverFun.elementClick(driverFun.findElement("xpath","//*[@text='确定']"));
            driverFun.awaitCoerce(3000);
            //判断结果 //*[contains(@text,'请输入游戏')]
            if (driverFun.isElementExist(By.xpath("//*[contains(@text,'成功')]"))){
                logger.info(driverFun.elementGetText(driverFun.findElement("xpath","//*[contains(@text,'成功')]")));
                driverFun.awaitCoerce(1000);
                //返回首页
                if (driverFun.isElementExist(By.id("com.snda.mhh:id/sell_ok"))){
                    driverFun.elementClick(driverFun.findElement("id","com.snda.mhh:id/sell_ok"));
                }else {
                    isHome();
                }
                driverFun.awaitCoerce(1000);
            }else {
                logger.error("上架失败返回首页");
                isHome();
            }
        }else {
            logger.info("发布按钮不在");
        }
    }

}
