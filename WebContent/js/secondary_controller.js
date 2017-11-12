$(document).ready( function(){
    //Esta plantilla le indica a JavaScript que realice un trabajo una vez cargada la página
    //A partir de este punto se pueden crear funciones para realizar control lógico sobre el HTML
     //Clear console
     console.clear();
     console.log("Consola limpia...");
     var myJSON = {"mensaje" : "Hola usuario"};
     
     $('button#btn-test-1').on("click", function (event) {
         console.log("Botón!!!");
         var cadena = JSON.stringify(myJSON);
         //alert("Botón!! "+cadena);
         // Dada la función matemática y = x^2, calcule los puntos en el plano (pero sin plano) desde -1 hasta 10

         var resultado = 0;
         var objetoJSON = { 
                 			x: 0, 
                 			y :0
              			  };
              
		var arregloJSON = [];
		var Punto = {
					 "x":0,
					 "y":0
					};
		var contenedor = "{posicion:[";

		for(var x = -1; x <= 10; x++)
		{
		    resultado = Math.pow(x, 2);
		    console.log("X: "+x+", Y: "+resultado);
		    Punto.x = x;
		    Punto.y = resultado;
		    if(x != 10)
		    {
		       contenedor += JSON.stringify(Punto) + ',';
		    }
		    else
		    {
		        contenedor += JSON.stringify(Punto);    
		    }
		    
		    console.log(JSON.stringify(Punto));
		}
		contenedor +="]}"
		arregloJSON = [  contenedor  ];
		for(var x = 0; x < arregloJSON.length; x++ )
		{
			console.log(JSON.stringify(arregloJSON[x].x)+" : "+JSON.stringify(arregloJSON[x].y));
		}
		
		
		$.ajax({
            url: "http://localhost:8080/AJAXMisc/maneja-puntos", 
            type: "POST",
            //Qué espero recibir de la ruta
            contentType: "application/json",
            //Que tipo de datos te voy a enviar
            dataType: "json",
            data: contenedor,

            //Servlet existe y me devolvió un JSON
            success: function(data, textStatus, jqXHR) {
                console.log("Si el acceso al servlet fue correcto");
                console.log(data.mensaje); 
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
    	});



     });

     
});