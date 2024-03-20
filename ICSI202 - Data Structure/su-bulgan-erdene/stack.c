#include "DS.h"
/*
  p-ийн зааж буй Stack-т x утгыг оруулна
 */
void s_push(Stack *p, int x)
{
        /* Энд оруулах үйлдлийг хийнэ үү */
        Elm *st = malloc(sizeof(Elm));
        st->x = x;
        st->next = p->top;
        p->top = st;
        p->len++;
}
/*
  p-ийн зааж буй Stack-аас гарах функц
 */
void s_pop(Stack *p)
{
        /* Энд гаргах үйлдлийг хийнэ үү */
        struct Elm *st = p->top;
        p->top = st->next;
        p->len--;
}
/*
    p-ийн зааж буй Stack-д байгаа элементүүдийг хэвлэх функц.
    Хамгийн сүүлд орсон элементээс эхлэн дарааллаар, нэг мөрөнд
    нэг л элемент хэвлэнэ.
 */
void s_print(Stack *p)
{
        /* Энд хэвлэх үйлдлийг хийнэ үү */
        Elm *st = p->top;
        while (st != NULL){
                printf("%d", st->x);
                st = st->next;
        }
}
