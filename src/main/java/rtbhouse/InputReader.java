package rtbhouse;

import java.util.Scanner;

public class InputReader {

    public static String getPath() {
        System.out.print("Enter path to the text file: ");
        System.out.flush();
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

}
