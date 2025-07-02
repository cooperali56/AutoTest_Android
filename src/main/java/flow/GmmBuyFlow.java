package flow;

import driver.DriverAppium;
import page.GmmDetailsBuy;
import page.GmmHome;
import page.GmmMe;

/**
 * 购买流程
 */
public class GmmBuyFlow {

    private DriverAppium driverBuyFlow;

    public GmmBuyFlow(DriverAppium driverBuyFlow) {
        this.driverBuyFlow = driverBuyFlow;
    }

    /**
     * 购买步骤
     * @param gameName
     * @param gameType
     * @param insurance
     */
    public void goodsBuy(String gameName, String gameType, String insurance){
        GmmHome gmmHome = new GmmHome(driverBuyFlow);
        gmmHome.buySelect(gameName,gameType,insurance);

        GmmDetailsBuy gmmDetailsBuy = new GmmDetailsBuy(driverBuyFlow);
        gmmDetailsBuy.goodsDetailsBuy("立即购买",insurance);

        GmmMe gmmMe = new GmmMe(driverBuyFlow);
        gmmMe.meBuy(gameType);

    }

}
