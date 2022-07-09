package it.polito.tdp.yelp.model;


import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	private YelpDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	
	
	public Model() {
		dao = new YelpDao();
	}
	
	public String creaGrafo(String citta, String locale) {
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(citta,locale));
		
		for(Coppia c : this.dao.getArchi(locale)) {
				Graphs.addEdgeWithVertices(this.grafo, c.getR1(), c.getR2(), c.getPeso());
		}
		
		return "Il grafo reato ha : "+this.grafo.vertexSet().size()
				+", "+this.grafo.edgeSet().size()+" "+" vertici e archi";
	}
	
	
	public List<String> getCitta(){
		return this.dao.getCitta();
	}
	
	public List<String> getLocale(String citta){
		return this.dao.getLocale(citta);
	}

	
	//trovo peso archi uscenti max!!!!!!!!!!!!!!!!!!!!!!!!1
	public RecensioneArchimax getRecArcoMax(){
		
		if(grafo == null) {
			return null;
		}
		
		int max = 0;
		String best = null; 
		
		int pesoUscente= 0;
		for(String s : this.grafo.vertexSet()) {
				pesoUscente = grafo.outgoingEdgesOf(s).size();
			
			if(pesoUscente>max) {
				max = pesoUscente;
				best = s;
			}	
		}
		return new RecensioneArchimax(best, max);
	}
	

}
