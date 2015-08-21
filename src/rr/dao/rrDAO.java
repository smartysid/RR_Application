package rr.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import rr.exceptions.RRExceptions;
import rr.model.RRDetails;
import rr.util.DBConnection;

public class rrDAO 
{

	//Get Details of All Employees
	public List<RRDetails> getAllValues() throws RRExceptions
	{
		List<RRDetails> details = new ArrayList<RRDetails>();
		Connection con = DBConnection.connectToDB();
		PreparedStatement p = null;
		ResultSet rs = null;
				
		try 
		{
			p = (PreparedStatement) con.prepareStatement("Select * from reservations");
			rs = p.executeQuery();
			
			//Add all records to List
			while(rs.next())
			{
				RRDetails rObj = new RRDetails();
				rObj.setConf(rs.getInt("c_no"));
				rObj.setTable_id(rs.getInt("t_id"));
				rObj.setFname(rs.getString("fname"));
				rObj.setLname(rs.getString("lname"));
				rObj.setEmail(rs.getString("email"));
				rObj.setContact(rs.getString("contactnumber"));
				rObj.setSize(rs.getInt("size"));
				rObj.setDate(rs.getString("date"));
				rObj.setTime(rs.getString("time"));
				rObj.setRequest(rs.getString("request"));
				
				details.add(rObj);
			}
			
			
		} catch (SQLException e) 
		{	//e.printStackTrace();
		    throw new RRExceptions("OOps we have an issue " + e.getMessage() + e.getCause());
		}
		finally
		{
			DBConnection.terminate(p, rs, con);
		}
		
		
	
		return details;
	}
	
	
	public RRDetails getValueByEmail(String sEmailID) throws RRExceptions
	{
		List<RRDetails> details = new ArrayList<RRDetails>();
		Connection con = DBConnection.connectToDB();
		PreparedStatement p = null;
		ResultSet rs = null;
				
		try 
		{
			p = (PreparedStatement) con.prepareStatement("Select * from reservations where email=?");
			p.setString(1, sEmailID);
			rs = p.executeQuery();
			
			//If there is record Present
			if(rs.next())
			{
				RRDetails rObj = new RRDetails();
				
				rObj.setTable_id(rs.getInt("t_id"));
				rObj.setFname(rs.getString("fname"));
				rObj.setLname(rs.getString("lname"));
				rObj.setEmail(rs.getString("email"));
				rObj.setContact(rs.getString("contactnumber"));
				rObj.setSize(rs.getInt("size"));
				rObj.setDate(rs.getString("date"));
				rObj.setTime(rs.getString("time"));
				rObj.setRequest(rs.getString("request"));
				
				//details.add(rObj);
				return rObj;
			}
			else
			{
				throw new RRExceptions("No reservation was made with Email id ");
			}
			
		} catch (SQLException e) 
		{	//e.printStackTrace();
		    throw new RRExceptions("OOps we have an issue " + e.getMessage() + e.getCause());
		}
		finally
		{
			DBConnection.terminate(p, rs, con);
		}
		
		
	
		
	}


	public RRDetails getValueByTable(int tid) throws RRExceptions
	{
		List<RRDetails> details = new ArrayList<RRDetails>();
		Connection con = DBConnection.connectToDB();
		PreparedStatement p = null;
		ResultSet rs = null;
				
		try 
		{
			p = (PreparedStatement) con.prepareStatement("Select * from reservations where t_id=?");
			p.setInt(1, tid);
			rs = p.executeQuery();
			
			//If there is record Present
			if(rs.next())
			{
				RRDetails rObj = new RRDetails();
				
				rObj.setTable_id(rs.getInt("t_id"));
				rObj.setFname(rs.getString("fname"));
				rObj.setLname(rs.getString("lname"));
				rObj.setEmail(rs.getString("email"));
				rObj.setContact(rs.getString("contactnumber"));
				rObj.setSize(rs.getInt("size"));
				rObj.setDate(rs.getString("date"));
				rObj.setTime(rs.getString("time"));
				rObj.setRequest(rs.getString("request"));
				
				//details.add(rObj);
				return rObj;
			}
			else
			{
				throw new RRExceptions("No reservation was made with Email id ");
			}
			
		} catch (SQLException e) 
		{	//e.printStackTrace();
		    throw new RRExceptions("OOps we have an issue " + e.getMessage() + e.getCause());
		}
		finally
		{
			DBConnection.terminate(p, rs, con);
		}
		
		
	}


	public RRDetails makeReservation(RRDetails rObj) throws RRExceptions
	{
		
		Connection con = DBConnection.connectToDB();
		PreparedStatement p = null;
		ResultSet rs = null;
				
		try 
		{
			p = (PreparedStatement) con.prepareStatement("INSERT INTO reservations (t_id,fname,lname,email,contactnumber,size,date,time,request) VALUES (?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			 
			p.setInt(1, rObj.getTable_id());
			p.setString(2, rObj.getFname());
			p.setString(3, rObj.getLname());
			p.setString(4, rObj.getEmail());
			p.setString(5, rObj.getContact());
			p.setInt(6, rObj.getSize());
			p.setString(7,rObj.getDate());
			p.setString(8,rObj.getTime());
			p.setString(9,rObj.getRequest());
			p.executeUpdate();
			
			rs = p.getGeneratedKeys();
			if(rs.next())
			{
				System.out.println("Keys Generated were returned" + rs.getInt(1));
				rObj.setConf(rs.getInt(1));
			}
			
		} catch (SQLException e) 
		{	//e.printStackTrace();
		    throw new RRExceptions("OOps we have an issue " + e.getMessage() + e.getCause());
		}
		finally
		{
			DBConnection.terminate(p, rs, con);
		}
		
		return rObj;
	}
	
	public RRDetails makeUserReservation(RRDetails rObj) throws RRExceptions
	{
		
		Connection con = DBConnection.connectToDB();
		PreparedStatement p = null;
		ResultSet rs = null;
		int tableid;
				
		try 
		{
			
			//Get the max tableID.(Assumption Tables are booked in sequence)
			p = (PreparedStatement) con.prepareStatement("select max(t_id) from restdb.reservations;");
			rs = p.executeQuery();
			if(rs.next())
			{
				int tid = rs.getInt(1);
				tid++;
				rObj.setTable_id(tid);
			}
			
			
			p = (PreparedStatement) con.prepareStatement("INSERT INTO reservations (t_id,fname,lname,email,contactnumber,size,date,time,request) VALUES (?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			p.setInt(1, rObj.getTable_id());
			p.setString(2, rObj.getFname());
			p.setString(3, rObj.getLname());
			p.setString(4, rObj.getEmail());
			p.setString(5, rObj.getContact());
			p.setInt(6, rObj.getSize());
			p.setString(7,rObj.getDate());
			p.setString(8,rObj.getTime());
			p.setString(9,rObj.getRequest());
			p.executeUpdate();
			
			rs = p.getGeneratedKeys();
			if(rs.next())
			{
				System.out.println("Keys Generated were returned" + rs.getInt(1));
				rObj.setConf(rs.getInt(1));
			}
			
		} catch (SQLException e) 
		{	//e.printStackTrace();
		    throw new RRExceptions("OOps we have an issue " + e.getMessage() + e.getCause());
		}
		finally
		{
			DBConnection.terminate(p, rs, con);
		}
		
		return rObj;
	}


	public RRDetails UpdateReservation(RRDetails rObj) throws RRExceptions
	{
		Connection con = DBConnection.connectToDB();
		PreparedStatement p = null;
		ResultSet rs = null;
		int tableid;
				
		try 
		{
			
			
			//p = (PreparedStatement) con.prepareStatement("INSERT INTO reservations (t_id,fname,lname,email,contactnumber,size,date,time,request) VALUES (?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			p = (PreparedStatement) con.prepareStatement("UPDATE reservations SET fname = ?, lname = ?, email = ?, contactnumber = ?,size = ?, date = ?, time = ?, request = ?  WHERE t_id = ?") ;
			//p.setInt(1, rObj.getTable_id());
			p.setString(1, rObj.getFname());
			p.setString(2, rObj.getLname());
			p.setString(3, rObj.getEmail());
			p.setString(4, rObj.getContact());
			p.setInt(5, rObj.getSize());
			p.setString(6,rObj.getDate());
			p.setString(7,rObj.getTime());
			p.setString(8,rObj.getRequest());
			p.setInt(9, rObj.getTable_id());
			p.executeUpdate();
			
			
			
		} catch (SQLException e) 
		{	//e.printStackTrace();
		    throw new RRExceptions("OOps we have an issue " + e.getMessage() + e.getCause());
		}
		finally
		{
			DBConnection.terminate(p, rs, con);
		}
		
		return rObj;
	}


	public RRDetails DeleteReservation(RRDetails rObj) throws RRExceptions 
	{
		Connection con = DBConnection.connectToDB();
		PreparedStatement p = null;
		ResultSet rs = null;
		
				
		try 
		{
			
			p = (PreparedStatement) con.prepareStatement("DELETE FROM reservations WHERE t_id = ?") ;
			p.setInt(1, rObj.getTable_id());
			p.executeUpdate();
			
		} catch (SQLException e) 
		{	//e.printStackTrace();
		    throw new RRExceptions("OOps we have an issue " + e.getMessage() + e.getCause());
		}
		finally
		{
			DBConnection.terminate(p, rs, con);
		}
		
		return rObj;
		
	}
}
