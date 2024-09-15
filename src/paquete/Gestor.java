package paquete;

import java.util.Scanner;

public class Gestor {

	private EmpleadoDAO emp;
	
	private Scanner entrada;
	
	public Gestor () {
		
		emp = new EmpleadoDAO();
		entrada = new Scanner(System.in);
		
	}
	
	public void insertarEmpleados () {
		
		System.out.println("Añade nombre: ");
		String nombreEmpleado = entrada.nextLine();
		System.out.println("Añade un teléfono: ");
		int tlfEmpleado = entrada.nextInt();
		entrada.nextLine();
		System.out.println("Añade una profesión de las disponibles: ");
		System.out.println("1 - Ingeniero\n2 - Médico\n3 - Abogado\n4 - Profesor");
		int tipoProfesion = entrada.nextInt();
		entrada.nextLine();
		Profesion profesion = null;

		switch (tipoProfesion) {

		case 1: 	
			profesion = Profesion.INGENIERO;
			break;

		case 2:
			profesion = Profesion.MEDICO;
			break;

		case 3:
			profesion = Profesion.ABOGADO;
			break;

		case 4:
			profesion = Profesion.PROFESOR;
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + tipoProfesion);
		}

		emp.insertarEmpleados(nombreEmpleado, tlfEmpleado, profesion);
		
	}
	
	public void consultarEmpleados () {
		
		emp.consultarEmpleados();
		
	}
	
	public void eliminarEmpleado () {
		
		System.out.println("Añade el ID del empleado a eliminar: ");
		int empleadoEliminar = entrada.nextInt();
		entrada.nextLine();

		emp.eliminarEmpleado(empleadoEliminar);
		
	}
	
	public void modificarEmpleado () {
		
		System.out.println("Añade el ID del empleado que deseas modificar: ");
		int idDelEmpleado = entrada.nextInt();
		entrada.nextLine();

		System.out.println("Deseas modificar el nombre del empleado? (si/no)");
		String respuesta1 = entrada.nextLine().toLowerCase().trim();
		String nombreNuevo = null;

		if (respuesta1.equals("si")) {

			System.out.println("Introduzca nombre nuevo: ");
			nombreNuevo = entrada.nextLine();

		} 

		System.out.println("Deseas modificar el teléfono del empleado? (si/no)");
		String respuesta2 = entrada.nextLine().toLowerCase().trim();
		int telefonoNuevo = 0;

		if (respuesta2.equals("si")) {

			System.out.println("Introduzca teléfono nuevo: ");
			telefonoNuevo = entrada.nextInt();
			entrada.nextLine();

		}

		System.out.println("Deseas modificar la profesión del empleado? (si/no)");
		String respuesta3 = entrada.nextLine().toLowerCase().trim();
		Profesion profesionNueva = null;

		if (respuesta3.equals("si")) {

			System.out.println("Elija la profesión nueva: ");
			System.out.println("1 - Ingeniero\n2 - Médico\n3 - Abogado\n4 - Profesor");
			int tipoProfesion2 = entrada.nextInt();
			entrada.nextLine();

			switch (tipoProfesion2) {

			case 1: 	
				profesionNueva = Profesion.INGENIERO;
				break;

			case 2:
				profesionNueva = Profesion.MEDICO;
				break;

			case 3:
				profesionNueva = Profesion.ABOGADO;
				break;

			case 4:
				profesionNueva = Profesion.PROFESOR;
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + tipoProfesion2);
			}

		}

		emp.modificarEmpleado(idDelEmpleado, nombreNuevo, telefonoNuevo, profesionNueva);
		
	}
	
	public void buscarEmpleado () {
		
		System.out.println("Introduce el ID del empleado que deseas buscar: ");
		int idDelEmpleado2 = entrada.nextInt();
		entrada.nextLine();
		
		emp.buscarEmpleado(idDelEmpleado2);
		
	}
	
}
