package com.hanyong.hbase.test;

import com.hanyong.hbase.test.dao.PersonDao;
import com.hanyong.hbase.test.entity.Person;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */
public class HbaseFirstTest {

    public static void main(String[] args) {
//        System.out.println(Bytes.toBytes(1000000000).length);
        System.out.println(Bytes.toBytes(1000000000000000000L).length);
        System.out.println(Bytes.toBytes("1000000000000000000").length);



//        testPersonDao();

    }

    public static void testPersonDao() {
        try (Connection conn = HbaseConnectionFactory.instance()) {
            PersonDao dao = new PersonDao(conn);

            long time = System.currentTimeMillis();
            dao.save("hanyong3", 30, "no work");
            long time2 = System.currentTimeMillis();
            Person person = dao.get("hanyong3");
            long time3 = System.currentTimeMillis();
            System.out.println(person);

            System.out.println("************");
            System.out.println(String.format("************save cost %s millis, and get cost %s millis.", time2 - time, time3 - time2));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }






















    public static void testMain(String[] args) {
        try (Connection connection = HbaseConnectionFactory.instance();
             Table table = connection.getTable(TableName.valueOf("test"))
        ) {
            long testGetBegin = System.currentTimeMillis();
            testGet(table);
            long testGetEnd = System.currentTimeMillis();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void testGet(Table table_test) throws IOException {
        Result result = table_test.get(new Get("1212".getBytes()));
        System.out.println(String.format("the result is: %s", result));

        List<Cell> cellList = result.listCells();

        System.out.println(cellList);


        if (null != cellList) {
            for (Cell cell : cellList) {
                System.out.println(String.format("**************************** %s**********************", CellUtil.getCellKeyAsString(cell)));
                System.out.println(String.format("row: %s", Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength())));
                System.out.println(String.format("family: %s", Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength())));
                System.out.println(String.format("qualifier: %s", Bytes.toString(CellUtil.cloneQualifier(cell))));
                System.out.println(String.format("tags: %s", Bytes.toString(cell.getTagsArray(), cell.getTagsOffset(), cell.getTagsLength())));
                System.out.println(String.format("timestamp: %s", cell.getTimestamp()));
                System.out.println(String.format("value: %s", Bytes.toString(CellUtil.cloneValue(cell))));

            }
        }


    }


    private static void printIfExistsTable(Admin admin, TableName tableName) throws IOException {

        System.out.println(String.format("exists table test? %s", admin.tableExists(tableName)));
    }


}
