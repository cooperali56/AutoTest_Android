package page;

import driver.DriverAppium;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

/**
 * 上架填写资料
 */
public class GmmShelf {

    private DriverAppium driverGmmShelf;

    public GmmShelf(DriverAppium driverGmmShelf) {
        this.driverGmmShelf = driverGmmShelf;
    }

    private final Logger logger = Logger.getLogger(GmmShelf.class);

    /**
     * 所有填写商品信息可操作
     */
    public void goodsShelfData(String gameType, String price, boolean insurance) {
        logger.info("填写上架信息···");
        driverGmmShelf.awaitCoerce(1000);
        //标题和价格
        if (gameType.equals("账号")) {
            driverGmmShelf.elementInput(driverGmmShelf.findElement("id", "com.snda.mhh:id/goods_title"), "sdkGOGO");
            driverGmmShelf.elementInput(driverGmmShelf.findElement("xpath", "//*[contains(@text,'商品价格')]"), price);
        } else {
            driverGmmShelf.elementInput(driverGmmShelf.findElement("xpath", "//*[contains(@text,'写商品')]"), "sdkGOGO");
            driverGmmShelf.elementInput(driverGmmShelf.findElement("xpath", "//*[contains(@text,'写价格')]"), price);
        }
        //当前页所有可点击元素
        List<WebElement> clickElementS = null;
        if (gameType.equals("账号")) {
            clickElementS = driverGmmShelf.findElements("xpath", "//*[@resource-id='com.snda.mhh:id/selectBtn']//*[@resource-id='com.snda.mhh:id/title']");
        } else {
            clickElementS = driverGmmShelf.findElements("id", "com.snda.mhh:id/title");
        }
        Random random = new Random();
        for (int i = 0; i < clickElementS.size(); i++) {
            //重复使用
            String again = "//*[@resource-id='com.snda.mhh:id/list']/android.widget.FrameLayout";
            String againZ = "//*[@resource-id='com.snda.mhh:id/mainGroup']/android.widget.RadioButton";
            //循环元素是否可操作和text值为指定参数的元素
            if (clickElementS.get(i).getText().contains("区服")) {
                driverGmmShelf.elementClick(clickElementS.get(i));
                driverGmmShelf.awaitCoerce(500);
                List<WebElement> againsZ = driverGmmShelf.findElements("xpath", againZ);
                driverGmmShelf.awaitCoerce(500);
                if (driverGmmShelf.isElementExist(By.xpath(againZ))) {
                    driverGmmShelf.elementClick(againsZ.get(random.nextInt(againsZ.size())));
                    driverGmmShelf.awaitCoerce(500);
                    String againss = "//*[@resource-id='com.snda.mhh:id/subGroup']/android.widget.RadioButton";
                    driverGmmShelf.elementClick(driverGmmShelf.findElement("xpath", againss));
                    driverGmmShelf.awaitCoerce(500);
                }
            }
            if (clickElementS.get(i).getText().contains("职业")) {
                driverGmmShelf.elementClick(clickElementS.get(i));
                driverGmmShelf.awaitCoerce(500);
                List<WebElement> agains = driverGmmShelf.findElements("xpath", again);
                if (agains != null && agains.size() != 0) {
                    driverGmmShelf.elementClick(agains.get(random.nextInt(agains.size())));
                    driverGmmShelf.awaitCoerce(500);
                }
            }
            if (clickElementS.get(i).getText().contains("等级")) {
                driverGmmShelf.elementClick(clickElementS.get(i));
                driverGmmShelf.awaitCoerce(500);
                List<WebElement> agains = driverGmmShelf.findElements("xpath", again);
                if (agains != null && agains.size() != 0) {
                    driverGmmShelf.elementClick(agains.get(random.nextInt(agains.size())));
                    driverGmmShelf.awaitCoerce(500);
                    List<WebElement> againss = driverGmmShelf.findElements("xpath", again);
                    if (againss != null) {
                        driverGmmShelf.elementClick(againss.get(random.nextInt(againss.size())));
                        driverGmmShelf.awaitCoerce(500);
                    }
                }
            }
            if (clickElementS.get(i).getText().contains("性别")) {
                driverGmmShelf.elementClick(clickElementS.get(i));
                driverGmmShelf.awaitCoerce(500);
                List<WebElement> agains = driverGmmShelf.findElements("xpath", again);
                if (agains != null && agains.size() != 0) {
                    driverGmmShelf.elementClick(agains.get(random.nextInt(agains.size())));
                    driverGmmShelf.awaitCoerce(500);
                }
            }
            if (clickElementS.get(i).getText().contains("阵营")) {
                driverGmmShelf.elementClick(clickElementS.get(i));
                driverGmmShelf.awaitCoerce(500);
                List<WebElement> agains = driverGmmShelf.findElements("xpath", again);
                if (agains != null && agains.size() != 0) {
                    driverGmmShelf.elementClick(agains.get(random.nextInt(agains.size())));
                    driverGmmShelf.awaitCoerce(500);
                }
            }
            if (clickElementS.get(i).getText().contains("商品有效期")) {
                clickElementS.get(i).click();
                driverGmmShelf.awaitCoerce(500);
                List<WebElement> againss = driverGmmShelf.findElements("xpath", again);
                if (againss != null && againss.size() != 0) {
                    driverGmmShelf.elementClick(againss.get(random.nextInt(againss.size())));
                    driverGmmShelf.awaitCoerce(500);
                }
            }
            driverGmmShelf.awaitCoerce(1000);
            if (clickElementS.get(i).getText().contains("保障服务")) {
                driverGmmShelf.elementClick(driverGmmShelf.findElement("id", "com.snda.mhh:id/select_ins"));
                if (insurance) {
                    driverGmmShelf.elementClick(driverGmmShelf.findElement("xpath", "//*[@text='30天保障']"));
                } else {
                    driverGmmShelf.elementClick(driverGmmShelf.findElement("xpath", "//*[@text='无保障']"));
                }
                driverGmmShelf.elementClick(driverGmmShelf.findElement("id", "com.snda.mhh:id/confirmBtn"));
            }
        }
        driverGmmShelf.awaitCoerce(1000);
        List<WebElement> inputElement = driverGmmShelf.findElements("id", "com.snda.mhh:id/value");
        for (int i = 0; i < inputElement.size(); i++) {
            if (inputElement.get(i).getText().contains("客服暗号")) {
                driverGmmShelf.elementInput(inputElement.get(i), "789987");
            }
            if (inputElement.get(i).getText().contains("角色名称")) {
                driverGmmShelf.elementInput(inputElement.get(i), "gmmgogo");
            }
            if (inputElement.get(i).getText().contains("存放处")) {
                driverGmmShelf.elementInput(inputElement.get(i), "脑子里");
            }
            if (inputElement.get(i).getText().contains("锁密码")) {
                driverGmmShelf.elementInput(inputElement.get(i), "6573");
            }
        }
        driverGmmShelf.awaitCoerce(1000);
        //下一步
        if (driverGmmShelf.isElementExist(By.id("com.snda.mhh:id/textView"))) {
            driverGmmShelf.elementClick(driverGmmShelf.findElement("id", "com.snda.mhh:id/textView"));
        }
        //传图
        if (driverGmmShelf.isElementExist(By.id("com.snda.mhh:id/mc_image"))) {
            driverGmmShelf.elementClick(driverGmmShelf.findElement("id", "com.snda.mhh:id/mc_image"));
            driverGmmShelf.awaitCoerce(500);
            driverGmmShelf.elementClick(driverGmmShelf.findElement("id", "com.snda.mhh:id/mc_take_pic_from_local"));
            driverGmmShelf.awaitCoerce(500);
            List<WebElement> imgList = driverGmmShelf.findElements("id", "com.snda.mhh:id/mc_image_item_select");
            driverGmmShelf.elementClick(imgList.get(random.nextInt(imgList.size())));
            driverGmmShelf.elementClick(driverGmmShelf.findElement("id", "com.snda.mhh:id/mc_btnRight"));
            driverGmmShelf.awaitCoerce(1000);
        }
        if (gameType.equals("账号")) {
            driverGmmShelf.slide("上");
            driverGmmShelf.slide("上");
        }
        driverGmmShelf.awaitCoerce(1000);
        List<WebElement> inputElementS = driverGmmShelf.findElements("id", "com.snda.mhh:id/value");
        for (int i = 0; i < inputElementS.size(); i++) {
            if (inputElementS.get(i).getText().contains("输入装备名称")) {
                driverGmmShelf.elementInput(inputElementS.get(i), "超级大刀");
            }
            if (inputElementS.get(i).getText().contains("出售装备件数")) {
                driverGmmShelf.elementInput(inputElementS.get(i), "20");
            }
            if (inputElementS.get(i).getText().contains("描述")) {
                driverGmmShelf.elementInput(inputElementS.get(i), "这装备我可是花了老大的功夫搞定的");
            }
            if (inputElementS.get(i).getText().contains("金币数量")) {
                driverGmmShelf.elementInput(inputElementS.get(i), "100");
            }
            if (inputElementS.get(i).getText().contains("角色名称")) {
                driverGmmShelf.elementInput(inputElementS.get(i), "G666");
            }
            if (inputElementS.get(i).getText().contains("品锁密码")) {
                driverGmmShelf.elementInput(inputElementS.get(i), "9870");
            }
            if (inputElementS.get(i).getText().contains("数客服")) {
                driverGmmShelf.elementInput(inputElementS.get(i), "543742");
            }
        }

    }

    public void goodsEdit() {
        WebElement element = driverGmmShelf.findElement("xpath", "//*[@resource-id='com.snda.mhh:id/goods_title']|//*[contains(@text,'写商品标')]");
        if (element != null) {
            element.sendKeys("编辑");
        } else {
            driverGmmShelf.findElement("xpath", "//*[@text='商品标题']/../..//*[@resource-id='com.snda.mhh:id/value']").sendKeys("编辑");
            driverGmmShelf.elementClickNeglect(driverGmmShelf.findElement("xpath","//*[@text='下一步']"));
        }
    }

    public void goodsAnew() {
        WebElement element =driverGmmShelf.findElement("xpath", "//*[@resource-id='com.snda.mhh:id/goods_title']|//*[contains(@text,'写商品标')]");
        if (element!=null){
            element.sendKeys("重新");
        }else {
            driverGmmShelf.findElement("xpath", "//*[@text='商品标题']/../..//*[@resource-id='com.snda.mhh:id/value']").sendKeys("重新");
            driverGmmShelf.elementClickNeglect(driverGmmShelf.findElement("xpath","//*[@text='下一步']"));
        }
    }
}
