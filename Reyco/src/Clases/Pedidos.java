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
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Pedidos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pedidos frame = new Pedidos();
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
	public Pedidos() {
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 436);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setForeground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(331, 181, 420, 153);
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
			},
			new String[] {
				"ID","producto","marca","cantidad"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblId.setBounds(10, 245, 25, 14);
		contentPane.add(lblId);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(81, 244, 121, 20);
		contentPane.add(textField);
		
		JLabel lblPieza = new JLabel("Marca:");
		lblPieza.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPieza.setBounds(10, 276, 61, 14);
		contentPane.add(lblPieza);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(81, 275, 121, 20);
		contentPane.add(textField_1);
		
		JLabel lblProducto = new JLabel("Cantidad:");
		lblProducto.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblProducto.setBounds(10, 307, 61, 14);
		contentPane.add(lblProducto);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(81, 306, 121, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(81, 367, 121, 20);
		contentPane.add(textField_4);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBuscar.setBounds(10, 368, 61, 14);
		contentPane.add(lblBuscar);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			private JEditorPane producto;
			private JEditorPane marca;
			private JEditorPane cantidad;
			private JEditorPane ID;

			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				Conexion ObjCon = new Conexion();
				Connection conn = ObjCon.getConection();
				ps = conn.prepareStatement("INSERT INTO pedidos(ID,marca,cantidad,producto) VALUES(?,?,?,?)");

				ID = null;
				ps.setString(1, ID.getText());
				cantidad = null;
				ps.setString(2, cantidad.getText());
				marca = null;
				ps.setString(3, marca.getText());
				producto = null;
				ps.setString(4, producto.getText());			
				ps.execute();				
				
				JOptionPane.showMessageDialog(null," Guardado Correctamente");
				

				} catch (Exception ex) {

				JOptionPane.showMessageDialog(null,"Error al Guardar");
				System.out.println(ex);
				}
			}

			
		});
		btnNewButton.setBounds(259, 366, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				Conexion ObjCon = new Conexion();
				Connection conn = ObjCon.getConection();
    int Fila = table.getSelectedRow();
    String idObj = table.getValueAt(Fila, 0).toString();

    ps = conn.prepareStatement("DELETE FROM pedidos WHERE id=?");
    ps.setString(1, idObj);
    ps.execute();

//modelo.removeRow(Fila);
 } catch (SQLException ex) {
System.out.println(ex.toString());
} 
			}
		});
		btnNewButton_1.setBounds(532, 367, 89, 20);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Actualizar");
		btnNewButton_2.setBounds(383, 366, 101, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ver datos");
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
					
					String sql = "SELECT ID,marca,producto,cantidad  FROM pedidos " + where;
					System.out.println(sql);
					ps = (PreparedStatement) con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					java.sql.ResultSetMetaData rsMd =  rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					
					modelo.addColumn("id");
					modelo.addColumn("Pieza");
			        modelo.addColumn("Marca");
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
		btnNewButton_3.setBounds(662, 366, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu mn = new Menu();
				mn.setVisible(true);
				Pedidos.this.dispose();
			}
		});
		btnRegresar.setBounds(662, 12, 89, 23);
		contentPane.add(btnRegresar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Pedidos.class.getResource("/Imagenes/2.jpg")));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setBounds(0, 11, 420, 169);
		contentPane.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(81, 336, 121, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblProducto_1 = new JLabel("Producto:");
		lblProducto_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblProducto_1.setBounds(10, 343, 61, 14);
		contentPane.add(lblProducto_1);
	}
}
