package DB.service;

import DB.DBUtil;
import DB.dao.CompaniesDao;
import DB.dao.UsersDao;
import DB.TangenDBNotFoundException;
import DB.dataRecord.CompaniesRecord;
import DB.dataRecord.UsersRecord;
import com.example.db_usermanagementsystem_gui.UserData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

interface UserServiceInterface extends DBService{
    UsersRecord findById(int searchId);
    List<UsersRecord> findByName(String searchName);
    int insert(UsersRecord data);
    int update(UsersRecord data);
    int bulkInsert(List<UsersRecord> data);

}

public class UsersService implements UserServiceInterface {
    public UsersService() {
        super();

    }

    @Override
    public UsersRecord findById(int searchId) {
        try (var connection= DBUtil.getConnection()) {
            var dao=new UsersDao(connection);
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
    public List<UsersRecord> findByName(String searchName) {
        try (var connection=DBUtil.getConnection()) {
            var dao=new UsersDao(connection);
            return dao.findByName(searchName);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(UsersRecord data) {
        try(var connection=DBUtil.getConnection()) {
            var dao=new UsersDao(connection);
            return dao.insert(data);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(UsersRecord data) {
        try(var connection=DBUtil.getConnection()) {
            var dao=new UsersDao(connection);
            return dao.update(data);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(int id) throws SQLException {
        try(var connection= DBUtil.getConnection()) {
            var dao=new UsersDao(connection);
            return dao.delete(id);
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    public int bulkInsert(List<UsersRecord> data) {
        try(var connection=DBUtil.getConnection()) {
            connection.setAutoCommit(false);
            var dao=new UsersDao(connection);
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

    public List<UsersRecord> getAllData(){
        try (var connection= DBUtil.getConnection()) {
            var dao=new UsersDao(connection);
            return dao.readAll();

        }catch (SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserData> toUserData(List<UsersRecord> usersData,List<CompaniesRecord> companyData){
        var userData=new ArrayList<UserData>(); //JavaFXのコンテンツに表示するのに必要なデータ類

        for(var record:usersData){
//            String companyName=companyData.get(record.companyId()-1).name();
            String companyName=companyData.get(record.companyId()).name();
            userData.add(new UserData(
                    record.id(),
                    companyName,
                    record.name(),
                    record.score()));
        }
        return userData;
    }

    public List<UserData> readAllUserData(){
        try(var connection = DBUtil.getConnection()){
            var usersDao=new UsersDao(connection); //DBからのデータを受け取る為のデータ類
            var companiesDao=new CompaniesDao(connection);

            List<UsersRecord> usersData=usersDao.readAll();
            List<CompaniesRecord> companyData=companiesDao.readAll();
            return toUserData(usersData,companyData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertUserData(UserData data) throws SQLException {
        var companiesDao=new CompaniesDao(DBUtil.getConnection());
        try {
            return insert(new UsersRecord(
                    data.getId().getValue(),
                    data.getName().getValue(),
                    companiesDao.findByName(data.getCompany().getValue()).getFirst().id(),
                    data.getScore().getValue()));
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

}
