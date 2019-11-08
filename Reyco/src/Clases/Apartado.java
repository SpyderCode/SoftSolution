package Clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//import com.mysql.jdbc.PreparedStatement;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(204, 65, 490, 140);
		contentPane.add(scrollPane);
		
		table = new JTable();
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
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(10, 86, 25, 14);
		contentPane.add(label);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(73, 85, 121, 20);
		contentPane.add(id);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNombre.setBounds(4, 111, 66, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(73, 110, 121, 20);
		contentPane.add(nombre);
		
		direccion = new JTextField();
		direccion.setColumns(10);
		direccion.setBounds(73, 135, 121, 20);
		contentPane.add(direccion);
		
		JLabel lblDomicilio = new JLabel("Direccion:");
		lblDomicilio.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDomicilio.setBounds(4, 136, 66, 14);
		contentPane.add(lblDomicilio);
		
		JLabel lblEstatus = new JLabel("Estatus:");
		lblEstatus.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEstatus.setBounds(4, 161, 66, 14);
		contentPane.add(lblEstatus);
		
		estatus = new JTextField();
		estatus.setColumns(10);
		estatus.setBounds(73, 160, 121, 20);
		contentPane.add(estatus);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBuscar.setBounds(4, 186, 61, 14);
		contentPane.add(lblBuscar);
		
		JButton button = new JButton("Guardar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				Conexion ObjCon = new Conexion();
				Connection conn = ObjCon.getConection();
				ps = conn.prepareStatement("INSERT INTO apartado(idapartado, nombre, direccion, estatus) VALUES(?,?,?,?)");

				ps.setString(1, id.getText());
				ps.setString(2, nombre.getText());
				ps.setString(3, direccion.getText());
				ps.setString(4, estatus.getText());			
				ps.execute();				
				
				JOptionPane.showMessageDialog(null," Guardado");
				

				} catch (Exception ex) {

				JOptionPane.showMessageDialog(null,"Error al Guardar");
				System.out.println(ex);
				}
			}

			
		});
		button.setBounds(262, 315, 89, 23);
		contentPane.add(button);
		
		buscar = new JTextField();
		buscar.setColumns(10);
		buscar.setBounds(73, 185, 121, 20);
		contentPane.add(buscar);
		
		JButton button_1 = new JButton("Eliminar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				Conexion ObjCon = new Conexion();
				Connection conn = ObjCon.getConection();
    int Fila = table.getSelectedRow();
    String idObj = table.getValueAt(Fila, 0).toString();

    ps = conn.prepareStatement("DELETE FROM apartado WHERE id=?");
    ps.setString(1, idObj);
    ps.execute();

//modelo.removeRow(Fila);
 } catch (SQLException ex) {
System.out.println(ex.toString());
} 
			}
		});
		button_1.setBounds(425, 315, 89, 23);
		contentPane.add(button_1);
		
		JButton button_3 = new JButton("Ver datos");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Campo = buscar.getText();
				String where = "";

				if (!"".equals(Campo))
				{
				where = "WHERE id = '" + Campo + "'";
				}
				try {
					DefaultTableModel modelo = new DefaultTableModel();
					table.setModel(modelo);
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = conn.getConection();
					
					String sql = "SELECT idproducto, nombre, direccion, estatus  FROM apartado " + where;
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
		button_3.setBounds(605, 315, 89, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Regresar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn = new Menu();
				mn.setVisible(true);
				Apartado.this.dispose();
			}
			
		});
		button_4.setBounds(605, 11, 89, 23);
		contentPane.add(button_4);
	}
	

}
