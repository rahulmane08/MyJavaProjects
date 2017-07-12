package graph;

public class Test2 {
	public static void main(String[] args) {
		Graph<String> graph = new Graph<>(true);
		Vertex<String> A = new Vertex<>(1);
		Vertex<String> B = new Vertex<>(2);
		Vertex<String> C = new Vertex<>(3);
		Vertex<String> D = new Vertex<>(4);
		Vertex<String> E = new Vertex<>(5);
		A.setData("A");
		B.setData("B");
		C.setData("C");
		D.setData("D");
		E.setData("E");
		
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		graph.addVertex(D);
		graph.addVertex(E);
		
		graph.addEdge(A, B, 1);
		graph.addEdge(A, C, 1);
		graph.addEdge(A, D, 1);
		graph.addEdge(E, A, 1);
		
		
		for(Vertex<String> v: graph.getAllVertexes())
			System.out.println(v.getData()+", inDegree="+v.getInDegree()+", outDegree="+v.getOutDegree());
	}
}
