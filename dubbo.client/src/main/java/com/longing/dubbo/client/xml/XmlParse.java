package com.longing.dubbo.client.xml;

import java.io.IOException;
import java.io.InputStream;













import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParse {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		InputStream inputStream = XmlParse.class.getClassLoader().getResourceAsStream("city.xml");
		Document document = documentBuilder.parse(inputStream);
		//list(document);
		add(document);
		list(document);
	}
	
	public static void add(Document document) throws TransformerException{
		Element element = document.createElement("guangzhou");
		element.setTextContent("π„÷›");
		Node node = document.getElementsByTagName("china").item(0);
		node.appendChild(element);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document),new StreamResult("D:\\code\\dubbo.client\\src\\main\\resources\\city.xml"));
		
	}
	public static void read(Document document){
		NodeList nodeList = document.getElementsByTagName("beijing");
		Node node = nodeList.item(0);
		System.out.println(node.getNodeName());
	}
	
	public static void list(Node node){
		if(node.getNodeType() == Node.ELEMENT_NODE){
			System.out.println(node.getNodeName());
		}
		NodeList nodeList = node.getChildNodes();
		for(int i =0;i<nodeList.getLength();i++){
			
			Node cnode = nodeList.item(i);
			list(cnode);
		}
	}
}
