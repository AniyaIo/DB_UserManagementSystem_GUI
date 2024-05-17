package DB_test.dao;

import DB.DBUtil;
import DB.dao.UsersDao;
import DB.dao.UsersDao;
import DB.dataRecord.UsersRecord;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsersDaoTest {
    @Test
    public void findByIdTest(){
        UsersRecord expected=new UsersRecord(1,"田中",2,50);
        var dao=new UsersDao(DBUtil.getConnection());
        UsersRecord result = null;
        try {
            result = dao.findById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }
    @Test
    public void findByIdTest2(){
        UsersRecord expected=null;
        var dao=new UsersDao(DBUtil.getConnection());
        UsersRecord result = null;
        try {
            result = dao.findById(-1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void findByNameTest(){
        var dao=new UsersDao(DBUtil.getConnection());

        List<UsersRecord> expected=new ArrayList<UsersRecord>();
        expected.add(new UsersRecord(2,"山田",4,86));
        List<UsersRecord> result = null;
        try {
            result = dao.findByName("山");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void insertTest(){
        var dao=new UsersDao(DBUtil.getConnection());

        var expected=1;
        //idは連番で設定されるので何番でも良い
        int result = 0;
        try {
            result = dao.insert(new UsersRecord(1,"佐藤",3,65));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void insertTest2(){
        var dao=new UsersDao(DBUtil.getConnection());

        var expected=1;
        int result = 0;
        try {
            result = dao.insert(new UsersRecord(1,"後藤",1,30));
            result =dao.insert(new UsersRecord(1,"喜多",1,100));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void updateTest(){
        var dao=new UsersDao(DBUtil.getConnection());

        var expected=1;
        int result = 0;
        try {
            result = dao.update(new UsersRecord(2,"アズナブル",2,99));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void updateTest2(){
        var dao=new UsersDao(DBUtil.getConnection());

        var expected=0;
        int result = 0;
        try {
            result = dao.update(new UsersRecord(5555,"アズナブル",2,99));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void deleteTest(){
        var dao=new UsersDao(DBUtil.getConnection());

        var expected=1;
        int result = 0;
        try {
            result = dao.delete(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void deleteTest2(){
        var dao=new UsersDao(DBUtil.getConnection());

        var expected=0;
        int result = 0;
        try {
            result = dao.delete(55555);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }
}