#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <ctype.h>

#define m 3
#define z 4
#define k 3
#define l 4
#define e 3
#define g 4

#define MEMORY_SIZE 150

struct PCB {
    int process_id;
    int priority;
    int state;
    int process_counter;
    int memory_start;
    int memory_size;
};

struct MemoryBlock {
    int start;
    int size;
    int allocated;
};

struct MemoryBlock memory[MEMORY_SIZE];

void initialize_memory()
{
    memory[0].start = 0;
    memory[0].size = MEMORY_SIZE;
    memory[0].allocated = 0;
}

int allocate_memory(int size)
{
    for (int i = 0; i < MEMORY_SIZE; i++)
    {
        if (!memory[i].allocated && memory[i].size >= size)
        {
            if (memory[i].size > size)
            {
                int remaining_size = memory[i].size - size;
                int remaining_start = memory[i].start + size;

                memory[i].size = size;
                memory[i].allocated = 1;

                memory[i + 1].start = remaining_start;
                memory[i + 1].size = remaining_size;
                memory[i + 1].allocated = 0;
            }
            else
            {
                memory[i].allocated = 1;
            }

            return memory[i].start;
        }
    }

    return -1;
}

void deallocate_memory(int start)
{
    for (int i = 0; i < MEMORY_SIZE; i++)
    {
        if (memory[i].start == start && memory[i].allocated)
        {
            memory[i].allocated = 0;
            return;
        }
    }
}

void quick_sort(int arr[], int low, int high)
{
    if (low < high)
    {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        int p = i + 1;
        quick_sort(arr, low, p - 1);
        quick_sort(arr, p + 1, high);
    }
}

void process1()
{
    struct PCB process;
    process.process_id = 1;
    process.priority = 1;
    process.state = 1;
    process.process_counter = 0;
    process.memory_size = 30;

    process.memory_start = allocate_memory(process.memory_size);

    if (process.memory_start == -1)
    {
        printf("Memory allocation failed for process %d\n", process.process_id);
        return;
    }

    int n = 20;
    int arr[n];
    int i;

    srand(time(NULL));

    time_t start_time = time(NULL);

    while (difftime(time(NULL), start_time) < 10.0)
    {
        for (i = 0; i < n; i++)
        {
            arr[i] = rand() % n;
        }

        quick_sort(arr, 0, n - 1);

        for (i = 0; i < n; i++)
        {
            printf("%d ", arr[i]);
        }
        printf("\n");
    }

    deallocate_memory(process.memory_start);
}

void process2()
{

    struct PCB process;
    process.process_id = 2;
    process.priority = 2;
    process.state = 1;
    process.process_counter = 0;
    process.memory_size = 40;

    process.memory_start = allocate_memory(process.memory_size);

    if (process.memory_start == -1)
    {
        printf("Memory allocation failed for process %d\n", process.process_id);
        return;
    }

    int A[m][z], B[k][l], C[e][g];

    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < z; j++)
        {
            A[i][j] = rand() % 10;
        }
    }

    for (int i = 0; i < k; i++)
    {
        for (int j = 0; j < l; j++)
        {
            B[i][j] = rand() % 10;
        }
    }

    clock_t start_time = clock();
    while (1)
    {
        for (int i = 0; i < e; i++)
        {
            for (int j = 0; j < g; j++)
            {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        printf("C MATRIX:\n");
        for (int i = 0; i < e; i++)
        {
            for (int j = 0; j < g; j++)
            {
                printf("%d ", C[i][j]);
            }
            printf("\n");
        }

        clock_t end_time = clock();
        double time_taken = ((double)(end_time - start_time)) / CLOCKS_PER_SEC;

        if (time_taken >= 5)
        {
            break;
        }
    }

    deallocate_memory(process.memory_start);
}

void process3()
{
    struct PCB process;
    process.process_id = 3;
    process.priority = 3;
    process.state = 1;
    process.process_counter = 0;
    process.memory_size = 20;

    process.memory_start = allocate_memory(process.memory_size);

    if (process.memory_start == -1)
    {
        printf("Memory allocation failed for process %d\n", process.process_id);
        return;
    }

    srand(time(NULL));

    time_t start_time = time(NULL);

    while (difftime(time(NULL), start_time) < 20)
    {
        char str[100];
        int length = rand() % 20 + 1;
        for (int i = 0; i < length; i++)
        {
            char ch = 'a' + rand() % 26;
            str[i] = ch;
        }
        str[length] = '\0';

        printf("String: %s\n", str);

        int counts[26] = {0};

        for (int i = 0; str[i] != '\0'; i++)
        {
            char ch = tolower(str[i]);
            if (isalpha(ch))
            {
                counts[ch - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++)
        {
            if (counts[i] > 0)
            {
                printf("%c: %d\n", 'a' + i, counts[i]);
            }
        }
    }

    deallocate_memory(process.memory_start);
}

void process4()
{
    struct PCB process;
    process.process_id = 4;
    process.priority = 4;
    process.state = 1;
    process.process_counter = 0;
    process.memory_size = 25;

    process.memory_start = allocate_memory(process.memory_size);

    if (process.memory_start == -1)
    {
        printf("Memory allocation failed for process %d\n", process.process_id);
        return;
    }

    srand(time(NULL));

    time_t start_time = time(NULL);

    while (difftime(time(NULL), start_time) < 8)
    {
        int num = rand() % 100 + 1;
        printf("Generated number: %d\n", num);
    }

    deallocate_memory(process.memory_start);
}

void process5()
{
    struct PCB process;
    process.process_id = 5;
    process.priority = 5;
    process.state = 1;
    process.process_counter = 0;
    process.memory_size = 35;

    process.memory_start = allocate_memory(process.memory_size);

    if (process.memory_start == -1)
    {
        printf("Memory allocation failed for process %d\n", process.process_id);
        return;
    }

    srand(time(NULL));

    time_t start_time = time(NULL);

    while (difftime(time(NULL), start_time) < 15)
    {
        char ch = 'A' + rand() % 26;
        printf("Generated character: %c\n", ch);
    }

    deallocate_memory(process.memory_start);
}

void context_switch(struct PCB processes[], int num_processes)
{
    int current_process = num_processes - 1;
    int previous_process;

    while (1)
    {
        previous_process = current_process;
        current_process = (current_process + 1) % num_processes;

        printf("Context Switching... Switched to process %d\n", processes[current_process].process_id);
        sleep(1.5);

        switch (processes[current_process].process_id)
        {
        case 1:
            process1();
            break;
        case 2:
            process2();
            break;
        case 3:
            process3();
            break;
        case 4:
            process4();
            break;
        case 5:
            process5();
            break;
        default:
            break;
        }

        processes[previous_process].state = 0;
        processes[current_process].state = 1;
    }
}

int main()
{
    struct PCB processes[] = {
        {1, 1, 1, 0, 0, 0}, // process_id, priority, state, process_counter, memory_start, memory_size
        {2, 2, 1, 0, 0, 0},
        {3, 3, 1, 0, 0, 0},
        {4, 4, 1, 0, 0, 0},
        {5, 5, 1, 0, 0, 0}};

    initialize_memory();

    context_switch(processes, sizeof(processes) / sizeof(processes[0]));

    return 0;
}
