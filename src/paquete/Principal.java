package paquete;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		
		//Instancia para establecer conexión a la BD
		Registro registro = new Registro();
		
		Menu menu = new Menu();		
		menu.menu();
		
		//cierra la conexión si ya no se necesita más adelante
		Conexion conexion = Conexion.getInstance();		
		try {
			
			conexion.cerrarConexion();
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
			
		}
		
	}
	
}
