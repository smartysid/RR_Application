/**
 * Created by Rajesh on 5/31/2015.
 */

(function(){
  'use strict'

    $('#list_modify').click(function(){

        $.ajax({
            type:'GET',
            url: 'api/admin/geAll',
            success : function(data){
                console.log(data);
            },
            error: function(error){
                console.log(error);
            }


        })
    })






})();