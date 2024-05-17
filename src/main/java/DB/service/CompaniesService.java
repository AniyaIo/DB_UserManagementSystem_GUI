package DB.service;

import DB.DBUtil;
import DB.dao.CompaniesDao;
import DB.TangenDBNotFoundException;
import DB.dataRecord.CompaniesRecord;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

interface CompaniesServiceInterface extends DBService{
    CompaniesRecord findById(int searchId);
    List<CompaniesRecord> findByName(String searchName);
    int insert(CompaniesRecord data);
    int update(CompaniesRecord data);
    int bulkInsert(List<CompaniesRecord> data);

}

public class CompaniesService implements CompaniesServiceInterface {
    public CompaniesService() {
        super();
    }

    @Override
    public CompaniesRecord findById(int searchId) {
        try (var connection= DBUtil.getConnection()) {
            var dao=new CompaniesDao(connection);
            if(dao.findById(searchId)==null){
                throw new TangenDBNotFoundException("IDが存在しません");
            }
            return dao.findById(searchId);

        }catch (SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CompaniesRecord> findByName(String searchName) {
        try (var connection= DBUtil.getConnection()) {
            var dao=new CompaniesDao(connection);
            return dao.findByName(searchName);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(CompaniesRecord data) {
        try(var connection=DBUtil.getConnection()) {
            var dao=new CompaniesDao(connection);
            return dao.insert(data);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(CompaniesRecord data) {
        try(var connection=DBUtil.getConnection()) {
            var dao=new CompaniesDao(connection);
            return dao.update(data);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(int id) {
        try(var connection= DBUtil.getConnection()) {
            var dao=new CompaniesDao(connection);
            return dao.delete(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int bulkInsert(List<CompaniesRecord> data) {
        try(var connection=DBUtil.getConnection()) {
            connection.setAutoCommit(false);
            var dao=new CompaniesDao(connection);
            for (var i:data){
                dao.insert(i);
            }
            connection.commit();
            return data.size();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> getAllName(){
        try (var connection= DBUtil.getConnection()) {
            var dao=new CompaniesDao(connection);
            var allData=dao.readAll();

            List<String>allName=new ArrayList<>();
            for (var data:allData){
                allName.add(data.name());
            }
            return allName;

        }catch (SQLException e ) {
            e.printStackTrace();
        }
        return null;

    }
}
