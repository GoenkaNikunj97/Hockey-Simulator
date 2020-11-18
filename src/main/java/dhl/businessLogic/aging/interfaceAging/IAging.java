package dhl.businessLogic.aging.interfaceAging;

import dhl.businessLogic.leagueModel.interfaceModel.IPlayer;
import dhl.businessLogic.leagueModel.interfaceModel.ITeam;

import java.util.List;
import java.util.Map;

public interface IAging {
    void ageAllPlayers(List<IPlayer> players);

    Map<String, List<IPlayer>> selectPlayersToRetire(ITeam team);

    List<IPlayer> selectFreeAgentsToRetire(List<IPlayer> freeAgents);

}