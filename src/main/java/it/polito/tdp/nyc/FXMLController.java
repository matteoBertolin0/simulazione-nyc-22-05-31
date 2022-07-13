/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.nyc;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.nyc.model.City;
import it.polito.tdp.nyc.model.Model;
import it.polito.tdp.nyc.model.quartieriPerDistanza;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProvider"
    private ComboBox<String> cmbProvider; // Value injected by FXMLLoader

    @FXML // fx:id="cmbQuartiere"
    private ComboBox<City> cmbQuartiere; // Value injected by FXMLLoader

    @FXML // fx:id="txtMemoria"
    private TextField txtMemoria; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="clQuartiere"
    private TableColumn<City, String> clQuartiere; // Value injected by FXMLLoader
 
    @FXML // fx:id="clDistanza"
    private TableColumn<City, Double> clDistanza; // Value injected by FXMLLoader
    
    @FXML // fx:id="tblQuartieri"
    private TableView<City> tblQuartieri; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
		txtResult.clear();
    	if(cmbProvider.getValue()!=null) {
    		this.model.creaGrafo(cmbProvider.getValue());
    		cmbQuartiere.getItems().clear();
    		for(City c : model.getGrafo().vertexSet()) {
    			cmbQuartiere.getItems().add(c);    		
    		}

    		txtResult.appendText("Grafo creato!\n#Vertici: "+model.getGrafo().vertexSet().size()+"\n#Archi: "+model.getGrafo().edgeSet().size()+"\n");    		
    	}else {
    		txtResult.setText("Selezionare un provider!");
    	}
    }

    @FXML
    void doQuartieriAdiacenti(ActionEvent event) {
    	tblQuartieri.getItems().clear();
    	if(cmbQuartiere.getValue()!=null) {
    		clQuartiere.setCellValueFactory(new PropertyValueFactory<City,String>("name"));
    		clDistanza.setCellValueFactory(new PropertyValueFactory<City,Double>("dist"));
    		List l = model.getAdiacenti(cmbQuartiere.getValue());
    		Collections.sort(l, new quartieriPerDistanza()) ;
    		tblQuartieri.setItems(FXCollections.observableArrayList(l));
   		
    	}else {
    		txtResult.setText("Selezionare un quartiere!");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	City scelto = cmbQuartiere.getValue();
    	if(scelto==null) {
    		txtResult.appendText("Errore: seleziona un quartiere\n");
    		return;
    	}

    	int N = 0;
    	try {
    		N = Integer.parseInt(txtMemoria.getText());
    	} catch(NumberFormatException ex) {
    		txtResult.appendText("Errore: inserire un numero valido\n");
    		return;
    	}
    	
    	model.simula(scelto, N);
    	
    	txtResult.appendText("Durata simulazione: "+model.getDurata()+" minuti\n");
    	txtResult.appendText("Impegni dei tecnici: "+model.getRevisionati()+"\n");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProvider != null : "fx:id=\"cmbProvider\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbQuartiere != null : "fx:id=\"cmbQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMemoria != null : "fx:id=\"txtMemoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clDistanza != null : "fx:id=\"clDistanza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clQuartiere != null : "fx:id=\"clQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbProvider.getItems().clear();
    	for(String s : model.getAllProvider())
    	cmbProvider.getItems().add(s);
    }

}
