package org.eclipse.basyx.models.controlcomponent;


/**
 * Receive change events from control components
 * 
 * @author kuhn
 *
 */
public interface ControlComponentChangeListener {

	
	
	/**
	 * Indicate change of a variable
	 */
	public void onVariableChange(String varName, Object newValue);
	
	
	/**
	 * Indicate new occupier
	 */
	public void onNewOccupier(String occupierId);

	
	/**
	 * Indicate new occupation state
	 */
	public void onNewOccupationState(OccupationState state);

	
	/**
	 * Indicate a change of last occupier. This is probably not relevant for many sub classes, therefore this class
	 * provides a default implementation. 
	 */
	default void onLastOccupier(String lastOccupierId) { /* Do nothing */ }

		
	/**
	 * Indicate an execution mode change
	 */
	public void onChangedExecutionMode(ExecutionMode newExecutionMode);

	
	/**
	 * Indicate an execution state change
	 */
	public void onChangedExecutionState(ExecutionState newExecutionState);

	
	/**
	 * Indicate an operation mode change
	 */
	public void onChangedOperationMode(String newOperationMode);

	
	/**
	 * Indicate an work state change
	 */
	public void onChangedWorkState(String newWorkState);

	
	/**
	 * Indicate an error state change
	 */
	public void onChangedErrorState(String newWorkState);

	
	/**
	 * Indicate an previous error state change. This is probably not relevant for many sub classes, therefore this class
	 * provides a default implementation. 
	 */
	default void onChangedPrevError(String newWorkState) { /* Do nothing */ }
}

