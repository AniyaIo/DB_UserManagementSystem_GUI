package DB.Dao;

import DB.dataRecord.DefaultRecord;
import DB.dataRecord.UsersRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DBDao {

    int delete(int id) throws SQLException;



}

//public class DBDao  {
//    Connection datas;
//    String tableName;
//
//    public DBDao(Connection datas,String tableName){
//        this.datas=datas;
//        this.tableName=tableName;
//    }
//
//    public DefaultDataRecord findById(int searchId) throws SQLException{
//        var sql = "SELECT * FROM "+this.tableName+" WHERE id = ?;";
//        DefaultDataRecord result=null;
//        try (var statement = datas.prepareStatement(sql)) {
//            // ?にパラメータを埋め込む
//            statement.setInt(1, searchId);
//            // SQL実行
//            var resultSet = statement.executeQuery();
//            while (resultSet.next()){
//                result=new DefaultDataRecord(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("price"));
//            }
//
//        } catch (final SQLException e) {
//            throw new SQLException(e);
//        }
//        return result;
//    }
//    public List<DefaultDataRecord> findByName(String searchName) throws SQLException{
//        var sql = "SELECT * FROM "+this.tableName+" WHERE name LIKE ?;";
//        List<DefaultDataRecord> result=new ArrayList<DefaultDataRecord>();
//        try (var statement = datas.prepareStatement(sql)) {
//            // ?にパラメータを埋め込む
//            statement.setString(1, "%"+searchName+"%");
//            // SQL実行
//            var resultSet = statement.executeQuery();
//            while (resultSet.next()){
//                result.add(new DefaultDataRecord(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("price")));
//            }
//
//        } catch (final SQLException e) {
//            throw new SQLException(e);
//        }
//        return result;
//    }
//    public int insert(DefaultDataRecord data)  throws SQLException{
//        var sql = "INSERT INTO "+this.tableName+" (name,price) VALUES (?,?)";
//        var result=0;
//        try (var statement = datas.prepareStatement(sql)) {
//            // ?にパラメータを埋め込む
//            statement.setString(1, data.name());
//            statement.setInt(2, data.price());
//
//            // SQL実行
//            result = statement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new SQLException(e);
//        }
//        return result;
//    }
//    public int update(DefaultDataRecord data)  throws SQLException{
//        var sql = "UPDATE "+this.tableName+" SET name=?,price=? WHERE id=?";
//        var result=0;
//        try (var statement = datas.prepareStatement(sql)) {
//
//            // ?にパラメータを埋め込む
//            statement.setString(1, data.name());
//            statement.setInt(2, data.price());
//            statement.setInt(3, data.id());
//
//            // SQL実行
//            result = statement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new SQLException(e);
//        }
//        return result;
//
//    }
//    public int delete(int id) throws SQLException{
//        var sql = "DELETE FROM "+this.tableName+" WHERE id=?";
//        var result=0;
//        try (var statement = datas.prepareStatement(sql)) {
//            // ?にパラメータを埋め込む
//            statement.setInt(1, id);
//            // SQL実行
//            result = statement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new SQLException(e);
//        }
//        return result;
//    }
//}
