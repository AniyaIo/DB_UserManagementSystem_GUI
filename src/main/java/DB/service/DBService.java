package DB.service;

import java.sql.SQLException;

public interface DBService {
    int delete(int id) throws SQLException;
}


//public class DBService {
//    String tableName;
//    public DBService(String tableName){
//        this.tableName=tableName;
//    }
//    public DefaultDataRecord findById(int searchId){
//        try (var connection= DBUtil.getConnection()) {
//            var dao=new DBDao(connection,tableName);
//            if(dao.findById(searchId)==null){
//                throw new ProductNotFoundException("IDが存在しません");
//            }
//            return dao.findById(searchId);
//
//        }catch (SQLException e ) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public List<DefaultDataRecord> findByName(String searchName){
//        try (var connection=DBUtil.getConnection()) {
//            var dao=new DBDao(connection,tableName);
//            return dao.findByName(searchName);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public int insert(DefaultDataRecord data){
//        try(var connection=DBUtil.getConnection()) {
//            var dao=new DBDao(connection,tableName);
//            return dao.insert(data);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return -1;
//    }
//    public int update(DefaultDataRecord data){
//        try(var connection=DBUtil.getConnection()) {
//            var dao=new DBDao(connection,tableName);
//            return dao.update(data);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    public int delete(int id){
//        try(var connection=DBUtil.getConnection()) {
//            var dao=new DBDao(connection,tableName);
//            return dao.delete(id);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    public int bulkInsert(List<DefaultDataRecord> data){
//        try(var connection=DBUtil.getConnection()) {
//            connection.setAutoCommit(false);
//            var dao=new DBDao(connection,tableName);
//            for (var i:data){
//                dao.insert(i);
//            }
//            connection.commit();
//            return data.size();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return 0;
//    }
//}
