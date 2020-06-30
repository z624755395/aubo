package org.eclipse.basyx.submodel.factory.xml;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.basyx.aas.factory.xml.api.parts.AssetXMLConverter;
import org.eclipse.basyx.aas.factory.xml.api.parts.ConceptDescriptionXMLConverter;
import org.eclipse.basyx.aas.factory.xml.converters.AssetAdministrationShellXMLConverter;
import org.eclipse.basyx.aas.metamodel.api.IAssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.api.parts.IAsset;
import org.eclipse.basyx.aas.metamodel.api.parts.IConceptDescription;
import org.eclipse.basyx.submodel.factory.xml.converters.SubmodelXMLConverter;
import org.eclipse.basyx.submodel.metamodel.api.ISubModel;
import org.eclipse.basyx.vab.factory.xml.XmlParser;
import org.xml.sax.SAXException;

/**
 * This class can be used to parse XML to Metamodel Objects
 * 
 * @author conradi
 *
 */
public class XMLToMetamodelConverter {
	
	private Map<String, Object> root = new HashMap<>();
	
	/**
	 * Initializes the Parser with XML given as a String
	 * 
	 * @param xml the XML to be parsed
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public XMLToMetamodelConverter(String xml) throws ParserConfigurationException, SAXException, IOException {		
		root.putAll((Map<? extends String, ? extends Object>) XmlParser.buildXmlMap(xml).get(XMLHelper.AASENV));
	}

	
	/**
	 * Parses the AASs form the XML
	 * 
	 * @return the AASs parsed form the XML
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<IAssetAdministrationShell> parseAAS() throws ParserConfigurationException, SAXException, IOException {
		Map<String, Object> xmlAASs = (Map<String, Object>) root.get(AssetAdministrationShellXMLConverter.ASSET_ADMINISTRATION_SHELLS);		
		return AssetAdministrationShellXMLConverter.parseAssetAdministrationShells(xmlAASs);
	}

	
	/**
	 * Parses the Assets form the XML
	 * 
	 * @return the Assets parsed form the XML
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<IAsset> parseAssets() throws ParserConfigurationException, SAXException, IOException {
		Map<String, Object> xmlAssets = (Map<String, Object>) root.get(AssetXMLConverter.ASSETS);
		return AssetXMLConverter.parseAssets(xmlAssets);
	}

	
	/**
	 * Parses the SubModels form the XML
	 * 
	 * @return the SubModels parsed form the XML
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<ISubModel> parseSubmodels() {
		Map<String, Object> xmlSubmodels = (Map<String, Object>) root.get(SubmodelXMLConverter.SUBMODELS);		
		return SubmodelXMLConverter.parseSubmodels(xmlSubmodels);		
	}

	
	/**
	 * Parses the ConceptDescriptions form the XML
	 * 
	 * @return the ConceptDescriptions parsed form the XML
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<IConceptDescription> parseConceptDescriptions() {
		Map<String, Object> xmlConceptDescriptions = (Map<String, Object>) root.get(ConceptDescriptionXMLConverter.CONCEPT_DESCRIPTIONS);		
		return ConceptDescriptionXMLConverter.parseConceptDescriptions(xmlConceptDescriptions);
	}
	
}
