package per.algorithms;

public class Prim {
	/*
	 * Prim算法（贪心算法）
	 * 从第一个顶点出发寻找最短的边构造生成树
	 * */
	public static void prim(float c[][],int n) {
		float[] lowcost=new float[n+1];//对应顶点当前最短路径
		int[] closet=new int [n+1];//下标对应顶点的前一顶点
		boolean[] s=new boolean[n+1];//是否访问
		s[1]=true;
		for(int i=2;i<=n;i++) {
			lowcost[i]=c[1][i];
			closet[i]=1;
			s[i]=false;
		}
		
		for(int i=1;i<n;i++) {
			int j=1;
			float min=Float.MAX_VALUE;
			for(int k=2;k<=n;k++) {
				if(min>lowcost[k] && !s[k]) {
					min=lowcost[k];
					j=k;
				}
			}
			System.out.println(j+", "+closet[j]);
			s[j]=true;
			for(int k=2;k<=n;k++) {
				if(c[j][k]<lowcost[k] && !s[k]) {
					lowcost[k]=c[j][k];
					closet[k]=j;
				}
			}
		}
	}
}
