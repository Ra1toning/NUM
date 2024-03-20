#include "DS.h"

/* p-ийн зааж буй List-д x утгыг төгсгөлд хийнэ */
void l_push_back(List *p, Token x)
{
	/* Энд оруулах үйлдлийг хийнэ үү */
  TokenElm *elm = malloc(sizeof(TokenElm));
  if (elm == NULL){
    return;
  }
  elm->x = x;
  elm->next = NULL;
  if (p->head == NULL && p->tail == NULL){
    p->head = p->tail = elm;
  }
  else {
    p->tail->next = elm ;
    p->tail = elm ;
  }
  p->len++ ;
}

/* p-ийн зааж буй List-д x утгыг эхэнд хийнэ
   Бүх элементүүд нэг нэг байрлал хойшилно.
 */
void l_push_front(List *p, Token x)
{
	/* Энд оруулах үйлдлийг хийнэ үү */
  TokenElm *elm = malloc(sizeof(TokenElm));
  elm->x = x;
  elm->next = p->head;
  if (p->head == NULL && p->tail == NULL){
    p->head = p->tail = elm;
  }
  else {
    p->head = elm;
  }
  p->len++;
}

/*
  p-ийн зааж буй List-д x утгыг pos байрлалд хийнэ
  pos болон түүнээс хойшхи элементүүд нэг байрлал ухарна.
  Тухайн байрлал List-ийн сүүлийн индексээс их бол төгсгөлд орно.
 */
void l_insert(List *p, Token x, int pos)
{
	/* Энд оруулах үйлдлийг хийнэ үү */
  if (pos == p->len)
    l_push_back(p , x);
  if (pos == 0)
    l_push_front(p, x);
  TokenElm *init_header = p->head;
  TokenElm *elm = malloc(sizeof(TokenElm));
  elm->x = x;
  for(int i = 1; i < pos; i++){
    init_header = init_header->next;
  }
  elm->next = init_header->next;
  init_header->next = elm;
  p->len++; 
}


/*
  p-ийн зааж буй List-н эхлэлээс гаргана.
  List-ийн бүх элементүүд нэг нэг байрлал урагшилна
 */
void l_pop_front(List *p)
{
	/* Энд гаргах үйлдлийг хийнэ үү */
  TokenElm *elm = p->head;
  p->head = p->head->next;
  if (p->head == NULL)
    p->tail = NULL;
  p->len--;
}

/* p-ийн зааж буй List-н төгсгөлөөс гаргана */
void l_pop_back(List *p)
{
	/* Энд гаргах үйлдлийг хийнэ үү */
  TokenElm *elm = p->head;
  if (p->head->next == NULL){
    free(p->head);
    p->head = NULL;
    p->tail = NULL;
  } 
  else {
    while (elm->next != p->tail){
      elm = elm->next;
    }
    free(p->tail);
    elm->next = NULL;
    p->tail = elm;
  } 
  p->len--;

}

/* p-ийн зааж буй List-н pos байрлалаас гаргана.
   pos болон түүнээс хойшхи элементүүд нэг байрлал урагшилна.
   pos байрлалаас гарах боломжгүй бол юу ч хийхгүй.
 */
void l_erase(List *p, int pos)
{
	/* Энд гаргах үйлдлийг хийнэ үү */
  TokenElm *elm = p->head;
  if (pos == 0)
    l_pop_front(p);
  if (pos < p->len && pos > 0){
    for(int i=1 ;i < pos ;i++ ){
      if (elm->next != NULL ){
        elm = elm->next;
      }
    }
    elm->next = elm->next->next;
    p->len--;
  }
}

/*
  p-ийн зааж буй List-н утгуудыг хэвлэнэ.
  Хамгийн эхний элементээс эхлэн дарааллаар, нэг мөрөнд
  нэг л элемент хэвлэнэ.
 */
void l_print(List *p)
{
        /* Энд хэвлэх үйлдлийг хийнэ үү */
        TokenElm *elm = p->head;
        while(elm != NULL){
          if (elm->x.flag)
            printf("%d", elm->x.val);
          else 
            printf("%c", elm->x.op);
          elm = elm->next;
        }
        printf("\n");
}

