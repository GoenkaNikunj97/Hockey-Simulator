package dhl.leagueModel;

import dhl.leagueModel.interfaceModel.IConference;
import dhl.leagueModel.interfaceModel.IDivision;
import dhl.leagueModel.interfaceModel.IPlayer;
import dhl.leagueModel.interfaceModel.ITeam;

public class InitializeObjectFactory extends InitializeObjectsAbstractFactory{
    @Override
    public ITeam createTeam() {
        return new Team();
    }

    @Override
    public IPlayer createPlayer() {
        return new Player();
    }

    @Override
    public IDivision createDivision() {
        return new Division();
    }

    @Override
    public IConference createConference() {
        return new Conference();
    }

}
