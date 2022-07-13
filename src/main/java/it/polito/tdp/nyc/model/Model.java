package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	private Graph<City, DefaultWeightedEdge> grafo;
	private List<City> listaCity = new ArrayList<>();
	private NYCDao dao = new NYCDao();
	
	private int durata;
	private List<Integer> revisionati;
	
	public void creaGrafo(String provider) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		

		
		this.listaCity = dao.getAllCityByProvider(provider);
		
		Graphs.addAllVertices(this.grafo, this.listaCity);
		
		for(City c1 : listaCity) {
			for(City c2 : listaCity) {
				double dist = LatLngTool.distance(c1.getLatlng(),c2.getLatlng(), LengthUnit.KILOMETER);
				if(!grafo.containsEdge(c1, c2) && !c1.equals(c2))
					Graphs.addEdgeWithVertices(this.grafo, c1, c2, dist);
			}
		}
				
		System.out.println("#VERTICI: " + this.grafo.vertexSet().size());
		System.out.println("#ARCHI: " + this.grafo.edgeSet().size());
	}
	
	public List<City> getAdiacenti(City c) {
		for(City c1 : Graphs.neighborListOf(this.grafo, c)) {
			c1.setDist(this.grafo.getEdgeWeight(this.grafo.getEdge(c, c1)));
		}
		return Graphs.neighborListOf(this.grafo, c);
	}
	
	public List<String> getAllProvider() {
		return dao.getAllProvider();
	}
	
	public Graph<City, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
	
	public Set<City> getAllVertices(){
		return this.grafo.vertexSet();
	}
	
	public void simula(City scelto, int N) {
		Simulator sim = new Simulator(this.grafo, this.listaCity);
		sim.init(scelto, N);
		sim.run();
		this.durata = sim.getDurata();
		this.revisionati = sim.getRevisionati();
	}

	public int getDurata() {
		return durata;
	}

	public List<Integer> getRevisionati() {
		return revisionati;
	}
	
}
