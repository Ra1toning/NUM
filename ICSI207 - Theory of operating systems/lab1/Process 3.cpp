#include <stdio.h>
#include <ctype.h>
int main() {
   char str[100];
   int i, useg[26] = {0}; //
   printf("Ugugdluu oruulna uu: ");
   gets(str);
   for(i = 0; str[i] != '\0'; i++) {
      if(isalpha(str[i])) {
         useg[tolower(str[i]) - 'a']++;
      }
   }
   printf("Garalt:\n");
   for(i = 0; i < 26; i++) {
      if(useg[i] > 0) {
         printf("%c=%d ", i + 'a', useg[i]);
      }
   }
   return 0;
}
