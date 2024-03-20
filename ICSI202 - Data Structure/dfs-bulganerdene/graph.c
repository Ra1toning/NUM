#include "DS.h"
/*
  Графыг эхлүүлэх функц: `g` графын хөршүүдийг хадгалах жагсаалтан хүснэгтийг эхлүүлэх функц.
  Санах ойг бэлдэж, жагсаалтын `head`, `tail` утгуудад `NULL` онооно.
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
  Гүний нэвтрэлтийн функц: `g` граф дээр гүний нэвтрэлт хийн холбоост бүрдлүүдийн талаарх
  даалгаварт заагдсан мэдээллийг хэвлэнэ.
 */
int c = 1;
void dfs(Graph *g, int v, int marked[]){

    List adj = g->adj[v];  // v oroitoi holbootoi buh elm-n medeellig hadgalah list
	
	List *t = &adj; // adj husnegtiin odoo baigaa tuluviig tur hadgalah t

	marked[v] = 1; // ochsn oroigoo ochsnoor temdeglene
	// 	Husnegtiin tolgoi haygiig NULL boltol shalgana
	while (t->head != NULL){

		 int temp = t->head->x;
		 l_pop_front(t) ; //urdaas ni ustgaad, t-g updateln
		
		if (marked[temp] == -1){ // tuhain oroi deer ochoogui bol
			c++; //dfs iig heden udaa duudna ter too ni heden shirheg component baina ve gedgiig ilerhiilne tiimes c++
			dfs(g, temp, marked);
		}
	}
}
void gr_connected_components(Graph *g, int *cc)
{
	/* Энд функцийг хэрэгжүүл */
	int v; // graphiin oroin too
	int marked[g->n+1]; // ochson oroigoo mark hiih array
	int t = 0; //temp
	for(v = 1; v < g->n+1; v++)
		marked[v] = -1; // oroinuudaa ochoogui gj duurgene
	for(v = 1; v < g->n+1; v++){
		//tuhain oroi deer umnu ni ochjegagu bol ter oroi luu ocood tendeesee ahiad adj oroiluu ochn
		if(marked[v] == -1){
			//ochsonin temdegldeg func
			dfs(g, v, marked);
			t++;
				//cc husnegtiin ehnii element l husnegted oruulsan elementiin too buyu niit heden burdel heseg baigaa talaarhi medeellig hadgalj baigaa harin busad tohioldold component tus buriin oroin toog hadgalna*/
			cc[t] = c;
		}
		c = 1;
	}
	cc[0] = t;
}

/*
  Ирмэг нэмэх функц: `g` графын ирмэгүүдийг хадгалах `adj` жагсаалтан хүснэгтэд ирмэг нэмнэ.
  Уг граф нь чиглэлгүй граф тул `x`-с `y`, `y`-с `x` гэсэн хоёр ирмэгийг оруулна.
 */
void gr_add_edge(Graph *g, int x, int y)
{
	/* Энд функцийг хэрэгжүүл */
  	l_push_back(&g->adj[x], y);
  	l_push_back(&g->adj[y], x);
}
