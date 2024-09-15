package paquete;

import javax.swing.JOptionPane;

public class Registro {

	private Conexion conectar;

	public Registro () {

		conectar = Conexion.getInstance();
		
		conectarABaseDeDatos();
		
	}

	private void conectarABaseDeDatos() {
		
		try {
			
			conectar.conectar();
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
			
		}
		
	}
	
}
