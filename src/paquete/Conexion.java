package paquete;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexion {

	//guardamos el estado de la conexión de la BD
	private static Connection conexion;
	
	private static Conexion instancia;
	
	//variables para conectarse a la BD
	private static final String URL = "jdbc:mysql://localhost/bd_primeros_registros";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	//constructor privado para llamar los métodos estáticos
	private Conexion () {
		
		
		
	}
	
	//método para conectarse a la BD
	public Connection conectar() {
		
		if (conexion == null) { //evitará que la conexión se establezca más de una vez
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				JOptionPane.showMessageDialog(null, "Conexión exitosa!");
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
				
			}
			
		}

		return conexion;
		
	}
	
	//método para cerrar la BD
	public void cerrarConexion() throws SQLException {
		
		if (conexion != null) {
			
			try {
				
				conexion.close();
				conexion = null; //reinicia la variable para evitar que se reutilice incorrectamente
				JOptionPane.showMessageDialog(null, "Se desconectó de la BD.");
				
			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
				conexion.close();
				
			}
			
		}
	
	}
	
	//patrón Singleton estático para llamarlo directamente
	//synchronized para que el método sea seguro en un acceso concurrente
	public static synchronized Conexion getInstance() {
		
		//si la instancia es nula crea una nueva
		if (instancia == null) {
			
			instancia = new Conexion(); 
			
		}
		
		return instancia;
		
	}
	
}
