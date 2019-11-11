package Control;

import java.awt.BorderLayout;
import Logica.Conexion;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;
import Clases.Menu;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Color;

public class Bitacora extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bitacora frame = new Bitacora();
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
	private final String base = "reyco";
	private final String URL ="jdbc:mysql://localhost:3306/" + base;
	private final String NOMBRE ="root";
	private final String Contraseña = "Marin389";
	private Connection con = null;
	
	 
	 public  Connection getConection()
	 {
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
				con = (Connection) DriverManager.getConnection(this.URL, this.NOMBRE, this.Contraseña);
		 }catch (SQLException e)
		 {
			 System.err.println(e);
		 }catch (ClassNotFoundException ex) {
			 Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 return con;
	 }


	public Bitacora() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Bitacora.class.getResource("/iconos/LogoInter.png")));
		setTitle("Registro De Movimientos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("CONSULTAR MOVIMIENTOS");
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.addActionListener(new ActionListener() {
			private ResultSet rs;

			public void actionPerformed(ActionEvent arg0) {
			//	String Campo = buscar.getText();
				String where = "";

			//	if (!"".equals(Campo))
				{
			//	where = "WHERE idProducto = '" + Campo + "'";
				}
				try {
					DefaultTableModel modelo = new DefaultTableModel();
					table.setModel(modelo);
					PreparedStatement ps = null;
					ResultSet rs = null;
					@SuppressWarnings("unused")
					Conexion conn = new Conexion();
					Connection con = Conexion.getConection();
					
					String sql = "SELECT id,operacion,usuario,host,modificado,tabla  FROM bitacora" + where;
					System.out.println(sql);
					ps = (PreparedStatement) con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					java.sql.ResultSetMetaData rsMd =  rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					
				        modelo.addColumn("id");
						modelo.addColumn("operacion");
				        modelo.addColumn("usuario");
				        modelo.addColumn("host");
				        modelo.addColumn("modificado");
				        modelo.addColumn("tabla");
			        
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
			
		btnNewButton.setBounds(484, 527, 191, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setIcon(new ImageIcon(Bitacora.class.getResource("/iconos/regresar.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn = new Menu();
				mn.setVisible(true);				
				Bitacora.this.dispose();
			}
		});
		btnNewButton_1.setBounds(742, 11, 132, 26);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 864, 437);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setShowGrid(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				java.sql.PreparedStatement ps = null;
				ResultSet rs = null;
				try {
								Conexion Con = new Conexion();
								Connection conn = Con.getConection();
								 
				
				ps = conn.prepareStatement ("SELECT id,operacion,usuario,host,modificado, tabla FROM bitacora");
				rs = ps.executeQuery();
				
				while (rs.next()) {

					   }
					} catch (SQLException ex) {
					   System.out.println(ex.toString());
					  }
				}
			});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "OPERACION", "USUARIO", "HOST", "MODIFICADO", "TABLA "
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.getColumnModel().getColumn(2).setPreferredWidth(108);
		table.getColumnModel().getColumn(3).setPreferredWidth(98);
		table.getColumnModel().getColumn(4).setPreferredWidth(122);
		table.getColumnModel().getColumn(5).setPreferredWidth(121);
		scrollPane.setViewportView(table);
		
		JButton BotonEliminar = new JButton("BORRAR REGISTRO");
		BotonEliminar.setBackground(SystemColor.menu);
		
		BotonEliminar.addActionListener(new ActionListener() {
			private DefaultTableModel modelo;	
			public void actionPerformed(ActionEvent arg0) {
				java.sql.PreparedStatement ps = null;
				try {
		//		Conexion ObjCon = new Conexion();
				Connection conn = Conexion.getConection();
    int Fila = table.getSelectedRow();
    String idObj = table.getValueAt(Fila, 0).toString();

    ps = conn.prepareStatement("DELETE FROM bitacora WHERE id=?");
    ps.setString(1, idObj);
    ps.execute();

//modelo.removeRow(Fila);
 } catch (SQLException ex) {
System.out.println(ex.toString());
} 
			}
		});
		BotonEliminar.setBounds(172, 527, 186, 23);
		contentPane.add(BotonEliminar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Bitacora.class.getResource("/iconos/REYCOLogoMediano.png")));
		lblNewLabel.setBounds(343, 11, 213, 68);
		contentPane.add(lblNewLabel);
	  }
        }
