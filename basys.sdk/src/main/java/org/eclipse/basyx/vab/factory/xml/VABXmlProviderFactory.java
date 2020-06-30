package org.eclipse.basyx.vab.factory.xml;

import org.eclipse.basyx.vab.modelprovider.map.VABMapProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides the VAB elements from given xml string.
 * 
 * @author kannoth
 *
 */
public class VABXmlProviderFactory {
	
	private static Logger logger = LoggerFactory.getLogger(VABXmlProviderFactory.class);

	public VABXmlProviderFactory() {
		// Empty Constructor
	}

	public VABMapProvider createVABElements(String aasXml) {
		try {
			return (new VABMapProvider(XmlParser.buildXmlMap(aasXml)));
		} catch (Exception e) {
			logger.error("Exception in createVABElements", e);
		}
		return null;
	}

}
