	
		$(document).ready( function()
		
		{

    //console.log("contenido",nombre);




   $("button#boton1").on(
     "click",

     function(event)
     {
        var correo = $("#email2").val();
        var   password = $("#contra").val();

        var testEmail =  /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;

        console.log("contenido",correo);
       

        console.log("contenido", password);
         

        if(  correo  !="" && password!="" && testEmail.test(correo))
        
        {       	
        	
        
     
        
        var logueo = {
                
                // numero:$("nusuario").length+1,
                    ccorreo :correo,
                    ppass :password,
                   // ppassword:password2
                    
                 };
                
            ///  enviar  el  registro  al srvlet conexin y desde ahi guardarlo en la base de datos 
        $.ajax({
            url: "http://localhost:8080/manzana/Loguear", //Aquí la ruta exacta de su servlet
            type: "POST",
            //Qué espero recibir de la ruta
            contentType: "application/json",
            //Que tipo de datos te voy a enviar
            dataType: "json",
            data: JSON.stringify(logueo),

            //Servlet existe y me devolvió un JSON
            success: function (data, textStatus, jqXHR) {
                console.log("Si el acceso al servlet fue correcto");
                console.log(data.control);
                
                
                if(data.control==0)
                	
                	
                	{
                	 
                	
                	  alert('upring suario  logueado  correctamente ');
                	  
                	  window.location.replace("http://localhost:8080/manzana/Loguear/dashboardNuevo.html");
                	
                	}
                
                else
                	
                	{
                	 alert("   el  correo  es invalido o la contraseña  estan escritas incorrectamente o   el usuario  no esta registrado en el sistema ");
                	    
                	}

                
                

            },
            error: function (jqXHR, textStatus, errorThrown) {
              console.log(jqXHR);
               console.log(textStatus);
              console.log(errorThrown);
            }
        });

        //Si la publicacion está vacía
    } else {
        alert("   el  correo  es invalido o la contraseña  estan escritas incorrectamente o   el usuario  no esta registrado en el sistema ");
    }



        

      

     });



//  funcoj  para    registara al usuario en la base de datos 
$("button#boton2").on(//  selecciona el boton al hacer click 
    "click",
    function(event)

    {
    	//console.clear();
    	console.log("entro");
        var nombre2 =  $('#nombre').val();
        var correo2 = $("#correo2").val();
        var   password2 = $("input#password").val();

        var testEmail =  /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;

        if(nombre2!=""  &&  correo2  !="" && password2!="" && testEmail.test(correo2))
        
        {
        
        
        var nusuario = {
            
            // numero:$("nusuario").length+1,
                nnombre :nombre2,
                ccorreo :correo2,
                ppassword :password2
                
                 };
        
     
            
    	
        ///  enviar  el  registro  al srvlet conexin y desde ahi guardarlo en la base de datos 
        $.ajax({
            url: "http://localhost:8080/manzana/conexion", //Aquí la ruta exacta de su servlet
            type: "POST",
            //Qué espero recibir de la ruta
            contentType: "application/json",
            //Que tipo de datos te voy a enviar
            dataType: "json",
           data : JSON.stringify(nusuario),
           // data: JSON.stringify(nuevaPublicacion),
            //Servlet existe y me devolvió un JSON
            
        
        
            success: function (data, textStatus, jqXHR) {
            	
            	
            	  
               console.log("Si el acceso al servlet fue correcto");
               console.log("todo esta  correcto");
               console.log(JSON.stringify(data));
              if(data.control==1)
            	  
            	  {
            	  
            	  alert("se  regitro el usuario  correctamente ");
            	  
            	  }
              else
            	  {
            	  alert("no se  pudo  introducir el usuario  porque   el correo   ya existe ");
            	  
            	  }
               
               
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log("eroror");
               console.log(errorThrown);
               
               //console.log("Si el acceso al servlet fue correcto");
            }
        

        });
        
        
        }
        else{

            alert('es necesario  llenar alguno  de  los campos  solicitados  o   incluir el  @  en el correo'); 
        }

 
        
        
    });


  

	 

});