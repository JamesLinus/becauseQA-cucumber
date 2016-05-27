package org.becausecucumber.eclipse.plugin.ui.wizard;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IProject;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Toutes les methodes utiles � la creation d'un projet
 * <ul>
 * <il>creation fichier .project </il> <il>creation fichier .classpath </il>
 * </ul>
 * 
 * @author samuel Liard
 * 
 */
public class ProjectCreationUtils {

	/**
	 * parse xmlFile in a Document
	 * 
	 * @param fileName
	 *            full name of a xml file
	 * @return a Dom Document
	 * @throws IOException
	 */
	public static Document readXmlFile(String fileName) throws IOException {

		Document doc = null;

		try {
			File xmlFile = new File(fileName);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			doc = docBuilder.parse(xmlFile);
		} catch (Exception e) {
			System.out.println("Error on parsing XML file : " + fileName);
			e.printStackTrace();
			throw new IOException("Error on parsing XML file : " + fileName);
		}

		return doc;
	}

	public static void writeFile(String fileName, Document content) throws IOException, TransformerException {
		writeFile(fileName, serializeDocument(content));
	}

	/**
	 * write a String in a new file
	 * 
	 * @param fileName
	 *            name of the new file
	 * @param content
	 *            content to write
	 * @throws IOException
	 */
	public static void writeFile(String fileName, String content) throws IOException {

		File file = new File(fileName);

		if (file.exists()) {
			throw new IOException("File " + fileName + " exist");
		}

		file.createNewFile();

		FileOutputStream flotS = new FileOutputStream(file);
		flotS.write(content.getBytes());
		flotS.close();
	}

	/**
	 * serialize a Document in a XML UTF8 String
	 * 
	 * @param doc
	 *            Dom Document to serialiaze
	 * @return @throws IOException
	 * @throws TransformerException
	 */
	public static String serializeDocument(Document doc) throws IOException, TransformerException {
		ByteArrayOutputStream s = new ByteArrayOutputStream();

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(doc);
		StreamResult outputTarget = new StreamResult(s);
		transformer.transform(source, outputTarget);

		return s.toString("UTF8");
	}

	/**
	 * Create a simple .project file
	 * 
	 * @param monProjet
	 *            project information
	 * @return a Dom Document
	 * @throws IOException
	 *             Error during the parsing Configuration
	 */
	public static Document createJavaProjectFile(IProject monProjet) throws IOException {
		Document document = docBuilder();

		Element root = document.createElement("projectDescription");
		Element elementName = document.createElement("name");
		Text projectName = document.createTextNode(monProjet.getName());
		elementName.appendChild(projectName);
		root.appendChild(elementName);
		Element elementComment = document.createElement("comment");
		root.appendChild(elementComment);
		Element elementProjects = document.createElement("projects");
		root.appendChild(elementProjects);
		Element elementBuildSpec = document.createElement("buildSpec");

		Element elementBuildCommand = document.createElement("buildCommand");
		elementBuildSpec.appendChild(elementBuildCommand);

		Element elementCommandName = document.createElement("name");
		Text projectCommandName = document.createTextNode("org.eclipse.jdt.core.javabuilder");
		elementCommandName.appendChild(projectCommandName);
		elementBuildCommand.appendChild(elementCommandName);

		Element elementArguments = document.createElement("arguments");
		elementBuildCommand.appendChild(elementArguments);

		root.appendChild(elementBuildSpec);

		root.appendChild(elementBuildSpec);
		Element elementNatures = document.createElement("natures");
		root.appendChild(elementNatures);

		Element elementNature = document.createElement("nature");
		elementNatures.appendChild(elementNature);
		Text projectNature = document.createTextNode("org.eclipse.jdt.core.javanature");
		Element xtextNature = document.createElement("nature");
		elementNatures.appendChild(xtextNature);
		Text projectNature2 = document.createTextNode(XtextProjectHelper.NATURE_ID);
		elementNature.appendChild(projectNature);
		xtextNature.appendChild(projectNature2);

		document.appendChild(root);

		return document;
	}

	/**
	 * Create a simple .classpath file</br>
	 * default source folder : src</br>
	 * default output folder : bin</br>
	 * 
	 * @return a Dom Document of .classpath
	 * @throws IOException
	 *             Error during the parsing Configuration
	 */
	public static Document createClassPathFile() throws IOException {
		Document document = docBuilder();

		Element root = document.createElement("classpath");
		Element classpathentry1 = document.createElement("classpathentry");
		classpathentry1.setAttribute("kind", "src");
		classpathentry1.setAttribute("path", "src");
		root.appendChild(classpathentry1);
		Element classpathentry2 = document.createElement("classpathentry");
		classpathentry2.setAttribute("kind", "con");
		classpathentry2.setAttribute("path", "org.eclipse.jdt.launching.JRE_CONTAINER");
		root.appendChild(classpathentry2);
		Element classpathentry3 = document.createElement("classpathentry");
		classpathentry3.setAttribute("kind", "output");
		classpathentry3.setAttribute("path", "bin");
		root.appendChild(classpathentry3);

		document.appendChild(root);

		return document;
	}

	/**
	 * Create a .classpath file with projectInformation
	 * 
	 * @param informatios
	 *            on project
	 * @return a Dom Document of .classpath
	 * @throws IOException
	 *             Error during the parsing Configuration
	 */
	public static Document createClassPathFile(IProjectInformation projectInfo) throws IOException {
		Document document = docBuilder();

		Element root = document.createElement("classpath");

		@SuppressWarnings("rawtypes")
		Iterator itSrcDir = projectInfo.getSrcDirs().iterator();
		while (itSrcDir.hasNext()) {
			String path = (String) itSrcDir.next();
			Element classpathentry1 = document.createElement("classpathentry");
			classpathentry1.setAttribute("kind", "src");
			classpathentry1.setAttribute("path", path);
			root.appendChild(classpathentry1);
		}

		String outputDir = projectInfo.getOutputDir();
		if (outputDir != null) {
			Element classpathentry3 = document.createElement("classpathentry");
			classpathentry3.setAttribute("kind", "output");
			classpathentry3.setAttribute("path", outputDir);
			root.appendChild(classpathentry3);
		} else {
			Element classpathentry3 = document.createElement("classpathentry");
			classpathentry3.setAttribute("kind", "output");
			classpathentry3.setAttribute("path", "bin");
			root.appendChild(classpathentry3);
		}

		Element classpathentry2 = document.createElement("classpathentry");
		classpathentry2.setAttribute("kind", "con");
		classpathentry2.setAttribute("path", "org.eclipse.jdt.launching.JRE_CONTAINER");
		root.appendChild(classpathentry2);

		document.appendChild(root);

		return document;
	}

	/**
	 * 
	 * @return @throws IOException
	 */
	private static Document docBuilder() throws IOException {
		Document document = null;

		try {
			DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
			document = docBuilder.newDocument();
		} catch (FactoryConfigurationError e) {
			System.out.println("createSimleProjectFile -> FactoryConfigurationError");
			e.printStackTrace();
			throw new IOException("Error during the parsing Configuration");
		} catch (ParserConfigurationException e) {
			System.out.println("createSimleProjectFile -> ParserConfigurationException");
			e.printStackTrace();
			throw new IOException("Error during the parsing Configuration");
		}

		return document;
	}

	/**
	 * A recursive method to create all folders
	 * 
	 * @param node
	 *            Node description of the XML file
	 * @param subPath
	 *            folders of project
	 * @param projectInfo
	 *            project informations
	 */
	public static void createAllSubDir(Node node, String subPath, IProjectInformation projectInfo) {

		File f = new File(projectInfo.getBasePath() + File.separator + subPath, node.getNodeName());
		f.mkdir();

		NamedNodeMap map = node.getAttributes();

		for (int j = 0; j < map.getLength(); j++) {
			Node n = map.item(j);
			if (n.getNodeName().equals("type")) {
				String path = "";
				if (subPath.equals("")) {
					path = node.getNodeName();
				} else {
					path = subPath + File.separator + node.getNodeName();
				}
				if (n.getNodeValue().equals("src")) {
					projectInfo.addSrcDir(path);
				} else if (n.getNodeValue().equals("output")) {
					projectInfo.setOutputDir(path);
				}
			}
		}

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node n = node.getChildNodes().item(i);
			String newSubPath = node.getNodeName();
			if (!subPath.equals("")) {
				newSubPath = subPath + File.separator + node.getNodeName();
			}
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				createAllSubDir(n, newSubPath, projectInfo);
			}
		}
	}

	public static void createAllDir(IProjectInformation projectInfo, String xmlFileName) throws IOException {
		Document docXml = readXmlFile(xmlFileName);

		NodeList nl = docXml.getFirstChild().getChildNodes();
		@SuppressWarnings({ "rawtypes", "unused" })
		Hashtable typeList = new Hashtable();

		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);

			if (n.getNodeType() == Node.ELEMENT_NODE) {
				createAllSubDir(nl.item(i), "", projectInfo);
			}
		}

	}
}