package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLng;

public class City {
	private String name;
	private LatLng latlng;
	private double dist;
	private int nHotSpot;
	
	public City(String name, LatLng latlng, int nHotSpot) {
		this.name = name;
		this.latlng = latlng;
		this.nHotSpot = nHotSpot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LatLng getLatlng() {
		return latlng;
	}

	public void setLatlng(LatLng latlng) {
		this.latlng = latlng;
	}
	

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public int getnHotSpot() {
		return nHotSpot;
	}

	public void setnHotSpot(int nHotSpot) {
		this.nHotSpot = nHotSpot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	} 
	
	
	
}
