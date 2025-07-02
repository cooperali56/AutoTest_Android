package driver;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

/**
 * androidDriver基础操作
 */
public class DriverAppium {

    private AndroidDriver driver;

    public DriverAppium(AndroidDriver driver) {
        this.driver = driver;
    }

    private final Logger logger = Logger.getLogger(DriverAppium.class);

    /*
            By              -----------------------------------------------------------------
     */

    /**
     * 判断定位值by是否存在
     *
     * @param by 定位值
     * @return 真假
     */
    public boolean isElementExist(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * By元素类型区分
     *
     * @param byMethod 定位方法
     * @param byValue  定位value
     * @return by
     */
    public By isBy(String byMethod, String byValue) {
        By by = null;
        switch (byMethod) {
            case "id":
                by = By.id(byValue);
                break;
            case "xpath":
                by = By.xpath(byValue);
                break;
            case "name":
                by = By.name(byValue);
                break;
            case "className":
                by = By.className(byValue);
                break;
            case "tagName":
                by = By.tagName(byValue);
                break;
            case "linkText":
                by = By.linkText(byValue);
                break;
            case "partial":
                by = By.partialLinkText(byValue);
                break;
            case "css":
                by = By.cssSelector(byValue);
                break;
            default:
                logger.info("定位值类型" + byMethod + "有误!");
        }
        return by;
    }

    /*
            Element              -----------------------------------------------------------------
     */

    /**
     * 获取元素
     * @param select
     * @param value
     * @return
     */
    public WebElement findElement(String select, String value) {
        /*
            所获取的元素不可一直使用，回退或者刷新后都需重新在DOM,get,findElement
         */
        WebElement element = null;
        try {
            switch (select) {
                case "id":
                    element = driver.findElementById(value);
                    break;
                case "xpath":
                    element = driver.findElementByXPath(value);
                    break;
                default:
                    logger.info("无此类型");
            }
        }catch (NoSuchElementException e){
            return null;
        }
        return element;
    }

    /**
     * id定位集合
     */
    public List<WebElement> findElements(String select, String value) {
        List<WebElement> webElements = null;
        try {
            switch (select) {
                case "id":
                    webElements = driver.findElementsById(value);
                    break;
                case "xpath":
                    webElements = driver.findElementsByXPath(value);
                    break;
                default:
                    logger.info("无此类型");
            }
        }catch (NoSuchElementException e){
            logger.info("此元素未找到" + value);
            return null;
        }
       return webElements;
    }

    /*
        操作元素element  ---------------------------------------------------------------------
     */

    /**
     * 点击元素
     *
     * @param element
     */
    public void elementClick(WebElement element) {
        if (element == null) {
            throw new NoSuchElementException("元素为空!");
        } else {
            element.click();
        }
    }

    /**
     * 点击元素忽略
     *
     * @param element
     */
    public void elementClickNeglect(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
        }
    }

    /**
     * 输入元素
     *
     * @param element
     * @param value
     */
    public void elementInput(WebElement element, String value) {
        if (element == null) {
            throw new NoSuchElementException("元素为空!");
        } else {
            try {
                element.sendKeys(value);
            } catch (Exception e) {
                throw new ElementClickInterceptedException("元素操作有误");
            }
        }
    }

    /**
     * 获取元素文本值
     *
     * @param element
     * @return
     */
    public String elementGetText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 滑动
     *
     * @param direction 放下
     */
    public void slide(String direction) {
        Dimension size = driver.manage().window().getSize();
        //获取手机屏幕的高度
        int height = size.height;
        //获取手机屏幕的宽度
        int width = size.width;
        if ("左".equals(direction)) {
            //new一个TouchAction对象，调用长按longPress方法
            new TouchAction(driver).longPress(PointOption.point(width - 100, height / 2)).
                    moveTo(PointOption.point(100, height / 2)).release().perform();
        }
        if ("右".equals(direction)) {
            new TouchAction(driver).longPress(PointOption.point(100, height / 2)).
                    moveTo(PointOption.point(width - 100, height / 2)).release().perform();
        }
        if ("上".equals(direction)) {
            new TouchAction(driver).longPress(PointOption.point(width / 2, height * 3 / 4))
                    .moveTo(PointOption.point(width / 2, height / 10)).release().perform();
        }
        if ("下".equals(direction)) {
            new TouchAction(driver).longPress(PointOption.point(width / 2, height / 10))
                    .moveTo(PointOption.point(width / 2, height * 3 / 4)).release().perform();
        }
    }

    /**
     * 按住移动
     *
     * @param element
     */
    public void slideElement(WebElement element) {
        //获取屏幕对象
        Dimension size = driver.manage().window().getSize();
        //获取元素开始坐标
        Point p = element.getLocation();
        int startX = p.x;
        int startY = p.y;
        //得到元素结束坐标
        int endX = startX + size.width / 2;
        int endY = startY;
        //触摸对象
        TouchAction touchAction = new TouchAction(driver);
        //进行移动
        touchAction.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, startY)).release().perform();
    }

    /**
     * 退出当前应用
     */
    public void outDriver() {
        driver.closeApp();
    }

    /**
     * 页面返回
     */
    public void windowBack() {
        driver.navigate().back();
    }

    /*
            等待              -----------------------------------------------------------------
     */

    /**
     * 强制等待
     *
     * @param time 毫秒值
     */
    public void awaitCoerce(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示等待
     *
     * @param by   定位值
     * @param time 分钟
     */
    public void awaitShow(By by, int time) {
        /*
        页面元素是否在页面上可用和可被单击	    elementToBeClickable(By locator)
        页面元素处于被选中状态	            elementToBeSelected(WebElement element)
        页面元素在页面中存在	            presenceOfElementLocated(By locator)
        在页面元素中是否包含特定的文本	    textToBePresentInElement(By locator)
        页面元素值　	                    textToBePresentInElementValue(Bylocator locator, String text)
        标题	                            titleContains(String title)
         */
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) {
            //System.out.println("···此by定位值等待超时:" + by);
        }
    }

    public void clearText(String text) {
        for (int i = 0; i < text.length(); i++) {
            String cmdstr="adb shell input keyevent 67";
            try {
                Runtime.getRuntime().exec(cmdstr).waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
