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
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Exhibicion extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField id;
	private JTextField cantidad;
	private JTextField marca;
	private JTextField producto;
	private JTextField buscar;
	private JTextField descripcion;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exhibicion.class.getResource("/iconos/LogoInter.png")));
		setTitle("Inventario En Exhibicion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 441);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(75, 196, 121, 20);
		contentPane.add(descripcion);
		
		JLabel lblDescripcion = new JLabel("descripcion");
		lblDescripcion.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDescripcion.setBounds(0, 199, 79, 14);
		contentPane.add(lblDescripcion);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Exhibicion.class.getResource("/iconos/REYCOLogoMediano.png")));
		label_2.setBounds(20, 0, 191, 79);
		contentPane.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 64, 474, 217);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setShowGrid(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "cantidad", "Marca", "Producto", "Descripcion"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("ID:");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(20, 69, 25, 14);
		contentPane.add(label);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(75, 68, 121, 20);
		contentPane.add(id);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCantidad.setBounds(0, 108, 61, 14);
		contentPane.add(lblCantidad);
		
		cantidad = new JTextField();
		cantidad.setColumns(10);
		cantidad.setBounds(75, 107, 121, 20);
		contentPane.add(cantidad);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblMarca.setBounds(10, 141, 61, 14);
		contentPane.add(lblMarca);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblProducto.setBounds(0, 166, 61, 14);
		contentPane.add(lblProducto);
		
		marca = new JTextField();
		marca.setColumns(10);
		marca.setBounds(75, 138, 121, 20);
		contentPane.add(marca);
		
		producto = new JTextField();
		producto.setColumns(10);
		producto.setBounds(75, 165, 121, 20);
		contentPane.add(producto);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBuscar.setBounds(10, 350, 61, 20);
		contentPane.add(lblBuscar);
		
		buscar = new JTextField();
		buscar.setColumns(10);
		buscar.setBounds(80, 352, 121, 20);
		contentPane.add(buscar);
		
		JButton button = new JButton("Guardar");
		button.setBackground(SystemColor.menu);
		button.setIcon(new ImageIcon(Exhibicion.class.getResource("/iconos/guardar.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.PreparedStatement ps = null;
				try {
		       //Conexion ObjCon = new Conexion();
				Connection conn = Conexion.getConection();
				ps = conn.prepareStatement("INSERT INTO exhibicion(idProducto, cantidad, marca, prodcuto,descripcion) VALUES(?,?,?,?,?)");
				ps.setString(1, id.getText());
				ps.setString(2, cantidad.getText());
				ps.setString(3, marca.getText());
				ps.setString(4, producto.getText());
				ps.setString(5, descripcion.getText());
				ps.execute();				
				
				JOptionPane.showMessageDialog(null,"Id Guardado");
				

				} catch (Exception ex) {

				JOptionPane.showMessageDialog(null,"Error al Guardar");
				System.out.println(ex);
				}
			}

			
		});
		button.setBounds(239, 350, 139, 41);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Eliminar");
		button_1.setBackground(SystemColor.menu);
		button_1.setIcon(new ImageIcon(Exhibicion.class.getResource("/iconos/eliminar.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.PreparedStatement ps = null;
				try {
		//		Conexion ObjCon = new Conexion();
				Connection conn = Conexion.getConection();
    int Fila = table.getSelectedRow();
    String idObj = table.getValueAt(Fila, 0).toString();

    ps = conn.prepareStatement("DELETE FROM Exhibicion WHERE idProducto=?");
    ps.setString(1, idObj);
    ps.execute();

//modelo.removeRow(Fila);
 } catch (SQLException ex) {
System.out.println(ex.toString());
} 
			}
		});
		button_1.setBounds(416, 350, 121, 41);
		contentPane.add(button_1);
		
		JButton button_3 = new JButton("Ver datos");
		button_3.setBackground(SystemColor.menu);
		button_3.setIcon(new ImageIcon(Exhibicion.class.getResource("/iconos/MostraV2.png")));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Campo = buscar.getText();
				String where = "";

				if (!"".equals(Campo))
				{
				where = "WHERE idProducto = '" + Campo + "'";
				}
				try {
					DefaultTableModel modelo = new DefaultTableModel();
					table.setModel(modelo);
					PreparedStatement ps = null;
					ResultSet rs = null;
					@SuppressWarnings("unused")
					Conexion conn = new Conexion();
					Connection con = Conexion.getConection();
					
					String sql = "SELECT idProducto, cantidad, marca, prodcuto,descripcion  FROM exhibicion " + where;
					System.out.println(sql);
					ps = (PreparedStatement) con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					java.sql.ResultSetMetaData rsMd =  rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					
					modelo.addColumn("id");
					modelo.addColumn("pieza");
			        modelo.addColumn("marca");
			        modelo.addColumn("Producto");
			        modelo.addColumn("descripcion");
			        
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
		button_3.setBounds(573, 350, 127, 41);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Regresar");
		button_4.setIcon(new ImageIcon(Exhibicion.class.getResource("/iconos/regresar.png")));
		button_4.setBackground(SystemColor.menu);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn = new Menu();
				mn.setVisible(true);
				Exhibicion.this.dispose();
			}
		});
		button_4.setBounds(573, 22, 127, 31);
		contentPane.add(button_4);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Exhibicion.class.getResource("/Imagenes/IMG_1091.png")));
		label_1.setBounds(0, 0, 725, 402);
		contentPane.add(label_1);
	}
}
