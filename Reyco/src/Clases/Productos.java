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
import java.awt.SystemColor;
import java.awt.Toolkit;
public class Productos extends JFrame{
	private JPanel contentPane;
	private JTextField idProducto;
	private JTextField Nombre;
	private JTextField Marca;
	private JTextField Descripcion;
	private JTextField Cantidad;
	private JTable table;
	private JTextField buscar;

	
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Productos.class.getResource("/iconos/LogoInter.png")));
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setForeground(Color.BLACK);
		setBounds(100, 100, 776, 500);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.windowBorder);
		contentPane.setForeground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btGuardar = new JButton("Guardar");
		btGuardar.setBackground(SystemColor.menu);
		btGuardar.setIcon(new ImageIcon(Productos.class.getResource("/iconos/guardar.png")));
		btGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
		       //Conexion ObjCon = new Conexion();
				Connection conn = Conexion.getConection();
				ps = conn.prepareStatement("INSERT INTO inventario(idProducto,nombre,marca,descripcion,Cantidad) VALUES(?,?,?,?,?)");
				ps.setString(1, idProducto.getText());
		        ps.setString(2, Cantidad.getText());
				ps.setString(3, Marca.getText());
				ps.setString(4, Descripcion.getText());
				ps.setString(5, Cantidad.getText());
				ps.execute();				
				
				JOptionPane.showMessageDialog(null,"Id Guardado");
				

				} catch (Exception ex) {

				JOptionPane.showMessageDialog(null,"Error al Guardar");
				System.out.println(ex);
				}
			}		
		});
		btGuardar.setBounds(292, 415, 110, 33);
		contentPane.add(btGuardar);
		
		JButton BtEliminar = new JButton("Eliminar");
		BtEliminar.setBackground(SystemColor.menu);
		BtEliminar.setIcon(new ImageIcon(Productos.class.getResource("/iconos/eliminar.png")));
		BtEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				@SuppressWarnings("unused")
				Conexion ObjCon = new Conexion();
				Connection conn = Conexion.getConection();
    int Fila = table.getSelectedRow();
    String idObj = table.getValueAt(Fila, 0).toString();

    ps = conn.prepareStatement("DELETE FROM inventario WHERE idProducto=?");
    ps.setString(1, idObj);
    ps.execute();

//modelo.removeRow(Fila);
 } catch (SQLException ex) {
System.out.println(ex.toString());
} 
			}
		});
		
		BtEliminar.setBounds(453, 415, 119, 33);
		contentPane.add(BtEliminar);
		
		JButton Btver = new JButton("Ver Datos");
		Btver.setBackground(SystemColor.menu);
		Btver.setIcon(new ImageIcon(Productos.class.getResource("/iconos/MostraV2.png")));
		Btver.addActionListener(new ActionListener() {
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
					@SuppressWarnings("unused")
					Conexion conn = new Conexion();
					Connection con = Conexion.getConection();
					
					String sql = "SELECT idProducto,nombre,marca,descripcion,Cantidad  FROM inventario " + where;
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
		Btver.setBounds(601, 415, 133, 33);
		contentPane.add(Btver);
		
		idProducto = new JTextField();
		idProducto.setBounds(80, 133, 86, 20);
		contentPane.add(idProducto);
		idProducto.setColumns(10);
		
		Nombre = new JTextField();
		Nombre.setBounds(80, 177, 86, 20);
		contentPane.add(Nombre);
		Nombre.setColumns(10);
		
		Marca = new JTextField();
		Marca.setBounds(80, 223, 86, 20);
		contentPane.add(Marca);
		Marca.setColumns(10);
		
		Descripcion = new JTextField();
		Descripcion.setBounds(80, 272, 86, 20);
		contentPane.add(Descripcion);
		Descripcion.setColumns(10);
		
		Cantidad = new JTextField();
		Cantidad.setBounds(80, 320, 86, 20);
		contentPane.add(Cantidad);
		Cantidad.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 89, 463, 273);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setShowGrid(false);
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
		lblId.setBounds(19, 136, 51, 14);
		contentPane.add(lblId);
		
		JLabel lblNombre = DefaultComponentFactory.getInstance().createLabel("Nombre:");
		lblNombre.setBounds(10, 180, 60, 14);
		contentPane.add(lblNombre);
		
		JLabel lblMarca = DefaultComponentFactory.getInstance().createLabel("Marca:");
		lblMarca.setBounds(10, 226, 60, 14);
		contentPane.add(lblMarca);
		
		JLabel lblDes = DefaultComponentFactory.getInstance().createLabel("Descripcion:");
		lblDes.setBounds(10, 275, 74, 14);
		contentPane.add(lblDes);
		
		JLabel lblCantidad = DefaultComponentFactory.getInstance().createLabel("Cantidad:");
		lblCantidad.setBounds(10, 323, 68, 14);
		contentPane.add(lblCantidad);
		
		JButton btnNewButton_4 = new JButton("Regresar");
		btnNewButton_4.setBackground(SystemColor.menu);
		btnNewButton_4.setIcon(new ImageIcon(Productos.class.getResource("/iconos/regresar.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				Menu mn = new Menu();
				mn.setVisible(true);
				Productos.this.dispose();
			}
		});
		btnNewButton_4.setBounds(615, 21, 118, 31);
		contentPane.add(btnNewButton_4);
		
		buscar = new JTextField();
		buscar.setBounds(80, 415, 86, 20);
		contentPane.add(buscar);
		buscar.setColumns(10);
		
		JLabel lblBuscar = DefaultComponentFactory.getInstance().createLabel("Buscar:");
		lblBuscar.setBounds(17, 418, 44, 14);
		contentPane.add(lblBuscar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Productos.class.getResource("/iconos/REYCOLogoMediano.png")));
		label.setBounds(19, 11, 213, 71);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Productos.class.getResource("/Imagenes/REYCOTRA.png")));
		label_1.setBounds(0, 0, 760, 461);
		contentPane.add(label_1);
		
	}
   public void transparencia() {
	   
		}
   {
    	
     }
    	}
