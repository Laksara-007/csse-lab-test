package com.csse.repository;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import com.csse.config.Config;
import com.csse.models.Employee;
import com.csse.utils.employee.EmployeeUtil;
import com.csse.utils.employee.EmployeeTransformer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/* *
 * this class is used to perform the database operations
 */
public class EmployeeRepository extends Config {

	private final ArrayList<Employee> el = new ArrayList<Employee>();

	private static Connection e2;

	private static Statement e3;

	private PreparedStatement e4;

	/*
	 * this constructor is used to connect to the database
	 */
	public EmployeeRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			e2 = DriverManager.getConnection(config.getProperty("url"), config.getProperty("username"),
					config.getProperty("password"));
		} catch (Exception e) {
		}
	}

	/*
	 * this method is used to get the employee details from the xml file
	 */
	public void e1() {

		try {
			int s = EmployeeTransformer.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> e0 = EmployeeTransformer.XMLXPATHS().get(i);
				Employee e6 = new Employee();
				e6.setId(e0.get("XpathEmployeeIDKey"));
				e6.setFullName(e0.get("XpathEmployeeNameKey"));
				e6.setAddress(e0.get("XpathEmployeeAddressKey"));
				e6.setFaculty(e0.get("XpathFacultyNameKey"));
				e6.setDepartment(e0.get("XpathDepartmentKey"));
				e6.setDesignation(e0.get("XpathDesignationKey"));
				el.add(e6);
				System.out.println(e6.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	/*
	 * this method is used to create the employee table
	 */
	public void create() {
		try {
			e3 = e2.createStatement();
			e3.executeUpdate(EmployeeUtil.getEmployeeById("q2"));
			e3.executeUpdate(EmployeeUtil.getEmployeeById("q1"));
		} catch (Exception e) {
		}
	}

	/*
	 * this method is used to insert many employee details to the database
	 */
	public void createMany() {
		try {
			e4 = e2.prepareStatement(EmployeeUtil.getEmployeeById("q3"));
			e2.setAutoCommit(false);
			for (int i = 0; i < el.size(); i++) {
				Employee e = el.get(i);
				e4.setString(1, e.getId());
				e4.setString(2, e.getFullName());
				e4.setString(3, e.getAddress());
				e4.setString(4, e.getFaculty());
				e4.setString(5, e.getDepartment());
				e4.setString(6, e.getDesignation());
				e4.addBatch();
			}
			e4.executeBatch();
			e2.commit();
		} catch (Exception e) {
		}
	}

	/*
	 * this method is used to delete one employee details to the database
	 */
	public void deleteEmployee(String eid) {
		try {
			e4 = e2.prepareStatement(EmployeeUtil.getEmployeeById("q6"));
			e4.setString(1, eid);
			e4.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/*
	 * this method is used to update one employee details to the database
	 */
	public void display() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			e4 = e2.prepareStatement(EmployeeUtil.getEmployeeById("q5"));
			ResultSet r = e4.executeQuery();
			while (r.next()) {
				Employee e = new Employee();
				e.setId(r.getString(1));
				e.setFullName(r.getString(2));
				e.setAddress(r.getString(3));
				e.setFaculty(r.getString(4));
				e.setDepartment(r.getString(5));
				e.setDesignation(r.getString(6));
				employees.add(e);
			}
		} catch (Exception e) {
		}
		printList(employees);
	}

	public void printList(ArrayList<Employee> employees) {
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out.println(
				"================================================================================================================");
		for (Employee e : employees) {
			System.out.println(e.toString());
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------");
		}
	}
}
