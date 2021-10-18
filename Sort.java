package per.algorithms;

public class Sort {
	public void bubbleSort(int a[]) {
		//ð������
		for(int i=a.length-1;i>1;i--) {
			for(int j=1;j<i;j++) {
				if(a[j+1]<a[j]) {
					int x=a[j+1];
					a[j+1]=a[j];
					a[j]=x;
				}
			}
			System.out.print("��"+(a.length-i)+"��ð������");
			for(int j=1;j<a.length;j++) {
				System.out.print(a[j]+" ");
			}
			System.out.println();
		}
	}
	
	/////////������//////////
	public void heapSort(int a[]) {
		
		for(int i=(a.length-1)/2;i>0;--i) {
			heapAdjest(a,i,a.length-1);
		}
		System.out.print("��ʼ�󶥶ѣ�");
		for(int j=1;j<a.length;j++) {
			System.out.print(a[j]+" ");
		}
		System.out.println();
		for(int i=a.length-1;i>1;--i) {
			int x=a[1];
			a[1]=a[i];
			a[i]=x;
			heapAdjest(a,1,i-1);
			
			System.out.print("��"+(a.length-i)+"�ζ�����");
			for(int j=1;j<a.length;j++) {
				System.out.print(a[j]+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 * @param a 
	 * @param s ���
	 * @param m �յ�
	 */
	public void heapAdjest(int a[],int s,int m) {
		//������
		int rc=a[s];
		for(int j=2*s;j<=m;j*=2) {
			if(j<m && a[j]>a[j+1]) ++j;
			if(a[j]>=rc) break;
			a[s]=a[j];
			s=j;
		}
		a[s]=rc;
	}

	/////////������////////////
	
	//////////////ϣ������(��С��������)
	public void shellSort(int a[]) {
		int num=a.length/2;
		int i,j,temp,y=1;
		while(num>=1) {
			for(i=num+1;i<a.length;i++) {
				temp=a[i];
				j=i-num;
				while(j>0 && a[j]>temp) {
					a[j+num]=a[j];
					j=j-num;
				}
				a[j+num]=temp;
			}
			num=num/2;
			
			
			System.out.print("��"+(y++)+"��ϣ������");
			for(int z=1;z<a.length;z++) {
				System.out.print(a[z]+" ");
			}
			System.out.println();
		}
	}
	
	////////��������///////////////
	public void qSort(int[] a,int low,int high) {
		if(low<high) {
			int pivotloc=partition(a, low, high);
			qSort(a, low, pivotloc-1);
			qSort(a,pivotloc+1,high);
		}
	}
	public int partition(int[] a,int low,int high) {
		int povotkey=a[low];
		while(low<high) {
			while(low<high && a[high]>=povotkey) {
				high--;
			}
			a[low]=a[high];
			while(low<high && a[low]<=povotkey) {
				low++;
			}
			a[high]=a[low];
		}
		a[low]=povotkey;
		
		
		System.out.print("��������");
		for(int z=0;z<a.length;z++) {
			System.out.print(a[z]+" ");
		}
		System.out.println();
		return low;
	}
	////////////��������/////////////////
}
