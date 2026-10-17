package parking.refactored;

public class FileLogger implements Logger {
    private final String fileName;

    public FileLogger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void log(String line) {
        // Code to write line to file
        System.out.println("[FileLogger - " + fileName + "]: " + line);
    }
}