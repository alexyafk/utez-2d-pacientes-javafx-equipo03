# utez-2d-pacientes-javafx-equipo03
Tarea integradora Alejandro Mena Pereyda y Alexa Pardo Diaz

# Directorio de Pacientes

Este proyecto consiste en una aplicación de escritorio desarrollada en Java utilizando JavaFX,
la cual permite gestionar un directorio de pacientes para un consultorio. El sistema implementa
operaciones CRUD (Crear, Leer, Actualizar y Eliminar), junto con persistencia en un archivo .csv,
lo que permite conservar la información incluso después de cerrar/reiniciar el programa.

Esta tarea integradora fue realizada para la materia programación orientada a objetos.

# Objetivos

Construir una aplicación JavaFX con CRUD completo.
Implementar persistencia en archivos. 
Aplicar POO básica.
Implementar validaciones y manejo de errores.

# Funcionalidades del sistema (CRUD)

*Alta*
- Se registran pacientes desde el formulario
- Se validan los datos
- Se guardan en archivo

*Consulta*
- Se muestran en un TableView
- Se cargan desde archivo al iniciar

*Actualización*
- Se edita un paciente seleccionado
- Se reescribe el archivo con los cambios

*Eliminación (lógica)*
- No se borra el registro
- Se cambia el estatus a INACTIVO

# Persistencia en archivo

El sistema utiliza el archivo:
_data-pacientes.csv_

- Lectura
Se leen todas las líneas del archivo.
Se convierten en objetos Paciente.

- Escritura
_appendNewLine()_ agrega registros nuevos.
_appendAllLines()_ reescribe todo el archivo.

# Validaciones implementadas

Las validaciones se encuentran en el **PacienteService**

- CURP no vacía
CURP única (sin duplicados).

- Nombre mínimo 5 caracteres

- Edad:
Solo números.
Rango de 0 a 120.

- Teléfono:
Solo números.
10 dígitos.

_Errores manejados con try/catch y IllegalArgumentException._

# Funcionalidades extra

- Estatus del paciente
ACTIVO / INACTIVO
Se puede cambiar dinámicamente

- Resumen automático
Total de pacientes
Activos - Inactivos
Se actualiza automáticamente

# Interfaz gráfica

- **Tabla**

CURP

Nombre

Edad

Teléfono

Alergias

Estatus

- **Formulario**

CURP

Nombre

Edad

Teléfono

Alergias

- **Botones**

Nuevo

Editar

Cambuar estatus

Eliminar

Recargar

Limpiar

# Funcionamiento del sistema

_Al iniciar la aplicación:_
Se cargan los pacientes desde el archivo data/pacientes.csv
Se muestran en una tabla (TableView)
Se actualiza automáticamente el resumen de registros

# Funcionalidades principales

- Agregar paciente
Se capturan los datos desde el formulario
Se validan antes de guardarse
Se almacenan en el archivo

- Consultar pacientes
Se visualizan en la tabla
Se cargan automáticamente al iniciar

- Editar paciente
Se selecciona un paciente de la tabla
Se modifican sus datos
Se actualiza el archivo completo

- Eliminar paciente
No se elimina físicamente
Se cambia el estatus a INACTIVO

- Cambiar estatus
Permite cambiar entre ACTIVO e INACTIVO

# Cómo ejecutar el proyecto

Clonar el repositorio:
-git clone < https://github.com/alexyafk/utez-2d-pac0ientes-javafx-equipo03.git >

Abrir el proyecto en IntelliJ

Configurar JavaFX en el proyecto

Ejecutar en _HelloApplication_

# Archivo de datos

- **Ejemplo de registro**:

GOML030512HDFRNS09-Juan Perez Diaz-25-7771234567-Ninguna-
