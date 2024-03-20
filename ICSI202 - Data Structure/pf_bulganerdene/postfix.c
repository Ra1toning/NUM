#include "DS.h"

int is_space(int x)
{
        switch(x) {
        case ' ':
        case '\t':
        case '\n':
        case '\r':
        case '\f':
        case '\v':
        case '\0':
                return 1;
        default:
                return 0;
        }
        return 0;
}

int convert_to_int(const char s[])
{
	int len = strlen(s);
	int t = 0, i;
	for (i = 0; i < len; i++)
		t = t * 10 + s[i] - '0';
	return t;
}

void tokenize(const char s[], List *p_list)
{
	char tmp[EQUATION_LENGTH];
	int i, j, k, len;
	j = 0;
	struct Token x;
	len = strlen(s);
	for (i = 0; i <= len; i++) {
		if ('0' <= s[i] && s[i] <= '9') {
			/* цифр орж ирлээ */
			tmp[j] = s[i];
			j++;
		} else {
			/* тэмдэгт орж ирлээ */
			if (j != 0) { /* Хөрвүүлэх тоо байгаа эсэх */
				tmp[j] = '\0';
				j = 0;
				/* хадгалсан цифрийн цувааг int-рүү хөрвүүл */
				k = convert_to_int(tmp);
				x.flag = 1;
				x.val = k;
				/*
				  Жагсаалтанд x элемнтийг оруулах
				  Жагсаалтын push_back функцыг дуудна
				*/
				l_push_back(p_list, x);
			}
			/*
			  тэмдэгтийг жагсаалтанд оруулах
			  Жагсаалтын push_back функцыг дуудна
			 */
			if (is_space(s[i])) /* хоосон зай, шинэ мөрийг хаяна. */
				continue;

            /*
              Энд +, -, *, /, (, ) тэмдэгтүүдийг жагсаалтад оруулна.
             */
			x.flag = 0;
			x.op = s[i];
			l_push_back(p_list, x);
		}
	}

	/* Тэмдэгтэн цуваанаас салгасан тэгшитгэлийг хэвлэх
	   Жагсаалтын print функцыг дуудна.
	 */
	/* l_print(p_list); */
}

/*
  p_token - жагсаалтад байгаа тэгштгэлийг postfix-рүү хөрвүүлнэ
 */
void convert_to_postfix(List *p_token, List *p_post)
{
	Stack *st = malloc(sizeof(Stack));
	st->len = 0;
	st->top = NULL;
	TokenElm *elm = p_token->head;


	while (elm != NULL){
		if (elm->x.flag == 0){
			int operator = elm->x.op;
			if (st->len == 0 || operator == 40){

				s_push(st, operator);
			}
			else {
				if (operator == 41){
					while(st->top->x != 40){
						Token token;
						token.flag = 0; 
						token.op = st->top->x;
						s_pop(st); 
						l_push_back(p_post, token); 
					}
					s_pop(st);
				}
				else {
					if (operator == '-' || operator == '+'){
						while(st->len > 0 && st->top->x != 40){
							Token token;
							token.flag = 0;
							token.op = st->top->x;
							s_pop(st);
							l_push_back(p_post, token);	
												}
						s_push(st, operator);
					}
					else {
						if (st->top->x == '/' || st->top->x == '*' || st->top->x == '%'){
							Token token;
							token.flag = 0;
							token.op = st->top->x;
							s_pop(st);
							l_push_back(p_post, token);

						}
						s_push(st, operator);
					}
				}
			}
		}
		else {
			l_push_back(p_post, elm->x);
		}
		elm = elm->next;
	}
	while (st->len){
		Token token;
		token.flag = 0;
		token.op = st->top->x;
		s_pop(st);
		l_push_back(p_post, token);
	}
		
}

int solve(List *p_post)
{
	Stack *st = malloc(sizeof(Stack));
	st->len = 0;
	st->top = NULL;
	int m, n;
	int s;

	TokenElm *elm = p_post->head;

	while (elm != NULL){
		if (elm->x.flag){

			s_push(st, elm->x.val);
		}
		else {

			m = st->top->x;

			s_pop(st);
			n = st->top->x;
			s_pop(st) ;
			if (elm->x.op == '+')
				s = n + m;
			
			else if (elm ->x.op == '*')
				s = n * m;

			else if (elm->x.op == '-')
				s = n - m;
			
			else if (elm->x.op == '%')
				s = n % m;
			
			else if (elm->x.op == '/')
				s = n / m ;
			
			s_push(st, s);
		}
		elm = elm->next;
	}
	return s;	
}
