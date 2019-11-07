package Clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

//import com.mysql.jdbc.PreparedStatement;
import Logica.Conexion;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Control.Bitacora;
import javax.swing.JMenuBar;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 469);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 624, 21);
		contentPane.add(menuBar);
		
		JButton btnNewButton = new JButton("Pedidos");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pedidos ped = new Pedidos();
				ped.setVisible(true);
				Menu.this.dispose();
			}
		});
		
		JButton btnNewButton_4 = new JButton("Inventario");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Productos prod = new Productos();
				prod.setVisible(true);
				Menu.this.dispose();
			}
		});
		menuBar.add(btnNewButton_4);
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exhibicion");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exhibicion ex = new Exhibicion();
				ex.setVisible(true);
				Menu.this.dispose();
			}
		});
		menuBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Apartado");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Apartado ap = new Apartado();
				ap.setVisible(true);
				Menu.this.dispose();
			}
		});
		menuBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cerrar Sesion");
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUser log = new LoginUser();
				log.setVisible(false);
				Menu.this.dispose();
			}
		});
		
		JButton btnControlUsuarios = new JButton("Control Usuarios");
		btnControlUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bitacora bi = new Bitacora();
				bi.setVisible(true);
				Menu.this.dispose();
			}
		});
		btnControlUsuarios.setBackground(Color.WHITE);
		menuBar.add(btnControlUsuarios);
		menuBar.add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setForeground(Color.RED);
		panel.setBounds(10, 11, 749, 395);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("G:\\ProyectoReyco\\src\\Imagenes\\edit2.png"));
		lblNewLabel.setBounds(180, 0, 657, 421);
		panel.add(lblNewLabel);
	}
}
