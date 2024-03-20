import java.util.Scanner;

public class hw3 {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);

        //textee oruulna
        System.out.print("Textee oruulaarai: ");
        String text = obj.nextLine();
        int maxWidth = 39; // k < 40

        //output array-d emhetgesen textuude hiine
        String[] output = rectangleText(text, maxWidth);

        //hevlene
        for (String line : output) {
            System.out.println(line);
        }

        obj.close();
    }

    public static String[] rectangleText(String text, int maxWidth) {


        String[] words = text.split(" ");//ug ugeer ni salgad words-d hiine
        String[] lines = new String[words.length];
        int lineCount = 0;
        String currentLine = "";
        int currentWidth = 0;

        /*Ene deer ug bolgonin neg murund oruulad urt ni 40 davbal dragin murluuge shiljdeg bolgono.
        Dragin murluge shiljhes umnu ali boloh baga spacer hoyr talaa bulanluga shahna geheer
        neg func zohiochi RectangleLine?? ene func ni tuhain murunduh ug hoorondin zaig ni ynzaldag bn?
         */
        for (String word : words) {
            if (currentWidth + word.length() + currentLine.length() > maxWidth) { //len 40 davbl emhetgeed neg lined chiheed tooluuruuda formatlchina
                lines[lineCount++] = rectangleLine(currentLine, maxWidth);
                currentLine = "";
                currentWidth = 0;
            }
            currentLine += word + " "; //40 davtal ni neg muruu baiguulna
            currentWidth += word.length() + 1; //urt?
        }
        //suulchin mur hevlegdkuenshde
        if (!currentLine.isEmpty()) {
            lines[lineCount++] = rectangleLine(currentLine, maxWidth);
        }

        //method copies a source array from a specific beginning position to the destination array from the mentioned position. gnee
        String[] outputLines = new String[lineCount];
        System.arraycopy(lines, 0, outputLines, 0, lineCount);

        return outputLines;
    }
    /*murniihuu ugnuudig salgad neg arrayd hiiged murunduh ugnuud dotr heden space avhaa olno totSpace?
    * ug hoorondin zaig olno ug hoorondin zai?
    * Yronhido string bolgoj gargana gj uzvel neg huvisagch zarlaad terendee ugee hiigeed araas ni zaigaa nemeh davtalt hiine
    * tgd result aa return hiine
    * */
    public static String rectangleLine(String line, int maxWidth) {
        String[] words = line.trim().split(" ");
        int totalSpaces = maxWidth - line.length() + words.length - 1;
        int spacesBetweenWords = words.length > 1 ? totalSpaces / (words.length - 1) : totalSpaces;
        int extraSpaces = words.length > 1 ? totalSpaces % (words.length - 1) : 0; //zarim space ni haygad bga bolhor haygad bga space uude hadgalah ym hiichi

        String result = "";

        for (int i = 0; i < words.length; i++) {
            result += words[i];
            if (i < words.length - 1) {
                int spacesToAdd = spacesBetweenWords + (i < extraSpaces ? 1 : 0); //iluu space uudee undsen space dre nemchi
                for (int j = 0; j < spacesToAdd; j++) {
                    result += " ";
                }
            }
        }

        return result;
    }
}
