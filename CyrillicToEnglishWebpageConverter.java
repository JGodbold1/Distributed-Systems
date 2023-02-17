import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;

public class CyrillicToEnglishWebpageConverter {

    public static String characterTable(char i){
        //create a table with all the string values and corresponding conversions
        HashMap<Character, String> Table = new HashMap<>();

        //UpperCase Letter Table
        Table.put('\u0410', "A");
        Table.put('\u0411', "B");
        Table.put('\u0412', "V");
        Table.put('\u0413', "G");
        Table.put('\u0414', "D");
        Table.put('\u0415', "Ye");
        Table.put('\u0416', "Zh");
        Table.put('\u0417', "Z");
        Table.put('\u0418', "I");
        Table.put('\u0419', "J");
        Table.put('\u041A', "K");
        Table.put('\u041B', "L");
        Table.put('\u041C', "M");
        Table.put('\u041D', "N");
        Table.put('\u041E', "O");
        Table.put('\u041F', "P");
        Table.put('\u0420', "R");
        Table.put('\u0421', "S");
        Table.put('\u0422', "T");
        Table.put('\u0423', "U");
        Table.put('\u0424', "F");
        Table.put('\u0425', "H");
        Table.put('\u0426', "Ts");
        Table.put('\u0427', "Ch");
        Table.put('\u0428', "Sh");
        Table.put('\u0429', "Shch");
        Table.put('\u042A', "");
        Table.put('\u042B', "Y");
        Table.put('\u042C', "");
        Table.put('\u042D', "Ye");
        Table.put('\u042E', "Yu");
        Table.put('\u042F', "Ya");

        //LowerCase Letter Table
        Table.put('\u0430', "a");
        Table.put('\u0431', "b");
        Table.put('\u0432', "v");
        Table.put('\u0433', "g");
        Table.put('\u0434', "d");
        Table.put('\u0435', "ye");
        Table.put('\u0436', "zh");
        Table.put('\u0437', "z");
        Table.put('\u0438', "i");
        Table.put('\u0439', "j");
        Table.put('\u043A', "k");
        Table.put('\u043B', "l");
        Table.put('\u043C', "m");
        Table.put('\u043D', "n");
        Table.put('\u043E', "o");
        Table.put('\u043F', "p");
        Table.put('\u0440', "r");
        Table.put('\u0441', "s");
        Table.put('\u0442', "t");
        Table.put('\u0443', "u");
        Table.put('\u0444', "f");
        Table.put('\u0445', "h");
        Table.put('\u0446', "ts");
        Table.put('\u0447', "ch");
        Table.put('\u0448', "sh");
        Table.put('\u0449', "shch");
        Table.put('\u044A', "");
        Table.put('\u044B', "y");
        Table.put('\u044C', "");
        Table.put('\u044D', "ye");
        Table.put('\u044E', "yu");
        Table.put('\u044F', "ya");

        //conditional if statement to check if the given char "i" is equal to a key in the table, else return it in string form
        if (!Table.containsKey(i)){
            return Character.toString(i);
        }
        else
            return Table.get(i);
    }





    //------------------------------------------------------------------------------------------------------------------------





    public static String conversionToEnglish (String line){
        //creation of a StringBuilder to hold the string of the converted chars
        StringBuilder converted = new StringBuilder();

            //for loop that puts together a string of the characters
            for (int i = 0; i < (line.length()); i++) {
                converted.append(characterTable(line.charAt(i)));
            }

        return (converted.toString());
    }




    //------------------------------------------------------------------------------------------------------------------------




    public static void main(String[] args){

        //returns a system error if there is no given arg
        if (args.length < 1) {
            System.err.println("Usage:\t CyrillicToEnglishWebpageConverter (URL is not given)");
            System.exit(1);
        }

        //creation of url variable to hold the given argument
        URL urlName = null;

        //main loop that does all the conversion work
        try {
            //try to open the url given in args[0]
            //set the name of the url to var urlName
            urlName = new URL(args[0]);

            //print out the information about the given url
            System.out.println("Protocol:\t" + urlName.getProtocol());
            System.out.println("User info:\t" + urlName.getUserInfo());
            System.out.println("Host:\t\t" + urlName.getHost());
            System.out.println("Port:\t\t" + urlName.getPort());
            System.out.println("Path:\t\t" + urlName.getPath());
            System.out.println("Query:\t\t" + urlName.getQuery());

            //open the input stream of the url and set it to the inputSt var
            InputStream inputSt = urlName.openStream();

            //pass the input stream to a temp buffer and read it
            BufferedReader r = new BufferedReader(new InputStreamReader(inputSt, "UTF-8"));

            //new String for holding temp input
            String tempStr;

            //setting of the output file
            PrintWriter outputSt = new PrintWriter("out.html", "UTF-8");

            while ((tempStr = r.readLine()) != null) {
                outputSt.println(conversionToEnglish(tempStr));
            }
            outputSt.close();

        } catch (Exception e) {
            System.err.println("Invalid url " + urlName);
        }
    }
}

//------------------------------------------------------------------------------------------------------------------------