package org.eclipse.basyx.submodel.metamodel.connected.submodelelement;

import java.util.Map;
import java.util.Set;

import org.eclipse.basyx.submodel.metamodel.api.qualifier.qualifiable.IConstraint;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElement;
import org.eclipse.basyx.submodel.metamodel.connected.ConnectedElement;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.HasDataSpecificationFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.HasSemanticsFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.ReferableFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.haskind.HasKindFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.qualifiable.QualifiableFacade;
import org.eclipse.basyx.submodel.metamodel.map.modeltype.ModelType;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.vab.modelprovider.VABElementProxy;

/**
 * "Connected" implementation of SubmodelElement
 * @author rajashek
 *
 */
public abstract class ConnectedSubmodelElement extends ConnectedElement implements ISubmodelElement {
	public ConnectedSubmodelElement(VABElementProxy proxy) {
		super(proxy);		
	}

	@Override
	public String getIdShort() {
		return new ReferableFacade(getElem()).getIdShort();
	}

	@Override
	public String getCategory() {
		return new ReferableFacade(getElem()).getCategory();
	}

	@Override
	public LangStrings getDescription() {
		return new ReferableFacade(getElem()).getDescription();
	}

	@Override
	public IReference getParent() {
		return new ReferableFacade(getElem()).getParent();
	}

	@Override
	public Set<IConstraint> getQualifier() {
		return new QualifiableFacade(getElem()).getQualifier();
	}

	@Override
	public IReference getSemanticId() {
		return new HasSemanticsFacade(getElem()).getSemanticId();
	}

	@Override
	public String getKind() {
		return new HasKindFacade(getElem()).getKind();
	}

	@Override
	public Set<IReference> getDataSpecificationReferences() {
		return new HasDataSpecificationFacade(getElem()).getDataSpecificationReferences();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public String getModelType() {
		return (String) ((Map<String, Object>) getElem().get(ModelType.MODELTYPE)).get(ModelType.NAME);
	}
}
