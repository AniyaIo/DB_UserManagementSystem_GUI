package DB_test.dao;

import DB.DBUtil;
import DB.dao.CompaniesDao;
import DB.dataRecord.CompaniesRecord;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompaniesDaoTest {
    @Test
    public void findByIdTest(){
        CompaniesRecord expected=new CompaniesRecord(1,"Google");
        var dao=new CompaniesDao(DBUtil.getConnection());
        CompaniesRecord result = null;
        try {
            result = dao.findById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }
    @Test
    public void findByIdTest2(){
        CompaniesRecord expected=null;
        var dao=new CompaniesDao(DBUtil.getConnection());
        CompaniesRecord result = null;
        try {
            result = dao.findById(-1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void findByNameTest(){
        var dao=new CompaniesDao(DBUtil.getConnection());

        List<CompaniesRecord> expected=new ArrayList<CompaniesRecord>();
        expected.add(new CompaniesRecord(2,"Apple"));
        List<CompaniesRecord> result = null;
        try {
            result = dao.findByName("pp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void insertTest(){
        var dao=new CompaniesDao(DBUtil.getConnection());

        var expected=1;
        //idは連番で設定されるので何番でも良い
        int result = 0;
        try {
            result = dao.insert(new CompaniesRecord(4,"Facebook"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void insertTest2(){
        var dao=new CompaniesDao(DBUtil.getConnection());

        var expected=1;
        int result = 0;
        try {
            result = dao.insert(new CompaniesRecord(5,"Yahoo"));
            result =dao.insert(new CompaniesRecord(5,"Yahoo"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void updateTest(){
        var dao=new CompaniesDao(DBUtil.getConnection());

        var expected=1;
        int result = 0;
        try {
            result = dao.update(new CompaniesRecord(7,"TOYOTA"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void updateTest2(){
        var dao=new CompaniesDao(DBUtil.getConnection());

        var expected=0;
        int result = 0;
        try {
            result = dao.update(new CompaniesRecord(5555,"Buffalo"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void deleteTest(){
        var dao=new CompaniesDao(DBUtil.getConnection());

        var expected=1;
        int result = 0;
        try {
            result = dao.delete(6);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void deleteTest2(){
        var dao=new CompaniesDao(DBUtil.getConnection());

        var expected=1;
        int result = 0;
        try {
            result = dao.delete(7);
            result = dao.delete(8);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void deleteTest3(){
        var dao=new CompaniesDao(DBUtil.getConnection());

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