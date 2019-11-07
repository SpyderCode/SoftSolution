package Clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Logica.Conexion;

import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Label;
import com.jgoodies.forms.factories.DefaultComponentFactory;
public class Productos extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Productos frame = new Productos();
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
	
	public Productos() {
		getContentPane().setLayout(null);
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 436);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setForeground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			private JEditorPane idProducto;
			private JEditorPane nombre;
		    private JEditorPane descripcion;
			private JEditorPane cantidad;
		    private JEditorPane marca;
			
			
			
			
		
			

			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				Conexion ObjCon = new Conexion();
				Connection conn = ObjCon.getConection();
				ps = conn.prepareStatement("INSERT INTO inventario(idProducto,nombre,descripcion,cantidad,marca) VALUES(?,?,?,?,?)");

				idProducto = null;
		        ps.setString(1, idProducto.getText());
				cantidad = null;
				ps.setString(2, cantidad.getText());
				marca = null;
				ps.setString(3, marca.getText());
				nombre = null;
				ps.setString(4, nombre.getText());		
				descripcion = null;
				ps.setString(4, descripcion.getText());	
				ps.execute();				
				
				JOptionPane.showMessageDialog(null," Guardado Correctamente");
				

				} catch (Exception ex) {

				JOptionPane.showMessageDialog(null,"Error al Guardar");
				System.out.println(ex);
				}
			}
		});
		btnNewButton.setBounds(66, 338, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Actualizar");
		btnNewButton_1.setBounds(217, 338, 105, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				Conexion ObjCon = new Conexion();
				Connection conn = ObjCon.getConection();
    int Fila = table.getSelectedRow();
    String idObj = table.getValueAt(Fila, 0).toString();

    ps = conn.prepareStatement("DELETE FROM inventario WHERE id=?");
    ps.setString(1, idObj);
    ps.execute();

//modelo.removeRow(Fila);
 } catch (SQLException ex) {
System.out.println(ex.toString());
} 
			}
		});
		
		btnNewButton_2.setBounds(353, 338, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ver Datos");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Campo = lblBuscar.getText();
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
					
					String sql = "SELECT idProducto,marca,nombre,cantidad,descripcion  FROM inventario " + where;
					System.out.println(sql);
					ps = (PreparedStatement) con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					java.sql.ResultSetMetaData rsMd =  rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					
					modelo.addColumn("id");
					modelo.addColumn("Nombre");
			        modelo.addColumn("Marca");
			        modelo.addColumn("Descripcion");
			        modelo.addColumn("Cantidad");
			        
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
		btnNewButton_3.setBounds(509, 338, 119, 23);
		contentPane.add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(80, 75, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 118, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(80, 161, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(80, 205, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(80, 254, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 51, 463, 248);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, "", null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Nombre", "Marca", "Descripcion", "Cantidad"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblId = DefaultComponentFactory.getInstance().createLabel("ID:");
		lblId.setBounds(10, 78, 51, 14);
		contentPane.add(lblId);
		
		JLabel lblNombre = DefaultComponentFactory.getInstance().createLabel("Nombre:");
		lblNombre.setBounds(10, 121, 92, 14);
		contentPane.add(lblNombre);
		
		JLabel lblMarca = DefaultComponentFactory.getInstance().createLabel("Marca:");
		lblMarca.setBounds(10, 164, 92, 14);
		contentPane.add(lblMarca);
		
		JLabel lblDes = DefaultComponentFactory.getInstance().createLabel("Descripcion:");
		lblDes.setBounds(10, 208, 92, 14);
		contentPane.add(lblDes);
		
		JLabel lblCantidad = DefaultComponentFactory.getInstance().createLabel("Cantidad:");
		lblCantidad.setBounds(10, 257, 92, 14);
		contentPane.add(lblCantidad);
		
		JButton btnNewButton_4 = new JButton("Regresar");
		btnNewButton_4.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				Menu mn = new Menu();
				mn.setVisible(true);
				Productos.this.dispose();
			}
		});
		btnNewButton_4.setBounds(644, 17, 89, 23);
		contentPane.add(btnNewButton_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(80, 296, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblBuscar = DefaultComponentFactory.getInstance().createLabel("Buscar:");
		lblBuscar.setBounds(17, 299, 44, 14);
		contentPane.add(lblBuscar);
		
	}

}
