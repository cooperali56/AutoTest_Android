package flow;

import driver.DriverAppium;
import page.*;

/**
 * android出售流程
 */
public class GmmSellFlow {

    private DriverAppium driverSellFlow;

    public GmmSellFlow(DriverAppium driverSellFlow) {
        this.driverSellFlow = driverSellFlow;
    }

    public void goodsSell(String gameName,String gameType,String price,boolean ins){
        GmmHome home = new GmmHome(driverSellFlow);
        home.sellSelect(gameName,gameType);

        GmmShelf gmmShelf = new GmmShelf(driverSellFlow);
        gmmShelf.goodsShelfData(gameType,price,ins);

        Function function = new Function(driverSellFlow);
        function.issue();

        GmmMe gmmMe = new GmmMe(driverSellFlow);
        gmmMe.meIssueSell(gameType);

        GmmGoods gmmGoods = new GmmGoods(driverSellFlow);
        gmmGoods.goodsDetails("编辑");

        function.sms();
        gmmShelf.goodsEdit();
        function.issue();
        gmmMe.meIssueSell(gameType);
        gmmGoods.goodsDetails("下架");
        gmmGoods.goodsDetails("重新上架");
        function.sms();
        gmmShelf.goodsAnew();
        function.issue();
        gmmMe.meIssueSell(gameType);
        gmmGoods.goodsDetails("下架");
    }

}
