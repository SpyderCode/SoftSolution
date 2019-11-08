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

public class Exhibicion extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField id;
	private JTextField pieza;
	private JTextField marca;
	private JTextField producto;
	private JTextField buscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exhibicion frame = new Exhibicion();
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
	public Exhibicion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 441);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 66, 349, 155);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Pieza", "Marca", "Producto"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("ID:");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(10, 73, 25, 14);
		contentPane.add(label);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(80, 70, 121, 20);
		contentPane.add(id);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCantidad.setBounds(10, 108, 61, 14);
		contentPane.add(lblCantidad);
		
		pieza = new JTextField();
		pieza.setColumns(10);
		pieza.setBounds(80, 101, 121, 20);
		contentPane.add(pieza);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblMarca.setBounds(10, 133, 61, 14);
		contentPane.add(lblMarca);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblProducto.setBounds(10, 163, 61, 14);
		contentPane.add(lblProducto);
		
		marca = new JTextField();
		marca.setColumns(10);
		marca.setBounds(80, 132, 121, 20);
		contentPane.add(marca);
		
		producto = new JTextField();
		producto.setColumns(10);
		producto.setBounds(80, 162, 121, 20);
		contentPane.add(producto);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBuscar.setBounds(10, 188, 61, 20);
		contentPane.add(lblBuscar);
		
		buscar = new JTextField();
		buscar.setColumns(10);
		buscar.setBounds(80, 190, 121, 20);
		contentPane.add(buscar);
		
		JButton button = new JButton("Guardar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.PreparedStatement ps = null;
				try {
				Conexion ObjCon = new Conexion();
				Connection conn = ObjCon.getConection();
				ps = conn.prepareStatement("INSERT INTO Exhibicion() VALUES(?,?,?,?)");

				ps.setString(1, id.getText());
				ps.setString(2, pieza.getText());
				ps.setString(3, marca.getText());
				ps.setString(4, producto.getText());			
				ps.execute();				
				
				JOptionPane.showMessageDialog(null,"Id Guardado");
				

				} catch (Exception ex) {

				JOptionPane.showMessageDialog(null,"Error al Guardar");
				System.out.println(ex);
				}
			}

			
		});
		button.setBounds(34, 278, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Eliminar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.PreparedStatement ps = null;
				try {
				Conexion ObjCon = new Conexion();
				Connection conn = ObjCon.getConection();
    int Fila = table.getSelectedRow();
    String idObj = table.getValueAt(Fila, 0).toString();

    ps = conn.prepareStatement("DELETE FROM Exhibicion WHERE id=?");
    ps.setString(1, idObj);
    ps.execute();

//modelo.removeRow(Fila);
 } catch (SQLException ex) {
System.out.println(ex.toString());
} 
			}
		});
		button_1.setBounds(160, 278, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Actualizar");
		button_2.setBounds(314, 278, 106, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Ver datos");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					
					String sql = "SELECT id, nombre, direccion, Estatus  FROM exhibicion " + where;
					System.out.println(sql);
					ps = (PreparedStatement) con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					java.sql.ResultSetMetaData rsMd =  rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					
					modelo.addColumn("id");
					modelo.addColumn("pieza");
			        modelo.addColumn("marca");
			        modelo.addColumn("Producto");
			        
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
		button_3.setBounds(465, 278, 89, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Regresar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn = new Menu();
				mn.setVisible(true);
				Exhibicion.this.dispose();
			}
		});
		button_4.setBounds(483, 21, 89, 23);
		contentPane.add(button_4);
	}

}
