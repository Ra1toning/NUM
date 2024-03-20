#include "sort.h"

/*
  Нийлүүлэн эрэмбэлэх аргын цааш хуваагдах ёсгүй хэмжээ
 */
#define CUTOFF 10

/*
  a, b хаяганд хадгалагдсан сурагчдыг нэрээр жиших функц.
  a->name, b->name-ээс их бол -1, тэнцүү бол 0, бага бол 1-ийг буцаана.
  
*/
int nereer(const Student *a, const Student *b)
{
        if(strcmp(a->name, b->name) < 0)
        	return 1;
        else if(strcmp(a->name, b->name) == 0)
        	return 0;
        else
        	return -1;
}

/*
  a, b хаяганд хадгалагдсан сурагчдыг насаар нь жиших функц.
  a->age, b->age-ээс их бол -1, тэнцүү бол 0, бага бол 1-ийг буцаана.
*/
int nasaar(const Student *a, const Student *b)
{
        /* функцийг гүйцээ */
        if(a->age < b->age)
        	return 1;
        else if(a->age == b->age)
        	return 0;
        else
        	return -1;
}

/*
  a, b хаяганд хадгалагдсан сурагчдыг голчоор  нь жиших функц.
  a->gpa, b->gpa-ээс их бол -1, тэнцүү бол 0, бага бол 1-ийг буцаана.  
*/
int golchoor(const Student *a, const Student *b)
{
        /* функцийг гүйцээ */
        if(a->gpa < b->gpa)
        	return 1;
        else if(a->gpa == b->gpa)
        	return 0;
        else
        	return -1;
	
}


/*
  Оруулан эрэмбэлэх функц.
  Жиших үйлдлийг less функцэн заагчийг ашиглан хийнэ.
  Уг функц нь эрэмбэлэхдээ a хүснэгтийн lo-оос
  hi завсыг л зөвхөн эрэмбэлнэ.
*/
void insertion_sort(Student a[],
                    int lo, int hi,
                    int (*less)(const Student *, const Student *))
{
        /* функцийг гүйцээ */	
        Student t;
        for(int i=lo ; i<=hi; i++){
          int j = i;
          while (less(a + j - 1, a + j) < 0 && j > lo){
            t = a[j - 1];
            a[j - 1] = a[j];
            a[j] = t;
            j = j - 1;
          }
        }
}

/*
  Нийлүүлсэн эрэмбэлэх аргын mege үйлдэл.
  Уг функц нь a хүснэгтэд [lo; mid], [mid+1; hi] завсарт
  эрэмбэлэгдсэн хүснэгтийг нийлүүлэн [lo; hi] завсарт эрэмбэлэгдсэн хүснэгт болгоно.
  aux хүснэгт нь нэмэлтээр ашиглах хүснэгт. Оюутнуудыг хооронд нь жишихдээ less функцэн
  заагчийг ашиглана.
*/
void merge(Student a[],
           Student aux[],
           int lo,
           int mid,
           int hi,
           int (*less)(const Student *, const Student *))
{
        /* функцийг гүйцээ */
        for(int k = lo; k <= hi; k++)
        	aux[k]=a[k];
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k++){
        	if(i > mid)
        		a[k] = aux[j++];
        	else if(j > hi)
        		a[k] = aux[i++];
        	else if(less(&aux[j],&aux[i]) > 0)
        		a[k] = aux[j++];
        	else
        		a[k] = aux [i++];
		}
}


/*
  Нийлүүлсэн эрэмбэлэх функц.
  hi - lo <= CUTOFF бол оруулан эрэмбэлэх аргыг хэрэглэнэ.
  Жиших үйлдлийг less функцэн заагчийг ашиглан хийнэ.
  Уг функц нь merge, insertion_sort функцүүдийг дуудан ашиглах ёстой.
*/
void mergesort(Student a[],
               Student aux[],
               int lo,
               int hi,
               int (*less)(const Student *, const Student *))
{
        /* функцийг гүйцээ */   
	if (hi - lo <= CUTOFF){
          insertion_sort(a, lo, hi, less);
          return ;
        }     
        int mid ;
        mid = lo + (hi - lo) / 2 ; 
        mergesort(a, aux, lo, mid, less); 
        mergesort(a, aux, mid + 1, hi, less);
        merge(a, aux, lo, mid, hi, less); 
}
