#include "DS.h"

/* p-ийн зааж буй List-д x утгыг төгсгөлд хийнэ */
void l_push_back(List *p, int x)
{
	/* Энд оруулах үйлдлийг хийнэ үү */
  Elm *li = malloc(sizeof(Elm));
  li->x = x;
  li->next = NULL;
  if(p->head == NULL && p->tail == NULL)
    p->head = p->tail = li;
  else{
    p->tail->next = li;
    p->tail = li;
  }
  p->len++;
}

/* p-ийн зааж буй List-д x утгыг эхэнд хийнэ
   Бүх элементүүд нэг нэг байрлал хойшилно.
 */
void l_push_front(List *p, int x)
{
	/* Энд оруулах үйлдлийг хийнэ үү */
  Elm *li = malloc(sizeof(Elm));
  li->x = x;
  li->next = p->head;
  if(p->head == NULL && p->tail == NULL)
    p->head = p->tail = li;
  else{
    p->head = li;
  }
  p->len++;
}

/*
  p-ийн зааж буй List-д x утгыг pos байрлалд хийнэ
  pos болон түүнээс хойшхи элементүүд нэг байрлал ухарна.
  Тухайн байрлал List-ийн сүүлийн индексээс их бол төгсгөлд орно.
 */
void l_insert(List *p, int x, int pos)
{
	/* Энд оруулах үйлдлийг хийнэ үү */
  if(pos == 0){
    l_push_front(p, x);
    return;
  }
  if(pos == p->len){
    l_push_back(p, x);
    return;  
  }
  Elm *li = (Elm *) malloc(sizeof(Elm));
  Elm *t = p->head;
  li->x = x;
  for(int i=1; i<pos; i++)
    t = t->next;
  li->next = t->next;
  t->next = li;
  p->len++;
}


/*
  p-ийн зааж буй List-н эхлэлээс гаргана.
  List-ийн бүх элементүүд нэг нэг байрлал урагшилна
 */
void l_pop_front(List *p)
{
	/* Энд гаргах үйлдлийг хийнэ үү */
  Elm *li = p->head;
  p->head = p->head->next;
  if(p->head == NULL)
    p->tail = NULL;
  p->len--;
}

/* p-ийн зааж буй List-н төгсгөлөөс гаргана */
void l_pop_back(List *p)
{
	/* Энд гаргах үйлдлийг хийнэ үү */
  Elm *li = p->head;
  if(p->head->next == NULL)
    p->head = p->tail = NULL;
  else{
    while(li->next != p->tail){
      li = li->next;
    }
    li->next = NULL;
    p->tail = li;
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
  Elm *li = p->head;
  if(pos == 0)
    l_pop_front(p);
  if(pos > 0 && pos < p->len){
    for(int i=1; i<pos; i++){
      if(li->next != NULL)
        li = li->next;
    }
    li->next = li->next->next;
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
        Elm *li = p->head;
        while(li != NULL){
        printf("%d\n", li->x);
        li = li->next;
        }
}

/*
  p-ийн зааж буй List-с x тоог хайн олдсон хаягийг буцаана.
  Олдохгүй бол NULL хаяг буцаана.
 */
Elm *l_search(List *p, int x)
{
	Elm *li;
  int t = 0;
  for(li = p->head; li != NULL; li = li->next){
    if(li->x == x){
      t = 1;
      break;
    }
  }
  if(t == 1)
    return li;
  else
    return NULL;
}
