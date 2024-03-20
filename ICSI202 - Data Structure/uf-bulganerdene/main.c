#include "DS.h"

int array[5000][5000];
int n, m;
WeightedUF uf;
int is_avail(int a, int b){
	if (a >= 0 & b>=0 & a < n & b < n){
		return 1;
	}
	else {
		return 0;
	}
}
int main()
{
	int x, y;
	scanf("%d%d", &n, &m);

	WeightedUF uf;
	uf_init(&uf, n * n + 2);          // Нэмэлт 2 хийсвэр оройг нэмнэ.
	// Цаашаа үргэлжүүлэн бичшнэ үү!
	// ...
	for(int i = 1 ; i <= m  ; i++){

		scanf("%d %d", &x, &y);
		array[x][y] = 1;
		if (x == 0){
			uf_union(&uf, y+1, 0);
		}
		if (x == n -1){
			uf_union(&uf, n*x+y+1, n*n+1);
		}
		if (is_avail(x + 1, y) && array[x+1][y]){  //Дамжихдаа зөвхөн бөөрөөрөө нийлсэн нүдээр дамжина.
			uf_union(&uf, n * x + y + 1, n * x + n + y + 1);
		}

		if (is_avail(x - 1, y) && array[x-1][y]){
			uf_union(&uf, n * x +y + 1, n * x - n + y + 1);
		
		}

		if (is_avail(x, y + 1) && array[x][y+1]){
			uf_union(&uf, n * x + y + 1, n * x + y + 2);
		}

		if (is_avail(x, y - 1) && array[x][y-1]){
			uf_union(&uf, n * x + y + 1, n * x + y);
		}

		if (uf_find(&uf, n * n + 1) == 0){
			printf("%d\n", i);
			return 0;
		}	
	}	

	printf("-1\n");
	return 0;
}
