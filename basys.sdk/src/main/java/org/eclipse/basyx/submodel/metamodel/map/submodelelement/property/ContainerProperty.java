package org.eclipse.basyx.submodel.metamodel.map.submodelelement.property;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.basyx.submodel.metamodel.api.submodelelement.IDataElement;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElement;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.operation.IOperation;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.property.IContainerProperty;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.property.PropertyType;
import org.eclipse.basyx.submodel.metamodel.facade.VABElementContainerFacade;
import org.eclipse.basyx.submodel.metamodel.map.IVABElementContainer;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;

public class ContainerProperty extends AbstractProperty implements IContainerProperty, IVABElementContainer {
	private static final long serialVersionUID = -8066834863070042378L;

	private VABElementContainerFacade containerFacade;

	
	public ContainerProperty() {
		containerFacade = new VABElementContainerFacade(this);

		put(SubModel.SUBMODELELEMENT, new HashMap<>());

		// Helper for operations and properties
		put(SubModel.PROPERTIES, new HashMap<>());
		put(SubModel.OPERATIONS, new HashMap<>());
	}
	
	/**
	 * Creates a ContainerProperty object from a map
	 * 
	 * @param obj a ContainerProperty object as raw map
	 * @return a ContainerProperty object, that behaves like a facade for the given map
	 */
	public static ContainerProperty createAsFacade(Map<String, Object> obj) {
		ContainerProperty facade = new ContainerProperty();
		facade.putAll(obj);
		return facade;
	}

	@Override
	public PropertyType getPropertyType() {
		return PropertyType.Container;
	}

	@Override
	public void addSubModelElement(ISubmodelElement element) {
		containerFacade.addSubModelElement(element);
	}

	@Override
	public Map<String, IDataElement> getDataElements() {
		return containerFacade.getDataElements();
	}

	@Override
	public Map<String, IOperation> getOperations() {
		return containerFacade.getOperations();
	}
	
	@Override
	public Map<String, ISubmodelElement> getSubmodelElements() {
		return containerFacade.getSubmodelElements();
	}
}
