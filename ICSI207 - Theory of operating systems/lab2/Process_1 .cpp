#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <signal.h>
#include <unistd.h>


struct PCB {
    int process_id;       
    int priority;          
    int state;             
    int process_counter;    
 
};


void bubble_sort(int arr[], int n) {
    int i, j, temp;
    for (i = 0; i < n-1; i++) {
        for (j = 0; j < n-i-1; j++) {
            if (arr[j] > arr[j+1]) {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
}


void sigint_handler(int sig) {
    printf("\nDohiog huleen avlaa. Exiting...\n");
    exit(0);
}

int main() {
  
    signal(SIGINT, sigint_handler);

    int n = 10;         
    int arr[n];        
    int i;

    
    while (1) {
     
        srand(time(NULL));    
        for (i = 0; i < n; i++) {
            arr[i] = rand() % n;   
        }

        
        bubble_sort(arr, n);

       
        
        for (i = 0; i < n; i++) {
            printf("%d ", arr[i]);
        }
        printf("\n");

        usleep(500000);    
    }

    return 0;
}

