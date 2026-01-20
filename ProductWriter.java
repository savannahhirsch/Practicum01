import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath().toString(), "ProductTestData.txt");

        String ID = "";
        String name = "";
        String description = "";
        double cost = 0;
        String CSVProductRec = "";


        Scanner in = new Scanner(System.in);
        ArrayList<String> csvProducts = new ArrayList<>();

        boolean done = false;

        do {
            ID = SafeInput.getNonZeroLenString(in, "enter ID");
            name = SafeInput.getNonZeroLenString(in, "enter name");
            description = SafeInput.getNonZeroLenString(in, "enter description");
            cost = SafeInput.getRangedDouble(in, "Enter the cost: ", 0, 9999);

            CSVProductRec = ID + "," + name + "," + description + "," + cost;

            csvProducts.add(CSVProductRec);

            done = SafeInput.getYNConfirm(in, "Are you done? (y/n): ");

        } while (!done);

        for (String p : csvProducts) {
            System.out.println(p);
        }


        try {

            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : csvProducts) {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
