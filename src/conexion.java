

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.mysql.jdbc.Statement;

import classes.JSONPost;

/**
 * Servlet implementation class conexion
 */
@WebServlet("/conexion")
public class conexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public conexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//response.setContentType("application/json");
		//Construir el mensaje por defecto, por si falla...
		response.setContentType("application/json");

        String jsonResult = "{\"status\":\"error\"}";
        
        
		
	
        
       System.out.println(jsonResult);
        
        Connection con = conectar();
        
        //System.out.println("Hola");
        
        if (con != null) {
            
            //System.out.println("Estoy conectado");
            
            //Send request reader and get
        	 JsonObject obtencion = JSONPost.getJsonObject( //obtiene objetos
                     request.getReader() //Aquí está nuestro json
            );
            
            //int     id        = jo.get("id").getAsInt();
            String  nombre= obtencion.get("nnombre").getAsString();
            String  correo = obtencion.get("ccorreo").getAsString();
            String  password = obtencion.get("ppassword").getAsString();
            
            //System.out.println(id);
            //System.out.println(contenido);
            
            String query = "INSERT INTO usuarios(nombre,correo,contrasena) " + 
                           "VALUES('" + nombre+ "',  '" + correo + "', '" + password + "')";
            
            System.out.println(query);
            
            
            
            try {
                
                //Consulta
                Statement insercion = (Statement) con.createStatement();
                //Insercion
                insercion.executeUpdate(query);//  esta linea ejecuta   el query en la base de datos 
                
              
                String mensaje="se  introdujo correctamnete el  usuario";
                con.close();
                
                //json que sera  devuelto  a javascript
                
               

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        
        

        //Escribir el JSON
        try (PrintWriter out = response.getWriter()) {
            
                 out.print(jsonResult);
            
            response.setContentType("application/json");
            
        }
	}

	
	
	///  se conecta  a la base de  datos    de manzana  e inserta  los datos   del  registro
	public Connection conectar()  
    {
        Connection conn1 = null;
        try{
           

        	
        	System.out.println("ya entro");
        	
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            String stringConnection = "jdbc:mysql://localhost:3306/manzana?useSSL=true";
            conn1 = DriverManager.getConnection(stringConnection,"root","100fuegos");
            
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn1;
    }
	
	
}
