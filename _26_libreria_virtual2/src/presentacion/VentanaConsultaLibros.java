package presentacion;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import model.Tema;
import presentacion.datamodel.AdaptadorListaTemas;
import presentacion.datamodel.AdaptadorTablaLibros;

public class VentanaConsultaLibros extends JFrame {

	private JPanel contentPane;
	private List<Tema> temas;
	private JTable tableLibros;

	public VentanaConsultaLibros(List<Tema> temas) {
		
		this.temas=temas;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecciona tema:");
		lblNewLabel.setBounds(66, 11, 110, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrolLista = new JScrollPane();
		scrolLista.setBounds(62, 53, 328, 54);
		contentPane.add(scrolLista);
		
		JComboBox comboTemas = new JComboBox();
		comboTemas.addItemListener(new ItemListener() {
		
			public void itemStateChanged(ItemEvent e) {
				//cogemos el tema seleccionado
				Tema tema=(Tema) comboTemas.getSelectedItem();
				//creamos el adaptador de la tabla y se lo asignamos
				var adapter=new AdaptadorTablaLibros(tema.getIdTema());
				tableLibros.setModel(adapter);
			}
		});
		scrolLista.setViewportView(comboTemas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 146, 469, 176);
		contentPane.add(scrollPane);
		
		tableLibros = new JTable();
		scrollPane.setViewportView(tableLibros);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//asignamos al combobox el adaptador de los temas
				var adapter=new AdaptadorListaTemas();
				comboTemas.setModel(adapter);
				comboTemas.addItem(new Tema(0,"-Seleccione uno-"));
			}
		});
		this.setVisible(true);
	}
}
