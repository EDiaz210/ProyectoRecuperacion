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
                if (Seleccionador.getSelectedItem().toString().equals("Administrador")){
                    System.out.println("Ingresaste a mod administrador ");

                    String url = "jdbc:mysql://ul1p0vkekpygav1f:g4TMqDpYcFoAkQKd4tWx@bjaibh7r281ex1wojndt-mysql.services.clever-cloud.com:3306/bjaibh7r281ex1wojndt";
                    String usuario = "ul1p0vkekpygav1f";
                    String password = "g4TMqDpYcFoAkQKd4tWx";

                    try (Connection connection = DriverManager.getConnection(url, usuario, password)) {
                        Statement statement = connection.createStatement();
                        String query = "SELECT * FROM usuarios WHERE username = '" + textField1.getText() + "' AND password = '" + new String(passwordField1.getPassword()) + "'";
                        ResultSet resultSet = statement.executeQuery(query);


                        if(resultSet.next()) {
                            if(textField1.getText().equals(resultSet.getString("usuario")) && new String(passwordField1.getPassword()).equals(resultSet.getString("contraseña"))) {
                                JFrame frame = new JFrame();
                                frame.setTitle("Login");
                                frame.setSize(350, 200);
                                frame.setContentPane(new MenuAdmin().menuAd);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setVisible(true);

                                ((JFrame) SwingUtilities.getWindowAncestor(logInButton)).dispose();
                            }
                        }else {
                            resultado.setText("Digite correctamente las credenciales");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else if (Seleccionador.getSelectedItem().toString().equals("Usuario")){

                    String url = "jdbc:mysql://ul1p0vkekpygav1f:g4TMqDpYcFoAkQKd4tWx@bjaibh7r281ex1wojndt-mysql.services.clever-cloud.com:3306/bjaibh7r281ex1wojndt";
                    String usuario = "ul1p0vkekpygav1f";
                    String password = "g4TMqDpYcFoAkQKd4tWx";

                    try (Connection connection = DriverManager.getConnection(url, usuario, password)) {
                        Statement statement = connection.createStatement();
                        String query = "SELECT * FROM usuarios WHERE username = '" + textField1.getText() + "' AND password = '" + new String(passwordField1.getPassword()) + "'";
                        ResultSet resultSet = statement.executeQuery(query);


                        if(resultSet.next()) {
                            if(textField1.getText().equals(resultSet.getString("usuario")) && new String(passwordField1.getPassword()).equals(resultSet.getString("contraseña"))) {
                                JFrame frame = new JFrame();
                                frame.setTitle("Login");
                                frame.setSize(350, 200);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setVisible(true);

                                ((JFrame) SwingUtilities.getWindowAncestor(logInButton)).dispose();
                            }
                        }else {
                            resultado.setText("Digite correctamente las credenciales");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
