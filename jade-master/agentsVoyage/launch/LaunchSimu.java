package launch;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.core.Runtime;

/**
 * launch the simulation of travelers and travel agencies
 * 
 * @author emmanueladam
 */
public class LaunchSimu {

	public static final Logger logger = Logger.getLogger("simu");

	/**
	 * @param args
	 */
	public static void main(String... args) {

		logger.setLevel(Level.ALL);
		Handler fh;
		try {
			fh = new FileHandler("./simuAgences.xml", false);
			logger.addHandler(fh);
		} 
		catch (SecurityException | IOException e) { e.printStackTrace(); }

		// ******************JADE******************
		
		// allow to send arguments to the JADE launcher
		var pp = new ExtendedProperties();
		// add the gui
		pp.setProperty(Profile.GUI, "true");
		// add the Topic Management Service
		pp.setProperty(Profile.SERVICES, "jade.core.messaging.TopicManagementService;jade.core.event.NotificationService");		 
		
		var lesAgents = new StringBuilder();
		lesAgents.append("client1:agents.TravellerAgent;");
		lesAgents.append("client2:agents.TravellerAgent;");
		//lesAgents.append("client3:agents.TravellerAgent;");
		//lesAgents.append("client4:agents.TravellerAgent;");
		lesAgents.append("agentCar:agents.AgenceAgent(jade-master/agentsVoyage/car.csv);");
		lesAgents.append("agentBus:agents.AgenceAgent(jade-master/agentsVoyage/bus.csv);");
		lesAgents.append("agentTrain:agents.AgenceAgent(jade-master/agentsVoyage/train.csv);");
		lesAgents.append("alert1:agents.AlertAgent;");
		lesAgents.append("enchere1:agents.EnchereAgent");
		pp.setProperty(Profile.AGENTS, lesAgents.toString());
		// create a default Profile
		var pMain = new ProfileImpl(pp);

		// launch the main jade container
		Runtime.instance().createMainContainer(pMain);

	}

}
