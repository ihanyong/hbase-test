package com.hanyong.hbase.test.dao;

import com.hanyong.hbase.test.Convert;
import com.hanyong.hbase.test.entity.Person;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by Administrator on 2017/10/12.
 */
public class PersonDao {
    private static final byte[] t = Bytes.toBytes("person");
    private static final byte[] f = Bytes.toBytes("f");

    private static final Convert<Person> CONVERT = new PersonConvert();

    private Connection conn;

    public PersonDao(Connection conn) {
        this.conn = conn;
    }

    public Person get(String name) {
        try (Table table = conn.getTable(TableName.valueOf(t))) {
            Result result = table.get(new Get(Bytes.toBytes(name)));
            return CONVERT.parse(result);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }



    public void save(String name, int age, String work) {
        save(new Person(name, age, work));
    }

    public void save(Person person) {
        try (Table table = conn.getTable(TableName.valueOf(t))) {

            table.put(CONVERT.put(person));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class PersonConvert implements Convert<Person> {
        @Override
        public Person parse(Result result) {
            String name = Bytes.toString(result.getRow());
            int age = 0;
            String work = null;


            byte[] bytes = result.getValue(f, Bytes.toBytes("age"));
            if (bytes != null) {
                age = Bytes.toInt(bytes);
            }

            bytes = result.getValue(f, Bytes.toBytes("work"));
            if (bytes != null) {
                work = Bytes.toString(bytes);
            }


            return new Person(name, age, work);
        }

        @Override
        public Put put(Person obj) {
            Put put = new Put(Bytes.toBytes(obj.getName()));

            put.addColumn(f, Bytes.toBytes("age"), Bytes.toBytes(obj.getAge()));
//            if (null != obj.getWork()) {
//                put.addColumn(f, Bytes.toBytes("work"), Bytes.toBytes(obj.getWork()));
//            }
            put.addColumn(f, Bytes.toBytes("work"), null!=obj.getWork()?Bytes.toBytes(obj.getWork()):null);

            return put;
        }
    }

}
