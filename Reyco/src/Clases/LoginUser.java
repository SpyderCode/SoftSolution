package Clases;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Logica.Conexion;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 *  grant all privileges on reyco.* to 'invitado'@'localhost' identified by 'invitado'
     with grant option;

 grant all privileges on reyco.* to 'Administrador'@'localhost' identified by 'admin'
     with grant option;
 */
public class LoginUser extends JFrame {

	private JPanel contentPane;
	private JTextField usuariotxt;
	private JPasswordField passwordtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					LoginUser frame = new LoginUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logica.Conexion.setcuenta(usuariotxt.getText(), passwordtxt.getText());
				Logica.Conexion.getConection();
				if(Logica.Conexion.getstatus()){
		     Menu obj = new Menu();
			     obj.setVisible(true);
				}
				
				else {
				   JOptionPane.showMessageDialog(null, "Usuario y password incorrectos ","Error de conexion",
							JOptionPane.ERROR_MESSAGE);
				   usuariotxt.setText("");
				   passwordtxt.setText("");
			     		
		}
				
			}
		});
		btnNewButton.setBounds(336, 186, 85, 23);
		contentPane.add(btnNewButton);
		
		usuariotxt = new JTextField();
		usuariotxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordtxt.requestFocus();
			}
		});
		usuariotxt.setColumns(10);
		usuariotxt.setBounds(67, 186, 82, 23);
		contentPane.add(usuariotxt);
		
		JLabel label = new JLabel("Usuario:");
		label.setForeground(Color.RED);
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		label.setBounds(10, 189, 47, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Contrase\u00F1a:");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		label_1.setBounds(159, 189, 75, 14);
		contentPane.add(label_1);
		
		passwordtxt = new JPasswordField();
		passwordtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					Logica.Conexion.setcuenta(usuariotxt.getText(), passwordtxt.getText());
					Logica.Conexion.getConection();
					if(Logica.Conexion.getstatus()){
				     Menu obj = new Menu();
				     obj.setVisible(true);
					}
					
					else {
					   JOptionPane.showMessageDialog(null, "Usuario y password incorrectos ","Error de conexion",
								JOptionPane.ERROR_MESSAGE);
					   usuariotxt.setText("");
					   passwordtxt.setText("");
				   } 												
				}
			}
		});
		passwordtxt.setBounds(237, 186, 89, 23);
		contentPane.add(passwordtxt);
		
		JLabel Jlabelimage = new JLabel("");
		Jlabelimage.setIcon(new ImageIcon(LoginUser.class.getResource("/Imagenes/2.jpg")));
		Jlabelimage.setBounds(0, 0, 427, 183);
		contentPane.add(Jlabelimage);
	}
}
