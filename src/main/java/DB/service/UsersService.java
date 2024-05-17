package DB.service;

import DB.DBUtil;
import DB.dao.CompaniesDao;
import DB.dao.UsersDao;
import DB.TangenDBNotFoundException;
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

    public List<UserData> toUserData(List<UsersRecord> recordData){
        List<UserData> userData=new ArrayList<UserData>();
        List

        for(var record:recordData){
            userData.add(new UserData(record.id(),));
        }
    }
}
