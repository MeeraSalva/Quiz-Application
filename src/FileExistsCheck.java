import java.io.File;

public class FileExistsCheck {
    public static void main(String[] args) {
        // Provide the path of the file you want to check
        String filePath = "C:/Quiz/questions.txt";
        
        // Create a File object
        File file = new File(filePath);
        
        // Check if the file exists
        if (file.exists()) {
            System.out.println("File exists: " + file.getPath());
        } else {
            System.out.println("File does not exist.");
        }
    }
}