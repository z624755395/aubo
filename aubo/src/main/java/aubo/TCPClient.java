package aubo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class TCPClient {
	private static Logger logger = LoggerFactory.getLogger(TCPClient.class);
	
	public void send(String body, Aubo aubo) throws Exception{ 		
    	logger.info("data：" + body);    	
       	Socket socket = null;
       	OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
        	//tcp connection
        	socket = new Socket("localhost", 8080);
         	logger.info("connection succeed");
         	//output
            outputStream = socket.getOutputStream();
           	outputStream.write(body.getBytes("UTF-8"));
           	logger.info("sending complete");          	
           	/*
            //input
            inputStream = socket.getInputStream();
           	br = new BufferedReader(new InputStreamReader(inputStream));
            String info=null;
            while((info=br.readLine())!=null){
            	this.readXMLResponse(info, aubo.getSensor());
             	logger.info(info);
            }
            br.close();
            inputStream.close();
            */
            outputStream.close();
            socket.close();
            logger.info("connection lost");
      	} catch (UnknownHostException e1) {
           	logger.info("connection fail");
        } catch (IOException e1) {
           	logger.info("sending fail");
        }
	}		
	
	public void readXMLResponse(String data, SensorSubModel ssb) {
		try {
			byte[] b = data.getBytes();
			InputStream inp = new ByteArrayInputStream(b);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(inp);
			//read position value
			NodeList nl1 = doc.getElementsByTagName("Position");
			Element pos = (Element) nl1.item(0);
		    String xValue = pos.getAttribute("x");
		    String yValue = pos.getAttribute("y");
		    String zValue = pos.getAttribute("z");
		    String rxValue = pos.getAttribute("rx");
		    String ryValue = pos.getAttribute("ry");
		    String rzValue = pos.getAttribute("rz");
			//read joint value
		    NodeList nl2 = doc.getElementsByTagName("JointStatus");
			Element joint = (Element) nl2.item(0);
		    String joint1 = joint.getAttribute("joint1");
		    String joint2 = joint.getAttribute("joint2");
		    String joint3 = joint.getAttribute("joint3");
		    String joint4 = joint.getAttribute("joint4");
		    String joint5 = joint.getAttribute("joint5");
		    String joint6 = joint.getAttribute("joint6");
		    PositionValue currentValue = new PositionValue();
			currentValue.joint1 = Double.parseDouble(joint1);
			currentValue.joint2 = Double.parseDouble(joint2);
			currentValue.joint3 = Double.parseDouble(joint3);
			currentValue.joint4 = Double.parseDouble(joint4);
			currentValue.joint5 = Double.parseDouble(joint5);
			currentValue.joint6 = Double.parseDouble(joint6);
			ssb.updatePosition(currentValue);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
