#include "DS.h"

static void swim(Heap *p, int);
static void sink(Heap *p, int);

/*
  Хоёр зурвасын чухлыг харьцуулах функц.
  b нь илүү чухал бол 1, бусад үед 0-ыг буцаах функц.
  Өөрөөр хэлбэл a < b үйлдэл юм.
*/
int less(const Msg *a, const Msg *b)
{
        // Энд жиших үйлдийг хийнэ
        if(a->priority > b->priority)
                return 1;
        else if(a->priority < b->priority)
                return 0;
        else{
                if(a->time > b->time)
                        return 1;
                else
                        return 0;
        }
}

/*
  Оруулах функц. heap зарчмаар чухлын дарааллыг баримтлан оруулна.
  Ингэхдээ хамгийн чухал зурвас heap-ын оройд хадгалагдана.
  x зурвасыг p-ын зааж буй heap бүтцэд оруулна.
 */
void insert(Heap *p, const Msg x)
{
        // Энд оруулах үйлдлийг хийнэ
        int t = p->h_len;
        p->h_arr[p->h_len++] = x;
        swim(p, t);
}

/*
  Heap бүтцийн swim үйлдэл.
  k нь swim үйлдлийг p-ын зааж буй heap дээр эхлүүлэх индекс.
 */
static void swim(Heap *p, int k)
{
        // Энд swim үйлдлийг хийнэ
        while(k >= 0 &&  less(&p->h_arr[k/2], &p->h_arr[k])){
                Msg t = p->h_arr[k];
                p->h_arr[k] = p->h_arr[k/2];
                p->h_arr[k/2] = t;
                k = k/2;
        }
}

/*
  Heap бүтцийн sink үйлдэл.
  k нь sink үйлдлийг p-ын зааж буй heap дээр эхлүүлэх индекс.
 */
static void sink(Heap *p, int k)
{
        // Энд sink үйлдлийг хийнэ
        while(2*k < p->h_len){
                int j=2*k;
                if(j < p->h_len-1 && less(&p->h_arr[j], &p->h_arr[j+1]))
                        j++;
                if(!less(&p->h_arr[k], &p->h_arr[j]))
                        break;
                Msg t = p->h_arr[j];
                p->h_arr[j] = p->h_arr[k];
                p->h_arr[k] = t;
                k = j;
        }
}

/*
  p-ын зааж буй heap бүтцээс оройн элементийг гаргаад буцаах функц.
  Гаргасны дараа орой бүрийн хувьд heap зарчим хадгалах ёстой.
 */
Msg delMin(Heap *p)
{
        // Энд хамгийн багыг гаргах үйлдлийг хийнэ
        Msg mn, t;
        mn = p->h_arr[0];
        t = p->h_arr[0];
        p->h_arr[0] = p->h_arr[p->h_len-1];
        p->h_arr[p->h_len-1] = t;
        p->h_len--;
        sink(p, 0);
        return mn;
        

        
}

