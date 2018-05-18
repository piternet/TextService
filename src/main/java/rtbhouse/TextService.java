package rtbhouse;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class TextService {

    private List<Long> linePositions;
    private RandomAccessFile file;

    public TextService(String path) {
        try {
        this.file = new RandomAccessFile(path, "r");
        this.linePositions = new ArrayList<>();
        determineLinePositions(file);
        } catch (Exception e) {
            System.err.println("File with given path not found!");
            System.exit(1);
        }
    }

    public String getLine(String line) throws LineDoesNotExistException {
        int n;
        try {
            n = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            n = -1;
        }
        if (n <= 0 || n > linePositions.size())
            throw new LineDoesNotExistException();
        try {
            file.seek(linePositions.get(n-1));
            return file.readLine();
        } catch (Exception e) {
            throw new LineDoesNotExistException();
        }
    }

    private void determineLinePositions(RandomAccessFile file) throws IOException {
        long pointer = file.getFilePointer();
        while (pointer < file.length()) {
            this.linePositions.add(pointer);
            file.readLine();
            pointer = file.getFilePointer();
        }
    }
}
