package paquete;

public enum Profesion {

	INGENIERO("Ingeniero"),
    MEDICO("Médico"),
    ABOGADO("Abogado"),
    PROFESOR("Profesor");
	
	private String nombre;
	
	Profesion (String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	@Override
	public String toString() {
		
		return getNombre();
		
	}
	
}
