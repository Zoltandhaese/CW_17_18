package autopilot;

import API.Autopilot;
import API.AutopilotConfig;
import API.AutopilotInputs;
import API.AutopilotOutputs;

public class Main implements Autopilot {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AutopilotOutputs simulationStarted(AutopilotConfig config, AutopilotInputs inputs) {
		
		
		AutopilotOutputs output = null;
		
		return output;
	}

	@Override
	public AutopilotOutputs timePassed(AutopilotInputs inputs) {

		AutopilotOutputs output = null;
		return output;
	}

	@Override
	public void simulationEnded() {
		// TODO Auto-generated method stub
		
	}
	
	

}
