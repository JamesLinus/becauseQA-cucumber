package com.framework.utilities;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;


/**
 * @ClassName: EmailerUtils
 * @Description: TODO
 * @author alterhu2020@gmail.com
 * @date Apr 9, 2014 5:55:47 PM
 * 
 */

/**
 * @ClassName: EmailerUtils
 * @Description: TODO
 * @author alterhu2020@gmail.com
 * @date Apr 14, 2014 5:59:41 PM
 * 
 */

public class XmlUtils {

	private static final Logger log = Logger.getLogger(XmlUtils.class);

	private static XmlUtils xmlUtils=null;
	
	private static SAXReader reader=null;
	private static Document document =null;

	private Element rootElement=null;

	private Node node;
	private List<Node> nodes;
	private static String namespaceURI=null;
	
	
	
	/** 
	* @Title: getInstance 
	* @Description: TODO
	* @author ahu@greendotcorp.com
	* @param @return    
	* @return XmlUtils    return type
	* @throws 
	*/ 
	
	public static XmlUtils getInstance(){
		if(xmlUtils==null){
			xmlUtils=new XmlUtils();
		}
		
		reader = new SAXReader();
		return xmlUtils;
		
	}
	/** 
	* @Title: getFirstNode 
	* @Description: TODO
	* @author ahu@greendotcorp.com
	* @param @param xml
	* @param @param path
	* @param @return    
	* @return Node    return type
	* @throws 
	*/ 
	
	public Node getFirstNode(String xml, String path) {
        try {
			document=reader.read(xml);
			rootElement = document.getRootElement();
			node = rootElement.selectSingleNode(path);
			log.info("First node content is: "+node.getText());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return node;
        
	}
	/** 
	* @Title: getNodes 
	* @Description: TODO
	* @author ahu@greendotcorp.com
	* @param @param xml
	* @param @param path
	* @param @return    
	* @return List<Node>    return type
	* @throws 
	*/ 
	
	@SuppressWarnings("unchecked")
	public List<Node> getNodes(InputStream xml, String nodename) {
        try {
			document=reader.read(xml);
			rootElement = document.getRootElement();
		    namespaceURI = rootElement.getNamespaceURI();
			if(namespaceURI!=null){
				DefaultXPath xpath = new DefaultXPath(nodename);
				Map<String,String> namespaces = new TreeMap<String,String>();
				namespaces.put("ns",namespaceURI);
				xpath.setNamespaceURIs(namespaces);
				nodes=xpath.selectNodes(document);
			}else{
				nodes = document.selectNodes(nodename);
			}
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return nodes;
        
	}
	
	
	public Node selectSingleNode(Node node, String nodename) {
        if(namespaceURI!=null){
			DefaultXPath xpath = new DefaultXPath(nodename);
			Map<String,String> namespaces = new TreeMap<String,String>();
			namespaces.put("ns",namespaceURI);
			xpath.setNamespaceURIs(namespaces);
			node=xpath.selectSingleNode(node);
		}else{
			node = node.selectSingleNode(nodename);
		}
        return node;
        
	}
	
	

	public Node getFirstNode(File xml, String path) {
		   try {
				document=reader.read(xml);
				rootElement = document.getRootElement();
				node = rootElement.selectSingleNode(path);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   return node;
	}
	
	public Node getFirstNode(InputStream xml, String path) {
		   try {
				document=reader.read(xml);
				rootElement = document.getRootElement();
				node = rootElement.selectSingleNode(path);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   return node;
		   
	}

	public static void main(String[] args) {
		try {
			//http://www.tutorialspoint.com/java_xml/java_dom4j_parse_document.htm
			File inputFile = new File(
					"C:\\p4v\\QA\\Cucumber\\Projects\\GDN\\branches\\BaseAutomation\\src\\main\\java\\com\\framework\\utilities\\input.txt");
			SAXReader reader = new SAXReader();

			Document document = reader.read(inputFile);

			System.out.println("Root element :"
					+ document.getRootElement().getName());

			Element classElement = document.getRootElement();

			Node selectSingleNode = classElement
					.selectSingleNode("student/firstname");
			System.out.println("node: " + selectSingleNode.getText());
			@SuppressWarnings("unchecked")
			List<Node> nodes = classElement.selectNodes("student");
			// document.selectNodes("/class/student/" );
			System.out.println("----------------------------");
			for (Node node : nodes) {
				System.out.println("\nCurrent Element :" + node.getName());
				System.out.println("Student roll no : "
						+ node.valueOf("@rollno"));
				System.out.println("First Name : "
						+ node.selectSingleNode("firstname").getText());
				System.out.println("Last Name : "
						+ node.selectSingleNode("lastname").getText());
				System.out.println("First Name : "
						+ node.selectSingleNode("nickname").getText());
				System.out.println("Marks : "
						+ node.selectSingleNode("marks").getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
