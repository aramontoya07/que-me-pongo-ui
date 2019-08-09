package arenaUI;
import applicationModel.BuscadorEventos;

import eventos.AsistenciaEvento;
import eventos.Evento;
import eventos.Unico;
import org.uqbar.arena.bindings.DateTransformer;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.widgets.Button;
import usuario.Usuario;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class BuscadorEventosWindow extends MainWindow<BuscadorEventos> {

    public BuscadorEventosWindow() {
        super(new BuscadorEventos());
//        this.getModelObject().search();
    }
    
	@Override
	public void createContents(Panel mainPanel) {
		this.setTitle("Buscador de Eventos");
		mainPanel.setLayout(new VerticalLayout());

		
		//TEXTOS PARA FECHA DESDE
		new Label(mainPanel).setText("Ingrese fecha de inicio (DD/MM/AAAA):");

		new TextBox(mainPanel).bindValueToProperty("fechaDesde").setTransformer(new DateTransformer()); //pasa un string de forma DD/MM/AAAA a Date
		
		//TEXTOS PARA FECHA HASTA
		new Label(mainPanel).setText("Ingrese fecha de fin (DD/MM/AAAA):");

		new TextBox(mainPanel).bindValueToProperty("fechaHasta").setTransformer(new DateTransformer());

		//BOTONES BUSCAR Y LIMPIAR
		new Button(mainPanel)
			.setCaption("Buscar")
			.onClick(()-> this.getModelObject().search());
		
		new Button(mainPanel)
			.setCaption("Limpiar")
			.onClick(()-> this.getModelObject().clear());

		//RESULTADOS DE BUSQUEDA
		Table<AsistenciaEvento> tablaEventos = new Table<AsistenciaEvento>(mainPanel, AsistenciaEvento.class);
		tablaEventos.bindItemsToProperty("resultados");
		
		new Column<AsistenciaEvento>(tablaEventos)
	    	.setTitle("Nombre")
	    	.setFixedSize(150)
	    	.bindContentsToProperty("nombre");

		new Column<AsistenciaEvento>(tablaEventos)
			.setTitle("Fecha")
			.setFixedSize(150)
			.bindContentsToProperty("fecha");

		new Column<AsistenciaEvento>(tablaEventos)
			.setTitle("Lugar")
			.setFixedSize(150)
			.bindContentsToProperty("lugar");

		new Column<AsistenciaEvento>(tablaEventos)
			.setTitle("Tiene Sugerencia")
			.setFixedSize(150)
			.bindContentsToProperty("tieneSugerencia");
		
	}

	public static void main(String[] args) {
  //inicializar un usuario y evento o sugerencia evento para probar.
		new BuscadorEventosWindow().startApplication();
	}
}
