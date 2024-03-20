#include "DS.h"

/*
  p-ийн зааж буй CBTree-д x утгыг оруулна
*/
void cb_push(CBTree *p, int x)
{
        /* Энд оруулах үйлдлийг хийнэ үү */
        p->tree.a[p->tree.len++] = x;
}

/*
  p-ийн зааж буй CBTree-д idx индекстэй оройны зүүн хүүгийн индексийг буцаана.
  Зүүн хүү байхгүй бол -1 буцаана.
*/
int cb_left(const CBTree *p, int idx)
{
        /* Энд зүүн хүүхдийн индексийг буцаах үйлдлийг хийнэ үү */
        if(2 * idx + 1 >= p->tree.len)
                return -1;
        else
                return 2 * idx + 1;
}

/*
  p-ийн зааж буй CBTree-д idx индекстэй оройны баруун хүүгийн индексийг буцаана.
  Баруун хүү байхгүй бол -1 буцаана.
*/
int cb_right(const CBTree *p, int idx)
{
        /* Энд баруун хүүхдийн индексийг буцаах үйлдлийг хийнэ үү */
        if(2 * idx + 2 >= p->tree.len)
                return -1;
        else
                return 2 * idx + 2;
}

/*
  p-ийн зааж буй CBTree-с x тоог хайн
  хамгийн эхэнд олдсон индексийг буцаана.
  Олдохгүй бол -1 утгыг буцаана.
*/
int cb_search(const CBTree *p, int x)
{
        /* Энд хайх үйлдлийг хийнэ */
        int c = 0;
        for(int i = 0; i < p->tree.len; i++){
                c = 0;
                if (p->tree.a[i] == x){
                        return i;
                        c++;
                }
        }
        if(c == 0)
                return -1;
}

/*
  p-ийн зааж буй CBTree-д idx индекстэй зангилаанаас дээшхи бүх өвөг эцэгийг олох үйлдлийг хийнэ.
  Тухайн орой өөрөө өвөг эцэгт орохгүй.
  Өвөг эцэг бүрийг нэг шинэ мөрөнд хэвлэнэ. Өвөг эцэгийг доороос дээшхи дарааллаар хэвлэнэ.
*/
void cb_ancestors(const CBTree *p, int idx)
{
        /* Энд өвөг эцгийг олох үйлдлийг хийнэ үү */
        while( idx < p->tree.len && idx > 0){
                printf("%d\n", p->tree.a[(idx - 1) / 2]);
                idx = (idx - 1) / 2;
        }
}

/*
  p-ийн зааж буй CBTree-ийн өндрийг буцаана
*/
int cb_height(const CBTree *p)
{
        /* Энд өндрийг олох үйлдлийг хийнэ */
        int c = 0;
        int i = 0;
        while ( i < p->tree.len){
                i = 2 * i + 1;
                c++;
        }
        return c;
}

/*
  p-ийн зааж буй CBTree-д idx оройны ах, дүү оройн дугаарыг буцаана.
  Тухайн оройн эцэгтэй адил эцэгтэй орой.
  Ах, дүү нь байхгүй бол -1-г буцаана.
*/
int cb_sibling(const CBTree *p, int idx)
{
        /* Энд ах, дүүг олох үйлдлийг хийнэ үү */
        if (idx == p->cb_len - 1 && idx % 2 == 1)
                return -1;
        else {
                if (idx < p->cb_len && idx > 0){
                        if (idx % 2 == 1){
                                return idx + 1;
                }
                                
                        else {
                                return idx - 1 ;
                        } 
                }
                        else {
                                return -1 ;
                        }
                }
}

/*
  p-ийн зааж буй CBTree-г idx дугаартай зангилаанаас эхлэн preorder-оор хэвлэ.
  Орой бүрийг нэг шинэ мөрөнд хэвлэнэ.
*/
void cb_preorder(const CBTree *p, int idx)
{
        /* Энд pre-order-оор хэвлэх үйлдлийг хийнэ үү */
        Stack *cbt = (struct Stack *) malloc(sizeof(Stack)) ;
        cbt->len = 0 ;
        cbt->top = NULL ;
        s_push(cbt, idx) ;
        while (cbt->len > 0){
                int top_cbt = cbt->top->x ;
                s_pop(cbt) ;
                if (top_cbt < p->tree.len) {
                        printf("%d\n", p->tree.a[top_cbt]);
                        s_push(cbt, top_cbt * 2 + 2);
                        s_push(cbt, top_cbt * 2 + 1);

                }
        }
}

/*
  p-ийн зааж буй CBTree-г idx дугаартай зангилаанаас эхлэн in-order-оор хэвлэ.
  Орой бүрийг нэг шинэ мөрөнд хэвлэнэ.
*/
void cb_inorder(const CBTree *p, int idx)
{
        /* Энд in-order-оор хэвлэх үйлдлийг хийнэ үү */
        int a[100];
        Stack *cbt = malloc(sizeof(Stack));
        memset(a, 0, sizeof(a));
        cbt->len = 0;
        cbt->top = NULL;

        s_push(cbt, idx);
        while(cbt->len){
                int top_cbt = cbt->top->x;
                if (p->tree.len <= top_cbt){
                        s_pop(cbt);
                        continue; 
                }
                if (a[top_cbt] == 0){
                        s_push(cbt, 2 * top_cbt + 1 );
                }
                if (a[top_cbt] == 1) {
                        s_pop(cbt);
                        printf("%d\n", p->tree.a[top_cbt]);
                        s_push(cbt, 2* top_cbt + 2 );
        
                }
                a[top_cbt]++;

        }
}

/*
  p-ийн зааж буй CBTree-г idx дугаартай зангилаанаас эхлэн post-order-оор хэвлэ.
  Орой бүрийг нэг шинэ мөрөнд хэвлэнэ.
 */
void cb_postorder(const CBTree *p, int idx)
{
        /* Энд post-order-оор хэвлэх үйлдлийг хийнэ үү */
        int a[100];
        Stack *elm = malloc(sizeof(Stack));
        memset(a, 0, sizeof(a));
        elm->len = 0;
        elm->top = NULL;
        s_push(elm, idx);
        while (elm->len){
                int top_elm;
                top_elm = elm->top->x;
                if (p->tree.len <= top_elm) {
                        s_pop(elm);
                        continue;
                }
                if (a[top_elm] == 0){
                        s_push(elm, 2 * top_elm + 1);
                }
                if (a[top_elm] == 1){
                        s_push(elm, 2 * top_elm + 2);
                }
                if (a[top_elm] == 2){
                        s_pop(elm) ;
                        printf("%d\n", p->tree.a[top_elm]);
                }
                a[top_elm]++;
        }
}

/*
  p-ийн зааж буй CBTree-с idx дугаартай зангилаанаас доошхи бүх навчийг олно.
  Навч тус бүрийн утгыг шинэ мөрөнд хэвлэнэ.
  Навчыг зүүнээс баруун тийш олдох дарааллаар хэвлэнэ.
*/
void cb_leaves(const CBTree *p, int idx)
{
        /* Энд навчуудыг үйлдлийг хийнэ үү */
        if (idx < p->tree.len){
                if (idx * 2 + 1 >= p->tree.len)
                        printf("%d\n", p->tree.a[idx]);
                cb_leaves(p, idx * 2 + 1); 
                cb_leaves(p, idx * 2 + 2);
        }

}

/*
  p-ийн зааж буй CBTree-д idx индекстэй оройноос доошхи бүх үр садыг хэвлэнэ.
  Тухайн орой өөрөө үр сад болохгүй.
  Үр, сад бүрийг нэг шинэ мөрөнд хэвлэнэ. Үр садыг pre-order дарааллаар хэлэх ёстой.
*/
void cb_descendants(const CBTree *p, int idx)
{
        /* Энд үр садыг олох үйлдлийг хийнэ үү */
        if (idx < p->tree.len){
                cb_descendants(p, idx * 2 + 1);
                cb_descendants(p, idx * 2 + 2); 
        }
}


/*
  p-ийн зааж буй Tree-д хэдэн элемент байгааг буцаана.
  CBTree-д өөрчлөлт оруулахгүй.
*/
int cb_size(const CBTree *p)
{
        /* Энд хэмжээг олох үйлдлийг хийнэ үү */
        return p->tree.len; 	
}


/*
  p-ийн зааж буй CBTree-д x утгаас үндэс хүртэлх оройнуудын тоог буцаана.
  x тоо олдохгүй бол -1-г буцаана.
*/
int cb_level(const CBTree *p, int x)
{
        /* Энд түвшинг олох үйлдлийг хийнэ үү */
        int i = cb_search(p, x);
        int c = 0;
        if (i == -1)
                return -1;
        else{
                while (i > 0){
                        if (i % 2 == 0){
                                i = i / 2 - 1 ;
                                c++ ;
                        }
                        else {
                                i = i / 2 ;
                                c++ ;
                        }
                }
                return c ;
        }

}

