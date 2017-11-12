

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
 * Servlet implementation class loguearusuario
 */
@WebServlet("/Loguear")
public class Loguear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loguear() {
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
		
	//doGet(request, response);
		
		
		response.setContentType("application/json");

        String jsonResult = "{\"status\":\"error\"}";
        
      System.out.println(jsonResult);
        
        Connection con = conectar();
        
        //System.out.println("Hola");
        
        if (con != null) {
            
            //System.out.println("Estoy conectado");
            
            //Send request reader and get
            JsonObject obtencion = JSONPost.getJsonObject( //obtiene un objeto json
                request.getReader() //Aquí está nuestro json
            );
            
            //int     id        = jo.get("id").getAsInt();
            String  correo = obtencion.get("ccorreo").getAsString();//pasa el objeto  json a   un  string 
            String  password = obtencion.get("ppass").getAsString();
           // String  password = obtencion.get("ppassword").getAsString();
            
           System.out.println("correoobtenido  "+correo);
            System.out.println("password obtenidos es"+password);
      
            
            
            
            try {
                
                //Consulta
                Statement insercion = (Statement) con.createStatement();
                
                String result = "SELECT * FROM usuarios ORDER BY id_usuario ASC  ";
                
               // java.sql.ResultSet rs   =  stmt.executeQuery(queryId);
                ResultSet   bd  =  insercion.executeQuery(result);
                
               // int     Idusuario      = 0;
                //String  uusuario = "";
            
                String  correobd= "";
                
                
                String  passwordbd = "";
                //int     rsControl = 0;
                
                while(bd.next()) {
                 //  Idusuario     = bd.getInt("id_usuario");
                   // uusuario = bd.getString("uusuario");
                    correobd = bd.getString("correo");
                    passwordbd =bd.getString("contrasena");
                    
                    System.out.println("correobd"+correobd);
                    System.out.println("password"+passwordbd);
              
                    if(correo.contentEquals(correobd )&& password.contentEquals(passwordbd))
                    	
                    {
                    	
                    	
                    System.out.println("se obtuvo  el  usuario");
                    	 jsonResult = "{" +
                                 //   "\"id\":\"" + Idusuario +"\"," +
                                 //   "\"content\":\"" + uusuario +"\"," +
                                 //   "\"control\":\"" + correobd +"\"" +
                                    "\"control\":\"" + 1  +"\""
                                + "}";
                           
                    	 con.close();
                    	break;
                    	
                    }
                    
                    
                    
                    else
                    	
                    {
                    	///  si no haya  el  dato en la base de datos devuelbe  el  mesaje  ue no se encontro 
                    	
                   	 jsonResult = "{" +
                             //   "\"id\":\"" + Idusuario +"\"," +
                             //   "\"content\":\"" + uusuario +"\"," +
                             //   "\"control\":\"" + correobd +"\"" +
                                "\"control\":\"" + 0 +"\""
                            + "}";
                       
                     // con.close();
                	//break;
                    	
                    }
                    
                }
                
                //json que sera  devuelto  a javascript
                
               

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        //Escribir el JSON
        try (PrintWriter out = response.getWriter()) {
        	
        	System.out.println(jsonResult);
            /* TODO output your page here. You may use following sample code. */
            out.print(jsonResult);
            
            response.setContentType("application/json");
        }
	}

	
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
