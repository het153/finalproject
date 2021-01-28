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

import com.midterm.tanvi;
import com.midterm.Mysqlconnection;
import com.midterm.branch;
import com.midterm.Mysqlconnection;
import com.midterm.Mysqlconnection;
import com.midterm.Mysqlconnection;

@Path("/awo")
public class tanvi {


	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	PreparedStatement preparedStatement = null;

	JSONObject mainObj = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	JSONObject childObj = new JSONObject();
	
public static void main (String a[]) {
		
		New myObj = new New();
	   
		
	}
@GET
@Path("/branch")
@Produces(MediaType.APPLICATION_JSON)

public Response branch() {
	
	
	
	Mysqlconnection connection = new Mysqlconnection();

	con = connection.getConnection();
	
	try {
		
		stmt = con.createStatement();

		rs = stmt.executeQuery("Select * from branch");
		

		while (rs.next()) {
			childObj = new JSONObject();

			childObj.accumulate("Branch Id", rs.getString("BRANCH_ID"));
			childObj.accumulate("Address", rs.getString("ADDRESS"));
			childObj.accumulate("city", rs.getString("CITY"));
			childObj.accumulate("Name", rs.getString("NAME"));
			childObj.accumulate("State", rs.getString("STATE"));
			childObj.accumulate("Zipcode", rs.getString("ZIP_CODE"));
		
			jsonArray.put(childObj);
		}

		mainObj.put("branch", jsonArray);
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
@Path("/createbranch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response branch(branch bran)
{
	Mysqlconnection connection = new Mysqlconnection();
	
	con = connection.getConnection();
	
try
{
	
	
	
	
	String query = "INSERT INTO `midterm`.`branch`(`BRANCH_ID`,`ADDRESS`,`CITY`,`NAME`,`STATE`,`ZIP_CODE`)"
			+ "VALUES(?,?,?,?,?,?)";
	
	
	preparedStatement = con.prepareStatement(query);

	preparedStatement.setInt(1, bran.getBRANCH_ID());
	preparedStatement.setString(2, bran.getADDRESS());
	preparedStatement.setString(3,bran.getCITY());
	preparedStatement.setString(4,bran.getNAME());
	preparedStatement.setString(5,bran.getSTATE());
	preparedStatement.setInt(6, bran.getZIP_CODE());
	
	
	
	int rowCount = preparedStatement.executeUpdate();
	
	if(rowCount>0)
	{
		System.out.println("Record inserted! : "+rowCount);
		
		mainObj.accumulate("Status", 201);
		mainObj.accumulate("Message", "Record added!");
	}else
	{
		mainObj.accumulate("Status", 500);
		mainObj.accumulate("Message", "Something wrong!");
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
@DELETE
@Path("/dele/{BRANCH_ID}")
public Response deletebranch(@PathParam("BRANCH_ID")String BRANCH_ID) {
	Mysqlconnection connection= new Mysqlconnection();
	
	con = connection.getConnection();
	Status status =Status.OK;
	
	try {
		
		String query ="DELETE FROM branch WHERE BRANCH_ID="+BRANCH_ID;
		
		stmt=con.createStatement();
		
		int rowCount = stmt.executeUpdate(query);
		
		if(rowCount>0) {
			status=Status.OK;
			mainObj.accumulate("Status", status);
			mainObj.accumulate("Message", " data  sucessfully deleted!");
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
@Path("/updatebranch/{BRANCH_ID}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updatebranch(@PathParam("BRANCH_ID") int BRANCH_ID,branch bran)
{

	Mysqlconnection connection = new Mysqlconnection();
	
	con = connection.getConnection();
	
	try
	{
		String query = "UPDATE midterm.branch SET ADDRESS = ? , CITY = ? , NAME = ? , STATE = ? , ZIP_CODE = ? WHERE BRANCH_ID ="+BRANCH_ID;
	
		preparedStatement = con.prepareStatement(query);

		
			preparedStatement.setString(1, bran.getADDRESS());
			preparedStatement.setString(2,bran.getCITY());
			preparedStatement.setString(3,bran.getNAME());
			preparedStatement.setString(4,bran.getSTATE());
			preparedStatement.setInt(5, bran.getZIP_CODE());
			
			
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
