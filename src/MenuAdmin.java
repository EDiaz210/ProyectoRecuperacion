import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

public class MenuAdmin {
    JPanel menuAd;
    private JTabbedPane tabbedPane1;
    private JButton Agregar_P;
    private JTextField codigo_p;
    private JTextField nombre_p;
    private JTextField cantidad_p;
    private JTextField preciou_p;
    private JTextField new_user;
    private JTextField new_pass;
    private JButton Ingresar_user;
    private JTextField descrip;
    private JButton button1;
    private JButton button2;
    private JTable table2;  // Asegurándome de declarar el JTable

    public MenuAdmin() {
        // Inicializar la tabla con un modelo vacío
        table2.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre", "Descripción", "Precio", "Stock"}
        ));

        // Acción para el botón "Agregar_P"
        Agregar_P.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtención de los datos del formulario
                String id = codigo_p.getText();
                String nombre = nombre_p.getText();
                String descripcion = descrip.getText();
                String precio = preciou_p.getText();
                String cantidad = cantidad_p.getText();

                // Verificación de la cantidad
                try {
                    int cantidadInt = Integer.parseInt(cantidad);
                    if (cantidadInt >= 10 && cantidadInt <= 350) {
                        // Si la cantidad es válida, realizar la inserción
                        String query = "INSERT INTO productos (id, nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?, ?)";

                        try (Connection connection = usuarios.ConexionBD.getConnection()) {
                            PreparedStatement pstmt = connection.prepareStatement(query);
                            pstmt.setInt(1, Integer.parseInt(id));
                            pstmt.setString(2, nombre);
                            pstmt.setString(3, descripcion);
                            pstmt.setDouble(4, Double.parseDouble(precio));
                            pstmt.setInt(5, Integer.parseInt(cantidad));
                            pstmt.executeUpdate();
                            JOptionPane.showMessageDialog(menuAd, "Producto agregado exitosamente.");

                            // Actualizar la tabla con la nueva fila
                            ((DefaultTableModel) table2.getModel()).addRow(new Object[]{id, nombre, descripcion, precio, cantidad});
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(menuAd, "Error al agregar producto: " + ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(menuAd, "VALOR DE STOCK INVALIDO / REINTENTE");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(menuAd, "La cantidad y precio deben ser números válidos.");
                }
            }
        });

        // Acción para el botón "button1"
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtención de los datos del formulario
                String id = codigo_p.getText();
                String nombre = nombre_p.getText();
                String descripcion = descrip.getText();
                String precio = preciou_p.getText();
                String cantidad = cantidad_p.getText();

                // Verificación de la cantidad
                try {
                    int cantidadInt = Integer.parseInt(cantidad);
                    if (cantidadInt >= 10 && cantidadInt <= 350) {
                        // Si la cantidad es válida, realizar la inserción
                        String query = "INSERT INTO productos (id, nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?, ?)";

                        try (Connection connection = usuarios.ConexionBD.getConnection()) {
                            PreparedStatement pstmt = connection.prepareStatement(query);
                            pstmt.setInt(1, Integer.parseInt(id));
                            pstmt.setString(2, nombre);
                            pstmt.setString(3, descripcion);
                            pstmt.setDouble(4, Double.parseDouble(precio));
                            pstmt.setInt(5, Integer.parseInt(cantidad));
                            pstmt.executeUpdate();
                            JOptionPane.showMessageDialog(menuAd, "Producto agregado exitosamente.");

                            // Actualizar la tabla con la nueva fila
                            ((DefaultTableModel) table2.getModel()).addRow(new Object[]{id, nombre, descripcion, precio, cantidad});
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(menuAd, "Error al agregar producto: " + ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(menuAd, "VALOR DE STOCK INVALIDO / REINTENTE");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(menuAd, "La cantidad y precio deben ser números válidos.");
                }
            }
        });

        // Acción para el botón "ingresar_user" (agregar usuario)
        Ingresar_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtención de los datos del formulario
                String usuario = new_user.getText();
                String password = new_pass.getText();

                // Verificación de los datos de usuario y contraseña
                if (usuario.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(menuAd, "El usuario y la contraseña no pueden estar vacíos.");
                    return;
                }

                // Consulta para insertar usuario
                String query = "INSERT INTO usuarios (usuario, password) VALUES (?, ?)";

                try (Connection connection = usuarios.ConexionBD.getConnection()) {
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, usuario);
                    pstmt.setString(2, password);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(menuAd, "Usuario agregado exitosamente.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(menuAd, "Error al agregar usuario: " + ex.getMessage());
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (Connection connection = usuarios.ConexionBD.getConnection()) {
                    Statement statement = connection.createStatement();
                    String query = "SELECT * FROM usuarios";
                    ResultSet resultSet = statement.executeQuery(query);

                    // Crear un modelo para la tabla
                    DefaultTableModel tableModel = new DefaultTableModel();

                    // Agregar las columnas
                    tableModel.addColumn("ID");
                    tableModel.addColumn("Usuario");
                    tableModel.addColumn("Contraseña");
                    tableModel.addColumn("Rol");

                    // Llenar el modelo de la tabla con los datos del ResultSet
                    while (resultSet.next()) {
                        Object[] row = {
                                resultSet.getInt("id"),
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("rol"),
                        };
                        tableModel.addRow(row);
                    }

                    // Establecer el modelo de la tabla
                    table2.setModel(tableModel);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Administrador");
        frame.setContentPane(new MenuAdmin().menuAd);
        frame.setSize(600, 400);  // Ajusta el tamaño según sea necesario
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
