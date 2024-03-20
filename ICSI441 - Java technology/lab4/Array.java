public class Array {
    private int[] data;
    private int size;
    private int capacity;

    public Array() {
        capacity = 5;
        data = new int[capacity];
        size = 0;
    }

    public void add(int num1, int num2) {
        if (size >= capacity) {
            capacity++;
            int[] newData = new int[capacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }

        if (willOverflow(num1, num2)) {
            throw new ArithmeticException("OVERFLOW");
        }

        data[size] = num1 + num2;
        size++;
    }

    public int at(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс хязгаараас хэтэрсэн");
        }
        return data[index];
    }

    private boolean willOverflow(int a, int b) {
        if ((a > 0 && b > Integer.MAX_VALUE - a) || (a < 0 && b < Integer.MIN_VALUE - a)) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array arr = new Array();

        arr.add(2, 3);
        arr.add(Integer.MAX_VALUE, 1); // Overflow үүсгэнэ
        arr.add(4, 5);
        arr.add(6, 7);
        arr.add(8, 9);
        arr.add(10, 11); // Массивын хэмжээг өөрчлөнө
        arr.add(12, 13);

        System.out.println("Size: " + arr.size());
        System.out.println("Capacity: " + arr.capacity());
        arr.print();

        System.out.println("2 Индекс дэх элемент: " + arr.at(2));
        System.out.println("6 Индекс дэх элемент: " + arr.at(6)); //Exception
    }
}
