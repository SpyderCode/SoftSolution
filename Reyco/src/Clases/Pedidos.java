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
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Pedidos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField id;
	private JTextField marca1;
	private JTextField cantidad1;
	private JTextField buscar;
	private JTextField producto1;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Pedidos.class.getResource("/iconos/LogoInter.png")));
		setTitle("Pedidos");
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 436);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setForeground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Pedidos.class.getResource("/iconos/REYCOLogoMediano.png")));
		label_1.setBounds(-5, 0, 207, 73);
		contentPane.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(313, 67, 423, 200);
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
			},
			new String[] {
				"ID","producto","marca","cantidad"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblId.setBounds(25, 103, 25, 14);
		contentPane.add(lblId);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(81, 102, 121, 20);
		contentPane.add(id);
		
		JLabel lblPieza = new JLabel("Marca:");
		lblPieza.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPieza.setBounds(10, 192, 61, 14);
		contentPane.add(lblPieza);
		
		marca1 = new JTextField();
		marca1.setColumns(10);
		marca1.setBounds(81, 146, 121, 20);
		contentPane.add(marca1);
		
		JLabel lblProducto = new JLabel("Cantidad:");
		lblProducto.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblProducto.setBounds(10, 228, 61, 14);
		contentPane.add(lblProducto);
		
		cantidad1 = new JTextField();
		cantidad1.setColumns(10);
		cantidad1.setBounds(81, 191, 121, 20);
		contentPane.add(cantidad1);
		
		buscar = new JTextField();
		buscar.setColumns(10);
		buscar.setBounds(81, 352, 121, 20);
		contentPane.add(buscar);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBuscar.setBounds(10, 353, 61, 14);
		contentPane.add(lblBuscar);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setIcon(new ImageIcon(Pedidos.class.getResource("/iconos/guardar.png")));
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
		       //Conexion ObjCon = new Conexion();
				Connection conn = Conexion.getConection();
				ps = conn.prepareStatement("INSERT INTO pedidos(ID,marca,producto,cantidad) VALUES(?,?,?,?)");
				ps.setString(1, id.getText());
		        ps.setString(2, marca1.getText());
				ps.setString(3, cantidad1.getText());
				ps.setString(4, producto1.getText());
				ps.execute();				
				
				JOptionPane.showMessageDialog(null,"Id Guardado");
				

				} catch (Exception ex) {

				JOptionPane.showMessageDialog(null,"Error al Guardar");
				System.out.println(ex);
				}
			}		
		});
		btnNewButton.setBounds(307, 339, 129, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setIcon(new ImageIcon(Pedidos.class.getResource("/iconos/eliminar.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
				@SuppressWarnings("unused")
				Conexion ObjCon = new Conexion();
				Connection conn = Conexion.getConection();
    int Fila = table.getSelectedRow();
    String idObj = table.getValueAt(Fila, 0).toString();

    ps = conn.prepareStatement("DELETE FROM pedidos WHERE ID=?");
    ps.setString(1, idObj);
    ps.execute();

//modelo.removeRow(Fila);
 } catch (SQLException ex) {
System.out.println(ex.toString());
} 
			}
		});
		btnNewButton_1.setBounds(479, 337, 109, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Ver datos");
		btnNewButton_3.setIcon(new ImageIcon(Pedidos.class.getResource("/iconos/MostraV2.png")));
		btnNewButton_3.setBackground(SystemColor.menu);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Campo = buscar.getText();
				String where = "";

				if (!"".equals(Campo))
				{
				where = "WHERE ID = '" + Campo + "'";
				}
				try {
					DefaultTableModel modelo = new DefaultTableModel();
					table.setModel(modelo);
					PreparedStatement ps = null;
					ResultSet rs = null;
					@SuppressWarnings("unused")
					Conexion conn = new Conexion();
					Connection con = Conexion.getConection();
					
					String sql = "SELECT ID,marca,producto,cantidad  FROM pedidos " + where;
					System.out.println(sql);
					ps = (PreparedStatement) con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					java.sql.ResultSetMetaData rsMd =  rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					
					modelo.addColumn("id");
					modelo.addColumn("marca");
			        modelo.addColumn("producto");
			        modelo.addColumn("cantidad");
			        
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
		btnNewButton_3.setBounds(614, 339, 122, 35);
		contentPane.add(btnNewButton_3);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setIcon(new ImageIcon(Pedidos.class.getResource("/iconos/regresar.png")));
		btnRegresar.setBackground(SystemColor.menu);
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu mn = new Menu();
				mn.setVisible(true);
				Pedidos.this.dispose();
			}
		});
		btnRegresar.setBounds(614, 11, 121, 31);
		contentPane.add(btnRegresar);
		
		producto1 = new JTextField();
		producto1.setBounds(81, 229, 121, 20);
		contentPane.add(producto1);
		producto1.setColumns(10);
		
		JLabel lblProducto_1 = new JLabel("Producto:");
		lblProducto_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblProducto_1.setBounds(10, 147, 61, 14);
		contentPane.add(lblProducto_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Pedidos.class.getResource("/Imagenes/IMG_1091.png")));
		label.setBounds(0, 0, 761, 397);
		contentPane.add(label);
	}
}
