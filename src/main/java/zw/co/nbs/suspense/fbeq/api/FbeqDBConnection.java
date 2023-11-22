package zw.co.nbs.suspense.fbeq.api;

import java.sql.ResultSet;

public interface FbeqDBConnection {

    ResultSet executeQuery(String sqlString);

}

