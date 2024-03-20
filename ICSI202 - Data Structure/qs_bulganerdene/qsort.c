#include "my_qsort.h"

/*
  quicksort эрэмбэлэх аргын цааш хуваагдах ёсгүй хэмжээ
*/
#ifndef CUTOFF
#define CUTOFF 10
#endif

static int init_seed = 0;  // Random seed 1 удаа эхлүүлнэ

/*
  Оруулан эрэмбэлэх функц.
  [lo, hi] завсрах тоонуудыг оруулан эрэмбэлэх аргаар эрэмбэлнэ.
*/
static void insertion_sort(int a[], int lo, int hi)
{
        /* Оруулан эрэмбэлэх аргыг хэрэгжүүл */
        int t;
        for(int i = lo+1; i <= hi; i++){
        	t=a[i];
        	int j=i-1;
        	while (j >= lo && a[j] > t){
        		a[j + 1] = a[j];
        		j--;
			}
			a[j + 1] = t;
		}
}

/*
  Хоёр утгыг хооронд солих функц
*/
static void swap(int *a, int *b)
{
        int tmp = *a;
        *a = *b;
        *b = tmp;
}

/*
  [lo, hi] завсрыг санамсаргүйгээр холих функц.
*/
static void random_shuffle(int a[], int lo, int hi)
{
        if (init_seed == 0) {
                srand(time(NULL));
                init_seed = 1;
        }
        int i, j;
        for (i = lo; i <= hi; i++) {
                j = rand() % (hi - lo) + lo;
                swap(&a[i], &a[j]);
        }
}

/*
  1-pivot хурдан эрэмбэлэх функц.
*/
static void _single_pivot_qsort(int a[], int lo, int hi)
{
        /* Энд quicksort хэрэгжүүл */
       
        if(hi - lo <= CUTOFF){
        	insertion_sort(a, lo, hi);
		return;}
		int p = a[lo];
		int i = lo + 1;
		int j = hi;
		while(i <= j){
			
			while(i <=j && a[i] <= p)
				i++;
			while(i<=j && a[j] > p)
				j--;
			if(i < j)
				swap(&a[i], &a[j]);
		}
		swap(&a[lo], &a[j]);
		_single_pivot_qsort(a, lo, j - 1);
		_single_pivot_qsort(a, j + 1, hi);
}

/*
  wrapper function for _single_pivot_qsort
  _single_pivot_qsort-ыг дуудахад ашиглах функц
 */
void single_pivot_qsort(int a[], int lo, int hi)
{
        random_shuffle(a, lo, hi);
        _single_pivot_qsort(a, lo, hi);
}

/*
  Dual-pivot хурдан эрэмбэлэх функц
*/
static void _dual_pivot_qsort(int a[], int lo, int hi)
{
        /*
          Энд dual-pivot quicksort хэрэгжүүл
        */

    if (hi - lo <= CUTOFF){
    	insertion_sort(a, lo, hi);
	return;
    }
    if (a[lo] > a[hi]){
        swap(&a[lo], &a[hi]);
    }

    int lt = lo + 1;
    int gt = hi - 1;
    int i = lo + 1;
    int  p1 = a[lo];
    int  p2 = a[hi]; 
    while(i <= gt){
    	if(a[i] < p1){
    		swap(&a[i], &a[lt]);
    		lt++;
		}
		else if(a[i] >= p2){
			while (a[gt] > p2 && i < gt){
				gt--;
			}
			swap (&a[i], &a[gt]);
			gt--;
			if (a[i] < p1){
				swap(&a[i], &a[lt]);
				lt++;
			}
		}
		i++;
	}
	 lt--;
	 gt++;
	swap(&a[lo], &a[lt]);
        swap(&a[hi], &a[gt]);
 
    _dual_pivot_qsort(a, lo, lt - 1);
    _dual_pivot_qsort(a, lt + 1, gt - 1);
    _dual_pivot_qsort(a, gt + 1, hi);
}
/*
  wrapper function for _dual_pivot_qsort
  _dual_pivot_qsort-ыг дуудахад ашиглах функц
*/
void dual_pivot_qsort(int a[], int lo, int hi)
{
        random_shuffle(a, lo, hi);
        _dual_pivot_qsort(a, lo, hi);
}

