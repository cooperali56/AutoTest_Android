package flow;

import driver.ConfigAppium;
import driver.DriverAppium;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.GmmHome;
import page.GmmMe;

/**
 * 测试类
 */
public class TestFlow {

    @DataProvider(name = "sell")
    private Object[][] sellTestData() {
        return new Object[][]{
                {"龙之谷", "账号", "55", true},
                {"星辰变", "账号", "55", false},
                {"彩虹岛", "账号", "55", true},
                {"冒险岛", "装备", "66", false},
                {"传奇永恒", "游戏币", "77", false},
        };
    }

    @DataProvider(name = "buy")
    private Object[][] buyTestData() {
        return new Object[][]{
                {"冒险岛", "账号", "随意"},
                {"彩虹岛", "账号", "保障"},
                {"龙之谷", "账号", "无保障"},
                {"龙之谷", "装备", "没有"},
                {"龙之谷", "游戏币", "没有"},
        };
    }

    ConfigAppium configAppium = new ConfigAppium();
    DriverAppium driverAppium = null;

    @Test
    @Parameters({"account", "deviceName","app","versions"})
    public void initLogin(String account, String deviceName, String app, String versions) {
        configAppium.androidConfig(deviceName, app, versions);
        driverAppium = new DriverAppium(ConfigAppium.getAndroidDriver());
        GmmHome home = new GmmHome(driverAppium);
        home.initApp();
        home.login(account);
    }

    @Test
    public void examine(){
        GmmMe gmmMe = new GmmMe(driverAppium);
        gmmMe.examine();
    }

    @Test(dataProvider = "sell")
    public void sellTestFlow(String gameName, String gameType, String price, boolean ins) {
        GmmSellFlow gmmSellFlow = new GmmSellFlow(driverAppium);
        gmmSellFlow.goodsSell(gameName, gameType, price, ins);
    }

    @Test(dataProvider = "buy")
    public void buyTestFlow(String gameName, String gameType, String ins){
        GmmBuyFlow gmmBuyFlow = new GmmBuyFlow(driverAppium);
        gmmBuyFlow.goodsBuy(gameName, gameType, ins);
    }

    @AfterSuite
    public void out(){
        driverAppium.outDriver();
    }

}
