package DB.dao;

import DB.dataRecord.UsersRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
interface UsersDaoInterface extends DBDao{
    UsersRecord findById(int searchId) throws SQLException;
    List<UsersRecord> findByName(String searchName) throws SQLException;
    int insert(UsersRecord data)  throws SQLException;
    int update(UsersRecord data)  throws SQLException;

}

public class UsersDao  implements DBDao{
    Connection datas;
    String tableName;
    public UsersDao(Connection datas) {
        super();
        this.datas=datas;
        this.tableName="users";
    }

    public UsersRecord findById(int searchId) throws SQLException {
        var sql = "SELECT * FROM "+this.tableName+" WHERE id = ?;";
        UsersRecord result=null;
        try (var statement = datas.prepareStatement(sql)) {
            // ?にパラメータを埋め込む
            statement.setInt(1, searchId);
            // SQL実行
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                result=new UsersRecord(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("company_id"),resultSet.getInt("score"));
            }

        } catch (final SQLException e) {
            throw new SQLException(e);
        }
        return result;
    }

    public List<UsersRecord> findByName(String searchName) throws SQLException {
        var sql = "SELECT * FROM "+this.tableName+" WHERE name LIKE ?;";
        List<UsersRecord> result=new ArrayList<UsersRecord>();
        try (var statement = datas.prepareStatement(sql)) {
            // ?にパラメータを埋め込む
            statement.setString(1, "%"+searchName+"%");
            // SQL実行
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(new UsersRecord(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("company_id"),
                        resultSet.getInt("score"))
                );
            }

        } catch (final SQLException e) {
            throw new SQLException(e);
        }
        return result;
    }

    public int insert(UsersRecord data) throws SQLException {
        var sql = "INSERT INTO "+this.tableName+" (name,company_id,score) VALUES (?,?,?);";
        var result=0;
        try (var statement = datas.prepareStatement(sql)) {
            // ?にパラメータを埋め込む
            statement.setString(1, data.name());
            statement.setInt(2, data.companyId());
            statement.setInt(3, data.score());

            // SQL実行
            result = statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return result;
    }

    public int update(UsersRecord data) throws SQLException {
        var sql = "UPDATE "+this.tableName+" " +
                "SET name=?,company_id=?,score=? WHERE id=?;";
        var result=0;
        try (var statement = datas.prepareStatement(sql)) {

            // ?にパラメータを埋め込む
            statement.setString(1, data.name());
            statement.setInt(2, data.companyId());
            statement.setInt(3, data.score());
            statement.setInt(4, data.id());

            // SQL実行
            result = statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return result;
    }

    @Override
    public int delete(int id) throws SQLException {
        var sql = "DELETE FROM "+this.tableName+" WHERE id=?;";
        var result=0;
        try (var statement = datas.prepareStatement(sql)) {
            // ?にパラメータを埋め込む
            statement.setInt(1, id);
            // SQL実行
            result = statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return result;
    }
}
