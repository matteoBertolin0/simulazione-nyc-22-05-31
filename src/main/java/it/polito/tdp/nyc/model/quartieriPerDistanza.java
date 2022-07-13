package it.polito.tdp.nyc.model;

import java.util.Comparator;

public class quartieriPerDistanza implements Comparator<City>{

	@Override
	public int compare(City o1, City o2) {
		return (int) (o1.getDist()*1000000-o2.getDist()*1000000);
	}

}
