import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class usuarios {
    public JTabbedPane usuarios_pestañas;
    public JPanel panel1;
    private JButton Salir1;
    private JButton Salir2;
    private JTextArea textArea1;
    private JTextArea textArea2;

    public usuarios() {
        Salir1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Salir2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        cargarProductos();
        cargarProductosBajoStock();
    }

    private void cargarProductos() {
        try (Connection connection = ConexionBD.getConnection()) {
            String query = "SELECT * FROM productos";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            textArea1.setText("");

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int stock = resultSet.getInt("stock");

                textArea1.append("ID: " + idProducto + ", Nombre: " + nombre + ", Descripción: " + descripcion +
                        ", Precio: " + precio + ", Stock: " + stock + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarProductosBajoStock() {
        try (Connection connection = ConexionBD.getConnection()) {
            String query = "SELECT * FROM productos WHERE stock <= 20";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            textArea2.setText("");

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int stock = resultSet.getInt("stock");

                textArea2.append("ID: " + idProducto + ", Nombre: " + nombre + ", Descripción: " + descripcion +
                        ", Precio: " + precio + ", Stock: " + stock + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static class ConexionBD {
        private static final String URL = "jdbc:mysql://ul1p0vkekpygav1f:g4TMqDpYcFoAkQKd4tWx@bjaibh7r281ex1wojndt-mysql.services.clever-cloud.com:3306/bjaibh7r281ex1wojndt";
        private static final String USERNAME = "ul1p0vkekpygav1f";
        private static final String PASSWORD = "g4TMqDpYcFoAkQKd4tWx";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
    }
}
