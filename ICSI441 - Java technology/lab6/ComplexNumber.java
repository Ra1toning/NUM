public class ComplexNumber<T extends Number> {
    private T re;
    private T im;

    public ComplexNumber(T re, T im) {
        this.re = re;
        this.im = im;
    }

    public T getReal() {
        return re;
    }

    public void setReal(T re) {
        this.re = re;
    }

    public T getImaginary() {
        return im;
    }

    public void setImaginary(T im) {
        this.im = im;
    }

    public ComplexNumber<T> add(ComplexNumber<T> number) {
        double newReal = this.re.doubleValue() + number.re.doubleValue();
        double newImaginary = this.im.doubleValue() + number.im.doubleValue();
        return new ComplexNumber<>(this.re instanceof Double ? (T) Double.valueOf(newReal) : (T) Integer.valueOf((int) newReal),
                this.im instanceof Double ? (T) Double.valueOf(newImaginary) : (T) Integer.valueOf((int) newImaginary));
    }

    public ComplexNumber<T> subtract(ComplexNumber<T> number) {
        double newReal = this.re.doubleValue() - number.re.doubleValue();
        double newImaginary = this.im.doubleValue() - number.im.doubleValue();
        return new ComplexNumber<>(this.re instanceof Double ? (T) Double.valueOf(newReal) : (T) Integer.valueOf((int) newReal),
                this.im instanceof Double ? (T) Double.valueOf(newImaginary) : (T) Integer.valueOf((int) newImaginary));
    }

    @Override
    public String toString() {
        return re + " + " + im + "i";
    }

    public static void main(String[] args) {
        ComplexNumber<Integer> complexInt1 = new ComplexNumber<>(1, 2);
        ComplexNumber<Integer> complexInt2 = new ComplexNumber<>(3, 4);

        System.out.println("int төрөлтэй Комплекс тоо 1: " + complexInt1);
        System.out.println("int төрөлтэй Комплекс тоо 2: " + complexInt2);

        ComplexNumber<Integer> sumInt = complexInt1.add(complexInt2);
        ComplexNumber<Integer> diffInt = complexInt1.subtract(complexInt2);

        System.out.println("int төрөлтэй Комплекс тооны нийлбэр: " + sumInt);
        System.out.println("int төрөлтэй Комплекс тооны ялгавар: " + diffInt + "\n");

        ComplexNumber<Double> complexDouble1 = new ComplexNumber<>(1.5, 2.5);
        ComplexNumber<Double> complexDouble2 = new ComplexNumber<>(3.5, 4.5);

        System.out.println("Double төрөлтэй Комплекс тоо 1: " + complexDouble1);
        System.out.println("Double төрөлтэй Комплекс тоо 2: " + complexDouble2);

        ComplexNumber<Double> sumDouble = complexDouble1.add(complexDouble2);
        ComplexNumber<Double> diffDouble = complexDouble1.subtract(complexDouble2);

        System.out.println("Double төрөлтэй Комплекс тооны нийлбэр: " + sumDouble);
        System.out.println("Double төрөлтэй Комплекс тооны ялгавар: " + diffDouble);
    }
}
