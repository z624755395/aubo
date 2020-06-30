package org.eclipse.basyx.aas.metamodel.map.parts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.basyx.aas.metamodel.api.parts.IConceptDescription;
import org.eclipse.basyx.aas.metamodel.api.parts.IConceptDictionary;
import org.eclipse.basyx.aas.metamodel.facade.parts.ConceptDictionaryFacade;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.ReferableFacade;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.Referable;

/**
 * ConceptDictionary class as described in DAAS document
 * 
 * @author elsheikh, schnicke
 *
 */
public class ConceptDictionary extends HashMap<String, Object> implements IConceptDictionary {

	/**
	 * Version of serialized instances
	 */
	private static final long serialVersionUID = 1L;

	public static final String CONCEPTDESCRIPTION = "conceptDescription";

	// Extension of meta model
	public static final String CONCEPTDESCRIPTIONS = "conceptDescriptions";

	/**
	 * Constructor
	 */
	public ConceptDictionary() {
		// Add qualifier (Referable)
		putAll(new Referable());
		put(CONCEPTDESCRIPTION, new HashSet<String>());
		put(CONCEPTDESCRIPTIONS, new HashSet<IConceptDescription>());
	}

	public ConceptDictionary(Set<IReference> ref) {
		// Add qualifier (Referable)
		putAll(new Referable());
		put(CONCEPTDESCRIPTION, ref);
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
	public IReference getParent() {
		return new ReferableFacade(this).getParent();
	}

	public void setIdShort(String idShort) {
		new ReferableFacade(this).setIdShort(idShort);

	}

	public void setCategory(String category) {
		new ReferableFacade(this).setCategory(category);

	}

	public void setDescription(LangStrings description) {
		new ReferableFacade(this).setDescription(description);

	}

	public void setParent(IReference obj) {
		new ReferableFacade(this).setParent(obj);

	}

	@Override
	public Set<IReference> getConceptDescription() {
		return new ConceptDictionaryFacade(this).getConceptDescription();
	}

	public void setConceptDescription(HashSet<IReference> ref) {
		new ConceptDictionaryFacade(this).setConceptDescription(ref);

	}

	@SuppressWarnings("unchecked")
	public void addConceptDescription(IConceptDescription description) {
		((Set<IConceptDescription>) get(CONCEPTDESCRIPTIONS)).add(description);
	}
}
