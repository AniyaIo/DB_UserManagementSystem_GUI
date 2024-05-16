package DB.Dao;

import DB.dataRecord.CompaniesRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

interface CompaniesDaoInterface extends DBDao{
    CompaniesRecord findById(int searchId) throws SQLException;
    List<CompaniesRecord> findByName(String searchName) throws SQLException;
    int insert(CompaniesRecord data)  throws SQLException;
    int update(CompaniesRecord data)  throws SQLException;

}

public class CompaniesDao implements CompaniesDaoInterface{
    Connection datas;
    String tableName;
    public CompaniesDao(Connection datas) {
        super();
        this.datas=datas;
        this.tableName="companies";
    }

    @Override
    public CompaniesRecord findById(int searchId) throws SQLException {
        return null;
    }

    @Override
    public List<CompaniesRecord> findByName(String searchName) throws SQLException {
        return null;
    }

    @Override
    public int insert(CompaniesRecord data) throws SQLException {
        return 0;
    }

    @Override
    public int update(CompaniesRecord data) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
