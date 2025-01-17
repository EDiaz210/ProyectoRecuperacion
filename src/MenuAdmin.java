import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    //ffr
    //Creacion total


    public MenuAdmin() {
        Ingresar_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String id = codigo_p.getText();
                String nombre = nombre_p.getText();
                String descripcion = descrip.getText();
                String precio = preciou_p.getText();
                String cantidad = cantidad_p.getText();

                String query = "INSERT INTO productos (id, nombre, descripcion ,precio, stock ) VALUES (?, ?, ?, ?, ?)";

                int cantidara_int=Integer.parseInt(cantidad);
                if (cantidara_int >= 10 && cantidara_int <= 350) {
                    try (Connection connection = usuarios.ConexionBD.getConnection()) {
                        PreparedStatement pstmt = connection.prepareStatement(query);

                        pstmt.setInt(1, Integer.parseInt(id));
                        pstmt.setString(2, nombre);
                        pstmt.setString(3, descripcion);
                        pstmt.setDouble(4, Double.parseDouble(precio));
                        pstmt.setInt(5, Integer.parseInt(cantidad));

                        pstmt.executeUpdate();


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(menuAd,"VALOR DE STOCK INVALIDO / REINTENTE");
                }
            }
        });
        JFrame frame=new JFrame("Administrador");
        frame.setContentPane( menuAd );
        frame.setSize( 300,300 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
    }
    public static void main(String[] args){
        new MenuAdmin();
    }
}
