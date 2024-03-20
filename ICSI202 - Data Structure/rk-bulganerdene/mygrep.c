#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int case_insensitive = 0;
int R = 256;
int Q = 997;
int RM1 = 1;

int sensitive(const char c){
        if (case_insensitive == 1 && c <= 'Z' && c >= 'A'){
                return c + 32 ;
        }
        return c; 
}
/*
  s тэмдэгтэн цувааны hash-ыг буцаана
 */
int hash(const char *s)
{
        /*
          Функцыг гүйцээ
         */
        long h = 0;
        for(int i = 0; i < strlen(s); i++){
           h =( h * R + s[i]) % Q;
        }
        return h;
}

/*
  Функц нь buf доторх тэмдэгтэн цуваанаас index байрлалаас эхлэн
  pattern тэмдэгтийг хайна. Олдвол олдсон индексийг буцанаа.
  Олдохгүй бол -1 утгыг буцаана.
  case_insensitive = 1 бол том жижиг гэж ялгалгүйгээр тааруулна.
  Мөн мөрний мэдээллийг давхар хариуцаж явна.
  Мөр шилжих үед (*pline)++

  buf     :    Хайлт хийх тэмдэгтэн цуваа
  n       :    buf-ын урт
  index   :    Хайлтыг эхлэх индекс
  pattern :    Хайх үг
  m       :    pattern-ны урт
  pat_hash:    pattern хэвийн hash
  pline   :    Мөрийн мэдээлэл хадгалах хувьсагчийн хаяг
 */


int val(const char *buf, const char *patt, int i){
        for(int k = 0 ; k < strlen(patt); k++){
                if (sensitive(patt[k]) != sensitive(buf[i + k])){
                        return 0; 
                }
        }
        return 1 ;
}

int find(const char *buf, int n, int index, const char *pattern, int m, int pat_hash, int *pline)
{
        /*
          Функцыг гүйцээ
         */
        int hash = 0 ;
        for(int i = index ; i < m  + index; i++){
                hash = (hash * R + sensitive(buf[i])) % Q ;
        }

        if ((pat_hash == hash) && val(buf, pattern, index)){
                return index;
        }
        for(int i = index + m; i < n ; i++){
                   
                hash = (hash + Q - RM1 * sensitive(buf[i - m]) % Q) % Q ;
                hash = (hash * R + sensitive(buf[i])) % Q;
                if (buf[i - m] == '\n') {

                        (*pline)++ ;
                } 
                int t = i - m + 1;
                if ((pat_hash == hash) && val(buf, pattern, t)) {                
                        return t;
                }
        }
        return -1;
}

int main(int argc, char *argv[])
{
        // Аргументийн тоо ядаж 3 байх ёстой.
        if (argc < 3) {
                printf("%s [-i] pattern FILE", argv[0]);
                exit(-1);
        }

        const char *pattern;
        if (argc > 3 && strcmp("-i", argv[1]) == 0) {
                case_insensitive = 1; // Том жижгийг хайхрахгүй адил гэж үзнэ.
                pattern = argv[2];
        } else
                pattern = argv[1];
        
        FILE *fin;
        fin = fopen(argv[argc - 1], "r");
        if (fin == NULL) {
                printf("Error openning %s file", argv[argc - 1]);
                exit(-1);
        }
        int len = 0;
        char ch;                 
        while (!feof(fin)) {
                ch = fgetc(fin);
                len++;          // Файлд доторх тэмдэгтүүдийг тоолъё.
        }
        char *buf;
        buf = (char *) malloc(sizeof(char) * (len + 1)); // Энд тэмдэгтүүдийг хадгална
        fseek(fin, 0, SEEK_SET);                        // Файл заагчийг буцаад эхлэлд нь аваачна.
        int i = 0;
        while (!feof(fin))
                buf[i++] = fgetc(fin); // Өгөгдлийг уншиж байна.
        buf[len - 1] = 0;              /* тэмдэгт мөр төгсгөл заагч */

        int ret = -1;
        int line = 0;
        int pat_hash = hash(pattern);  // hash кодыг тооцоолно
        int m = strlen(pattern);
        
        for(int i = 1; i <m; i++){
           RM1 = (RM1 * R) % Q;
        }
        do {
                ret = find(buf, len - 1, ret + 1, pattern, m, pat_hash, &line);
                if (ret != -1) {
                        printf("%d: ", line);
                        /*
                          Олдсон газраас доошоо 5
                          дээшээ 5 тэмдэгтийг хэвлэнэ.
                         */
                        int L = ret - 5;
                        int R = ret + strlen(pattern) + 5;
                        if (L < 0) L = 0;
                        if (R >= len) R = len - 1;
                        for (i = L; i <= R; i++)
                                printf("%c", buf[i]);
                        printf("\n");
                }
        } while (ret != -1);

        free(buf);
        return 0;
}
