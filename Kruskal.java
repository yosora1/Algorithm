package per.algorithms;

import java.util.ArrayList;
import java.util.Scanner;


public class Kruskal {
	//克鲁斯卡尔
	//构造最小生成树，利用优先队列和并查集
	boolean root[];//是否为根
	int parent[];//下标对应节点的父节点
	int currentSize=0;//当前堆的长度
	int maxSize=0;//当前顶点集合可存放的最大长度
	EdgeNode[] minHeap=new EdgeNode[20];
	
	/*
	 * 初始化每个顶点为一个类
	 * */
	public void initialize(int verNum) {
		root=new boolean[verNum+1];
		parent=new int[verNum+1];
		for(int vertex=1;vertex<=verNum;vertex++) {
			root[vertex]=true;
			parent[vertex]=1;
		}
	}
	
	/*
	 * 寻找某个顶点元素所在的类
	 * */
	public int find(int vertex) {
		while(!root[vertex]) {
			vertex=parent[vertex];
		}
		return vertex;
	}
	
	/*
	 * 重量规则合并两个类
	 * */
	public void union(int i,int j) {
		if(parent[i]<parent[j]) {
			parent[j]+=parent[i];
			root[i]=false;
			parent[i]=j;
		}else {
			parent[i]+=parent[j];
			root[j]=false;
			parent[j]=i;
		}
	}
	
	/*
	 * 通过权值构建以EdgeNode为节点的最小堆
	 * */
	public void creatMinHeap(EdgeNode[] edgeNode) {
		currentSize=edgeNode.length;
		maxSize=minHeap.length;
		if(currentSize>=maxSize) {
			maxSize*=2;
			minHeap=new EdgeNode[maxSize];
		}
		for(int i=0;i<currentSize;i++) {
			minHeap[i+1]=edgeNode[i];
		}
		int y,c;
		for(int i=currentSize/2;i>=1;i--) {
			EdgeNode node=minHeap[i];
			y=node.weight;
			c=2*i;
			while(c<currentSize) {
				if(c<=currentSize && minHeap[c].weight>minHeap[c+1].weight) {
					c++;
				}
				if(minHeap[c].weight>=y)break;
				minHeap[c/2]=minHeap[c];
				c*=2;
			}
			minHeap[c/2]=node;
		}
	}
	
	/*
	 * 最小堆删除
	 * */
	public EdgeNode deleteMinHeap() {
		if(currentSize<1) {
			System.out.println("堆已空");
		}
		EdgeNode node=minHeap[1];
		minHeap[1]=minHeap[currentSize];
		currentSize--;
		
		int c=2,j=1;
		EdgeNode node1=minHeap[currentSize+1];
		while(c<=currentSize) {
			if(c<currentSize && minHeap[c].weight>minHeap[c+1].weight) {
				c++;
			}
			if(node1.weight<=minHeap[c].weight) {
				break;
			}
			minHeap[j]=minHeap[c];
			j=c;
			c*=2;
		}
		minHeap[j]=node1;
		return node;
	}
	
	/*
	 * 根据图的顶点集合带权边集生成MST
	 * */
	public void minSpanningTree(int[] verArray,EdgeNode[] edgeNode) {
		ArrayList<EdgeNode> nodeList=new ArrayList<EdgeNode>();
		initialize(verArray.length);
		creatMinHeap(edgeNode);
		for(int i=1;i<=currentSize;i++) {
			System.out.println(minHeap[i].u+" "+minHeap[i].v+" "+minHeap[i].weight);
		}
		for(int i=0;i<edgeNode.length;i++) {
			EdgeNode node=deleteMinHeap();
			int jRoot=find(node.u);
			int kRoot=find(node.v);
			if(jRoot!=kRoot) {
				nodeList.add(node);
				union(jRoot,kRoot);
			}
		}
		System.out.println("使用Kruskal算法得到的图的最小生成树为：");
		for(int i=0;i<nodeList.size();i++) {
			System.out.println(nodeList.get(i).u+" "+nodeList.get(i).v+" "+nodeList.get(i).weight);
		}
	}
	public static void main(String args[]) {
		System.out.println("请输入图的顶点数和边数：");
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		int verNum=scan.nextInt();
		int edgeNum=scan.nextInt();
		int[] verArray=new int[verNum];
		System.out.println("请依次输入顶点：");
		for(int i=0;i<verNum;i++) {
			int vertex=scan.nextInt();
			verArray[i]=vertex;
		}
		EdgeNode[] edgeNode=new EdgeNode[edgeNum];
		System.out.println("请依次输入边的顶点和权重：");
		for(int i=0;i<edgeNum;i++) {
			int u=scan.nextInt();
			int v=scan.nextInt();
			int weight=scan.nextInt();
			EdgeNode node=new EdgeNode();
			node.u=u;
			node.v=v;
			node.weight=weight;
			edgeNode[i]=node;
		}
		Kruskal kruskal=new Kruskal();
		kruskal.minSpanningTree(verArray, edgeNode);
	}
}

class EdgeNode{
	int weight;
	int u,v;
}