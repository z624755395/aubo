package aubo;

import java.io.ByteArrayOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AuboOperation {
	
	public String operation1() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element bmxsequence = document.createElement("BMXSequence");
		document.appendChild(bmxsequence);
		//instruction 1
		Element instruction1 = document.createElement("Instruction");
		Element linetarget1 = document.createElement("LineTarget");
		Element joint1 = document.createElement("Joint");
		joint1.setAttribute("JointName", "Joint.1");
		joint1.setAttribute("Units", "deg");
		Element value1 = document.createElement("JointValue");
		value1.appendChild(document.createTextNode("-43"));
		joint1.appendChild(value1);
		linetarget1.appendChild(joint1);
		
		Element joint2 = document.createElement("Joint");
		joint2.setAttribute("JointName", "Joint.2");
		joint2.setAttribute("Units", "deg");
		Element value2 = document.createElement("JointValue");
		value2.appendChild(document.createTextNode("0"));
		joint2.appendChild(value2);
		linetarget1.appendChild(joint2);
		
		Element joint3 = document.createElement("Joint");
		joint3.setAttribute("JointName", "Joint.3");
		joint3.setAttribute("Units", "deg");
		Element value3 = document.createElement("JointValue");
		value3.appendChild(document.createTextNode("-68"));
		joint3.appendChild(value3);
		linetarget1.appendChild(joint3);
		
		Element joint4 = document.createElement("Joint");
		joint4.setAttribute("JointName", "Joint.4");
		joint4.setAttribute("Units", "deg");
		Element value4 = document.createElement("JointValue");
		value4.appendChild(document.createTextNode("20"));
		joint4.appendChild(value4);
		linetarget1.appendChild(joint4);
		
		Element joint5 = document.createElement("Joint");
		joint5.setAttribute("JointName", "Joint.5");
		joint5.setAttribute("Units", "deg");
		Element value5 = document.createElement("JointValue");
		value5.appendChild(document.createTextNode("-89"));
		joint5.appendChild(value5);
		linetarget1.appendChild(joint5);
		
		Element joint6 = document.createElement("Joint");
		joint6.setAttribute("JointName", "Joint.6");
		joint6.setAttribute("Units", "deg");
		Element value6 = document.createElement("JointValue");
		value6.appendChild(document.createTextNode("-149"));
		joint6.appendChild(value6);
		linetarget1.appendChild(joint6);
		instruction1.appendChild(linetarget1);
		
		//instreuction2
		Element instruction2 = document.createElement("Instruction");
		Element linetarget2 = document.createElement("LineTarget");
		Element joint2_1 = document.createElement("Joint");
		joint2_1.setAttribute("JointName", "Joint.1");
		joint2_1.setAttribute("Units", "deg");
		Element value2_1 = document.createElement("JointValue");
		value2_1.appendChild(document.createTextNode("26"));
		joint2_1.appendChild(value2_1);
		linetarget2.appendChild(joint2_1);
		
		Element joint2_2 = document.createElement("Joint");
		joint2_2.setAttribute("JointName", "Joint.2");
		joint2_2.setAttribute("Units", "deg");
		Element value2_2 = document.createElement("JointValue");
		value2_2.appendChild(document.createTextNode("18"));
		joint2_2.appendChild(value2_2);
		linetarget2.appendChild(joint2_2);
		
		Element joint3_2 = document.createElement("Joint");
		joint3_2.setAttribute("JointName", "Joint.3");
		joint3_2.setAttribute("Units", "deg");
		Element value3_2 = document.createElement("JointValue");
		value3_2.appendChild(document.createTextNode("-48"));
		joint3_2.appendChild(value3_2);
		linetarget2.appendChild(joint3_2);
		
		Element joint4_2 = document.createElement("Joint");
		joint4_2.setAttribute("JointName", "Joint.4");
		joint4_2.setAttribute("Units", "deg");
		Element value4_2 = document.createElement("JointValue");
		value4_2.appendChild(document.createTextNode("22"));
		joint4_2.appendChild(value4_2);
		linetarget2.appendChild(joint4_2);
		
		Element joint5_2 = document.createElement("Joint");
		joint5_2.setAttribute("JointName", "Joint.5");
		joint5_2.setAttribute("Units", "deg");
		Element value5_2 = document.createElement("JointValue");
		value5_2.appendChild(document.createTextNode("-89"));
		joint5_2.appendChild(value5_2);
		linetarget2.appendChild(joint5_2);
		
		Element joint6_2 = document.createElement("Joint");
		joint6_2.setAttribute("JointName", "Joint.6");
		joint6_2.setAttribute("Units", "deg");
		Element value6_2 = document.createElement("JointValue");
		value6_2.appendChild(document.createTextNode("-149"));
		joint6_2.appendChild(value6_2);
		linetarget2.appendChild(joint6_2);
		
		instruction2.appendChild(linetarget2);
		
		bmxsequence.appendChild(instruction1);
		bmxsequence.appendChild(instruction2);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		transformer = tf.newTransformer();

		DOMSource source = new DOMSource(document);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		transformer.transform(source, new StreamResult(bos));
		String xmlStr = bos.toString();
		return xmlStr;
	}
	
	public String operation2() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		Element bmxsequence = document.createElement("BMXSequence");
		document.appendChild(bmxsequence);
		//instruction 1
		Element instruction1 = document.createElement("Instruction");
		Element linetarget1 = document.createElement("LineTarget");
		Element joint1 = document.createElement("Joint");
		joint1.setAttribute("JointName", "Joint.1");
		joint1.setAttribute("Units", "deg");
		Element value1 = document.createElement("JointValue");
		value1.appendChild(document.createTextNode("128"));
		joint1.appendChild(value1);
		linetarget1.appendChild(joint1);
		
		Element joint2 = document.createElement("Joint");
		joint2.setAttribute("JointName", "Joint.2");
		joint2.setAttribute("Units", "deg");
		Element value2 = document.createElement("JointValue");
		value2.appendChild(document.createTextNode("16"));
		joint2.appendChild(value2);
		linetarget1.appendChild(joint2);
		
		Element joint3 = document.createElement("Joint");
		joint3.setAttribute("JointName", "Joint.3");
		joint3.setAttribute("Units", "deg");
		Element value3 = document.createElement("JointValue");
		value3.appendChild(document.createTextNode("-49"));
		joint3.appendChild(value3);
		linetarget1.appendChild(joint3);
		
		Element joint4 = document.createElement("Joint");
		joint4.setAttribute("JointName", "Joint.4");
		joint4.setAttribute("Units", "deg");
		Element value4 = document.createElement("JointValue");
		value4.appendChild(document.createTextNode("24"));
		joint4.appendChild(value4);
		linetarget1.appendChild(joint4);
		
		Element joint5 = document.createElement("Joint");
		joint5.setAttribute("JointName", "Joint.5");
		joint5.setAttribute("Units", "deg");
		Element value5 = document.createElement("JointValue");
		value5.appendChild(document.createTextNode("-89"));
		joint5.appendChild(value5);
		linetarget1.appendChild(joint5);
		
		Element joint6 = document.createElement("Joint");
		joint6.setAttribute("JointName", "Joint.6");
		joint6.setAttribute("Units", "deg");
		Element value6 = document.createElement("JointValue");
		value6.appendChild(document.createTextNode("-149"));
		joint6.appendChild(value6);
		linetarget1.appendChild(joint6);
		
		instruction1.appendChild(linetarget1);
		
		//instreuction2
		Element instruction2 = document.createElement("Instruction");
		Element linetarget2 = document.createElement("LineTarget");
		Element joint2_1 = document.createElement("Joint");
		joint2_1.setAttribute("JointName", "Joint.1");
		joint2_1.setAttribute("Units", "deg");
		Element value2_1 = document.createElement("JointValue");
		value2_1.appendChild(document.createTextNode("173"));
		joint2_1.appendChild(value2_1);
		linetarget2.appendChild(joint2_1);
		
		Element joint2_2 = document.createElement("Joint");
		joint2_2.setAttribute("JointName", "Joint.2");
		joint2_2.setAttribute("Units", "deg");
		Element value2_2 = document.createElement("JointValue");
		value2_2.appendChild(document.createTextNode("-2"));
		joint2_2.appendChild(value2_2);
		linetarget2.appendChild(joint2_2);
		
		Element joint3_2 = document.createElement("Joint");
		joint3_2.setAttribute("JointName", "Joint.3");
		joint3_2.setAttribute("Units", "deg");
		Element value3_2 = document.createElement("JointValue");
		value3_2.appendChild(document.createTextNode("-70"));
		joint3_2.appendChild(value3_2);
		linetarget2.appendChild(joint3_2);
		
		Element joint4_2 = document.createElement("Joint");
		joint4_2.setAttribute("JointName", "Joint.4");
		joint4_2.setAttribute("Units", "deg");
		Element value4_2 = document.createElement("JointValue");
		value4_2.appendChild(document.createTextNode("23"));
		joint4_2.appendChild(value4_2);
		linetarget2.appendChild(joint4_2);
		
		Element joint5_2 = document.createElement("Joint");
		joint5_2.setAttribute("JointName", "Joint.5");
		joint5_2.setAttribute("Units", "deg");
		Element value5_2 = document.createElement("JointValue");
		value5_2.appendChild(document.createTextNode("-89"));
		joint5_2.appendChild(value5_2);
		linetarget2.appendChild(joint5_2);
		
		Element joint6_2 = document.createElement("Joint");
		joint6_2.setAttribute("JointName", "Joint.6");
		joint6_2.setAttribute("Units", "deg");
		Element value6_2 = document.createElement("JointValue");
		value6_2.appendChild(document.createTextNode("-149"));
		joint6_2.appendChild(value6_2);
		linetarget2.appendChild(joint6_2);
		
		instruction2.appendChild(linetarget2);
		
		// bmxsequence
		bmxsequence.appendChild(instruction1);
		bmxsequence.appendChild(instruction2);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		transformer = tf.newTransformer();

		DOMSource source = new DOMSource(document);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		transformer.transform(source, new StreamResult(bos));
		String xmlStr = bos.toString();
		return xmlStr;
	}
	
	public String getCurrentPosition() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element generalInfo = document.createElement("GeneralInfo");
		document.appendChild(generalInfo);
		Element modus = document.createElement("Modus");
		modus.appendChild(document.createTextNode("true"));
		Element maxspeed = document.createElement("MaxSpeed");
		maxspeed.setAttribute("Units", "m/s");
		maxspeed.appendChild(document.createTextNode("1.5"));
		generalInfo.appendChild(modus);
		generalInfo.appendChild(maxspeed);
			
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		transformer = tf.newTransformer();

		DOMSource source = new DOMSource(document);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		transformer.transform(source, new StreamResult(bos));
		String xmlStr = bos.toString();
		return xmlStr;
	}

}

	