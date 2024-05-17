package DB_test.Service;

import DB.dataRecord.CompaniesRecord;
import DB.service.CompaniesService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import DB.TangenDBNotFoundException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompaniesServiceTest {
    @Test
    public void findByIdTest(){
        var service=new CompaniesService();
        var expected= new CompaniesRecord(2,"Apple");
        var result =service.findById(2);

        assertEquals(expected, result);
    }

    @Test
    public void findByIdTest2(){
        var service=new CompaniesService();

        //エラーが出れば成功
        TangenDBNotFoundException expected = assertThrows(TangenDBNotFoundException.class, () -> service.findById(22222));
        assertThat(expected.getMessage(), is("IDが存在しません"));
    }

    @Test
    public void findByNameTest(){
        var service=new CompaniesService();
        List<CompaniesRecord> expected= new ArrayList<CompaniesRecord>();
        expected.add(new CompaniesRecord(3,"Amazon"));
        var result =service.findByName("ma");

        assertEquals(expected, result);
    }

    @Test
    public void findByNameTest2(){
        var service=new CompaniesService();
        List<CompaniesRecord> expected= new ArrayList<CompaniesRecord>();
        var result =service.findByName("水筒");

        assertEquals(expected, result);
    }

    @Test
    public void insertTest(){
        var service=new CompaniesService();
        var expected= 1;
        var result =service.insert(new CompaniesRecord(1,"Axiz"));

        assertEquals(expected, result);
    }
    @Test
    public void insertTest2(){
        var service=new CompaniesService();
        var expected= 2;
        var result =service.insert(new CompaniesRecord(1,"Tsukumo"));
        result +=service.insert(new CompaniesRecord(1,"ドスパラ"));

        assertEquals(expected, result);
    }

    @Test
    public void updateTest(){
        var service=new CompaniesService();
        var expected= 1;
        var result =service.update(new CompaniesRecord(10,"Dell"));

        assertEquals(expected, result);
    }
    @Test
    public void updateTest2(){
        var service=new CompaniesService();
        var expected= 2;
        var result =service.update(new CompaniesRecord(11,"ASUS"));
        result +=service.update(new CompaniesRecord(9,"MouseComputer"));

        assertEquals(expected, result);
    }

    @Test
    public void deleteTest(){
        var service=new CompaniesService();
        var expected= 1;
        var result =service.delete(6);

        assertEquals(expected, result);
    }

    @Test
    public void bulkInsertTest(){
        var data = new ArrayList<CompaniesRecord>();
        data.add(new CompaniesRecord(1,"Kokuyo"));
        data.add(new CompaniesRecord(1,"HP"));
        data.add(new CompaniesRecord(1,"KonamiSoft"));

        var service=new CompaniesService();
        var expected= data.size();
        var result =service.bulkInsert(data);

        assertEquals(expected, result);
    }

    @Test
    public void bulkInsertTest2(){
        var data = new ArrayList<CompaniesRecord>();
        data.add(new CompaniesRecord(1,"Kokuyo"));
        data.add(new CompaniesRecord(1,"50文字を超える入力50文字を超える入力50文字を超える入力50文字を超える入力50文字を超える入力5"));
        data.add(new CompaniesRecord(1,"HP"));

        var service=new CompaniesService();
        var expected= 0;
        var result =service.bulkInsert(data);

        assertEquals(expected, result);
    }


}