import java.util.Scanner;

public class hw2 {
    public static String FindMostUppercase(String[] arr){
        String result = null;
        int MaxUppercase = 0;

        for(String str : arr){
            int UppercaseCount = str.replaceAll("[^A-Z]","").length();

            if(UppercaseCount > MaxUppercase){
                MaxUppercase = UppercaseCount;
                result = str;
            }
        }
        return result;
    }
    public static void main(String[] args){
        /*Scanner obj = new Scanner(System.in);
        //String input = obj.next();

        String[] arr = input.split(" "); */
        String[] arr = {"tEST2","Lorem Ipsum", "JAVA-ch GOE HICHEEL SHUU", "Baby SHARK DOo DOo DOO"};
        String result = FindMostUppercase(arr);

        System.out.println("Hamgiin olon TOM useg aguulag temdegt mur: " + result);
    }
}
