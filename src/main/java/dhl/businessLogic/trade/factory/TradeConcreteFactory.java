package dhl.businessLogic.trade.factory;

import dhl.businessLogic.leagueModel.interfaceModel.IPlayer;
import dhl.businessLogic.leagueModel.interfaceModel.ITeam;
import dhl.businessLogic.simulationStateMachine.interfaces.IUpdateUserTeamRoster;
import dhl.businessLogic.trade.AiAiTrade;
import dhl.businessLogic.trade.AiUserTrade;
import dhl.businessLogic.trade.ExchangingPlayerTradeOffer;
import dhl.businessLogic.trade.interfaces.ITradeOffer;
import dhl.businessLogic.trade.interfaces.ITradeType;
import dhl.inputOutput.importJson.interfaces.IGameConfig;
import dhl.inputOutput.ui.IUserInputOutput;

import java.util.ArrayList;

public class TradeConcreteFactory implements TradeAbstractFactory{

    public ITradeType createAiAiTrade(ITradeOffer tradeOffer, IGameConfig gameConfig) {
        return new AiAiTrade(tradeOffer, gameConfig);
    }

    public ITradeType createAiUserTrade(ITradeOffer tradeOffer, IUserInputOutput ioObject, IUpdateUserTeamRoster updateUserTeamRoster) {
        return new AiUserTrade(tradeOffer, ioObject, updateUserTeamRoster);
    }

    public ITradeOffer createExchangingPlayerTradeOffer(ITeam offeringTeam, ITeam receivingTeam, ArrayList<IPlayer> playersOffered, ArrayList<IPlayer> playersWantedInExchange) {
        return new ExchangingPlayerTradeOffer(offeringTeam, receivingTeam, playersOffered, playersWantedInExchange);
    }
}