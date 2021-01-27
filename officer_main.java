package com.midterm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.glassfish.hk2.api.Self;
import org.json.JSONArray;
import org.json.JSONObject;

import com.midterm.officer_main;
import com.midterm.Mysqlconnection;
import com.midterm.officer;
import com.midterm.Mysqlconnection;
import com.midterm.Mysqlconnection;
import com.midterm.Mysqlconnection;

@Path("/awo")
public class officer_main {


	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	PreparedStatement preparedStatement = null;

	JSONObject mainObj = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	JSONObject childObj = new JSONObject();
	
public static void main (String a[]) {
		
		officer_main myObj = new officer_main();
	    //System.out.println(myObj.deletedepartment("4").toString());
		
	}
@GET
@Path("/getOfficer")
@Produces(MediaType.APPLICATION_JSON)
/*public String getEmployee() {
	return "MMMM";
}*/
public Response getofficer() {
	
	
	
	Mysqlconnection connection = new Mysqlconnection();

	con = connection.getConnection();
	
	try {
		
		stmt = con.createStatement();

		rs = stmt.executeQuery("Select * from officer");
		

		while (rs.next()) {
			childObj = new JSONObject();

			childObj.accumulate("Officer Id", rs.getString("OFFICER_ID"));
			childObj.accumulate("End Date", rs.getString("END_DATE"));
			childObj.accumulate("First Name", rs.getString("FIRST_NAME"));
			childObj.accumulate("Last Name", rs.getString("LAST_NAME"));
			childObj.accumulate("Start Date", rs.getString("START_DATE"));
			childObj.accumulate("Title", rs.getString("TITLE"));
			childObj.accumulate("Customer id", rs.getString("CUST_ID"));

			jsonArray.put(childObj);
		}

		mainObj.put("officer", jsonArray);
	} catch (SQLException e) {
		//return "SQL Exception : " + e.getMessage();
		System.out.println("SQL Exception : " + e.getMessage());
	} finally {
		try {
			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			//return "Finally Block SQL Exception : " + e.getMessage();
			System.out.println("Finally Block SQL Exception : " + e.getMessage());
		}
	}
	


	return Response.status(200).entity(mainObj.toString()).build();
}

@POST
@Path("/createofficer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response createofficer(officer offi)
{
	Mysqlconnection connection = new Mysqlconnection();
	
	con = connection.getConnection();
	
try
{
	
	
	
	
	String query = "INSERT INTO `midterm`.`officer`(`OFFICER_ID`,`END_DATE`,`FIRST_NAME`,`LAST_NAME`,`START_DATE`,`TITLE`,`CUST_ID`)"
			+ "VALUES(?,?,?,?,?,?,?)";
	
	
	preparedStatement = con.prepareStatement(query);

	preparedStatement.setInt(1, offi.getOFFICER_ID());
	preparedStatement.setDate(2, offi.getEND_DATE());
	preparedStatement.setString(3,offi.getFIRST_NAME());
	preparedStatement.setString(4,offi.getLAST_NAME());
	preparedStatement.setDate(5,offi.getSTART_DATE());
	preparedStatement.setString(6, offi.getTITLE());
	preparedStatement.setInt(7, offi.getCUST_ID());
	
	
	int rowCount = preparedStatement.executeUpdate();
	
	if(rowCount>0)
	{
		System.out.println("Record inserted Successfully! : "+rowCount);
		
		mainObj.accumulate("Status", 201);
		mainObj.accumulate("Message", "Record Successfully added!");
	}else
	{
		mainObj.accumulate("Status", 500);
		mainObj.accumulate("Message", "Something went wrong!");
	}
	
	
}catch (SQLException e) {

	mainObj.accumulate("Status", 500);
	mainObj.accumulate("Message", e.getMessage());
}finally {
	try
	{
		con.close();
		preparedStatement.close();
	}catch (SQLException e) {
		System.out.println("Finally SQL Exception : "+e.getMessage());
	}
}


return Response.status(201).entity(mainObj.toString()).build();

	
	
}


@GET
@Path("/officer/{OFFICER_ID}")
@Produces(MediaType.APPLICATION_JSON)
public Response getOfficer(@PathParam("OFFICER_ID")String OFFICER_ID)
{

	Mysqlconnection connection = new Mysqlconnection();
	
	con = connection.getConnection();
	
try
{
	stmt=con.createStatement();
	String query =("select * from officer where officer_id="+OFFICER_ID);
	rs=stmt.executeQuery(query);
	while(rs.next())
	{
		mainObj.accumulate("OFFICER_ID",rs.getString("OFFICER_ID"));
		mainObj.accumulate("END_DATE",rs.getString("END_DATE"));
		mainObj.accumulate("FIRST_NAME",rs.getString("FIRST_NAME"));
		mainObj.accumulate("LAST_NAME",rs.getString("LAST_NAME"));
		mainObj.accumulate("START_DATE",rs.getString("START_DATE"));
		mainObj.accumulate("TITLE",rs.getString("TITLE"));
		mainObj.accumulate("CUST_ID",rs.getString("CUST_ID"));
		
		
	}
	if(!mainObj.isEmpty())
	{
		return Response.ok().entity(mainObj.toString()).build();
	}else
	{
		mainObj.accumulate("Status", 500);
		mainObj.accumulate("Message", "Something went wrong!");
	}
	}
	catch (SQLException e) {

		mainObj.accumulate("Status", 204);
		mainObj.accumulate("Message", e.getMessage());
	
		
}
return Response.noContent().entity(mainObj.toString()).build();
}
	

@DELETE
@Path("/del/{OFFICER_ID}")
public Response deletedepartment(@PathParam("OFFICER_ID")String OFFICER_ID) {
	Mysqlconnection connection= new Mysqlconnection();
	
	con = connection.getConnection();
	Status status =Status.OK;
	
	try {
		
		String query ="DELETE FROM officer WHERE OFFICER_ID="+OFFICER_ID;
		
		stmt=con.createStatement();
		
		int rowCount = stmt.executeUpdate(query);
		
		if(rowCount>0) {
			status=Status.OK;
			mainObj.accumulate("Status", status);
			mainObj.accumulate("Message", " data deleted!");
	}else {
		status=Status.NOT_MODIFIED;
		mainObj.accumulate("Status", status);
		mainObj.accumulate("Message", " oops wrong!");
	}
}catch(SQLException e) {
	e.printStackTrace();
	status=Status.NOT_MODIFIED;
	mainObj.accumulate("Status", status);
	mainObj.accumulate("Message", "something wrong");
	
}
	return Response.status(status).entity(mainObj.toString()).build();
}

@PUT
@Path("/updateoffi/{OFFICER_ID}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updateaccount(@PathParam("OFFICER_ID") int OFFICER_ID,officer offie)
{

	Mysqlconnection connection = new Mysqlconnection();
	
	con = connection.getConnection();
	
	try
	{
		String query = "UPDATE midterm.officer SET END_DATE = ? , FIRST_NAME = ? , LAST_NAME = ? , START_DATE = ? , TITLE = ? , CUST_ID = ? WHERE OFFICER_ID ="+OFFICER_ID;
	
		preparedStatement = con.prepareStatement(query);

		
			preparedStatement.setDate(1, offie.getEND_DATE());
			preparedStatement.setString(2,offie.getFIRST_NAME());
			preparedStatement.setString(3,offie.getLAST_NAME());
			preparedStatement.setDate(4,offie.getSTART_DATE());
			preparedStatement.setString(5, offie.getTITLE());
			preparedStatement.setFloat(6, offie.getCUST_ID());
			
		int rowCount = preparedStatement.executeUpdate();
		Status	status=Status.OK;
		if(rowCount>0)
		{
			status=Status.OK;
			
			mainObj.accumulate("Status", status);
			mainObj.accumulate("Message", "Record Successfully updated!");
		}else
		{
			status = Status.NOT_MODIFIED;
			mainObj.accumulate("Status", status);
			mainObj.accumulate("Message", "Something went wrong!");
		}
		
			
			
	
	
}
	catch (SQLException e) {
	
	e.printStackTrace();
}
return null;
}



}
