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
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.Response;
	import javax.ws.rs.core.Response.Status;
	import org.glassfish.hk2.api.Self;
	import org.json.JSONArray;
	import org.json.JSONObject;

	import com.midterm.mainclass;
	import com.midterm.Mysqlconnection;
	import com.midterm.account;
	import com.midterm.employee;

	@Path("/abc")
	public class mainclass {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		PreparedStatement preparedStatement = null;

		JSONObject mainObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject childObj = new JSONObject();
		
	public static void main (String a[]) {
			
			mainclass myObj = new mainclass();
		    
		}

		@GET
		@Path("/Emp")
		@Produces(MediaType.APPLICATION_JSON)
		
		public Response getEmployee() {
			
			Mysqlconnection connection = new Mysqlconnection();

			con = connection.getConnection();
			
			try {
				
				stmt = con.createStatement();

				rs = stmt.executeQuery("Select * from employee");
				

				while (rs.next()) {
					childObj = new JSONObject();

					childObj.accumulate("Employee Number", rs.getString("EMP_ID"));
					childObj.accumulate("Last Name", rs.getString("LAST_NAME"));
					childObj.accumulate("First Name", rs.getString("FIRST_NAME"));
					childObj.accumulate("Start Date", rs.getString("START_DATE"));
					childObj.accumulate("Title", rs.getString("TITLE"));

					jsonArray.put(childObj);
				}

				mainObj.put("Employee", jsonArray);
			} catch (SQLException e) {
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
		@Path("/createEmp")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response createEmployee(employee emp)
		{
			Mysqlconnection connection = new Mysqlconnection();
			
			con = connection.getConnection();
			
		try
		{
			
			String query = "INSERT INTO `midterm`.`employee`(`EMP_ID`,`END_DATE`,`FIRST_NAME`,`LAST_NAME`,`START_DATE`,`ASSIGNED_BRANCH_ID`,`DEPT_ID`,`SUPERIOR_EMP_ID`)"
					+ "VALUES(?,?,?,?,?,?,?,?)";
			
			
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setInt(1, emp.getEMP_ID());
			preparedStatement.setDate(2, emp.getEND_DATE());
			preparedStatement.setString(3,emp.getFIRST_NAME());
			preparedStatement.setString(4,emp.getLAST_NAME());
			preparedStatement.setDate(5,emp.getSTART_DATE());
			preparedStatement.setInt(6, emp.getASSIGNED_BRANCH_ID());
			preparedStatement.setInt(7, emp.getDEPT_ID());
			preparedStatement.setInt(8, emp.getSUPERIOR_EMP_ID());
			
			int rowCount = preparedStatement.executeUpdate();
			
			if(rowCount>0)
			{
				System.out.println("Record insert! : "+rowCount);
				
				mainObj.accumulate("Status", 201);
				mainObj.accumulate("Message", "Record add!");
			}else
			{
				mainObj.accumulate("Status", 500);
				mainObj.accumulate("Message", "Something wrong");
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
		@Path("/account/{ACCOUNT_ID}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getAccount(@PathParam("ACCOUNT_ID")String ACCOUNT_ID)
		{

			Mysqlconnection connection = new Mysqlconnection();
			
			con = connection.getConnection();
			
		try
		{
			stmt=con.createStatement();
			String query =("select * from account where account_id="+ACCOUNT_ID);
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				mainObj.accumulate("ACCOUNT_ID",rs.getString("ACCOUNT_ID"));
				mainObj.accumulate("AVAIL_BALANCE",rs.getString("AVAIL_BALANCE"));
				mainObj.accumulate("CLOSE_DATE",rs.getString("CLOSE_DATE"));
				mainObj.accumulate("LAST_ACTIVITY_DATE",rs.getString("LAST_ACTIVITY_DATE"));
				mainObj.accumulate("OPEN_DATE",rs.getString("OPEN_DATE"));
				mainObj.accumulate("PENDING_BALANCE",rs.getString("PENDING_BALANCE"));
				mainObj.accumulate("STATUS",rs.getString("STATUS"));
				mainObj.accumulate("CUST_ID",rs.getString("CUST_ID"));
				mainObj.accumulate("OPEN_BRANCH_ID",rs.getString("OPEN_BRANCH_ID"));
				mainObj.accumulate("OPEN_EMP_ID",rs.getString("OPEN_EMP_ID"));
				mainObj.accumulate("PRODUCT_CD",rs.getString("PRODUCT_CD"));
				
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
	}
