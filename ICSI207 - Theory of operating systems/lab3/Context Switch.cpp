#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <time.h>
#include <unistd.h>
#include <ctype.h>

#define m 3 
#define z 4 
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
void quick_sort(int arr[], int low, int high) {
    if (low < high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        int p = i + 1;
        quick_sort(arr, low, p - 1);
        quick_sort(arr, p + 1, high);
    }
}
void process1() {
	struct PCB process;
    process.process_id = 1;
    process.priority = 1;
    process.state = 1;
    process.process_counter = 0;
    int n = 20;
    int arr[n];
    int i;

    srand(time(NULL));

    time_t start_time = time(NULL);

    while (difftime(time(NULL), start_time) < 10.0) {
        for (i = 0; i < n; i++) {
            arr[i] = rand() % n;
        }

        quick_sort(arr, 0, n-1);

        for (i = 0; i < n; i++) {
            printf("%d ", arr[i]);
        }
        printf("\n");

        usleep(500000);
    }
}
void process2() {

	struct PCB process;
    process.process_id = 2;
    process.priority = 2;
    process.state = 1;
    process.process_counter = 0;
    int A[m][z], B[k][l], C[e][g]; 

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < z; j++) {
            A[i][j] = rand() % 10;
        }
    }

    for (int i = 0; i < k; i++) {
        for (int j = 0; j < l; j++) {
            B[i][j] = rand() % 10;
        }
    }

    clock_t start_time = clock();
    while (1) { 
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < g; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        printf("C MATRIX:\n");
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < g; j++) {
                printf("%d ", C[i][j]);
            }
            printf("\n");
        }

        clock_t end_time = clock();
        double time_taken = ((double) (end_time - start_time)) / CLOCKS_PER_SEC;

        if (time_taken >= 5) {
            break; 
        }
    }
}
void process3() {
	struct PCB process;
    process.process_id = 3;
    process.priority = 3;
    process.state = 1;
    process.process_counter = 0;
    srand(time(NULL)); 

    time_t start_time = time(NULL); 

    while (difftime(time(NULL), start_time) < 3) {
        char str[100];
        int length = rand() % 20 + 1; 
        for (int i = 0; i < length; i++) {
            char ch = 'a' + rand() % 26; 
            str[i] = ch;
        }
        str[length] = '\0'; 

        printf("String: %s\n", str);

        int counts[26] = {0};

        for (int i = 0; str[i] != '\0'; i++) {
            char ch = tolower(str[i]); 
            if (isalpha(ch)) { 
                counts[ch - 'a']++; 
            }
        }

        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                printf("%c: %d\n", 'a' + i, counts[i]); 
            }
        }
    }
}
void context_switch(struct PCB processes[], int num_processes) {
    int current_process = 0;
    int previous_process = num_processes - 1;

    while (1) {
        // dragin process-luu switch hiine
        previous_process = current_process;
        current_process = (current_process + 1) % num_processes;

        // 3second pause hiine
        printf("Context Switching...Huleene uu...\n");
        sleep(3);

        // print  process
        printf("Switched to process %d\n", processes[current_process].process_id);

        // execute process
        switch (processes[current_process].process_id) {
            case 1:
                process1();
                break;
            case 2:
                process2();
                break;
            case 3:
                process3();
                break;
            default:
                break;
        }

        // process state shinechlene
        processes[previous_process].state = 0;
        processes[current_process].state = 1;
    }
}
int main() {
    struct PCB processes[] = {
        {1, 1, 1, 0},
        {2, 2, 1, 0},
        {3, 3, 1, 0}
    };

    int num_processes = sizeof(processes) / sizeof(struct PCB);

    context_switch(processes, num_processes);

    return 0;
}

