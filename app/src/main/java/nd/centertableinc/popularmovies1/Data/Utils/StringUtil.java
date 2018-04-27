package nd.centertableinc.popularmovies1.Data.Utils;

import java.util.List;

public class StringUtil {
    public static String composeStringsWithDelimiter(List<String> strings, String delimiter)
    {
        StringBuilder result = new StringBuilder();

        String tempDelimiter = "";
        for(String item : strings) {
            result.append(tempDelimiter).append(item);
            tempDelimiter = delimiter;
        }

        return result.toString();
    }
}
