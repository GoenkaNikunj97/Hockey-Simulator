package dhl.leagueModel.interfaceModel;

import dhl.leagueModel.interfaceModel.ICoach;
import dhl.leagueModel.interfaceModel.ILeagueObjectModel;
import dhl.leagueModel.interfaceModel.IPlayer;

import java.util.ArrayList;
import java.util.List;

public interface ITraining {
    public ILeagueObjectModel findPlayersForStatIncrease(ILeagueObjectModel objLeagueObjectModel) throws Exception;
    public List<IPlayer> updatePlayerStats(List<IPlayer> arrPlayer, ICoach objCoach, String[] randomValues) throws Exception;
}
