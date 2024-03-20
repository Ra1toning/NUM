#include "DS.h"

Elm *createNode(int x){
        Elm *avl=(Elm *)malloc(sizeof(Elm));
        avl->x=x;
        avl->height=1;
        avl->len=1;
        avl->R=NULL;
        avl->L=NULL;
        return avl;
}
int max(int a, int b){
    return (a > b) ? a : b;
}
int getHeight(Elm *node){
    if(node==NULL){
        return 0;
    }
    return node->height;
}
Elm *rightRotate(Elm *y){
    Elm *x = y->L;
    Elm *T2 = x->R;
    x->R = y;
    y->L = T2;
    y->height = max(getHeight(y->L),getHeight(y->R))+1;
    x->height = max(getHeight(x->L),getHeight(x->R))+1;
    y->len = 1 + (y->L?y->L->len:0)+(y->R?y->R->len:0);
    x->len = 1 + (x->L?x->L->len:0)+(x->R?x->R->len:0);
    return x;
}
Elm *leftRotate(Elm *x){
    Elm *y = x->R;
    Elm *T2 = y->L;
    y->L = x;
    x->R = T2;
    x->height = max(getHeight(x->L),getHeight(x->R))+1;
    y->height = max(getHeight(y->L),getHeight(y->R))+1;
    x->len = 1 + (x->L?x->L->len:0)+(x->R?x->R->len:0);
    y->len = 1 + (y->L?y->L->len:0)+(y->R?y->R->len:0);
    return y;
}
int getBalance(Elm *node){
    if(node == NULL){
        return 0;
    }
    return getHeight(node->L)-getHeight(node->R);
}
Elm *put(Elm *node,int x){
        if(node == NULL){
                node=(Elm*)malloc(sizeof(Elm));
                node->x=x;
                node->L=NULL;
                node->R=NULL;
                node->height=1;
                node->len=1;
                return node;
        } if(node->x > x){
                node->L = put(node->L,x);
        } else if(x>node->x){
                node->R = put(node->R,x);
        } else{
                return node;
        }
        node->height = 1 + max(getHeight(node->L),getHeight(node->R));
        node->len = 1 + (node->L?node->L->len:0)+(node->R?node->R->len:0);
        int balance = getBalance(node);
        if(balance > 1){
                if(x<node->L->x){
                        return rightRotate(node);
                } else{
                        node->L=leftRotate(node->L);
                        return rightRotate(node);
                }
        }
        if(balance < -1){
                if(x > node->R->x){
                return leftRotate(node);
                } else{
                        node->R = rightRotate(node->R);
                        return leftRotate(node);
                }
        }
        return node;
}
void avl_put(AVL *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        if(ptree->root == NULL){
                ptree->root = createNode(x);
                return;
        }
        ptree->root = put(ptree->root,x);
}
Elm *get(Elm *avl,int x){
        if(avl == NULL || x == avl->x){
                return avl;
        } if(x > avl->x){
                return get(avl->R,x);
        } else{
                return get(avl->L,x);
        }
}
Elm *avl_get(const AVL *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        return get(ptree->root,x);
}

int min(Elm *p){
        Elm *t = p;
        while(t->L != NULL){
                t = t->L;
        } return t->x;
}
Elm *del(Elm *node,int x){
        if(node == NULL){
                return node;
        }
        if(x < node->x){
                node->L = del(node->L,x);
        } else if(x>node->x){
                node->R = del(node->R,x);
        } else{
                if((node->L == NULL) || (node->R == NULL)){
                        Elm *temp=node->L ? node->L : node->R;
                        if(temp == NULL){
                                temp = node;
                                node = NULL;
                        } else{
                                *node = *temp;
                        }
                        free(temp);
                } else{
                        Elm *temp=node->R;
                        while(temp->L != NULL){
                                temp=temp->L;
                        }
                        node->x = temp->x;
                        node->R = del(node->R,temp->x);
                        node->height = 1 + max(getHeight(node->L), getHeight(node->R));
                        node->len = 1 + (node->L ? node->L->len : 0) + (node->R ? node->R->len : 0);
                }
        }
        if(node == NULL){
                return node;
        }
        node->height = 1 + max(getHeight(node->L),getHeight(node->R));
        node->len=1+(node->L ? node->L->len : 0) + (node->R ? node->R->len : 0);
        int balance=getBalance(node);
        if(balance>1){
        if(getBalance(node->L) >= 0){
            return rightRotate(node);
        } else{
            node->L = leftRotate(node->L);
            return rightRotate(node);
        }
    }
    if(balance < -1){
        if(getBalance(node->R) <= 0){
            return leftRotate(node);
        } else{
            node->R = rightRotate(node->R);
            return leftRotate(node);
        }
    }
    return node;
}
void avl_del(AVL *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        ptree->root = del(ptree->root,x);
}


Elm *delMin(Elm *p){
        if(p->L == NULL){
                return p->R;
        }
        p->L = delMin(p->L);
        p->height = 1 + max(getHeight(p->L),getHeight(p->R));
        p->len = 1 + (p->L?p->L->len:0)+(p->R?p->R->len:0);
        return p;
}
int avl_delMin(AVL *ptree)
{
        // Функцийг хэрэгжүүлнэ үү
        int t = min(ptree->root);
        ptree->root = delMin(ptree->root);
        return t;
}

void inorder(Elm *avl){
        if(avl==NULL){
                return;
        }
        inorder(avl->L);
        printf("%d %d %d\n",avl->x,avl->len,avl->height);
        inorder(avl->R);
}
void avl_inorder(const AVL *ptree)
{
        // Функцийг хэрэгжүүлнэ үү
        inorder(ptree->root);
}


int avl_size(const AVL *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        Elm *avl=avl_get(ptree,x);
        return avl == NULL ? -1 : avl->len;
}

int avl_height(const AVL *ptree, int x)
{
        // Функцийг хэрэгжүүлнэ үү
        Elm *avl = avl_get(ptree,x);
        return avl == NULL ? -1 : avl->height;
}
