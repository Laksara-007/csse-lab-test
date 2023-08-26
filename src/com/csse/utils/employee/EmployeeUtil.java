package com.csse.utils.employee;

import com.csse.config.Config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class EmployeeUtil extends Config {

	private static final Logger logger = Logger.getLogger("Main");

	/*
	 * this method is used to get the employee query from the xml file
	 */
	public static String getEmployeeById(String id) throws Exception {
		try {
			NodeList n;
			Element e = null;
			n = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new File("src/resources/EmployeeQuery.xml")).getElementsByTagName("query");
			for (int x = 0; x < n.getLength(); x++) {
				e = (Element) n.item(x);
				if (e.getAttribute("id").equals(id))
					break;
			}
			return e.getTextContent().trim();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to read query file - " + e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.log(Level.SEVERE, "Failed to parse employee query - " + e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to get employee - " + e.getMessage());
		}
		return "";
	}
}
