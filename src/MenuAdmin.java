import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuAdmin {
    public JPanel menuAd;
    private JTabbedPane tabbedPane1;
    private JButton Agregar_P;
    private JTextField codigo_p;
    private JTextField nombre_p;
    private JTextField cantidad_p;
    private JTextField preciou_p;
    private JTextField new_user;
    private JTextField new_pass;
    private JButton Ingresar_user;

    //ffr
    //Creacion total


    public MenuAdmin() {
        Ingresar_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String query = "INSERT INTO jugadores (id, nombre, posicion ,equipo, edad ) VALUES (?, ?, ?, ?, ?)";
                String correo = new_user.getText().trim();
                String password = new_pass.getText().trim();

                try(Connection connection = usuarios.ConexionBD.getConnection()){
                    PreparedStatement cadenaPreparada = connection.prepareStatement(query);

                    cadenaPreparada.setString(1,correo);
                    cadenaPreparada.setString(2,password);
                    cadenaPreparada.executeUpdate();






            } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            });

    }
}
