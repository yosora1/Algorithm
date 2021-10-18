package per.algorithms;

public class Dijkstra {
	//�Ͻ�˹��������·�����ȵ����Ĵ���������·��
	/**
	 * 
	 * @param v ���
	 * @param a ͼ
	 * @param dist v�����±��Ӧ��������·��
	 * @param prev �±��Ӧ����ĸ��ڵ�
	 */
	public static void dijkstra(int v,float[][] a,float[] dist,int[] prev) {
		boolean[] s=new boolean[dist.length];//�Ƿ����
		int n=dist.length-1;
		if(v<1 || v>n)return;
		//��ʼ��
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
			//����u�����·��
			for(int j=1;j<=n;j++) {
				if(!s[j] && dist[j]<temp) {
					u=j;
					temp=dist[j];
				}
				
			}
			s[u]=true;
			//����u��Ӧ�ĸ���������·��
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
