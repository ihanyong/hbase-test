package com.hanyong.hbase.test;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

/**
 * Created by Administrator on 2017/10/12.
 */
public interface Convert <T> {
    T parse(Result result);

    Put put(T obj);
}

