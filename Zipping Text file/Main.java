import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
class Main {

    public static void main(String[] args) throws IOException {
        String str = "";
        Scanner s = new Scanner(System.in);
        Huff_tree tree = new Huff_tree();
        System.out.print("Enter File Location");
        String add = s.nextLine();
        Path fileName = Path.of(add+".txt");
         str = Files.readString(fileName);
        tree.buildHuffmanTree(str);
        // System.out.println("File Compressed");

        // C:\\Users\\user\\Desktop\\Project_DSA\\Document
    }
}