#include "DS.h"
#include <stdio.h>
/*
  Графыг эхлүүлэх функц: g графын хөршүүдийг хадгалах жагсаалтан хүснэгтийг эхлүүлэх функц.
  Санах ойг бэлдэж, жагсаалтын head, tail утгуудад NULL онооно.
 */
void gr_init_graph(Graph *g, int n)
{
	int i;
	g->adj = (List *) malloc(sizeof(List) * (n + 1));
	g->n = n;
	for (i = 0; i <= n; i++) {
		g->adj[i].head = g->adj[i].tail = NULL;
		g->adj[i].len = 0;
	}
}

/*
  Төвшний нэвтрэлтийн функц: g граф дээр s оройгоос эхлэн төвшний нэвтрэлтийг хийнэ.
  pi: Төвшний нэвтрэлт хийсний дараа pi[u] нь u оройд ямар оройгоос ирснийг хадгална.
 */
void gr_bfs(Graph *g, int s, int pi[])
{
	/* Энд функцийг хэрэгжүүл */
	Queue qu;
	qu.head=qu.tail=NULL;
	qu.len=0;
	q_push(&qu,s);
	int i;
	while(qu.len!=0){
		i=qu.head->x;
		q_pop(&qu);
		for(Elm *t=g->adj[i].head;t!=NULL;t=t->next){
			if(pi[t->x]==-1&&pi[i]!=t->x){
				pi[t->x]=i;
				q_push(&qu,t->x);
			}
		}
	}
}

/*
  Замын оройнуудыг хэвлэх функц: pi хүснэгтээс s, t орой хоорондын замын мэдээллийг хэвлэнэ.
  Хамгийн эхний мөрөнд замын уртыг, дараагийн мөрөнд s оройгоос эхлэн ямар оройгоор дамжиж
  очиж байгаа оройнуудыг 1 хоосон зайгаар тусгаарлан хэвлэнэ.
  Хэрэв зам байхгүй бол -1 гэсэн ганц утга хэвлэнэ.
 */
void gr_print_path(int pi[], int s, int t)
{
	Stack st;
	st.len = 0;
	st.top = NULL;
	/* Энд функцийг хэрэгжүүл */
	//...
	int count=0;
  	int i;
  	for(i=t;i!=-1;i=pi[i]){
    		s_push(&st,i);
    		count++;
  	}
  	if(st.top->x==s){
    		printf("%d\n",count);
    		while(st.len!=0){
      			count=st.top->x;
      			s_pop(&st);
      			printf("%d ",count);
    		}
  	} 
	else{
    		printf("%d",-1);
  	}
}

/*
  Ирмэг нэмэх функц: g графын ирмэгүүдийг хадгалах adj жагсаалтан хүснэгтэд ирмэг нэмнэ.
  Уг граф нь чиглэлгүй граф тул x`-с y`, y`-с x` гэсэн хоёр ирмэгийг оруулна.
 */
void gr_add_edge(Graph *g, int x, int y)
{
	/* Энд функцийг хэрэгжүүл */
	l_push_back(&g->adj[x],y);
	l_push_back(&g->adj[y],x);
}
