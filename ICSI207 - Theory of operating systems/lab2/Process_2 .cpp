#include <stdio.h>
#include <stdlib.h>
#include <conio.h> 
#define m 3 
#define n 4 
#define k 3 
#define l 4 
#define e 3 
#define g 4 

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

    int A[m][n], B[k][l], C[e][g]; 


    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            A[i][j] = rand() % 10;
        }
    }

    for (int i = 0; i < k; i++) {
        for (int j = 0; j < l; j++) {
            B[i][j] = rand() % 10;
        }
    }

    while (1) { 
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < g; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        printf("C MATRITS:\n");
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < g; j++) {
                printf("%d ", C[i][j]);
            }
            printf("\n");
        }

        process.process_counter++;

        if (_kbhit()) {
            char ch = _getch(); 
            if (ch == 27) { 
                break; 
            }
        }
    }

    return 0;
}

