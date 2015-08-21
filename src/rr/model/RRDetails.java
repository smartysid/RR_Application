package rr.model;

import java.util.Date;

//POJO class that contains information of one record.
public class RRDetails 
{
   private int conf;   
   private int table_id;
   private String fname;
   private String lname;
   private String email;
   private int size;
   private String contact;
   private String date;
   private String time;
   private String request;
   
   
   public int getConf() {
		return conf;
	}
	public void setConf(int conf) {
		this.conf = conf;
	}
public int getTable_id() {
	return table_id;
}
public void setTable_id(int table_id) {
	this.table_id = table_id;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getRequest() {
	return request;
}
public void setRequest(String request) {
	this.request = request;
}
   
   
}
