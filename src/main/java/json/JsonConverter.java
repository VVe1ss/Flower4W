package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonConverter {
    public static String path = "test.json";
    private static final Gson gson = configureGson();
    private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);

    public static void convertToJson(JsonConverterData jsonConverterData){
        try (FileWriter writer = new FileWriter(path)){
            String json = gson.toJson(jsonConverterData);
            logger.trace(json);
            writer.write(json);
        } catch (IOException e) {
            logger.error("cannot write file\n" + e.getMessage());
        }
    }

    public static JsonConverterData convertToJsonConverterData(){
        StringBuilder jsonString = new StringBuilder();
        File myObj = new File(path);
        if (myObj.exists()){
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    jsonString.append(myReader.nextLine());
                }
            } catch (FileNotFoundException e) {
                logger.error("cannot read file\n" + e.getMessage());
            }
        } else {
            convertToJson(new JsonConverterData(null,null,null));
            convertToJsonConverterData();
        }

        return gson.fromJson(jsonString.toString(), JsonConverterData.class);
    }

    private static Gson configureGson (){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls().setPrettyPrinting();
        return gsonBuilder.create();
    }

    public static String getPath() {
        return path;
    }

    public static boolean setPath(String path) {
        if (isValidName(path)) {
            JsonConverter.path = path;
            return true;
        }
        else {System.out.println("path is not valid");
            logger.info("path is not valid");
            return false;
        }

    }


    public static boolean isValidName(String text)
    {
        Pattern pattern = Pattern.compile(
                "# Match a valid Windows filename (unspecified file system).          \n" +
                        "^                                # Anchor to start of string.        \n" +
                        "(?!                              # Assert filename is not: CON, PRN, \n" +
                        "  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n" +
                        "    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n" +
                        "    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n" +
                        "  )                              # LPT6, LPT7, LPT8, and LPT9...     \n" +
                        "  (?:\\.[^.]*)?                  # followed by optional extension    \n" +
                        "  $                              # and end of string                 \n" +
                        ")                                # End negative lookahead assertion. \n" +
                        "[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n" +
                        "[^<>:\"/\\\\|?*\\x00-\\x1F\\ .]  # Last char is not a space or dot.  \n" +
                        "$                                # Anchor to end of string.            ",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
