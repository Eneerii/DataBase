package presentacion.datamodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Libro;
import service.LibreriaServiceFactory;
import service.LibrosService;

public class AdaptadorTablaLibros extends AbstractTableModel{
	List<Libro> libros;
	public AdaptadorTablaLibros(int idTema) {
		LibrosService service=LibreriaServiceFactory.getLibrosService();
		libros=service.librosTemas(idTema);
	}
	@Override
	public int getRowCount() {
		return libros.size();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		return switch(columnIndex) {
		case 0->libros.get(rowIndex).getTitulo();
		case 1->libros.get(rowIndex).getAutor();
		case 2->libros.get(rowIndex).getPrecio();
		case 3->libros.get(rowIndex).getPaginas();
		default->"";
		};
	
	}
	@Override 
	public String getColumnName(int column) {
		return switch(column) {
		case 0->"Titulo";
		case 1->"Autor";
		case 2->"Precio";
		case 3->"Paginas";
		default->"";
		};
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return switch(columnIndex) {
			case 0->String.class;
			case 1->String.class;
			case 2->Double.class;
			case 3->Integer.class;
			default->null;
		};
	
	}

}
