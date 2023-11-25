package zw.co.nbs.suspense.fbeq;

import java.sql.ResultSet;

public interface FbeqDBConnection {

    ResultSet executeQuery(String sqlString);

}

