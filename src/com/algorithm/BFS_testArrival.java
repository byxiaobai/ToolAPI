package com.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * bfs测试是否能到达
 */
public abstract class BFS_testArrival {
	final int MAXN=10000;
	
	Edge e[]=new Edge[MAXN*4];
	int cnt=0;
	
	int head[]=new int[MAXN];
	
	void addEdge(int from,int to) {
		if(e[cnt+1]==null)e[cnt+1]=new Edge();
		e[++cnt].nxt=head[from];
		e[cnt].from=from;
		e[cnt].to=to;
		head[from]=cnt;
	}
	
	int source,end;
	
	public BFS_testArrival(int source,int end) {
		this.source=source;
		this.end=end;
	}
	
	public abstract void addEdges();
	
	
	Queue<Integer> q=new ArrayDeque<>();
	boolean vis[]=new boolean[MAXN];
	public boolean testArriavl() {//测试是否能到达
		addEdges();
		q.add(source);
		vis[source]=true;
		while(!q.isEmpty()) {
			int top=q.peek();
			q.poll();
			for(int i=head[top];i!=0;i=e[i].nxt) {
				Edge edge=e[i];
				if(edge.to==end)return true;
				if(!vis[edge.to]) {
					q.add(edge.to);
					vis[edge.to]=true;
				}
			}
		}
		return false;
	}
	
}
