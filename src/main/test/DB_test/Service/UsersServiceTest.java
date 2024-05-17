package DB_test.Service;

import DB.dataRecord.UsersRecord;
import DB.service.UsersService;
import org.junit.jupiter.api.Test;

import DB.ProductNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsersServiceTest {
    @Test
    public void findByIdTest(){
        var service=new UsersService();
        var expected= new UsersRecord(1,"田中",2,50);
        var result =service.findById(1);

        assertEquals(expected, result);
    }

    @Test
    public void findByIdTest2(){
        var service=new UsersService();

        //エラーが出れば成功
        ProductNotFoundException expected = assertThrows(ProductNotFoundException.class, () -> service.findById(22222));
        assertThat(expected.getMessage(), is("IDが存在しません"));
    }

    @Test
    public void findByNameTest(){
        var service=new UsersService();
        List<UsersRecord> expected= new ArrayList<UsersRecord>();
        expected.add(new UsersRecord(1,"田中",2,50));
        var result =service.findByName("中");

        assertEquals(expected, result);
    }

    @Test
    public void findByNameTest2(){
        var service=new UsersService();
        List<UsersRecord> expected= new ArrayList<UsersRecord>();
        var result =service.findByName("水筒");

        assertEquals(expected, result);
    }

    @Test
    public void insertTest(){
        var service=new UsersService();
        var expected= 1;
        var result =service.insert(new UsersRecord(1,"レイ",4,41));

        assertEquals(expected, result);
    }
    @Test
    public void insertTest2(){
        var service=new UsersService();
        var expected= 2;
        var result =service.insert(new UsersRecord(1,"ボウ",4,84));
        result +=service.insert(new UsersRecord(1,"ノア",4,95));

        assertEquals(expected, result);
    }

    @Test
    public void updateTest(){
        var service=new UsersService();
        var expected= 1;
        var result =service.update(new UsersRecord(3,"コバヤシ",3,50));

        assertEquals(expected, result);
    }
    @Test
    public void updateTest2(){
        var service=new UsersService();
        var expected= 1;
        var result =service.update(new UsersRecord(3,"シデン",3,20));
        result =service.update(new UsersRecord(4,"アカハナ",5,66));

        assertEquals(expected, result);
    }

    @Test
    public void deleteTest(){
        var service=new UsersService();
        var expected= 1;
        int result=0;
        try {
            result =service.delete(6);
        }catch (SQLException e){
            e.printStackTrace();
        }

        assertEquals(expected, result);
    }

    @Test
    public void bulkInsertTest(){
        var data = new ArrayList<UsersRecord>();
        data.add(new UsersRecord(1,"ミハル",1,56));
        data.add(new UsersRecord(1,"ジーン",2,45));
        data.add(new UsersRecord(1,"ラル",3,36));

        var service=new UsersService();
        var expected= data.size();
        var result =service.bulkInsert(data);

        assertEquals(expected, result);
    }

    @Test
    public void bulkInsertTest2(){
        var data = new ArrayList<UsersRecord>();
        data.add(new UsersRecord(1,"ミハル",1,50));
        data.add(new UsersRecord(1,"255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力255文字を超える入力",2,48));
        data.add(new UsersRecord(1,"ラル",3,10));

        var service=new UsersService();
        var expected= 0;
        var result =service.bulkInsert(data);

        assertEquals(expected, result);
    }


}