#include <stdio.h>

int main() {
	int a[100][100], b[100][100], sum[100][100], i, j, r, c;
	printf("Matritsiin muriin hemjee: ");
	scanf("%d", &r);
	printf("Matritsiin baganii hemjee: ");
	scanf("%d", &c);


	printf("\nEhnii matritsiin elementuudiig oruul:\n");
	for (i = 0; i < r; ++i)
    for (j = 0; j < c; ++j) {
    	printf("a%d%d-deh element: ", i + 1, j + 1);
    	scanf("%d", &a[i][j]);
    }

	printf("Daraah matritsiin elementuudiig oruul:\n");
	for (i = 0; i < r; ++i)
    	for (j = 0; j < c; ++j) {
      		printf("b%d%d-deh element: ", i + 1, j + 1);
      		scanf("%d", &b[i][j]);
    }

	for (i = 0; i < r; ++i)
    	for (j = 0; j < c; ++j) {
      		sum[i][j] = a[i][j] + b[i][j];
    }


	printf("\nniilber: \n");
  		for (i = 0; i < r; ++i)
    		for (j = 0; j < c; ++j) {
      			printf("%d   ", sum[i][j]);
      			if (j == c - 1) {
        			printf("\n\n");
      			}
    		}
  return 0;
}
