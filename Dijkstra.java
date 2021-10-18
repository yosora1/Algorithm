package per.algorithms;

public class Dijkstra {
	//迪杰斯特拉，按路径长度递增的次序产生最短路径
	/**
	 * 
	 * @param v 起点
	 * @param a 图
	 * @param dist v到达下标对应顶点的最短路径
	 * @param prev 下标对应顶点的父节点
	 */
	public static void dijkstra(int v,float[][] a,float[] dist,int[] prev) {
		boolean[] s=new boolean[dist.length];//是否访问
		int n=dist.length-1;
		if(v<1 || v>n)return;
		//初始化
		for(int i=1;i<=n;i++) {
			dist[i]=a[v][i];
			s[i]=false;
			if(dist[i]==Float.MAX_VALUE)prev[i]=0;
			else prev[i]=v;
		}
		dist[v]=0;
		s[v]=true;
		for(int i=1;i<n;i++) {
			float temp=Float.MAX_VALUE;
			int u=v;
			//查找u的最短路径
			for(int j=1;j<=n;j++) {
				if(!s[j] && dist[j]<temp) {
					u=j;
					temp=dist[j];
				}
				
			}
			s[u]=true;
			//更新u对应的各顶点的最短路径
			for(int j=1;j<=n;j++) {
				if(!s[j] && a[u][j]<Float.MAX_VALUE) {
					float newdist=dist[u]+a[u][j];
					if(newdist<dist[j]) {
						dist[j]=newdist;
						prev[j]=u;
					}
				}
			}
		}
	}

}
