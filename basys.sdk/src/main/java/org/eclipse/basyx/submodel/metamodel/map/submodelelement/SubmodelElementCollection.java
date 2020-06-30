package org.eclipse.basyx.submodel.metamodel.map.submodelelement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElement;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.HasDataSpecificationFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.ReferableFacade;
import org.eclipse.basyx.submodel.metamodel.facade.submodelelement.SubmodelElementFacadeFactory;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.modeltype.ModelType;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.Referable;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.property.Property;

/**
 * SubmodelElementCollection as defined by DAAS document <br/>
 * A submodel element collection is a set or list of submodel elements
 * 
 * @author schnicke
 *
 */
public class SubmodelElementCollection extends SubmodelElement implements ISubmodelElementCollection {
	private static final long serialVersionUID = 1L;

	public static final String ORDERED = "ordered";
	public static final String ALLOWDUPLICATES = "allowDuplicates";
	public static final String MODELTYPE = "SubmodelElementCollection";


	/**
	 * Constructor
	 */
	public SubmodelElementCollection() {
		// Add model type
		putAll(new ModelType(MODELTYPE));

		// Put attributes
		put(Property.VALUE, new ArrayList<>());
		put(ORDERED, true);
		put(ALLOWDUPLICATES, true);
	}

	/**
	 * 
	 * @param value
	 *            Submodel element contained in the collection
	 * @param ordered
	 *            If ordered=false then the elements in the property collection are
	 *            not ordered. If ordered=true then the elements in the collection
	 *            are ordered.
	 * @param allowDuplicates
	 *            If allowDuplicates=true then it is allowed that the collection
	 *            contains the same element several times
	 */
	public SubmodelElementCollection(Collection<ISubmodelElement> value, boolean ordered, boolean allowDuplicates) {
		// Add model type
		putAll(new ModelType(MODELTYPE));
		
		// Put attributes
		put(Property.VALUE, value);
		put(ORDERED, ordered);
		put(ALLOWDUPLICATES, allowDuplicates);
	}
	
	/**
	 * Creates a SubmodelElementCollection object from a map
	 * 
	 * @param obj a SubmodelElementCollection object as raw map
	 * @return a SubmodelElementCollection object, that behaves like a facade for the given map
	 */
	public static SubmodelElementCollection createAsFacade(Map<String, Object> obj) {
		SubmodelElementCollection facade = new SubmodelElementCollection();
		facade.putAll(obj);
		return facade;
	}
	
	/**
	 * Adds an element to the SubmodelElementCollection
	 * 
	 * @param elem
	 */
	public void addElement(ISubmodelElement elem) {
		getValue().add(elem);
	}

	@Override
	public Set<IReference> getDataSpecificationReferences() {
		return new HasDataSpecificationFacade(this).getDataSpecificationReferences();
	}

	@Override
	public void setDataSpecificationReferences(Set<IReference> ref) {
		new HasDataSpecificationFacade(this).setDataSpecificationReferences(ref);

	}

	@Override
	public String getIdShort() {
		return new ReferableFacade(this).getIdShort();
	}

	@Override
	public String getCategory() {
		return new ReferableFacade(this).getCategory();
	}

	@Override
	public LangStrings getDescription() {
		return new ReferableFacade(this).getDescription();
	}

	@Override
	public void setValue(Collection<ISubmodelElement> value) {
		put(Property.VALUE, value);

	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<ISubmodelElement> getValue() {
		Collection<ISubmodelElement> ret = new ArrayList<>();
		Collection<Object> smElems = (ArrayList<Object>) get(Property.VALUE);
		for(Object smElemO: smElems) {
			Map<String, Object> smElem = (Map<String, Object>) smElemO;
			ret.add(SubmodelElementFacadeFactory.createSubmodelElement(smElem));
		}
		return ret;
	}

	@Override
	public void setOrdered(boolean value) {
		put(SubmodelElementCollection.ORDERED, value);

	}

	@Override
	public boolean isOrdered() {
		return (boolean) get(SubmodelElementCollection.ORDERED);
	}

	@Override
	public void setAllowDuplicates(boolean value) {
		put(SubmodelElementCollection.ALLOWDUPLICATES, value);

	}

	@Override
	public boolean isAllowDuplicates() {
		return (boolean) get(SubmodelElementCollection.ALLOWDUPLICATES);
	}

	@Override
	public void setElements(Map<String, ISubmodelElement> value) {
		put(SubModel.SUBMODELELEMENT, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, ISubmodelElement> getElements() {
		Map<String, ISubmodelElement> ret = new HashMap<>();
		Collection<Object> smElems = ((Map<String, Object>) get(SubModel.SUBMODELELEMENT)).values();
		for(Object smElemO: smElems) {
			Map<String, Object> smElem = (Map<String, Object>) smElemO;
			ret.put((String) smElem.get(Referable.IDSHORT), SubmodelElementFacadeFactory.createSubmodelElement(smElem));
		}
		return ret;
	}
}
