package dhl.businessLogic.aging;

import dhl.InputOutput.importJson.Interface.IGameConfig;
import dhl.businessLogic.aging.interfaceAging.IAgingSystem;
import dhl.businessLogic.leagueModel.interfaceModel.IPlayer;
import dhl.businessLogic.leagueModel.interfaceModel.IPlayerStatistics;
import dhl.businessLogic.leagueModel.interfaceModel.ITeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgingSystem implements IAgingSystem {
    private int averageRetirementAge;
    private int maximumAge;
    private double likelihoodForGreaterThanAvg;
    private double likelihoodForLesserThanAvg;

    public AgingSystem(IGameConfig gameConfig) {
        averageRetirementAge = Integer.parseInt(gameConfig.getValueFromOurObject(gameConfig.getAging(), gameConfig.getAverageRetirementAge()));
        maximumAge = Integer.parseInt(gameConfig.getValueFromOurObject(gameConfig.getAging(), gameConfig.getMaximumAge()));
        likelihoodForGreaterThanAvg = AgingConstant.LIKELIHOODFORGREATERTHANAVGAGE.getValue();
        likelihoodForLesserThanAvg = AgingConstant.LIKELIHOODFORLESSERTHANAVGAGE.getValue();
    }

    public double getLikelihoodForGreaterThanAvg() {
        return likelihoodForGreaterThanAvg;
    }

    public void setLikelihoodForGreaterThanAvg(double likelihoodForGreaterThanAvg) {
        this.likelihoodForGreaterThanAvg = likelihoodForGreaterThanAvg;
    }

    public double getLikelihoodForLesserThanAvg() {
        return likelihoodForLesserThanAvg;
    }

    public void setLikelihoodForLesserThanAvg(double likelihoodForLesserThanAvg) {
        this.likelihoodForLesserThanAvg = likelihoodForLesserThanAvg;
    }

    public int getAverageRetirementAge() {
        return averageRetirementAge;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public void ageAllPlayers(List<IPlayer> players) {
        for (IPlayer player : players) {
            agePlayer(player.getPlayerStats());
        }
    }

    public void agePlayer(IPlayerStatistics playerStatistics) {
        playerStatistics.setAge(playerStatistics.getAge() + 1);

    }

    public Map<String, List<IPlayer>> selectPlayersToRetire(ITeam team) {
        Map<String, List<IPlayer>> playersSelectedToRetire = new HashMap<>();
        playersSelectedToRetire.put(team.getTeamName(), retirementAlgorithmBasedOnAge(team.getPlayers()));
        return playersSelectedToRetire;
    }

    public List<IPlayer> selectFreeAgentsToRetire(List<IPlayer> freeAgents) {
        return retirementAlgorithmBasedOnAge(freeAgents);
    }

    public List<IPlayer> retirementAlgorithmBasedOnAge(List<IPlayer> players) {
        int rangeOfAge = (maximumAge - averageRetirementAge) / AgingConstant.RANGEDIVISION.getValue();
        List<IPlayer> retiringPlayers = new ArrayList<>();
        for (IPlayer player : players) {
            IPlayerStatistics playerStatistics = player.getPlayerStats();
            if (playerStatistics.getAge() == maximumAge) {
                retiringPlayers.add(player);
            } else if (playerStatistics.getAge() >= averageRetirementAge) {
                if (checkLikelihoodOfRetirement(getLikelihoodForGreaterThanAvg())) {
                    retiringPlayers.add(player);
                }
            } else if (playerStatistics.getAge() < averageRetirementAge && playerStatistics.getAge() > averageRetirementAge - rangeOfAge) {
                if (checkLikelihoodOfRetirement(getLikelihoodForLesserThanAvg())) {
                    retiringPlayers.add(player);
                }
            }
        }
        return retiringPlayers;
    }

    public boolean checkLikelihoodOfRetirement(double likelihood) {
        double ramdomNumber = Math.random();
        ramdomNumber = ramdomNumber * 100;
        if (ramdomNumber <= likelihood) {
            return true;
        }
        return false;
    }

}
