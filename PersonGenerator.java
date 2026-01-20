import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath().toString(), "src", "PersonTestData.txt");

        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        String CSVPersonRec = "";
        int yearOfBirth = 0;

        Scanner in = new Scanner(System.in);
        ArrayList<String>csvPersons = new ArrayList<>();

        boolean done = false;

        do{
            ID = SafeInput.getNonZeroLenString(in, "enter ID");
            firstName = SafeInput.getNonZeroLenString(in, "enter first name");
            lastName = SafeInput.getNonZeroLenString(in, "enter last name");
            title = SafeInput.getNonZeroLenString(in, "enter title");
            yearOfBirth = SafeInput.getRangedInt(in, "Enter birth year: ", 1000, 2000);

            CSVPersonRec = ID + "," + firstName + "," + lastName + "," + title + "," + yearOfBirth;

            csvPersons.add(CSVPersonRec);

            done = SafeInput.getYNConfirm(in, "Are you done? (y/n): ");

        }while(!done);

        for(String p:  csvPersons){
            System.out.println(p);
        }
        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(String rec : csvPersons)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}