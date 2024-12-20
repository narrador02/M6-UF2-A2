import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer; 
import javax.xml.transform.TransformerFactory; 
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult; 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.sql.*;

public class ExportarXML {
    public static void exportarDepartamentosToXML() {
        String query = "SELECT * FROM departamentos";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
             
             // Crea el documento XML
             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
             DocumentBuilder builder = factory.newDocumentBuilder();
             Document doc = builder.newDocument();
             Element root = doc.createElement("Departamentos");
             doc.appendChild(root);

             while (rs.next()) {
                 Element departamento = doc.createElement("Departamento");
                 root.appendChild(departamento);
                 
                 Element id = doc.createElement("ID");
                 id.appendChild(doc.createTextNode(String.valueOf(rs.getInt("id"))));
                 departamento.appendChild(id);
                 
                 Element nombre = doc.createElement("Nombre");
                 nombre.appendChild(doc.createTextNode(rs.getString("nombre_departamento")));
                 departamento.appendChild(nombre);
             }

             // Guarda el documento XML
             TransformerFactory transformerFactory = TransformerFactory.newInstance();
             Transformer transformer = transformerFactory.newTransformer();
             DOMSource source = new DOMSource(doc);
             StreamResult result = new StreamResult(new File("departamentos.xml"));
             transformer.transform(source, result);

             System.out.println("XML generado correctamente.");
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
}