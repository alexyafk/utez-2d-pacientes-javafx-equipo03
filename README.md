# utez-2d-pacientes-javafx-equipo03
Tarea integradora Alejandro Mena Pereyda y Alexa Pardo Diaz
Se crea la interfaz para que los usuarios agreguen información que se guarda en un archivo csv. 
Contiene los siguientes datos para su ejecución: 
1. CURP (ID) 2. NOMBRE COMPLETO 3. TELÉFONO 4. ESTATUS 5. EDAD 6. ALERGIAS
El directorio repositories contiene un archivo PacienteRepository.java que su funcion es: 
1) "private final Path filePath = Paths.get("data", "pacientes.csv");": define las rutas en donde se guardaran los datos de los pacientes.
2) "private void ensureFileExist() throws IOException": este metodo se asegura que el archivo este creado, si no existe, este metodo lo creara.
3) "public List<String> readAllLines() throws IOException": este metodo lee todas las lineas existentes y las devuelve en un listado de tipo string.
4) "public void appendNewLine(String line) throws IOException": este metodo se encarga de agregar una nueva linea(paciente) al archivo csv.
5) "public void appendAllLines(List<String> lines) throws IOException": este metodo se encarga de reemplazar lineas, funciona para actualizar o eliminar lineas.
