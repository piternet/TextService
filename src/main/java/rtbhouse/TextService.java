package rtbhouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextService {

    private List<String> lines;

    public TextService(String path) {
        File file = openFile(path);
        try {
            this.lines = splitFileIntoLines(file);
        } catch (Exception e) {
            System.err.println("File with given path not found!");
            System.exit(0);
        }
    }

    public String getLine(String line) throws LineDoesNotExistException {
        int n;
        try {
            n = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            n = -1;
        }
        if (n < 0 || n >= lines.size())
            throw new LineDoesNotExistException();
        return lines.get(n-1);
    }

    private List<String> splitFileIntoLines(File file) throws FileNotFoundException {
        List<String> lines = new ArrayList<String>();
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }
        return lines;
    }

    private File openFile(String path) {
        return new File(path);
    }
}
