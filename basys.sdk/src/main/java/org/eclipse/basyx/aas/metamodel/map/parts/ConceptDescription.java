package org.eclipse.basyx.aas.metamodel.map.parts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.basyx.aas.metamodel.api.dataspecification.IDataSpecification;
import org.eclipse.basyx.aas.metamodel.api.dataspecification.IDataSpecificationIEC61360;
import org.eclipse.basyx.aas.metamodel.api.parts.IConceptDescription;
import org.eclipse.basyx.aas.metamodel.facade.parts.ConceptDescriptionFacade;
import org.eclipse.basyx.aas.metamodel.map.dataspecification.DataSpecification;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.IAdministrativeInformation;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.HasDataSpecificationFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.IdentifiableFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.ReferableFacade;
import org.eclipse.basyx.submodel.metamodel.map.modeltype.ModelType;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.HasDataSpecification;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.Identifiable;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;

/**
 * ConceptDescription class as described in DAAS document
 * 
 * @author schnicke
 *
 */
public class ConceptDescription extends HashMap<String, Object> implements IConceptDescription {
	private static final long serialVersionUID = 1L;

	public static final String ISCASEOF = "isCaseOf";
	public static final String MODELTYPE = "ConceptDescription";

	// Addition to meta model
	public static final String DATASPECIFICATIONS = "dataSpecifications";

	public ConceptDescription() {
		// Add model type
		putAll(new ModelType(MODELTYPE));

		// Add qualifiers
		putAll(new HasDataSpecification());
		putAll(new Identifiable());

		// Add attributes
		put(ISCASEOF, new HashSet<Reference>());
		put(DATASPECIFICATIONS, new HashSet<IDataSpecificationIEC61360>());
	}

	@Override
	public Set<IReference> getDataSpecificationReferences() {
		return new HasDataSpecificationFacade(this).getDataSpecificationReferences();
	}

	public void setDataSpecificationReferences(Set<IReference> ref) {
		new HasDataSpecificationFacade(this).setDataSpecificationReferences(ref);
	}

	@Override
	public IAdministrativeInformation getAdministration() {
		return new IdentifiableFacade(this).getAdministration();
	}

	@Override
	public IIdentifier getIdentification() {
		return new IdentifiableFacade(this).getIdentification();
	}

	public void setAdministration(String version, String revision) {
		new IdentifiableFacade(this).setAdministration(version, revision);
	}

	public void setIdentification(String idType, String id) {
		new IdentifiableFacade(this).setIdentification(idType, id);

	}

	@Override
	public Set<IReference> getIsCaseOf() {
		return (Set<IReference>) new ConceptDescriptionFacade(this).getIsCaseOf();
	}

	public void setIsCaseOf(Set<Reference> ref) {
		new ConceptDescriptionFacade(this).setIscaseOf(ref);
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

	@SuppressWarnings("unchecked")
	public Set<IDataSpecification> getDataSpecifications() {
		return (Set<IDataSpecification>) get(DATASPECIFICATIONS);
	}

	public void addDataSpecification(IDataSpecificationIEC61360 spec) {
		getDataSpecifications().add(new DataSpecification(spec));
	}


}