# Registro de Empleados en Base de Datos

Este proyecto está **sin mejorar** es un ejercicio práctico de manejo de registros en una base de datos utilizando una estructura de clases organizada en el paquete `paquete`. Se simula el registro de empleados, cada uno con una profesión, en una base de datos llamada `bd_primeros_registros`. Las operaciones principales incluyen la conexión a la base de datos, inserción de registros y gestión de empleados.

## Estructura del Proyecto

El proyecto está organizado en el paquete `paquete`, que contiene las siguientes clases:

### Clases:

- **`Conexion`**: Clase encargada de gestionar la conexión a la base de datos `bd_primeros_registros`. Contiene los métodos necesarios para abrir y cerrar conexiones.
  
- **`EmpleadoDAO`**: Data Access Object (DAO) que se encarga de las operaciones CRUD (crear, leer, actualizar, eliminar) sobre la tabla de empleados en la base de datos. Esta clase actúa como intermediario entre la aplicación y la base de datos.

- **`Gestor`**: Clase que gestiona la lógica principal del sistema. Coordina las operaciones de registro, actualización y eliminación de empleados utilizando la clase `EmpleadoDAO`.

- **`Menu`**: Clase que genera el menú interactivo para el usuario, permitiendo la selección de las distintas operaciones que se pueden realizar con los empleados en la base de datos.

- **`Principal`**: Punto de entrada de la aplicación. Contiene el método `main` que inicia el programa y muestra el menú principal.

- **`Registro`**: Clase que representa el modelo de un empleado. Contiene los atributos del empleado, como nombre, apellido, profesión, etc.

- **`Profesion`**: Enumeración (`enum`) que define las profesiones posibles para los empleados. Cada empleado debe estar asociado a una profesión definida en este `enum`.
