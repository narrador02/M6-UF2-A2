package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        
        do {
            System.out.println("\n--- Menú de Gestión de Departamentos ---");
            System.out.println("1. Insertar departamento");
            System.out.println("2. Mostrar todos los departamentos (paginado)");
            System.out.println("3. Buscar departamento por ID");
            System.out.println("4. Buscar departamento por nombre");
            System.out.println("5. Actualizar departamento");
            System.out.println("6. Eliminar departamento");
            System.out.println("7. Exportar departamentos a XML");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = Integer.parseInt(reader.readLine());
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del departamento: ");
                    int id = Integer.parseInt(reader.readLine());
                    System.out.print("Ingrese nombre del departamento: ");
                    String nombre = reader.readLine();
                    System.out.print("Ingrese ID de la empresa: ");
                    int idEmpresa = Integer.parseInt(reader.readLine());
                    System.out.print("Ingrese ID del gerente (o 0 si no tiene): ");
                    int idGerente = Integer.parseInt(reader.readLine());
                    DepartamentoManager.createDepartamento(id, nombre, idEmpresa, idGerente == 0 ? null : idGerente);
                    break;
                case 2:
                    System.out.print("Ingrese número de página: ");
                    int pagina = Integer.parseInt(reader.readLine());
                    DepartamentoManager.readDepartamentosPaginando(pagina);
                    break;
                case 3:
                    System.out.print("Ingrese ID del departamento: ");
                    int idBuscar = Integer.parseInt(reader.readLine());
                    DepartamentoManager.mostrarPorId(idBuscar);
                    break;
                case 4:
                    System.out.print("Ingrese nombre (o parte) del departamento: ");
                    String nombreBuscar = reader.readLine();
                    DepartamentoManager.mostrarPorNombre(nombreBuscar);
                    break;
                case 5:
                    System.out.print("Ingrese ID del departamento a actualizar: ");
                    int idActualizar = Integer.parseInt(reader.readLine());
                    System.out.print("Ingrese nuevo nombre del departamento: ");
                    String nuevoNombre = reader.readLine();
                    System.out.print("Ingrese nuevo ID del gerente (o 0 si no cambia): ");
                    int nuevoIdGerente = Integer.parseInt(reader.readLine());
                    DepartamentoManager.updateDepartamento(idActualizar, nuevoNombre, nuevoIdGerente == 0 ? null : nuevoIdGerente);
                    break;
                case 6:
                    System.out.print("Ingrese ID del departamento a eliminar: ");
                    int idEliminar = Integer.parseInt(reader.readLine());
                    DepartamentoManager.deleteDepartamento(idEliminar);
                    break;
                case 7:
                    ExportarXML.exportarDepartamentosToXML();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        reader.close();
    }
}