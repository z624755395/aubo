package aubo;
import org.eclipse.basyx.models.controlcomponent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuboControlComponent extends SimpleControlComponent implements ControlComponentChangeListener {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AuboControlComponent.class);

    public static final String OPMODE_BASIC = "BSTATE";
    public static final String OPMODE_MOVE1 = "MOVE1";
    public static final String OPMODE_MOVE2 = "MOVE2";
    public static final String OPMODE_GETPOSITION = "GETPOSITION";
    private Aubo aubo;
    
	public AuboControlComponent(Aubo aubo) {
		this.aubo = aubo;
		addControlComponentChangeListener(this);
	}	
	
	@Override
	public void onChangedExecutionState(ExecutionState newExecutionState) {
		logger.info("AuboControlComponent: new execution state: " + newExecutionState);
		if (newExecutionState == ExecutionState.EXECUTE) {
			if (this.getOperationMode().equals(OPMODE_GETPOSITION)) {
				System.out.println("get position");
				try {
					String xml = aubo.getOperation().getCurrentPosition();
					TCPClient tcp = new TCPClient();
					tcp.send(xml, aubo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				setExecutionState(ExecutionState.COMPLETE.getValue());
			}
			if (this.getOperationMode().equals(OPMODE_MOVE1)) {
				System.out.println("Operation1");
				try {
					String xml = aubo.getOperation().operation1();
					TCPClient tcp = new TCPClient();
					tcp.send(xml, aubo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				setExecutionState(ExecutionState.COMPLETE.getValue());
			} else if (this.getOperationMode().equals(OPMODE_MOVE2)) {
				System.out.println("Operation2");
				try {
					String xml = aubo.getOperation().operation2();
					TCPClient tcp = new TCPClient();
					tcp.send(xml, aubo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				setExecutionState(ExecutionState.COMPLETE.getValue()); 
			}
			else {
				setExecutionState(ExecutionState.COMPLETE.getValue());
			}
		}
		
	}
	
	@Override
	public void onChangedExecutionMode(ExecutionMode newExecutionMode) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onVariableChange(String varName, Object newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNewOccupier(String occupierId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNewOccupationState(OccupationState state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChangedOperationMode(String newOperationMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChangedWorkState(String newWorkState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChangedErrorState(String newWorkState) {
		// TODO Auto-generated method stub
		
	}

}
