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

import com.midterm.neetu;
import com.midterm.Mysqlconnection;
import com.midterm.individual;
import com.midterm.Mysqlconnection;
import com.midterm.Mysqlconnection;
import com.midterm.Mysqlconnection;

@Path("/awo")
public class neetu {

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
@Path("/individual")
@Produces(MediaType.APPLICATION_JSON)

public Response individual() {
	
	
	
	Mysqlconnection connection = new Mysqlconnection();

	con = connection.getConnection();
	
	try {
		
		stmt = con.createStatement();

		rs = stmt.executeQuery("Select * from individual");
		

		while (rs.next()) {
			childObj = new JSONObject();

			childObj.accumulate("Birth_Date", rs.getString("BIRTH_DATE"));
			childObj.accumulate("First_Name", rs.getString("FIRST_NAME"));
			childObj.accumulate("Last_Name", rs.getString("LAST_NAME"));
			childObj.accumulate("Cust_Id", rs.getString("CUST_ID"));
			
		
			jsonArray.put(childObj);
		}

		mainObj.put("individual", jsonArray);
	} catch (SQLException e) {
		
		System.out.println("SQL Exception : " + e.getMessage());
	} finally {
		try {
			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			
			System.out.println(" Block SQL Exception : " + e.getMessage());
		}
	}
	


	return Response.status(200).entity(mainObj.toString()).build();
}

@POST
@Path("/createindi")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response individual(individual ind)
{
	Mysqlconnection connection = new Mysqlconnection();
	
	con = connection.getConnection();
	
try
{
	
	
	
	
	String query = "INSERT INTO `midterm`.`individual`(`BIRTH_DATE`,`FIRST_NAME`,`LAST_NAME`,`CUST_ID`)"
			+ "VALUES(?,?,?,?)";
	
	
	preparedStatement = con.prepareStatement(query);

	preparedStatement.setDate(1, ind.getBIRTH_DATE());
	preparedStatement.setString(2, ind.getFIRST_NAME());
	preparedStatement.setString(3,ind.getLAST_NAME());
	preparedStatement.setInt(4,ind.getCUST_ID());
	
	
	
	
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
@Path("/delete/{CUST_ID}")
public Response deleteindividual(@PathParam("CUST_ID")String CUST_ID) {
	Mysqlconnection connection= new Mysqlconnection();
	
	con = connection.getConnection();
	Status status =Status.OK;
	
	try {
		
		String query ="DELETE FROM individual WHERE CUST_ID="+CUST_ID;
		
		stmt=con.createStatement();
		
		int rowCount = stmt.executeUpdate(query);
		
		if(rowCount>0) {
			//status=Status.OK;
			mainObj.accumulate("Status", status);
			mainObj.accumulate("Message", " data  deleted!");
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
@Path("/updateindi/{CUST_ID}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updateindividual(@PathParam("CUST_ID") int CUST_ID,individual ind)
{

	Mysqlconnection connection = new Mysqlconnection();
	
	con = connection.getConnection();
	
	try
	{
		String query = "UPDATE midterm.individual SET BIRTH_DATE = ? , FIRST_NAME = ? , LAST_NAME = ? WHERE CUST_ID ="+CUST_ID;
	
		preparedStatement = con.prepareStatement(query);

		
			preparedStatement.setDate(1, ind.getBIRTH_DATE());
			preparedStatement.setString(2,ind.getFIRST_NAME());
			preparedStatement.setString(3,ind.getLAST_NAME());
			
			
			
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
