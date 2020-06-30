package aubo;


public class Aubo {
	private AuboOperation operation;
	private SensorSubModel sensor;
	public Aubo() {
		
		operation = new AuboOperation();
		sensor = new SensorSubModel();
	}
	
	
	public SensorSubModel getSensor() {
		return sensor;
	}
	public AuboOperation getOperation() {
		return operation;
	}
}
