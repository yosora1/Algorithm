#include <iostream>

using namespace std;

void swap(int* a, int i, int j) {
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}

int partition(int *a, int low, int high,int x) {
	int povotkey = x;
	while (low < high) {
		while (low<high && a[high]>=povotkey) {
			high--;
		}
		a[low] = a[high];
		while (low < high && a[low] <= povotkey) {
			low++;
		}
		a[high] = a[low];
	}
	a[low] = povotkey;
	return low;
}

//快排
void qSort(int *a, int low, int high) {
	if (low < high) {
		int pivotloc = partition(a, low, high,a[low]);
		qSort(a,low,pivotloc-1);
		qSort(a,pivotloc+1,high);
	}
}

int LinearSelect(int a[],int left,int right,int k) {
	if (right - left < 5) {
		qSort(a,left,right);
		return a[left + k - 1];
	}
	for (int i = 0; i <= (right - left - 4) / 5; i++) {//分i组
		//取中位数
		int start = left + 5 * i;
		int end = start + 4;
		for (int l = 0; l < 3; l++) {
			for (int j = l + 1; j <= 4; j++) {
				if (a[start + l] < a[start + j]) swap(a, start + l, start + j);
			}
		}
		swap(a, left + i, start + 2);
	}
	int x = LinearSelect(a, left, left + (right - left + 4) / 5, (right - left + 6) / 10);
	int i = partition(a, left, right, x);
	int j = i - left + 1;
	if (k <= j) return LinearSelect(a, left, i, k);
	else
		return LinearSelect(a,i+1,right,k-j);
}

int main()
{
	int a[17] = {1,2,12,3,13,4,14,5,15,6,11,7,10,8,9,23,27};
	int x = LinearSelect(a, 0, 14, 16);
	cout << x << endl;
}