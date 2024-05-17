package DB.dao;

import DB.dataRecord.CompaniesRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
        var sql = "SELECT * FROM "+this.tableName+" WHERE id = ?;";
        CompaniesRecord result=null;
        try (var statement = datas.prepareStatement(sql)) {
            // ?にパラメータを埋め込む
            statement.setInt(1, searchId);
            // SQL実行
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                result=new CompaniesRecord(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }

        } catch (final SQLException e) {
            throw new SQLException(e);
        }
        return result;
    }

    @Override
    public List<CompaniesRecord> findByName(String searchName) throws SQLException {
        var sql = "SELECT * FROM "+this.tableName+" WHERE name LIKE ?;";
        List<CompaniesRecord> result=new ArrayList<CompaniesRecord>();
        try (var statement = datas.prepareStatement(sql)) {
            // ?にパラメータを埋め込む
            statement.setString(1, "%"+searchName+"%");
            // SQL実行
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(new CompaniesRecord(
                        resultSet.getInt("id"),
                        resultSet.getString("name"))
                );
            }

        } catch (final SQLException e) {
            throw new SQLException(e);
        }
        return result;
    }

    @Override
    public int insert(CompaniesRecord data) throws SQLException {
        var sql = "INSERT INTO "+this.tableName+" (name) VALUES (?)";
        var result=0;
        try (var statement = datas.prepareStatement(sql)) {
            // ?にパラメータを埋め込む
            statement.setString(1, data.name());

            // SQL実行
            result = statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return result;
    }

    @Override
    public int update(CompaniesRecord data) throws SQLException {
        var sql = "UPDATE "+this.tableName+" " +
                "SET name=? WHERE id=?";
        var result=0;
        try (var statement = datas.prepareStatement(sql)) {

            // ?にパラメータを埋め込む
            statement.setString(1, data.name());
            statement.setInt(2, data.id());

            // SQL実行
            result = statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return result;
    }

    @Override
    public int delete(int id) throws SQLException {
        var sql = "DELETE FROM "+this.tableName+" WHERE id=?";
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
