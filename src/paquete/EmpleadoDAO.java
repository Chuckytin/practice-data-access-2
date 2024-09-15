package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class EmpleadoDAO {

    private Conexion conectar;

    public EmpleadoDAO() {
    	
        conectar = Conexion.getInstance();
        
    }

    // Método para insertar empleados pasándole por parámetros los datos necesarios
    public void insertarEmpleados(String nombre, int telefono, Profesion profesion) {
    	
        Connection conn = null;
        PreparedStatement insertar = null;

        try {
        	
            conn = conectar.conectar();
            insertar = conn.prepareStatement("INSERT INTO empleados (nombre, telefono, profesion) VALUES (?, ?, ?)");

            insertar.setString(1, nombre.trim());
            insertar.setInt(2, telefono);
            insertar.setString(3, profesion.getNombre());

            int filaInsertada = insertar.executeUpdate();

            if (filaInsertada > 0) {
            	
                JOptionPane.showMessageDialog(null, "Empleado agregado correctamente.");
                
            }

        } catch (Exception e) {
        	
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            
        } finally {
        	
            cerrarRecursos(conn, insertar, null);
            
        }
        
    }

    // Método para consultar los datos de la BD
    public void consultarEmpleados() {
    	
        Connection conn = null;
        PreparedStatement seleccionar = null;
        ResultSet consulta = null;

        try {
        	
            conn = conectar.conectar();
            seleccionar = conn.prepareStatement("SELECT * FROM empleados");

            consulta = seleccionar.executeQuery();

            if (!consulta.isBeforeFirst()) {
            	
                JOptionPane.showMessageDialog(null, "No existe datos.");
                
            } else {
            	
                JOptionPane.showMessageDialog(null, "La BD tiene datos. Revisa la consola para más detalles.");

                while (consulta.next()) {
                	
                    int idEmpleado = consulta.getInt("idEmpleado");
                    String nombre = consulta.getString("nombre");
                    int telefono = consulta.getInt("telefono");
                    String profesion = consulta.getString("profesion");

                    String datos = String.format("ID: %d, Nombre: %s, Teléfono: %d, Profesión: %s", 
                            idEmpleado, nombre, telefono, profesion);

                    System.out.println(datos);
                }
                
            }

        } catch (Exception e) {
        	
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            
        } finally {
        	
            cerrarRecursos(conn, seleccionar, consulta);
            
        }
    }

    // Método para eliminar datos de la BD
    public void eliminarEmpleado(int idEmpleado) {
    	
        Connection conn = null;
        PreparedStatement eliminar = null;
        PreparedStatement seleccionar = null;
        ResultSet consulta = null;

        try {
        	
            conn = conectar.conectar();

            seleccionar = conn.prepareStatement("SELECT * FROM empleados WHERE idEmpleado = ?");
            seleccionar.setInt(1, idEmpleado);
            consulta = seleccionar.executeQuery();

            if (consulta.next()) {
            	
                int id = consulta.getInt("idEmpleado");
                String nombre = consulta.getString("nombre");
                int telefono = consulta.getInt("telefono");
                String profesion = consulta.getString("profesion");

                int confirmacion = JOptionPane.showConfirmDialog(null,
                        String.format("¿Estás seguro de que deseas eliminar al empleado?\nID: %d, Nombre: %s, Teléfono: %d, Profesión: %s",
                                id, nombre, telefono, profesion),
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                	
                    eliminar = conn.prepareStatement("DELETE FROM empleados WHERE idEmpleado = ?");
                    eliminar.setInt(1, idEmpleado);

                    int filasAfectadas = eliminar.executeUpdate();

                    if (filasAfectadas > 0) {
                    	
                        JOptionPane.showMessageDialog(null, "Empleado eliminado exitosamente.");
                        
                    } else {
                    	
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el empleado.");
                        
                    }
                    
                } else {
                	
                    JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
                    
                }

            } else {
            	
                JOptionPane.showMessageDialog(null, "No se encontró un empleado con el ID " + idEmpleado);
          
            }

        } catch (Exception e) {
        	
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            
        } finally {
        	
            cerrarRecursos(conn, seleccionar, consulta);
            cerrarRecursos(null, eliminar, null);
            
        }
        
    }

    // Método para modificar los datos
    public void modificarEmpleado(int idEmpleado, String nombre, int telefono, Profesion profesion) {
    	
        Connection conn = null;
        PreparedStatement modificar = null;

        try {
        	
            conn = conectar.conectar();

            StringBuilder consultaSQL = new StringBuilder("UPDATE empleados SET ");
            List<Object> parametros = new ArrayList<>();

            if (nombre != null) {
            	
                consultaSQL.append("nombre = ?, ");
                parametros.add(nombre);
                
            }
            
            if (telefono != 0) {
            	
                consultaSQL.append("telefono = ?, ");
                parametros.add(telefono);
                
            }
            
            if (profesion != null) {
            	
                consultaSQL.append("profesion = ?, ");
                parametros.add(profesion.getNombre());
                
            }

            consultaSQL.setLength(consultaSQL.length() - 2); // Quita la última coma y espacio
            consultaSQL.append(" WHERE idEmpleado = ?");
            parametros.add(idEmpleado);

            modificar = conn.prepareStatement(consultaSQL.toString());

            for (int i = 0; i < parametros.size(); i++) {
            	
                modificar.setObject(i + 1, parametros.get(i));
                
            }

            int filasModificadas = modificar.executeUpdate();

            if (filasModificadas > 0) {
            	
                JOptionPane.showMessageDialog(null, "Empleado modificado con éxito.");
                
            } else {
            	
                JOptionPane.showMessageDialog(null, "No se pudo modificar el empleado.");
                
            }

        } catch (Exception e) {
        	
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            
        } finally {
        	
            cerrarRecursos(conn, modificar, null);
            
        }
        
    }

    // Método para buscar empleado
    public void buscarEmpleado(int idEmpleado) {
    	
        Connection conn = null;
        PreparedStatement buscar = null;
        ResultSet consulta = null;

        try {
        	
            conn = conectar.conectar();

            buscar = conn.prepareStatement("SELECT * FROM empleados WHERE idEmpleado = ?");
            buscar.setInt(1, idEmpleado);

            consulta = buscar.executeQuery();

            if (!consulta.isBeforeFirst()) {
            	
                JOptionPane.showMessageDialog(null, "No existe el empleado con ID " + idEmpleado);
                
            } else {
            	
                if (consulta.next()) {
                	
                    JOptionPane.showMessageDialog(null, "Se encontró el empleado. Revisa la consola.");

                    String nombre = consulta.getString("nombre");
                    int telefono = consulta.getInt("telefono");
                    String profesion = consulta.getString("profesion");

                    String datos = String.format("ID: %d, Nombre: %s, Teléfono: %d, Profesión: %s",
                            idEmpleado, nombre, telefono, profesion);

                    System.out.println(datos);
                    
                } else {
                	
                    JOptionPane.showMessageDialog(null, "No se encontró el empleado con ID " + idEmpleado);
                }
                
            }

        } catch (Exception e) {
        	
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            
        } finally {
        	
            cerrarRecursos(conn, buscar, consulta);
            
        }
        
    }

    // Método para cerrar recursos
    private void cerrarRecursos(Connection conn, PreparedStatement ps, ResultSet rs) {
    	
        try {
        	
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
            
        } catch (Exception e) {
        	
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            
        }
        
    }
    
}
