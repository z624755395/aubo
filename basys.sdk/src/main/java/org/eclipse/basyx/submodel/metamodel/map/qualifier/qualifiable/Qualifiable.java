package org.eclipse.basyx.submodel.metamodel.map.qualifier.qualifiable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.basyx.submodel.metamodel.api.qualifier.qualifiable.IConstraint;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.qualifiable.IQualifiable;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.qualifiable.QualifiableFacade;

/**
 * Qualifiable class
 * 
 * @author kuhn
 *
 */
public class Qualifiable extends HashMap<String, Object> implements IQualifiable {

	/**
	 * Version of serialized instances
	 */
	private static final long serialVersionUID = 1L;

	public static final String CONSTRAINTS = "constraints";

	/**
	 * Constructor
	 */
	public Qualifiable() {
		// The instance of an element may be further qualified by one or more
		// qualifiers.
		put(CONSTRAINTS, null);
	}

	/**
	 * Constructor
	 */
	public Qualifiable(Constraint qualifier) {
		// Create collection with qualifiers
		Set<Constraint> qualifiers = new HashSet<Constraint>();
		// - Add qualifier
		qualifiers.add(qualifier);

		// The instance of an element may be further qualified by one or more
		// qualifiers.
		put(CONSTRAINTS, qualifiers);
	}

	/**
	 * Constructor
	 */
	public Qualifiable(Collection<Constraint> qualifier) {
		// The instance of an element may be further qualified by one or more
		// qualifiers.
		put(CONSTRAINTS, qualifier);
	}

	public void setQualifier(Set<IConstraint> qualifiers) {
		new QualifiableFacade(this).setQualifier(qualifiers);

	}

	@Override
	public Set<IConstraint> getQualifier() {
		return new QualifiableFacade(this).getQualifier();
	}
}
