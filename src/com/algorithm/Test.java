package com.algorithm;

public class Test {

	public static void main(String[] args) {
		BFS_testArrival bfs=new BFS_testArrival(1,10) {

			@Override
			public void addEdges() {
				addEdge(1,2);
				addEdge(2,3);
				addEdge(3,5);
				addEdge(5,10);
			}
			
		};
		System.out.println(bfs.testArriavl());

	}

}
