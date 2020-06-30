package aubo;

import java.util.function.Function;

import javax.servlet.http.HttpServlet;

import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.descriptor.AASDescriptor;
import org.eclipse.basyx.aas.metamodel.map.descriptor.ModelUrn;
import org.eclipse.basyx.aas.metamodel.map.descriptor.SubmodelDescriptor;
import org.eclipse.basyx.aas.registration.api.IAASRegistryService;
import org.eclipse.basyx.aas.registration.memory.InMemoryRegistry;
import org.eclipse.basyx.aas.registration.restapi.DirectoryModelProvider;
import org.eclipse.basyx.aas.restapi.AASModelProvider;
import org.eclipse.basyx.aas.restapi.VABMultiSubmodelProvider;
import org.eclipse.basyx.models.controlcomponent.ControlComponent;
import org.eclipse.basyx.models.controlcomponent.ExecutionState;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.Operation;
import org.eclipse.basyx.submodel.restapi.SubModelProvider;
import org.eclipse.basyx.vab.coder.json.connector.JSONConnector;
import org.eclipse.basyx.vab.modelprovider.VABElementProxy;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;
import org.eclipse.basyx.vab.modelprovider.map.VABMapProvider;
import org.eclipse.basyx.vab.protocol.basyx.connector.BaSyxConnector;
import org.eclipse.basyx.vab.protocol.basyx.server.BaSyxTCPServer;
import org.eclipse.basyx.vab.protocol.http.server.AASHTTPServer;
import org.eclipse.basyx.vab.protocol.http.server.BaSyxContext;
import org.eclipse.basyx.vab.protocol.http.server.VABHTTPInterface;


public class AASstart {

	public static void main(String[] args) throws Exception {
		Aubo myAubo = new Aubo();
		startMyControlComponent(myAubo);
		startMyAssetAdministrationShell(myAubo);

	}
	
	public static void startMyControlComponent(Aubo aubo) {
		// Given is a local control component that can directly control the virtual oven device
		ControlComponent cc = new AuboControlComponent(aubo);
 
		// Like the VAB model created before, the structure of the control component is a Map
		// Map ccModel = (Map) cc;
 
		// Create a server for the Control Component and provide it in the VAB (at port 4002)
		VABMapProvider ccProvider = new VABMapProvider(cc);
		// This time, a BaSyx-specific TCP interface is used.
		// Likewise, it is also possible to wrap the control component using a http servlet as before
		BaSyxTCPServer<IModelProvider> server = new BaSyxTCPServer<>(ccProvider, 4002);
		server.start();
	}
	
	public static void startMyAssetAdministrationShell(Aubo myAubo) {
		/**
		 * Sensor Submodel
		 */
		SensorSubModel sensorSubModel = myAubo.getSensor();
 
		/**
		 * Control Submodel
		 */
		SubModel ControlSubModel = new SubModel();
		ControlSubModel.setIdShort("Control");
		// Create an operation that uses the control component 
		Function<Object[], Object> operation1Invokable = (params) -> {
			// Connect to the control component
			VABElementProxy proxy = new VABElementProxy("", new JSONConnector(new BaSyxConnector("localhost", 4002)));
			// Select the operation from the control component
			proxy.setModelPropertyValue("status/opMode", AuboControlComponent.OPMODE_MOVE1);
			// Start the control component operation asynchronous
			proxy.invokeOperation("/operations/service/start");
			PositionValue currentValue = new PositionValue();
			currentValue.joint1 = 173;
			currentValue.joint2 = -2;
			currentValue.joint3 = -70;
			currentValue.joint4 = 23;
			currentValue.joint5 = -89;
			currentValue.joint6 = -149;
			sensorSubModel.updatePosition(currentValue);
			
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
		ControlSubModel.addSubModelElement(operation);
		
		Function<Object[], Object> operation2Invokable = (params) -> {
			// Connect to the control component
			VABElementProxy proxy = new VABElementProxy("", new JSONConnector(new BaSyxConnector("localhost", 4002)));			
			// Select the operation from the control component
			proxy.setModelPropertyValue("status/opMode", AuboControlComponent.OPMODE_MOVE2);		
			// Start the control component operation asynchronous
			proxy.invokeOperation("/operations/service/start");
			// Update the sensor value 
			PositionValue currentValue = new PositionValue();
			currentValue.joint1 = 173;
			currentValue.joint2 = -2;
			currentValue.joint3 = -70;
			currentValue.joint4 = 23;
			currentValue.joint5 = -89;
			currentValue.joint6 = -149;
			sensorSubModel.updatePosition(currentValue);			
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
		Operation operation2 = new Operation();
		LangStrings ls2 = new LangStrings("en","send a xml file to the tcp server");
		operation2.setDescription(ls2);
		operation2.setIdShort("Move2");
		operation2.setInvocable(operation2Invokable);
		ControlSubModel.addSubModelElement(operation2);
		
		Function<Object[], Object> operation3Invokable = (params) -> {
			// Connect to the control component
			VABElementProxy proxy = new VABElementProxy("", new JSONConnector(new BaSyxConnector("localhost", 4002)));			
			// Select the operation from the control component
			proxy.setModelPropertyValue("status/opMode", AuboControlComponent.OPMODE_GETPOSITION);		
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
		Operation operation3 = new Operation();
		LangStrings ls3 = new LangStrings("en","get current position");
		operation3.setDescription(ls3);
		operation3.setIdShort("getPosition");
		operation3.setInvocable(operation3Invokable);
		ControlSubModel.addSubModelElement(operation3);
		
		/**
		 * Minimal AAS Information
		 */	
		AssetAdministrationShell aas = new AssetAdministrationShell();
		// Add a unique Identification and a name (== short ID)
		ModelUrn aasURN = new ModelUrn("de.IMI", "devices.robot", "AAS", "1.0", "1", "aubo01", "001");
		aas.setIdentification(aasURN);
		aas.setIdShort("aubo01 VWS");
		// Note: The submodels are not directly integrated into the AAS model. This makes it possible to distribute submodels to different nodes
		// The header contains references to the previously created submodels.
		// Here, the submodel endpoints are not yet known. They can be specified as soon as the real endpoints are known
 
		/**
		 * Again: Wrap the model in an IModelProvider (now specific to the AAS and submodel)
		 */
		// AASModelProvider and SubModelProvider implement the IModelProvider interface
		AASModelProvider aasProvider = new AASModelProvider(aas);
		SubModelProvider sensorSMProvider = new SubModelProvider(sensorSubModel);
		SubModelProvider controlSMProvider = new SubModelProvider(ControlSubModel);
 
		// Add the independent providers to the MultiSubmodelProvider that can be deployed on a single node
		VABMultiSubmodelProvider fullProvider = new VABMultiSubmodelProvider();
		fullProvider.setAssetAdministrationShell(aasProvider);
		fullProvider.addSubmodel("Sensor", sensorSMProvider);
		fullProvider.addSubmodel("Control", controlSMProvider);
 
		// Although the providers for aas/submodels implement the AAS API, they are still IModelProviders!
		// IModelProvider aasIModelProvider = fullProvider;
 
		/**
		 * Deployment
		 */
		// The IModelProvider is given to a HTTP servlet that gives access to the model
		// => The model will be published using an HTTP-REST interface
		HttpServlet aasServlet = new VABHTTPInterface<IModelProvider>(fullProvider);
 
		// For this HandsOn, create an InMemoryRegistry for registering the AAS
		IAASRegistryService registry = new InMemoryRegistry();
		IModelProvider registryProvider = new DirectoryModelProvider(registry);
		HttpServlet registryServlet = new VABHTTPInterface<IModelProvider>(registryProvider);
 
		// now add the references of the submodels to the AAS header
		aas.addSubModel(new SubmodelDescriptor(sensorSubModel,
				"http://localhost:4000/robot/aubo/aas/submodels/Sensor/submodel"));
		aas.addSubModel(new SubmodelDescriptor(ControlSubModel, "http://localhost:4000/robot/aubo/aas/submodels/Control/submodel"));
 
		// Register the VAB model at the directory (locally in this case)
		AASDescriptor aasDescriptor = new AASDescriptor(aas, "http://localhost:4000/robot/aubo/aas");
		registry.register(aasDescriptor);
 
		// Deploy the AAS on a HTTP server
		BaSyxContext context = new BaSyxContext("/robot", "", "localhost", 4000);
		context.addServletMapping("/aubo/*", aasServlet);
		context.addServletMapping("/registry/*", registryServlet);
		AASHTTPServer httpServer = new AASHTTPServer(context);
		httpServer.start();
 
		// Now in the browser, look at the various endpoints to see what is returned:
		// - AAS: http://localhost:4000/robot/aubo/aas/
		// - Sensor Submodel: http://localhost:4000/robot/aubo/aas/submodels/Sensor/
		// - Control Submodel: http://localhost:4000/robot/aubo/aas/submodels/Control/
 
		// Similar, the registry also has a HTTP-REST interface. So, it is possible to directly query it:
		// - Show all AAS: http://localhost:4000/robot/registry/api/v1/registry/
		// - Show my AAS: http://localhost:4000/robot/registry/api/v1/registry/urn:de.IMI:devices.robot:AAS:1.0:1:aubo01%23001
		// Note: the "#" character in the URN s encoded as "%23"
 
		// The server can also be shut down:
		/* 
		try {
			// Wait for 5s and then shutdown the server
			Thread.sleep(5000);
			httpServer.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
	}
}
