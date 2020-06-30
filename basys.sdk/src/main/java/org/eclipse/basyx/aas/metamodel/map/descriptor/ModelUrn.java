package org.eclipse.basyx.aas.metamodel.map.descriptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.submodel.metamodel.map.identifier.IdentifierType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create URNs with the format urn:<legalEntity>:<subUnit>:<subModel>:<version>:<revision>:<elementID>#<elementInstance>
 * 
 * @author kuhn
 *
 */
public class ModelUrn implements IIdentifier {

	private static Logger logger = LoggerFactory.getLogger(ModelUrn.class);

	
	/**
	 * URN string
	 */
	protected String urnString = null;
	
	
	
	/**
	 * Constructor that accepts a single, raw URN
	 */
	public ModelUrn(String rawURN) {
		urnString = rawURN;
	}
	
	
	/**
	 * Constructor that build a URN
	 */
	public ModelUrn(String legalEntity, String subUnit, String subModel, String version, String revision, String elementId, String elementInstance) {
		// Goal is: urn:<legalEntity>:<subUnit>:<subModel>:<version>:<revision>:<elementID>#<elementInstance>
		StringBuffer urnBuilder = new StringBuffer();
		
		// Start with header
		urnBuilder.append("urn:");
		// - Add URN components until instance
		if (legalEntity != null) urnBuilder.append(legalEntity); urnBuilder.append(":");
		if (subUnit != null)     urnBuilder.append(subUnit);     urnBuilder.append(":");
		if (subModel != null)    urnBuilder.append(subModel);    urnBuilder.append(":");
		if (version != null)     urnBuilder.append(version);     urnBuilder.append(":");
		if (revision != null)    urnBuilder.append(revision);    urnBuilder.append(":");
		if (elementId != null)   urnBuilder.append(elementId); 
		// - Add element instance, prefix with '#'
		if (elementInstance != null) urnBuilder.append("#"+elementInstance);
		
		// Build URN
		urnString = urnBuilder.toString();
	}
	
	
	
	/**
	 * Get URN as string
	 */
	public String getURN() {
		return urnString;
	}
	
	
	/**
	 * Get URL encoded URN as string
	 */
	public String getEncodedURN() {
		try {
			// Try to encode urn string
			return URLEncoder.encode(urnString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// Catch block
			logger.error("Exception in getEncodedURN", e);
			return null;
		}
	}
	
	
	/**
	 * Convert to string
	 */
	@Override
	public String toString() {
		return getEncodedURN();
	}
	
	
	/**
	 * Create a new ModelUrn by appending a String to the URN string, e.g. to create a new element instance
	 */
	public ModelUrn append(String suffix) {
		// Append suffix
		return new ModelUrn(urnString + suffix);
	}
	
	/**
	 * A ModelUrn is a unique identifier and is a specialization of an URI
	 */
	@Override
	public String getIdType() {
		return IdentifierType.URI;
	}

	/**
	 * A ModelUrn is a unique identifier => the urn is the id
	 */
	@Override
	public String getId() {
		return getURN();
	}
	
	/**
	 * HashCode method - required to be able to use this class as hashmap key
	 */
	@Override
	public int hashCode() {
		return urnString.hashCode();
	}
	
	
	/**
	 * Check equality - required to be able to use this class as hashmap key
	 */
	@Override
	public boolean equals(Object obj) {
		// Type check
		if (!(obj instanceof ModelUrn)) return false;
		
		// Check values
		return urnString.equals(((ModelUrn) obj).urnString);
	}
}

