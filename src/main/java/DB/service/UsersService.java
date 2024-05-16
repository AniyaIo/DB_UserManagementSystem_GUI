package DB.service;

import DB.DBUtil;
import DB.Dao.UsersDao;
import DB.dataRecord.UsersRecord;

import java.sql.SQLException;
import java.util.List;

interface UserServiceInterface extends DBService{
    public UsersRecord findById(int searchId);
    public List<UsersRecord> findByName(String searchName);
    public int insert(UsersRecord data);
    public int update(UsersRecord data);
    public int bulkInsert(List<UsersRecord> data);

}

public class UsersService implements UserServiceInterface {
    String tableName;
    public UsersService(String tableName) {
        super();
        this.tableName=tableName;

    }

    @Override
    public UsersRecord findById(int searchId) {
        return null;
    }

    @Override
    public List<UsersRecord> findByName(String searchName) {
        return null;
    }

    @Override
    public int insert(UsersRecord data) {
        return 0;
    }

    @Override
    public int update(UsersRecord data) {
        return 0;
    }

    public int bulkInsert(List<UsersRecord> data) {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        try(var connection= DBUtil.getConnection()) {
            var dao=new UsersDao(connection);
            return dao.delete(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
