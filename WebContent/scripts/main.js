
(function(){
  'use strict';

    var currentTableID = 0;
    
    $("#btnLogin").click(function(){
    	var user = $("#txtuser").val();
    	var pass = $("#txtpass").val();
    	if(user == "root" && pass == "root")
    		{
    		window.location.replace("admin.html");
    		}
    	else
    		{
    		 alert("Incorrect Username or Password");
    		 window.location.replace("Home.html");
    		}
    	
    });
    //This is for User Request Modification
    $("#btnModify").click(function(){

    	var email = $("#Memail").val();
    	//alert(email);
        $.ajax({
            type:'GET',
            url: 'api/user/getByEmail/'+email,
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
    });
    
    
    $("form").submit(function(){
		event.preventDefault();
	//$('#btnReserve').click(function(){
		console.log("Started Placing Order");
		//alert("Here in Add Booking");
		var rDetails = {
				
				table_id: 0,
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
			url : 'api/user/add',
			data: JSON.stringify(rDetails),
			contentType : 'application/json',
			complete: function(values)
			{						
				$('#AddBooking').modal('hide');
				window.location.replace("sucess.html?conf="+confno);
			    //location.reload();
			},
			success : function(values)
			{
				console.log(values);
				confno  = values.data.conf;
				alert("Desired Reservation has now been complete. Your Confirmaiton no is " + confno);
			},
			error : function(error){
				console.log(error);
			}
			
		});

		return false;
				
	});
    
    

})();