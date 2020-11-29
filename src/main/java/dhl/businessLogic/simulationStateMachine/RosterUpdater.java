package dhl.businessLogic.simulationStateMachine;

import dhl.businessLogic.simulationStateMachine.interfaces.ITeamRosterUpdater;
import dhl.inputOutput.ui.interfaces.IUserInputOutput;

public class RosterUpdater extends RosterUpdaterAbstractFactory {

    public ITeamRosterUpdater createAiTeamRosterUpdater() {
        return new AiTeamRosterUpdater();
    }

    public ITeamRosterUpdater createUpdateUserTeamRoster(IUserInputOutput ioObject) {
        return new UpdateUserTeamRoster(ioObject);
    }
}