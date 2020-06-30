package aubo;

import java.util.function.Function;

import org.eclipse.basyx.models.controlcomponent.ExecutionState;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.Operation;
import org.eclipse.basyx.vab.coder.json.connector.JSONConnector;
import org.eclipse.basyx.vab.modelprovider.VABElementProxy;
import org.eclipse.basyx.vab.protocol.basyx.connector.BaSyxConnector;

public class ControlSubModel extends SubModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlSubModel() {
		setIdShort("Control");
		// Create an operation that uses the control component 
		Function<Object[], Object> operation1Invokable = (params) -> {
			// Connect to the control component
			VABElementProxy proxy = new VABElementProxy("", new JSONConnector(new BaSyxConnector("localhost", 4002)));
			// Select the operation from the control component
			proxy.setModelPropertyValue("status/opMode", AuboControlComponent.OPMODE_MOVE1);
			// Start the control component operation asynchronous
			proxy.invokeOperation("/operations/service/start");
			// Wait until the operation is completed
			while (!proxy.getModelPropertyValue("status/exState").equals(ExecutionState.COMPLETE.getValue())) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
			proxy.invokeOperation("operations/service/reset");
			// Then return -> synchronous
			return null;
		};
		// Create the Operation
		Operation operation = new Operation();
		LangStrings ls = new LangStrings("en","send xml data to set the robot in motion");
		operation.setDescription(ls);
		operation.setIdShort("Operation1");
		operation.setInvocable(operation1Invokable);
		addSubModelElement(operation);
	}
}
