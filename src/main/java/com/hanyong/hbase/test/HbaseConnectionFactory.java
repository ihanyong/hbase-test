package com.hanyong.hbase.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2017/10/12.
 */
public class HbaseConnectionFactory {
    public static Connection instance() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "172.16.1.99");
        Connection connection = ConnectionFactory.createConnection(conf);

        return connection;
    }
}
