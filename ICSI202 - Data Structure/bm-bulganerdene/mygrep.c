#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define max(n,m) ((n) > (m) ? (n) : (m))
int case_insensitive = 0;

/*
  Шилжилтийн хүснэгтийг байгуулах функц
 */
void BM(const char *pattern, int right[])
{
        /*
          Функцыг гүйцээ
         */          
 for (int i = 0; i < 256; i++){ 
     right[i] = -1; 
   } 
   for (int i = 0; i < strlen(pattern); i++){ 
     if (case_insensitive){ 
       right[pattern[i]] = i; 
       right[pattern[i] + 32] = i; 
       right[pattern[i] - 32] = i; 
  
     } 
     else{ 
       right[pattern[i]] = i; 
     }
   }
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
  right   :    шилжилтийн хүснэгт
  pline   :    Мөрийн мэдээлэл хадгалах хувьсагчийн хаяг
 */
int find(const char *buf, int n, int index, const char *pattern, int m, int right[], int *pline, int tlen)
{
        /*
          Функцыг гүйцээ
         */      
         int s = 0; 
         int i;
         for (i = index; i <= tlen; i = i + s) { 
             s = 0; 
             for (int j = m - 1; j >= 0; j--) {         
             if (case_insensitive) { 
                 if (buf[i + j] != pattern[j] && buf[i + j] != pattern[j] + 32 && buf[i + j] != pattern[j] - 32) { 
                    s = max(1, j - right[buf[i + j]]); 
                    break; 
                 } 
            } 
            else { 
                 if (buf[i + j] != pattern[j]) { 
                    s = max(1, j - right[buf[i + j]]); 
                    break; 
                  } 
            } 
       } 
       if (s == 0) { 
          for (int k = i; k > index; k--) { 
               if (buf[k] == '\n') { 
                  (*pline)++; 
               } 
          } 
      return i; 
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


        int R = 256;
        int right[R];
        int m = strlen(pattern);
        BM(pattern, right);
        
        do {
                ret = find(buf, len - 1, ret + 1, pattern, m, right, &line, len);
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
