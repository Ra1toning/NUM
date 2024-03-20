#include "DS.h"

/* p-ийн зааж буй Queue-д x утгыг хийнэ */
void q_push(Queue *p, int x)
{
        /* Энд оруулах үйлдлийг хийнэ үү */
		p->q_arr[p->q_len++] = x;

}

/* p-ийн зааж буй Queue-с гаргана */
void q_pop(Queue *p)
{
        /* Энд гаргах үйлдлийг хийнэ үү */
        for(int i=1; i<=p->q_len; i++){
        	p->q_arr[i-1] = p->q_arr[i];
		}
		p->q_len--;
}

/* p-ийн зааж буй Queue-н утгуудыг хэвлэнэ */
void q_print(Queue *p)
{
        int i;
        for (i = 0; i < p->q_len; i++) {
                printf("%d ", p->q_arr[i]);
        }
        printf("\n");
}
