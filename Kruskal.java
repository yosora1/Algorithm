package per.algorithms;

import java.util.ArrayList;
import java.util.Scanner;


public class Kruskal {
	//��³˹����
	//������С���������������ȶ��кͲ��鼯
	boolean root[];//�Ƿ�Ϊ��
	int parent[];//�±��Ӧ�ڵ�ĸ��ڵ�
	int currentSize=0;//��ǰ�ѵĳ���
	int maxSize=0;//��ǰ���㼯�Ͽɴ�ŵ���󳤶�
	EdgeNode[] minHeap=new EdgeNode[20];
	
	/*
	 * ��ʼ��ÿ������Ϊһ����
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
	 * Ѱ��ĳ������Ԫ�����ڵ���
	 * */
	public int find(int vertex) {
		while(!root[vertex]) {
			vertex=parent[vertex];
		}
		return vertex;
	}
	
	/*
	 * ��������ϲ�������
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
	 * ͨ��Ȩֵ������EdgeNodeΪ�ڵ����С��
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
	 * ��С��ɾ��
	 * */
	public EdgeNode deleteMinHeap() {
		if(currentSize<1) {
			System.out.println("���ѿ�");
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
	 * ����ͼ�Ķ��㼯�ϴ�Ȩ�߼�����MST
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
		System.out.println("ʹ��Kruskal�㷨�õ���ͼ����С������Ϊ��");
		for(int i=0;i<nodeList.size();i++) {
			System.out.println(nodeList.get(i).u+" "+nodeList.get(i).v+" "+nodeList.get(i).weight);
		}
	}
	public static void main(String args[]) {
		System.out.println("������ͼ�Ķ������ͱ�����");
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		int verNum=scan.nextInt();
		int edgeNum=scan.nextInt();
		int[] verArray=new int[verNum];
		System.out.println("���������붥�㣺");
		for(int i=0;i<verNum;i++) {
			int vertex=scan.nextInt();
			verArray[i]=vertex;
		}
		EdgeNode[] edgeNode=new EdgeNode[edgeNum];
		System.out.println("����������ߵĶ����Ȩ�أ�");
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