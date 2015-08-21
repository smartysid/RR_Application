package rr.api.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.ws.rs.*;

import rr.app.RRresponse;
import rr.dao.rrDAO;
import rr.exceptions.RRExceptions;
import rr.model.RRDetails;


@Path("/admin")
public class RRAdminController {
	
	@GET
	@Path("/getAll")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	//public Object getAll(){
    public RRresponse getAll()
	{		
	//public List<RRDetails> getAll(){
		RRresponse rResp = new RRresponse();
		try 
		{
	     
	     
		rrDAO obj = new rrDAO();
		List<RRDetails> RRrecords = null;
		
		RRrecords = obj.getAllValues();
		rResp.setStatus("success");
		rResp.setMessage("null");
		rResp.setData(RRrecords);
		
		//return RRrecords;
		return rResp;
		//return  "Details of ALL users";
		} catch (RRExceptions e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			rResp.setStatus("error");
			rResp.setMessage(e.getMessage());
			rResp.setData(null);
			return rResp;
			//return e.getMessage();
		}
			
	}
	
	@GET
	@Path("/getByID/{tableid}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	//public Object getAll(){
    public RRresponse getParticularReservation(@PathParam("tableid") int tid)
	{		
	//public List<RRDetails> getAll(){
		RRresponse rResp = new RRresponse();
		try 
		{
	     
	     
		rrDAO obj = new rrDAO();
		RRDetails RRrecords = null;
		
		RRrecords = obj.getValueByTable(tid);
		rResp.setStatus("success");
		rResp.setMessage("null");
		rResp.setData(RRrecords);
		
		//return RRrecords;
		return rResp;
		//return  "Details of ALL users";
		} catch (RRExceptions e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			rResp.setStatus("error");
			rResp.setMessage(e.getMessage());
			rResp.setData(null);
			return rResp;
			//return e.getMessage();
		}
			
	}
	
	
	
	@POST
	@Path("/modify")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)	
	public RRresponse modifyrr(RRDetails rObj)
	{
        System.out.println("Handling Modify Request From Admin");
		
		RRresponse rResp = new RRresponse();
		try 
		{
	     
	     
		rrDAO obj = new rrDAO();
		RRDetails RRrecords = null;
		
		RRrecords = obj.UpdateReservation(rObj);
		rResp.setStatus("success");
		rResp.setMessage("null");
		rResp.setData(RRrecords);
		
		//return RRrecords;
		return rResp;
		//return  "Details of ALL users";
		} catch (RRExceptions e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			rResp.setStatus("error");
			rResp.setMessage(e.getMessage());
			rResp.setData(null);
			return rResp;
			//return e.getMessage();
		}
			
		
		//return  "Add a reservation by Admin";		
			
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)	
	public RRresponse delete(RRDetails rObj)
	{
        System.out.println("Handling Delete Request From Admin");
		
		RRresponse rResp = new RRresponse();
		try 
		{
	     
	     
		rrDAO obj = new rrDAO();
		RRDetails RRrecords = null;
		
		RRrecords = obj.DeleteReservation(rObj);
		rResp.setStatus("success");
		rResp.setMessage("null");
		rResp.setData(RRrecords);
		
		//return RRrecords;
		return rResp;
		//return  "Details of ALL users";
		} catch (RRExceptions e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			rResp.setStatus("error");
			rResp.setMessage(e.getMessage());
			rResp.setData(null);
			return rResp;
			//return e.getMessage();
		}
			
		
		//return  "Add a reservation by Admin";		
			
	}
	
	
	
	@POST
	@Path("/add")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public RRresponse add(RRDetails rObj)
	{	
		
		System.out.println("Handling Add Request From Admin");
		
		
		RRresponse rResp = new RRresponse();
		try 
		{
	     
	     
		rrDAO obj = new rrDAO();
		RRDetails RRrecords = null;
		
		RRrecords = obj.makeReservation(rObj);
		rResp.setStatus("success");
		rResp.setMessage("null");
		rResp.setData(RRrecords);
		
		//return RRrecords;
		return rResp;
		//return  "Details of ALL users";
		} catch (RRExceptions e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			rResp.setStatus("error");
			rResp.setMessage(e.getMessage());
			rResp.setData(null);
			return rResp;
			//return e.getMessage();
		}
			
		
		//return  "Add a reservation by Admin";		
			
		
	}
	

}
