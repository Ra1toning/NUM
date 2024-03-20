#include <stdio.h>

insertion_sort(int a[],int lo, int n){
		for(int i=1; i<n; i++){
	    int	t=a[i];
		int j=i-1;
		while (j >= 0 && a[j] > t){
			a[j+1] = a[j];
			j--;
		}
		a[j+1]=t;
	}
}
main(){
	int a[100];
	int lo=0, n=0;
	scanf("%d",&n);
	for(int i=0; i<n; i++)
		scanf("%d",&a[i]);
	insertion_sort(a,lo,n);
	for(int i=0; i<n; i++)
		printf("%d ",a[i]);
}
