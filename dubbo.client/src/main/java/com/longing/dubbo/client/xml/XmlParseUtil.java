package com.longing.dubbo.client.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParseUtil {
	
	public String strXml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+		
"<SERVICE xmlns=\"http://www.allinfinance.com/dataspec\">"+
"<SERVICE_HEADER>"+
"<SERVICE_SN>20200122233926836518</SERVICE_SN>"+
"<SERVICE_ID>13010</SERVICE_ID>"+
"<ORG>000064020000</ORG>"+
"<CHANNEL_ID>08</CHANNEL_ID>"+
"<REQUST_TIME>20200122233926</REQUST_TIME>"+
"<VERSION_ID>01</VERSION_ID>"+
"</SERVICE_HEADER>"+
"<SERVICE_BODY>"+
"<EXT_ATTRIBUTES>"+
"<AUTH/>"+
"</EXT_ATTRIBUTES>"+
"<REQUEST>"+
"<CARD_NO>6251931808000003</CARD_NO>"+
"<CURR_CD>156</CURR_CD>"+
"<START_DATE>20200103</START_DATE>"+
"<END_DATE>20200122</END_DATE>"+
"<FIRSTROW>0</FIRSTROW>"+
"<LASTROW>4</LASTROW>"+
"</REQUEST>"+
"</SERVICE_BODY>"+
"</SERVICE>";
	public  void list(Node node){
		if(node.getNodeType() == Node.ELEMENT_NODE ){
			System.out.println(node.getNodeName() +"  "+node.getTextContent());
		}
		NodeList nodeList = node.getChildNodes();
		for(int i =0;i<nodeList.getLength();i++){
			
			Node cnode = nodeList.item(i);
			list(cnode);
		}
	}

	public void string(){
		System.out.println(strXml);
	}
	
	public Map<String,Map<String,String>> parse() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();


		Document document = documentBuilder.parse(new InputSource(new StringReader(strXml)));
		Map<String,Map<String,String>> map = parseContent(document);
		return map;
	}
	
	public  Map<String,String> makeupContent(NodeList nodeList){
		
		Map<String,String> map = new HashMap<String, String>();
		for(int i=0 ;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() ==  Node.ELEMENT_NODE && node.getTextContent() != null && !node.getTextContent().equals("")){
				map.put(node.getNodeName(), node.getTextContent());
			}
		}
		return map;
	}
	
	public Map<String,Map<String,String>> parseContent(Document document){
		
		Map<String,Map<String,String>> map = new HashMap<String,Map<String,String>>();
		Map<String,String> headerMap = new HashMap<String, String>();
		Map<String,String> extMap = new HashMap<String, String>();
		Map<String,String> reqMap = new HashMap<String, String>();
		
		NodeList headerList = document.getElementsByTagName("SERVICE_HEADER").item(0).getChildNodes();
		NodeList extList = document.getElementsByTagName("EXT_ATTRIBUTES").item(0).getChildNodes();
		NodeList reqList = document.getElementsByTagName("REQUEST").item(0).getChildNodes();
		
		headerMap = makeupContent(headerList);
		extMap = makeupContent(extList);
		reqMap = makeupContent(reqList);
		
		map.put("SERVICE_HEADER", headerMap);
		map.put("EXT_ATTRIBUTES", extMap);
		map.put("REQUEST", reqMap);
		
		return map;
		
	}
	
	public void create() throws ParserConfigurationException, TransformerException{
		String xml = "";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		Element root = document.createElement("SERVICE");
		root.setAttribute("xmlns", "http://www.allinfinance.com/dataspec/");
		document.appendChild(root);
		Element service = document.createElement("SERVICE_HEADER");
		Element serviceSn = document.createElement("SERVICE_SN");
		serviceSn.setTextContent("20200122233926836518");
		service.appendChild(serviceSn);
		root.appendChild(service);
		
		TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
		DOMSource domSource = new DOMSource(document);
		  
        // xml transform String
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        transformer.transform(domSource, new StreamResult(bos));
        xml = bos.toString();
        System.out.println(xml);
	}
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		XmlParseUtil xml = new XmlParseUtil();
		xml.create();
		//xml.string();
		/*Map<String,Map<String,String>> map = xml.parse();
		for(String str:map.keySet()){
			Map<String,String> cMap = map.get(str);
			for(String strs:cMap.keySet()){
				System.out.println(strs+"   "+cMap.get(strs));
			}
		}*/
		
	}
}
