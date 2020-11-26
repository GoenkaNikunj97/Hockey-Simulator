package dhl.businessLogic.trade;

import dhl.businessLogic.simulationStateMachine.RosterUpdaterAbstractFactory;
import dhl.businessLogic.simulationStateMachine.UpdateUserTeamRoster;
import dhl.businessLogic.trade.factory.TradeAbstractFactory;
import dhl.businessLogic.trade.factory.TradeConcreteFactory;
import dhl.businessLogic.trade.interfaces.IScout;
import dhl.businessLogic.trade.interfaces.ITradeType;
import dhl.inputOutput.ui.IUserInputOutput;
import dhl.businessLogic.leagueModel.interfaceModel.*;
import dhl.businessLogic.simulationStateMachine.interfaces.ITeamRosterUpdater;
import dhl.businessLogic.trade.interfaces.ITradeOffer;
import dhl.businessLogic.trade.interfaces.ITradingEngine;
import dhl.inputOutput.ui.UserInputOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TradingEngine extends ITradingEngine {

    private ITradeOffer currentTrade;
    private IGameConfig gameConfig;
    private ILeagueObjectModel leagueObjectModel;
    IUserInputOutput ioObject;
    ITeam userTeam;
    ITeamRosterUpdater updateUserTeamRoster;
    TradeAbstractFactory factory;

    private static final Logger logger = LogManager.getLogger(TradingEngine.class);

    public TradingEngine(IGameConfig gameConfig, ILeagueObjectModel leagueObjectModel, ITeam userTeam) {
        factory = new TradeConcreteFactory();

        // TODO: 20-11-2020 remove these new when team make factory
        this.ioObject = new UserInputOutput();

        this.updateUserTeamRoster = RosterUpdaterAbstractFactory.instance().createUpdateUserTeamRoster(ioObject);
        this.gameConfig = gameConfig;
        this.leagueObjectModel = leagueObjectModel;
        this.userTeam = userTeam;
    }

    public void startEngine() {
        try {
            long configLossPoint = Long.parseLong(gameConfig.getValueFromOurObject(gameConfig.getTrading(), gameConfig.getLossPoint()));
            double configRandomTradeChance = Double.parseDouble(gameConfig.getValueFromOurObject(gameConfig.getTrading(), gameConfig.getRandomTradeOfferChance()));
            for (IConference conference : leagueObjectModel.getConferences()) {
                for (IDivision division : conference.getDivisions()) {
                    for (ITeam team : division.getTeams()) {
                        if (findLossPointOfTheTeam(team) > configLossPoint) {
                            double randomNumber = Math.random();
                            if (randomNumber > configRandomTradeChance) {
                                performTrade(team);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            ioObject.printMessage(e.getMessage());
        }
    }

    public void performTrade(ITeam tradingTeam){
        IScout teamScout = factory.createScout(tradingTeam, leagueObjectModel, gameConfig);
        int congifMaxPlayerPerTrade = Integer.parseInt(gameConfig.getValueFromOurObject(gameConfig.getTrading(), gameConfig.getMaxPlayersPerTrade()));
        currentTrade = teamScout.findTrade(congifMaxPlayerPerTrade);
        if (currentTrade == null){
            logger.warn("Trade not possible for Team:"+ tradingTeam.getTeamName());
        }
        else{
            tradingTeam.setLossPoint(0);
            sendTradeToRecevingTeam(currentTrade, userTeam);
        }
    }

    public void sendTradeToRecevingTeam(ITradeOffer currentTrade, ITeam userTeam) {
        logger.warn("Sending trade offer to team:"+ currentTrade.getReceivingTeam().getTeamName());
        ITradeType tradeType;
        if(currentTrade.getReceivingTeam() == userTeam){
            tradeType = factory.createAiUserTrade(currentTrade, ioObject, updateUserTeamRoster);
        }else{
            tradeType = factory.createAiAiTrade(currentTrade, gameConfig);
        }
        if (tradeType.isTradeAccepted()) {
            ioObject.printMessage("Trade done between: " + currentTrade.getOfferingTeam().getTeamName() + " And " + currentTrade.getReceivingTeam().getTeamName());
            currentTrade.implementTrade();
            tradeType.validateTeamRosterAfterTrade(currentTrade.getReceivingTeam(), leagueObjectModel);
        }
    }

    public int findLossPointOfTheTeam(ITeam team) {
        int teamLossPoint = team.getLossPoint();
        return teamLossPoint;
    }

    public void setIoObject(IUserInputOutput ioObject) {
        this.ioObject = ioObject;
    }

    public ITradeOffer getCurrentTrade() {
        return currentTrade;
    }

}
