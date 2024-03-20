#include "DS.h"

/*
  `ptree`-ийн зааж байгаа модонд `x` утгыг оруулна.
  Оруулахдаа хоёртын хайлтын модны зарчмаар оруулах бөгөөд оруулсан
  байрлалаас дээшхи өвөг эцгийн `len`, `height` утгууд өөрчлөгдөнө.
  Мод хоосон байсан бол `ptree->root` хаяг өөрчлөгдөж шинээр орсон оройг заана.
 */
Elm *createNode(int x){
        Elm *bs = (Elm *) malloc(sizeof(Elm));
        bs-> x = x;
        bs->height = bs->len = 1;
        bs->R = bs->L = NULL;
        return bs;
        }

Elm *gt_elm(Elm *ptr, int x){
        if (ptr == NULL || x == ptr->x)
                return ptr;
        if (x > ptr->x)
                return gt_elm(ptr->R, x);
        else
                return gt_elm(ptr->L, x);

}
Elm *delete(Elm *p, int x){
        Elm *t;
        if (p == NULL)
                return p;

        if (x < p->x)
                p->L = delete(p->L, x);

        else if (x > p->x)
                p->R = delete(p->R, x);
        else
        {
                if (p->L == NULL)
                {
                        t= p->R;
                        return t;
                }
                else if (p->R == NULL)
                {
                        t = p->L;
                        return t;
                }
                t = p->R;
                while (t->L != NULL)
                        t = t->L;

                p->x = t->x;
                p->R = delete(p->R, p->x);
        }
        int lc = p->L == NULL ? 1 : p->L->height + 1;
        int rc = p->R == NULL ? 1 : p->R->height + 1;
        p->height = lc > rc ? lc : rc;
        p->len--;
        return p;
}
int min(Elm *p)
{
        Elm *t = p;
        while (t->L != NULL)
        {
        t = t->L;
        }
        return t->x;
}
void insert(Elm *p, int x){
        if (p == NULL){
                p = (Elm *)malloc(sizeof(Elm));
                p->x = x;
                p->L = p->R = NULL;
                }
        if (p->x > x){
                if (p->L == NULL)
                {
                        p->L = createNode(x);
                }
                else
                        insert(p->L, x);
                }
        else {
                if (p->R == NULL)
                {
                        p->R = createNode(x);
                }
                else
                        insert(p->R, x);
        }

        int lc = p->L == NULL ? 1 : p->L->height + 1;
        int rc = p->R == NULL ? 1 : p->R->height + 1;
        p->height = lc > rc ? lc : rc;
        p->len++;
}
void bs_put(BST *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
         if (ptree->root == NULL){
                ptree->root = createNode(x);
                return;
        }
        insert(ptree->root, x);

}

/*
  `ptree`-ийн зааж байгаа модноос `x` утгыг хайн олдсон оройн `Elm*` хаягийг буцаана.
  Олдохгүй бол `NULL` хаягийг буцаана.
  Мод дандаа ялгаатай элементүүд хадгална гэж үзэж болно.
 */
Elm *bs_get(const BST *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        gt_elm(ptree->root, x);
}

/*
  Устгах функц: ХХМноос `x` утгыг хайж олоод устгана.
  Олдохгүй бол юу ч хийхгүй.
 */
void bs_del(BST *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        ptree->root = delete(ptree->root, x);


}

/*
  Хамгийн багыг устгах функц: ХХМноос хамгийг бага утгыг нь устгах функц.
  Устгасан утгыг буцаана.
 */
Elm *dlmin(Elm *p){
        if (p->L == NULL){
                return p->R ;
        }
        p->L = dlmin(p->L) ;
        p->len--; 
        return p;
}

int bs_delMin(BST *ptree)
{
        // Функцийг хэрэгжүүлнэ үү
        int t = min(ptree->root) ;
        ptree->root = dlmin(ptree->root);
        return t;
}

/*
  ХХМыг inorder дарааллаар, нэг мөрөнд нэг утга хэвлэнэ.
 */
void inor(Elm *ptr){
        if (ptr == NULL){
                return;
        }
        inor(ptr->L) ;
        printf("%d %d %d\n", ptr->x, ptr->len, ptr->height) ;
        inor(ptr->R) ;
        
}
void bs_inorder(const BST *ptree)
{
        // Функцийг хэрэгжүүлнэ үү
       inor(ptree->root);
}

/*
  ХХМноос `x` утгаас эрс бага буюу тэнцүү байх хэдэн орой байгааг олж буцаана.
  Өөрөөр хэлбэл хоёртын хайлтын модны утгуудыг өсөх дарааллаар байрлуулбал
  `x`-ийг оролцуулаад өмнө хэдэн тоо байх вэ? гэсэн үг.
  `x` утга заавал модонд байх албагүй.
 */
int lo_r(Elm *ptr, int x) {
        if (ptr == NULL){
                return 0;
        }
        if (ptr->x > x){
                return lo_r(ptr->L, x);
        }
        int left = ptr->L == NULL ? 1 : ptr->L->len + 1;
        return left + lo_r(ptr->R, x);
        
}
int bs_rank(const BST *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        if (ptree->root != NULL){
        return lo_r(ptree->root, x) ;
        }
}

/*
  ХХМноос `x` утгатай оройг хайж олоод, тухайн оройд суурилсан
  дэд модонд хэдэн орой байгааг олж буцаана.
  Олдохгүй бол -1-ийг буцаана.
 */
int bs_size(const BST *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        Elm *ptr = bs_get(ptree, x);
        return ptr == NULL ? -1 : ptr->len ;
}

/*
  XXMноос `x`-ээс бага буюу тэнцүү байх хамгийн их утгын `Elm *` хаягийг олж буцаана.
  Олдохгүй бол `NULL` хаягийг буцаана.
 */
Elm *fl(Elm *ptr, int x){
        if (ptr == NULL) 
                return NULL;
        if (ptr-> x > x){
                return fl(ptr->L, x);
        }
        else if (ptr->x == x){
                return ptr;
        }
        else {
                Elm *t = fl(ptr->R, x);
                if (t == NULL){
                        return ptr;
                }
                else {
                        return t;
                }
        }
}
Elm *bs_floor(const BST *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
         if (ptree->root == NULL)
                return NULL;
        else
                return fl(ptree->root, x);

}

/*
  XXMноос `x`-ээс их буюу тэнцүү байх хамгийн бага утгын `Elm *` хаягийг олж буцаана.
  Олдохгүй бол `NULL` хаягийг буцаана.
 */
Elm *ceil(Elm *ptr, int x){
        if (ptr == NULL) {
                return NULL ;
        }
        if (ptr->x < x){
                return ceil(ptr->R, x) ;
        }
        else if (ptr->x == x){
                return ptr ;
        }
        else {
                Elm *t = ceil(ptr->L, x);
                if (t == NULL)
                        return ptr;
                else
                        return t;
        }
}
Elm *bs_ceiling(const BST *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        if (ptree->root == NULL)
                return NULL;
        else
                return ceil(ptree->root, x);
}

/*
  ХХМноос `x` утгатай оройг хайж олоод, тухайн оройд суурилсан
  дэд модны өндөр хэд байгааг олж буцаана. Олдохгүй бол -1-ийг буцаана.
 */
int bs_height(const BST *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        Elm *ptr = bs_get(ptree, x);
        return ptr == NULL ? -1 : ptr->height;
}
