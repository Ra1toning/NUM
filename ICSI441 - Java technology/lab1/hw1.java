import java.util.Scanner;

public class hw1 {
    public static void main(String[] args){

        Scanner obj = new Scanner(System.in);

        System.out.println("Temdegt muruu oruul:");

        String input = obj.next().toLowerCase();
        String container = input;

        while(!input.equals("stop")){
            input = obj.next().toLowerCase();

            if(!container.contains(input)){
                container += " " + input;
            }
        }
        String[] temp = container.split(" ");
        int len = temp.length;
        System.out.println("Ylgaatai " + len + "-n ug baina");
    }
}
