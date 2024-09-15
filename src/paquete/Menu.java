package paquete;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Menu {

	private Gestor gestor;
	
	//constructor
	public Menu () {
		
		gestor = new Gestor();
		
	}
	
	//método
	public void menu () {

		Scanner entrada = new Scanner(System.in);

		boolean salir = false;
		int opcion;


		while (!salir) {

			System.out.println("Elige una opción: ");
			System.out.println("1 - Insertar un empleado");
			System.out.println("2 - Consultar los empleados");
			System.out.println("3 - Eliminar un empleado");
			System.out.println("4 - Modificar un empleado");
			System.out.println("5 - Buscar un empleado");
			System.out.println("0 - Salir");
			
			opcion = entrada.nextInt();
			entrada.nextLine();

			switch (opcion) {

			case 1:

				gestor.insertarEmpleados();

				break;

			case 2:

				gestor.consultarEmpleados();

				break;

			case 3:

				gestor.eliminarEmpleado();
				
				break;

			case 4:

				gestor.modificarEmpleado();

				break;

			case 5:
				
				gestor.buscarEmpleado();
							
				break;
				
			case 0:
				
				System.out.println("Hasta luego...");
				salir = true;
				
				break;
				
			default: JOptionPane.showMessageDialog(null, "Opción no válida.");

			}

		}

		entrada.close();

	}

}
