package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartamentoManager {

    public static void createDepartamento(int id, String nombreDepartamento, int idEmpresa, Integer idGerente) {
        String query = "INSERT INTO departamentos (id, nombre_departamento, id_empresa, id_gerente) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            conn.setAutoCommit(true);  
            stmt.setInt(1, id);
            stmt.setString(2, nombreDepartamento);
            stmt.setInt(3, idEmpresa);
            stmt.setObject(4, idGerente, java.sql.Types.INTEGER);  // Maneja si hay valor NULL en id_gerente           
            stmt.executeUpdate();
            System.out.println("Departamento insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar departamento: " + e.getMessage());
        }
    }

    public static void readDepartamentos() {
        String query = "SELECT * FROM departamentos";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreDepartamento = rs.getString("nombre_departamento");
                int idEmpresa = rs.getInt("id_empresa");
                Integer idGerente = (Integer) rs.getObject("id_gerente");  // Permite que id_gerente sea NULL
                System.out.println("ID: " + id + ", Nombre: " + nombreDepartamento + ", Empresa ID: " + idEmpresa + ", Gerente ID: " + idGerente);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer departamentos: " + e.getMessage());
        }
    }

    public static void readDepartamentosPaginando(int page) {
        int pageLarg = 10;
        int offset = (page - 1) * pageLarg;       
        String query = "SELECT * FROM departamentos LIMIT ? OFFSET ?";       
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            conn.setAutoCommit(true);
            stmt.setInt(1, pageLarg);
            stmt.setInt(2, offset); 
            ResultSet rs = stmt.executeQuery();           
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreDepartamento = rs.getString("nombre_departamento");
                int idEmpresa = rs.getInt("id_empresa");
                Integer idGerente = (Integer) rs.getObject("id_gerente");  // Permite que id_gerente sea NULL
                System.out.println("ID: " + id + ", Nombre: " + nombreDepartamento + ", Empresa ID: " + idEmpresa + ", Gerente ID: " + idGerente);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer departamentos con paginación: " + e.getMessage());
        }
    }

    public static void updateDepartamento(int id, String newNombreDepartamento, Integer newIdGerente) {
        String query = "UPDATE departamentos SET nombre_departamento = ?, id_gerente = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            conn.setAutoCommit(true);
            stmt.setString(1, newNombreDepartamento);
            stmt.setObject(2, newIdGerente, java.sql.Types.INTEGER);  // Maneja si hay NULL en id_gerente
            stmt.setInt(3, id);            
            stmt.executeUpdate();
            System.out.println("Departamento actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar departamento: " + e.getMessage());
        }
    }

    public static void deleteDepartamento(int id) {
        String query = "DELETE FROM departamentos WHERE id = ?";       
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {             
            conn.setAutoCommit(true);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Departamento eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar departamento: " + e.getMessage());
        }
    }
}
