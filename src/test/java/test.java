import driver.ConfigAppium;
import driver.DriverAppium;
import org.testng.annotations.Test;
import page.*;

public class test {

    @Test
    public void tests(){

        ConfigAppium configAppium = new ConfigAppium();
        String deviceName = "SNJ4C19A22005144";
        String app = "gmm-official-release-v3.7.7.693.apk";
        String versions = "10.0.0";
        configAppium.androidConfig(deviceName,app,versions);

        DriverAppium driverAppium = new DriverAppium(ConfigAppium.getAndroidDriver());

        GmmHome home = new GmmHome(driverAppium);

        home.initApp();
        home.login("17501675705");
        home.sellSelect("冒险岛","装备");

        GmmShelf gmmShelf = new GmmShelf(driverAppium);
        gmmShelf.goodsShelfData("装备","55",true);

        Function function = new Function(driverAppium);
        function.issue();

        GmmMe gmmMe = new GmmMe(driverAppium);
        gmmMe.meIssueSell("装备");

        GmmGoods gmmGoods = new GmmGoods(driverAppium);
        gmmGoods.goodsDetails("编辑");

        function.sms();
        gmmShelf.goodsEdit();
        function.issue();
        gmmMe.meIssueSell("装备");
        gmmGoods.goodsDetails("下架");
        gmmGoods.goodsDetails("重新上架");
        function.sms();
        gmmShelf.goodsAnew();
        function.issue();
        gmmMe.meIssueSell("装备");
        gmmGoods.goodsDetails("下架");
    }


}
