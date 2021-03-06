package org.eclipse.basyx.submodel.factory.xml.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.basyx.submodel.factory.xml.XMLHelper;
import org.eclipse.basyx.submodel.factory.xml.converters.qualifier.HasDataSpecificationXMLConverter;
import org.eclipse.basyx.submodel.factory.xml.converters.qualifier.HasSemanticsXMLConverter;
import org.eclipse.basyx.submodel.factory.xml.converters.qualifier.IdentifiableXMLConverter;
import org.eclipse.basyx.submodel.factory.xml.converters.qualifier.haskind.HasKindXMLConverter;
import org.eclipse.basyx.submodel.factory.xml.converters.qualifier.qualifiable.QualifiableXMLConverter;
import org.eclipse.basyx.submodel.factory.xml.converters.submodelelement.SubmodelElementXMLConverter;
import org.eclipse.basyx.submodel.metamodel.api.ISubModel;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElement;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Handles the conversion between ISubModel objects and the XML tag &lt;aas:submodels&gt; in both directions
 * 
 * @author conradi
 *
 */
public class SubmodelXMLConverter {

	public static final String SUBMODELS = "aas:submodels";
	public static final String SUBMODEL = "aas:submodel";
	
	/**
	 * Parses &lt;aas:submodels&gt; and builds the SubModel objects from it
	 * 
	 * @param xmlObject a Map containing the content of the XML tag &lt;aas:submodels&gt;
	 * @return a List of ISubModel objects parsed form the given XML Map
	 */
	public static List<ISubModel> parseSubmodels(Map<String, Object> xmlObject) {
		List<Map<String, Object>> xmlSubmodels = XMLHelper.getList(xmlObject.get(SUBMODEL));
		List<ISubModel> submodels = new ArrayList<>();
		
		for (Map<String, Object> xmlSubmodel : xmlSubmodels) {
			SubModel submodel = new SubModel();
			
			IdentifiableXMLConverter.populateIdentifiable(xmlSubmodel, submodel);
			HasSemanticsXMLConverter.populateHasSemantics(xmlSubmodel, submodel);
			HasDataSpecificationXMLConverter.populateHasDataSpecification(xmlSubmodel, submodel);
			QualifiableXMLConverter.populateQualifiable(xmlSubmodel, submodel);
			HasKindXMLConverter.populateHasKind(xmlSubmodel, submodel);
						
			List<ISubmodelElement> submodelElements = SubmodelElementXMLConverter.parseSubmodelElements(xmlSubmodel);
						
			for (ISubmodelElement submdoElement : submodelElements) {
				submodel.addSubModelElement(submdoElement);
			}
			
			submodels.add(submodel);
		}
		return submodels;
	}
	
	
	
	
	/**
	 * Builds &lt;aas:submodels&gt; from a given Collection of ISubModel objects
	 * 
	 * @param document the XML document
	 * @param subModels a Collection of ISubModel objects to build the XML for
	 * @return the &lt;aas:submodels&gt; XML tag for the given ISubModel objects
	 */
	public static Element buildSubmodelsXML(Document document, Collection<ISubModel> subModels) {
		Element root = document.createElement(SUBMODELS);
		
		List<Element> xmlSubmodelList = new ArrayList<>();
		for(ISubModel subModel: subModels) {
			Element subModelRoot = document.createElement(SUBMODEL);

			IdentifiableXMLConverter.populateIdentifiableXML(document, subModelRoot, subModel);
			HasSemanticsXMLConverter.populateHasSemanticsXML(document, subModelRoot, subModel);
			HasDataSpecificationXMLConverter.populateHasDataSpecificationXML(document, subModelRoot, subModel);
			HasKindXMLConverter.populateHasKindXML(document, subModelRoot, subModel);
			QualifiableXMLConverter.populateQualifiableXML(document, subModelRoot, subModel);
			
			Collection<ISubmodelElement> submodelElements = (Collection<ISubmodelElement>) subModel.getSubmodelElements().values();

			Element xmlSubmodelElements = SubmodelElementXMLConverter.buildSubmodelElementsXML(document, submodelElements);
			subModelRoot.appendChild(xmlSubmodelElements);
			
			xmlSubmodelList.add(subModelRoot);
		}
		
		for(Element element: xmlSubmodelList) {
			root.appendChild(element);
		}
		return root;
	}
}