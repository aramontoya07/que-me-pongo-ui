package arenaUI;
import applicationModel.BuscadorEventos;

import org.uqbar.arena.bindings.DateTransformer;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.widgets.Button;
import org.uqbar.lacar.ui.model.PanelBuilder;


@SuppressWarnings("serial")
public class BuscadorEventosWindow extends MainWindow<BuscadorEventos> {



	private BuscadorEventosWindow() {
		super(new BuscadorEventos());
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
		Panel botonesPanel = new Panel(mainPanel);
		botonesPanel.setLayout(PanelBuilder::setHorizontalLayout);

		new Button(botonesPanel)
				.setCaption("Buscar")
				.onClick(()-> this.getModelObject().search());

		new Button(botonesPanel)
				.setCaption("Limpiar")
				.onClick(()-> this.getModelObject().clear());

		//RESULTADOS DE BUSQUEDA
		Table<BuscadorEventos> tablaEventos = new Table<BuscadorEventos>(mainPanel, BuscadorEventos.class);
		tablaEventos.bindItemsToProperty("resultados");

		new Column<BuscadorEventos>(tablaEventos)
				.setTitle("Nombre")
				.setFixedSize(150)
				.bindContentsToProperty("nombre");

		new Column<BuscadorEventos>(tablaEventos)
				.setTitle("Fecha")
				.setFixedSize(150)
				.bindContentsToProperty("fecha");

		new Column<BuscadorEventos>(tablaEventos)
				.setTitle("Lugar")
				.setFixedSize(150)
				.bindContentsToProperty("lugar");

		new Column<BuscadorEventos>(tablaEventos)
				.setTitle("Tiene Sugerencia")
				.setFixedSize(150)
				.bindContentsToProperty("tieneSugerencia");

	}

	public static void main(String[] args) {
		new BuscadorEventosWindow().startApplication();
	}
}
