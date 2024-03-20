#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio.h>

#define MAX_LEN 100 
struct PCB {
    int process_id;
    int priority;
    int state;
    int process_counter;

};

int main() {
    struct PCB process;
    process.process_id = 1;
    process.priority = 10;
    process.state = 1;
    process.process_counter = 0;

    char input_str[MAX_LEN + 1]; 
    int char_count[256] = {0}; 
    while (1) { 

        printf("Enter a string: ");
        fgets(input_str, sizeof(input_str), stdin);
        input_str[strcspn(input_str, "\n")] = '\0'; 

       
        for (int i = 0; i < strlen(input_str); i++) {
            char_count[(int)input_str[i]]++;
        }

        printf("Garaas: %s\n", input_str);
        printf("Toolson:\n");
        for (int i = 0; i < 256; i++) {
            if (char_count[i] > 0) {
                printf("%c : %d\n", (char)i, char_count[i]);
            }
        }

        process.process_counter++; 

       
        memset(char_count, 0, sizeof(char_count));

        if (_kbhit()) {
            char ch = _getch(); 
            if (ch == 27) { 
                break; 
            }
        }
    }

    return 0;
}

