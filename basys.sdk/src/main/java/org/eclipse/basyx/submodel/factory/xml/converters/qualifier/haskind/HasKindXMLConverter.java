package org.eclipse.basyx.submodel.factory.xml.converters.qualifier.haskind;

import java.util.Map;

import org.eclipse.basyx.submodel.factory.xml.XMLHelper;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.haskind.IHasKind;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.haskind.HasKind;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Handles the conversion between an IHasKind object and the XML tag &lt;aas:kind&gt; in both directions
 * 
 * @author conradi
 *
 */
public class HasKindXMLConverter {
	
	public static final String KIND = "aas:kind";
	
	
	/**
	 * Populates a given IHasKind object with the data form the given XML
	 * 
	 * @param xmlObject the XML map containing the &lt;aas:kind&gt; tag
	 * @param hasKind the IHasKind object to be populated -treated as Map here-
	 */
	public static void populateHasKind(Map<String, Object> xmlObject, Map<String, Object> hasKind) {
		//The IHasKind object has to be treated as Map here, as the Interface has no Setters
		
		String hasKindValue = XMLHelper.getString(xmlObject.get(KIND));
		hasKind.put(HasKind.KIND, hasKindValue);
	}
	
	
	/**
	 * Populates a given XML map with the data form a given IHasKind object<br>
	 * Creates the &lt;aas:kind&gt; tag in the given root
	 * 
	 * @param document the XML document
	 * @param root the XML root Element to be populated
	 * @param hasKind the IHasKind object to be converted to XML
	 */
	public static void populateHasKindXML(Document document, Element root, IHasKind hasKind) {
		if(hasKind.getKind() != null) {
			Element kindRoot = document.createElement(KIND);
			kindRoot.appendChild(document.createTextNode(hasKind.getKind()));
			root.appendChild(kindRoot);
		}
	}
}