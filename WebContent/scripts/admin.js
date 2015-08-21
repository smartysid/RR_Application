var modify;
(function(){
	'use strict';
	var currentableID = 0;
	$(document).ready(function(){
		
	 //alert("Page is ready :)");
	 
	         $.ajax({
	             type:'GET',
	             url: 'api/admin/getAll',
	             success : function(values){
	                 console.log(values);
	                 var table = '<tbody>';
	                 //$("tr:has(td)").remove();
	                 for(var i=0;i<values.data.length;i++)
	                 	{
	                 	  console.log(values.data[i]);
	                 	  $('#'+values.data[i].table_id).removeClass("glyphicon glyphicon-unchecked").addClass("glyphicon glyphicon-sound-dolby");
	                 	 table = '<tr><td>' + values.data[i].conf + '</td><td>' + values.data[i].table_id + '</td><td>' + values.data[i].fname + '</td><td>' + values.data[i].lname + '</td><td>' + values.data[i].email + '</td><td>' + values.data[i].contact + '</td><td><button type="button" class="btn btn-warning" value='+values.data[i].table_id+' onclick="modify('+values.data[i].table_id+')" >Modify Reservation</button></td></tr>';
	                 	$('#AdminTable').append(table);
	                 	 
	                 	}
	                
	                 //alert("Reached Here");
	             },
	             error: function(error){
	                 console.log(error);
	                 alert("Oops and error was encountered while running this");
	             }
	         });
	    });
	
	$("form").submit(function(){
		event.preventDefault();
	//$('#btnReserve').click(function(){
		console.log("Started Placing Order");
		//alert("Here in Add Booking");
		var rDetails = {
				
				table_id:  currentableID,
				fname:  $('#firstname').val(),
	            lname:  $('#lastname').val(),
	            email:  $('#email').val(),
	            size:   $('#Size').val(),
	            contact: $('#tel').val(),
	            date : $('#date').val(),
	            time: $('#time').val(),
		        request: $('#Request').val()
		};
		var confno;
		$.ajax({
			
			type: 'POST',
			url : 'api/admin/add',
			data: JSON.stringify(rDetails),
			contentType : 'application/json',
			complete: function(values)
			{
				
				
				alert("Desired Reservation has now been complete. Your Confirmaiton no is " + confno);
				$('#ModifyBooking').modal('hide');
			    //location.reload();
			},
			success : function(values)
			{
				console.log(values);
				confno  = values.data.conf;
			},
			error : function(error){
				console.log(error);
			}
			
		});

		return false;
				
	});
	
	//This is for Admin Grid Modification
    $("span").click(function(){
    	
        var id = $(this).attr('id');
        currentableID = id;
        var myclass = $(this).attr('class');
        //alert("Here" + id + " "  + myclass);
        if(myclass == "glyphicon glyphicon-unchecked")
        	{
        	  //alert("In If" + id + " "  + myclass);
        	  $('#AddBooking').modal('show');
        	  
        	  
        	}
        if(myclass == "glyphicon glyphicon-sound-dolby")
        	{
        	  //alert(" In else" + id + " "  + myclass);
        	
        	  
        	  $('#ModifyBooking').modal('show');
        	  $.ajax({
                  type:'GET',
                  //url: 'api/user/getByEmail/rajesh@shetty.com',
                  url: 'api/admin/getByID/'+id,
                  success : function(values){
                      console.log(values);
                      
                      $('#fname').val(values.data.fname);
                      $('#lname').val(values.data.lname);
                      $('#Cemail').val(values.data.email);
                      $('#Ctel').val(values.data.contact);
                      $('#CPartySize').val(values.data.size);
                      $('#CRequest').val(values.data.request);
                      $('#Cdate').val(values.data.date);
                      $('#Ctime').val(values.data.time);
                      
                      for(var i=0;i<values.data.length;i++)
                      	{
                      	  console.log(values.data[i]);
                      	}
                      
                      $('#Modify').modal('hide');
                  },
                  error: function(error){
                      console.log(error);
                      alert("We dont have any reservations with the current email ID" + error);
                  }
              });
        	  
        	}
    });
    
    
    $('#btnAdminModify').click(function(){
		console.log("Started Modifying  Order");
		//alert("Here in Modify Booking in Admin");
		var rDetails = {
				
				table_id: currentableID,
				fname:  $('#fname').val(),
	            lname:  $('#lname').val(),
	            email:  $('#Cemail').val(),
	            size:   $('#CPartySize').val(),
	            contact: $('#Ctel').val(),
	            date : $('#Cdate').val(),
	            time: $('#Ctime').val(),
		        request: $('#CRequest').val()
		};
		var confno;
		$.ajax({
			
			type: 'POST',
			url : 'api/admin/modify',
			data: JSON.stringify(rDetails),
			contentType : 'application/json',
			complete: function(values)
			{						
				window.location.replace("Home.html");
				alert("Desired Modification Complete");
			    //location.reload();
			},
			success : function(values)
			{
				console.log(values);
				//confno  = values.data.conf;
				
			},
			error : function(error){
				console.log(error);
			}
			
		});

		return false;
				
	});
    
    $('#btnAdminCancel').click(function(){
		console.log("Started Deleting  Order");
		//alert("Here in Cancel Booking in Admin");
		var rDetails = {
				
				table_id: currentableID,
				
		};
		var confno;
		$.ajax({
			
			type: 'DELETE',
			url : 'api/admin/delete',
			data: JSON.stringify(rDetails),
			contentType : 'application/json',
			complete: function(values)
			{						
				
				window.location.replace("Home.html");
				alert("Desired Modification Complete");
			    //location.reload();
			},
			success : function(values)
			{
				console.log(values);
				//confno  = values.data.conf;
				
			},
			error : function(error){
				console.log(error);
			}
			
		});

		return false;
				
	});
	
    modify = function(id)
    {
    	
            currentableID = id;
        //    var myclass = $(this).attr('class');
            //alert("Here" + id );
         
      
            	  $('#ModifyBooking').modal('show');
            	  //alert("Here" + id );
            	  $.ajax({
                      type:'GET',
                      //url: 'api/user/getByEmail/rajesh@shetty.com',
                      url: 'api/admin/getByID/'+id,
                      success : function(values){
                          console.log(values);
                          
                          $('#fname').val(values.data.fname);
                          $('#lname').val(values.data.lname);
                          $('#Cemail').val(values.data.email);
                          $('#Ctel').val(values.data.contact);
                          $('#CPartySize').val(values.data.size);
                          $('#CRequest').val(values.data.request);
                          $('#Cdate').val(values.data.date);
                          $('#Ctime').val(values.data.time);
                          
                          for(var i=0;i<values.data.length;i++)
                          	{
                          	  console.log(values.data[i]);
                          	}
                          
                          //$('#Modify').modal('hide');
                      },
                      error: function(error){
                          console.log(error);
                          alert("We dont have any reservations with the current email ID" + error);
                      }
                  });
            	  
            	//}
        
        
    };
    
   
})();

//modify();