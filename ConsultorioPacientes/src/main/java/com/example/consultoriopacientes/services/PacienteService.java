package com.example.consultoriopacientes.services;

import com.example.consultoriopacientes.model.Paciente;
import com.example.consultoriopacientes.repositories.PacienteRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    private PacienteRepository repo = new PacienteRepository();

    public List<Paciente> loadDataForList() throws IOException {
        List<String> lines = repo.readAllLines();
        List<Paciente> result = new ArrayList<>();
        for(String line : lines){
            if(line == null || line.isBlank()) continue;
            String[] parts = line.split(",", -1);
            if(parts.length >= 6){
                Paciente pacien = new Paciente(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim());
                result.add(pacien);
            }
        }
        return result;
    }

    public void addPaciente(String curp, String nombre, String edad, String telefono, String alergias, String status) throws IOException {
        validatePaciente(curp, nombre, edad, telefono);
        List<Paciente> registros = loadDataForList();
        for(Paciente pacien : registros){
            if(pacien.getCurp().equalsIgnoreCase(curp)){
                throw new IllegalArgumentException("Ya existe un paciente con esa CURP.");
            }

        }

        String curpNoComa = curp.replace(",", "");
        String nombreNoComa = nombre.replace(",", "");
        String edadNoComa = edad.replace(",", "");
        String telNoComa = telefono.replace(",", "");
        String alergiasNoComa = alergias.replace(",", "");
        String statusNoComa = status.replace(",", "");

        repo.appendNewLine(curpNoComa + "," + nombreNoComa + "," + edadNoComa + "," + telNoComa + "," + alergiasNoComa + "," + statusNoComa);
    }

    public void editarPaciente(String curp, String nombre, String edad, String telefono, String alergias) throws IOException {
        List<Paciente> lista = loadDataForList();
        List<String> result = new ArrayList<>();

        for (Paciente p : lista) {
            if (p.getCurp().equalsIgnoreCase(curp)) {
                p.setNombreCompleto(nombre);
                p.setEdad(edad);
                p.setTelefono(telefono);
                p.setAlergias(alergias);
            }
            String linea = p.getCurp() + "," + p.getNombreCompleto() + "," + p.getEdad() + "," + p.getTelefono() + "," + p.getAlergias() + "," + p.getStatus();
            result.add(linea);
        }
        repo.appendAllLines(result);
    }
    public void cambiarEstatus(String curp) throws IOException {
        List<Paciente> registros = loadDataForList();
        List<String> result = new ArrayList<>();
        for (Paciente pacien : registros) {
            if (pacien.getCurp().equalsIgnoreCase(curp)) {
                String nuevoEstado = pacien.getStatus().equalsIgnoreCase("ACTIVO") ? "INACTIVO" : "ACTIVO";
                pacien.setStatus(nuevoEstado);
            }
            String linea = pacien.getCurp() + "," + pacien.getNombreCompleto() + "," + pacien.getEdad() + "," + pacien.getTelefono() + "," + pacien.getAlergias() + "," + pacien.getStatus();
            result.add(linea);
        }
        repo.appendAllLines(result);
    }

    public void deletePaciente(String curp) throws IOException {
        List<Paciente> registros = loadDataForList();
        List<String> result =new ArrayList<>();
        for (Paciente pacien : registros){
            if(pacien.getCurp().equalsIgnoreCase(curp)){
                    pacien.setStatus("INACTIVO");
            }
            String linea = pacien.getCurp() + "," + pacien.getNombreCompleto() + "," + pacien.getEdad() + "," + pacien.getTelefono() + "," + pacien.getAlergias() + "," + pacien.getStatus();
            result.add(linea);
        }
        repo.appendAllLines(result);
    }

    public int totalPacientes(List<Paciente> pacientes){
        return pacientes.size();
    }

    public int pacientesActivos(List<Paciente> pacientes){
        int activos = 0;
        for(Paciente pacien : pacientes){
            if(pacien.getStatus().equalsIgnoreCase("ACTIVO")){
                activos++;
            }
        }
        return activos;
    }

    public int pacientesInactivos(List<Paciente> pacientes){
        int inactivos = 0;
        for (Paciente pacien : pacientes){
            if(pacien.getStatus().equalsIgnoreCase("INACTIVO")){
                inactivos++;
            }
        }
        return inactivos;
    }

    private void validatePaciente(String curp, String nombre, String edad, String telefono){
        if(curp.isBlank()){
            throw new IllegalArgumentException("La curp no puede estar vacia");
        }

        if(nombre.isBlank() || nombre.length() < 5){
            throw new IllegalArgumentException("El nombre debe tener minimo  5 caracteres");
        }

        if(edad.isBlank() || !edad.matches("\\d+")){
            throw new IllegalArgumentException("La edad debe ser un valor numero. No puede estar vacia");
        }

        int age = Integer.parseInt(edad);
        if(age < 0 || age > 120){
            throw new IllegalArgumentException("Edad fuera de rango (0-120)");
        }

        if (telefono.length() != 10 || !telefono.matches("\\d+")) {
            throw new IllegalArgumentException("El teléfono debe ser de al menos 10 dígitos numéricos");
        }
    }
}
