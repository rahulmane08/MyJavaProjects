package graph.mst;

import java.util.Comparator;

import graph.Edge;

public class WeightComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge o1, Edge o2) {
		Integer w1 = o1.getWeight();
		Integer w2 = o2.getWeight();
		return w1.compareTo(w2);
	}

}
