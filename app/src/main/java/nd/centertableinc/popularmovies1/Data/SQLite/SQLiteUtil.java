package nd.centertableinc.popularmovies1.Data.SQLite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static nd.centertableinc.popularmovies1.Data.Utils.StringUtil.composeStringsWithDelimiter;

public class SQLiteUtil {

    //record map is constructed as <column, value>
    public static String getInsertQuery(String tableName, Map<String, String> record)
    {
        StringBuilder query = new StringBuilder();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        query.append("INSERT INTO");
        query.append(tableName);

        String delimiter = "";
        for(Map.Entry<String, String> rec : record.entrySet()) {
            columns.append(delimiter).append(rec.getKey());
            values.append(delimiter).append(rec.getValue());

            delimiter = ",";
        }

        query.append(" (").append(columns.toString()).append(")");
        query.append(" VALUES(").append(values.toString()).append(")");

        query.append(";");
        return query.toString();
    }

    //record map is constructed as <column, value>
    public static String getDeleteQuery(String tableName, Map<String, String> record)
    {
        StringBuilder query = new StringBuilder();
        StringBuilder criteria = new StringBuilder();

        query.append("DELETE FROM ").append(tableName);
        query.append(" WHERE ");


        String delimiter = "";
        for(Map.Entry<String, String> rec : record.entrySet()) {
            criteria.append(rec.getKey()).append(" = ").append(rec.getValue());
            criteria.append(delimiter);

            delimiter = " AND ";
        }
        query.append(criteria.toString());

        query.append(";");
        return query.toString();
    }

    //record map is constructed as <column, value>
    public static String getSelectQuery(String tableName, List<String> select, Map<String, String> record)
    {
        StringBuilder query = new StringBuilder();
        List<String> criteriaList = new ArrayList<>();
        String selects, criterias;

        query.append("SELECT");

        if( select == null )
        {
            selects = "*";
        }
        else {
            selects = composeStringsWithDelimiter(select, ",");
        }

        query.append(" ").append(selects.toString());

        query.append(" FROM ").append(tableName);
        query.append(" WHERE ");


        for(Map.Entry<String, String> item : record.entrySet()) {
            criteriaList.add(item.getKey() + " = " + item.getValue());
        }

        criterias = composeStringsWithDelimiter(criteriaList, " AND ");
        query.append(criterias);

        query.append(";");
        return query.toString();
    }
}
