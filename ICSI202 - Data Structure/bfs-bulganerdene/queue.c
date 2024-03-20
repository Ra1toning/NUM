#include "DS.h"

/* p-ийн зааж буй Queue-д x утгыг хийнэ */
void q_push(Queue *p, int x)
{
        /* Энд оруулах үйлдлийг хийнэ үү */
        struct Elm *qu = (struct Elm *) malloc(sizeof(Elm));
        qu->x = x;
        qu->next = NULL;
        if(p->tail == NULL && p->head == NULL)
                p->head = p->tail = qu;
        else{
                p->tail->next = qu;
                p->tail = qu;
        }
        p->len++;
}

/* p-ийн зааж буй Queue-с гаргана */
void q_pop(Queue *p)
{
        /* Энд гаргах үйлдлийг хийнэ үү */
        struct Elm *qu;
        p->head = p->head->next;
        if(p->head == NULL)
                p->tail = NULL;
        p->len--;


}

/*
  p-ийн зааж буй Queue-н утгуудыг хэвлэнэ.
  Хамгийн эхний элементээс эхлэн дарааллаар, нэг мөрөнд
  нэг л элемент хэвлэнэ.
 */
void q_print(Queue *p)
{
        /* Энд хэвлэх үйлдлийг хийнэ үү */
        struct Elm * qu = p->head;
        while(qu != NULL){
                printf("%d\n", qu->x);
                qu = qu->next;
        }
}