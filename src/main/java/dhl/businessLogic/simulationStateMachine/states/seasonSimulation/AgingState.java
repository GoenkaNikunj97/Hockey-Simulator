package dhl.businessLogic.simulationStateMachine.states.seasonSimulation;


import dhl.businessLogic.aging.agingFactory.AgingAbstractFactory;
import dhl.businessLogic.aging.interfaceAging.IAging;
import dhl.businessLogic.aging.interfaceAging.ILeagueSchedule;
import dhl.businessLogic.aging.interfaceAging.IRetirement;
import dhl.businessLogic.leagueModel.interfaceModel.ILeagueObjectModel;
import dhl.businessLogic.simulationStateMachine.SimulationContext;
import dhl.businessLogic.simulationStateMachine.states.seasonScheduler.interfaces.IScheduler;
import dhl.businessLogic.simulationStateMachine.states.seasonSimulation.interfaces.ISimulationSeasonState;
import dhl.inputOutput.importJson.SerializeDeserialize.SerializeDeserializeAbstractFactory;
import dhl.inputOutput.importJson.serializeDeserialize.interfaces.ISerializeLeagueObjectModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class AgingState implements ISimulationSeasonState {
    public static Logger log = LogManager.getLogger(AgingState.class);
    static AgingAbstractFactory agingFactory;
    static SerializeDeserializeAbstractFactory serializeDeserializeAbstractFactory;
    SimulationContext simulationContext;

    public AgingState(SimulationContext simulationContext) {
        this.simulationContext = simulationContext;
    }

    static void agingCalculation(SimulationContext simulationContext) {
        agingFactory = AgingAbstractFactory.instance();
        serializeDeserializeAbstractFactory = SerializeDeserializeAbstractFactory.instance();
        IAging aging = agingFactory.createAging(simulationContext.getGameConfig());
//        IAging aging = new Aging(simulationContext.getGameConfig());
        ILeagueObjectModel leagueObjectModel = simulationContext.getInMemoryLeague();
        ISerializeLeagueObjectModel serializeModel = serializeDeserializeAbstractFactory.createSerializeLeagueObjectModel(leagueObjectModel.getLeagueName());
//        ISerializeLeagueObjectModel serializeModel = new SerializeLeagueObjectModel(leagueObjectModel.getLeagueName());
        IRetirement retirement = agingFactory.createRetirement(serializeModel, simulationContext.getInMemoryLeague());
//        IRetirement retirement = new Retirement(serializeModel, simulationContext.getInMemoryLeague());
        ILeagueSchedule leagueSchedule = agingFactory.createLeagueSchedule(simulationContext.getNumberOfDays(), simulationContext.getInMemoryLeague());
//        ILeagueSchedule leagueSchedule = new LeagueSchedule(aging, retirement, simulationContext.getInjurySystem(), simulationContext.getInMemoryLeague(), simulationContext.getNumberOfDays());
        try {
            leagueSchedule.initiateAging();
        } catch (Exception e) {
            log.error("Error occured while aging" + e.getMessage());
            e.printStackTrace();
        }
    }

    public SimulationContext getSimulationContext() {
        return simulationContext;
    }

    public void setSimulationContext(SimulationContext simulationContext) {
        this.simulationContext = simulationContext;
    }

    @Override
    public void seasonStateEntryProcess() {

    }

    @Override
    public void seasonStateProcess() {
        agingCalculation(simulationContext);
    }

    @Override
    public void seasonStateExitProcess() {
//        IScheduler scheduler = simulationContext.getRegularScheduler();
        IScheduler scheduler = simulationContext.getPlayOffScheduleRound1();
//        LocalDate startOfSimulation = simulationContext.getStartOfSimulation();
//        LocalDate currentDate = startOfSimulation.plusDays(simulationContext.getNumberOfDays());
        LocalDate currentDate = LocalDate.now();
        if (scheduler.stanleyCupWinner(currentDate)) {
            simulationContext.setCurrentSimulation(simulationContext.getAdvanceToNextSeason());
        } else {
            simulationContext.setCurrentSimulation(simulationContext.getPersistsSeason());
        }
    }
}
