package rr.api.controller;

import javax.ws.rs.*;

import rr.app.RRresponse;
import rr.dao.rrDAO;
import rr.exceptions.RRExceptions;
import rr.model.RRDetails;

@Path("/user")
public class RRUserController
{
	

	@GET 
	@Path("/getMy")
	public String getMyReservation()
	{
		return "Getting Email By ID";
	}
	
	@GET
	@Path("/makerr")
	public String makereservation()
	{	
		return "Adding a Value to the database";
	}
	
	@GET
	@Path("/cancelrr")
	public String cancelreservation()
	{	
		return "Cancelling my Reservation";
	}
	
	@GET
	@Path("/getByEmail/{email}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	//public Object getAll(){
    public RRresponse getParticularReservation(@PathParam("email") String sEmail)
	{		
	//public List<RRDetails> getAll(){
		RRresponse rResp = new RRresponse();
		try 
		{
	     
	     
		rrDAO obj = new rrDAO();
		RRDetails RRrecords = null;
		
		RRrecords = obj.getValueByEmail(sEmail);
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
	@Path("/add")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public RRresponse add(RRDetails rObj)
	{	
		
		System.out.println("Here In User");
		
		
		RRresponse rResp = new RRresponse();
		try 
		{
	     
	     
		rrDAO obj = new rrDAO();
		RRDetails RRrecords = null;
		
		RRrecords = obj.makeUserReservation(rObj);
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
