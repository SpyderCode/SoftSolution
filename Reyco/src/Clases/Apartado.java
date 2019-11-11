package Clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Logica.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Apartado extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField id;
	private JTextField nombre;
	private JTextField direccion;
	private JTextField estatus;
	private JTextField buscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apartado frame = new Apartado();
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
	public Apartado() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Apartado.class.getResource("/iconos/LogoInter.png")));
		setResizable(false);
		setTitle("Apartados ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Apartado.class.getResource("/iconos/REYCOLogoMediano.png")));
		label_1.setBounds(0, 0, 200, 75);
		contentPane.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(204, 65, 490, 140);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setShowGrid(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nombre", "Direccion", "Estatus De Entrega"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("ID:");
		label.setForeground(SystemColor.text);
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(10, 86, 25, 14);
		contentPane.add(label);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(73, 85, 121, 20);
		contentPane.add(id);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(SystemColor.text);
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNombre.setBounds(0, 117, 66, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(73, 116, 121, 20);
		contentPane.add(nombre);
		
		direccion = new JTextField();
		direccion.setColumns(10);
		direccion.setBounds(73, 147, 121, 20);
		contentPane.add(direccion);
		
		JLabel lblDomicilio = new JLabel("Direccion:");
		lblDomicilio.setForeground(SystemColor.text);
		lblDomicilio.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDomicilio.setBounds(0, 148, 66, 14);
		contentPane.add(lblDomicilio);
		
		JLabel lblEstatus = new JLabel("Estatus:");
		lblEstatus.setForeground(SystemColor.text);
		lblEstatus.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEstatus.setBounds(0, 191, 66, 14);
		contentPane.add(lblEstatus);
		
		estatus = new JTextField();
		estatus.setColumns(10);
		estatus.setBounds(73, 185, 121, 20);
		contentPane.add(estatus);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(SystemColor.text);
		lblBuscar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBuscar.setBounds(10, 311, 61, 14);
		contentPane.add(lblBuscar);
		
		JButton button = new JButton("Guardar");
		button.setBackground(SystemColor.menu);
		button.setIcon(new ImageIcon(Apartado.class.getResource("/iconos/guardar.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				//Conexion ObjCon = new Conexion();
				Connection conn = Conexion.getConection();
				ps = conn.prepareStatement("INSERT INTO apartado(nombre, direccion, estatus, idapartado) VALUES(?,?,?,?)");			
				ps.setString(1, nombre.getText());
				ps.setString(2, direccion.getText());
				ps.setString(3, estatus.getText());
				ps.setString(4, id.getText());
				ps.execute();				
				
				JOptionPane.showMessageDialog(null," Guardado");
				

				} catch (Exception ex) {

				JOptionPane.showMessageDialog(null,"Error al Guardar");
				System.out.println(ex);
				}
			}

			
		});
		button.setBounds(237, 303, 114, 35);
		contentPane.add(button);
		
		buscar = new JTextField();
		buscar.setColumns(10);
		buscar.setBounds(73, 310, 121, 20);
		contentPane.add(buscar);
		
		JButton button_1 = new JButton("Eliminar");
		button_1.setBackground(SystemColor.menu);
		button_1.setIcon(new ImageIcon(Apartado.class.getResource("/iconos/eliminar.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				Conexion ObjCon = new Conexion();
				Connection conn = ObjCon.getConection();
    int Fila = table.getSelectedRow();
    String idObj = table.getValueAt(Fila, 0).toString();

    ps = conn.prepareStatement("DELETE FROM apartado WHERE idapartado=?");
    ps.setString(1, idObj);
    ps.execute();

//modelo.removeRow(Fila);
 } catch (SQLException ex) {
System.out.println(ex.toString());
} 
			}
		});
		button_1.setBounds(407, 303, 121, 35);
		contentPane.add(button_1);
		
		JButton button_3 = new JButton("Ver datos");
		button_3.setIcon(new ImageIcon(Apartado.class.getResource("/iconos/MostraV2.png")));
		button_3.setBackground(SystemColor.menu);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Campo = buscar.getText();
				String where = "";

				if (!"".equals(Campo))
				{
				where = "WHERE idapartado = '" + Campo + "'";
				}
				try {
					DefaultTableModel modelo = new DefaultTableModel();
					table.setModel(modelo);
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = Conexion.getConection();
					
					String sql = "SELECT idapartado, nombre, direccion, estatus  FROM apartado " + where;
					System.out.println(sql);
					ps = (PreparedStatement) con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					java.sql.ResultSetMetaData rsMd =  rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					
					modelo.addColumn("id");
					modelo.addColumn("nombre");
			        modelo.addColumn("direccion");
			        modelo.addColumn("estatus");
			        
			                int[] anchos = {50, 100, 100, 120, 100, 100, 70};
			        for (int x = 0; x < cantidadColumnas; x++) {
			        table.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
			        }
					while (rs.next()) {
						Object[] filas = new Object[cantidadColumnas];
						for (int i = 0; i< cantidadColumnas; i++)
						{
							filas[i] = rs.getObject(i + 1);
						}
						modelo.addRow(filas);
					}
						
				} catch (SQLException ex) {
					System.err.println(ex.toString());
				 }
			}
		});
		button_3.setBounds(573, 303, 121, 35);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Regresar");
		button_4.setBackground(SystemColor.menu);
		button_4.setIcon(new ImageIcon(Apartado.class.getResource("/iconos/regresar.png")));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn = new Menu();
				mn.setVisible(true);
				Apartado.this.dispose();
			}
			
		});
		button_4.setBounds(573, 11, 121, 29);
		contentPane.add(button_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Apartado.class.getResource("/Imagenes/IMG_1092.png")));
		lblNewLabel.setBounds(0, 0, 714, 371);
		contentPane.add(lblNewLabel);
	}
	

}
