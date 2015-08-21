
(function(){
  'use strict';

    var currentTableID = 0;
    
    //This is for User Request Modification
    $("#btnModify").click(function(){

    	var email = $("#inputid").val();
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
                currentTableID = values.data.table_id;
                
                
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
    
    
    //$("form").submit(function(){
		//event.preventDefault();
	$('#btnConfirmModify').click(function(){
		console.log("Started Modifying  Order");
		//alert("Here in Modify Booking");
		var rDetails = {
				
				table_id: currentTableID,
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
    
    

})();