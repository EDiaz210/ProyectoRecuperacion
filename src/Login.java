import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JComboBox Seleccionador;
    public JPanel login;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JLabel resultado;

    public Login() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    try (Connection connection = usuarios.ConexionBD.getConnection()) {
                        Statement statement = connection.createStatement();
                        String query = "SELECT * FROM usuarios WHERE username = '" + textField1.getText() + "' AND password = '" + new String(passwordField1.getPassword()) + "' AND rol = '" + Seleccionador.getSelectedItem().toString() + "'";
                        ResultSet resultSet = statement.executeQuery(query);


                        if(resultSet.next()) {
                            if (Seleccionador.getSelectedItem().equals(resultSet.getString("rol"))){
                                if(textField1.getText().equals(resultSet.getString("username")) && new String(passwordField1.getPassword()).equals(resultSet.getString("password"))) {
                                    if(resultSet.getString("rol").equals("Administrador")){
                                        System.out.println("Ingresaste a modo administrador ");
                                        JFrame frame = new JFrame();
                                        frame.setTitle("Login");
                                        frame.setSize(350, 300);
                                        frame.setContentPane(new MenuAdmin().menuAd);
                                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        frame.setVisible(true);

                                        ((JFrame) SwingUtilities.getWindowAncestor(logInButton)).dispose();

                                    }else if(resultSet.getString("rol").equals("Usuario")){
                                        System.out.println("Ingresaste a modo usuario ");
                                        JFrame frame = new JFrame();
                                        frame.setTitle("Login");
                                        frame.setSize(350, 300);
                                        frame.setContentPane(new usuarios().panel1);
                                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        frame.setVisible(true);
                                    }

                                }
                            }else {
                            resultado.setText("Digite correctamente las credenciales");
                        }
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);

                }
            }
        });
    }
}
