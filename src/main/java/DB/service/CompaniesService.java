package DB.service;

import DB.dataRecord.CompaniesRecord;

import java.util.List;

interface CompaniesServiceInterface extends DBService{
    public CompaniesRecord findById(int searchId);
    public List<CompaniesRecord> findByName(String searchName);
    public int insert(CompaniesRecord data);
    public int update(CompaniesRecord data);
    public int bulkInsert(List<CompaniesRecord> data);

}

public class CompaniesService implements CompaniesServiceInterface {
    String tableName;
    public CompaniesService(String tableName) {
        super();
        this.tableName=tableName;
    }

    @Override
    public CompaniesRecord findById(int searchId) {
        return null;
    }

    @Override
    public List<CompaniesRecord> findByName(String searchName) {
        return null;
    }

    @Override
    public int insert(CompaniesRecord data) {
        return 0;
    }

    @Override
    public int update(CompaniesRecord data) {
        return 0;
    }

    @Override
    public int bulkInsert(List<CompaniesRecord> data) {
        return 0;
    }



    @Override
    public int delete(int id) {
        return 0;
    }

}
