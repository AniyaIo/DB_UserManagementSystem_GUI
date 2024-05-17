package DB_test;

import DB.DBUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBUtilTest {
    @Test
    public void getConnectionTest(){
        var expected = true;
        var resultUtil= DBUtil.getConnection();
        //Connection型であるべきだが、PgConnectionにキャストされている
        //var result = ((Object) resultUtil).getClass().getSimpleName().equals("PgConnection");

        //instanceofは継承元も同一と見なす
        var result2 =resultUtil instanceof Connection;
//        System.out.println(((Object)resultUtil).getClass().getSimpleName());

        assertEquals(expected, result2);
    }
}