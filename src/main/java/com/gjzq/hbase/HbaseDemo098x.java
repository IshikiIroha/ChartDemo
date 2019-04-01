//package com.gjzq.hbase;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.*;
//import org.apache.hadoop.hbase.client.*;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class HbaseDemo098x {
//    private static HBaseAdmin admin;//在Hbase访问表需要HbaseAdmin对象
//    private static Configuration conf;
//    static{
//        conf = HBaseConfiguration.create();//使用HbaseConfiguration实例化一个Hbase及Hadoop管理配置对象
//        try{
//            conf.set("hbase.zookeeper.quorum", "hbase-host");
//            conf.set("hbase.zookeeper.property.clientPort", "2181");
//            admin = new HBaseAdmin(conf);//在Hbase访问表需要HbaseAdmin对象
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    /**
//     * @Author:CJ
//     * @Description:判断Hbase表是否存在
//     * @throws
//     */
//    public static boolean tableExists(String tableName) throws Exception {
//        return admin.tableExists(tableName);
//    }
//    /**
//     * @Author:CJ
//     * @Description:创建Hbase表
//     * @throws
//     */
//    public static void creteTable(String tableName,String... columnFamily) throws Exception {
//        if(tableExists(tableName)){
//            System.out.println("createTable: "+tableName+" exists");
//        }else{
//            //创建表属性描述对象
//            HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));
//            //添加多个列族
//            for(String cf:columnFamily){
//                descriptor.addFamily(new HColumnDescriptor(cf));
//            }
//            //根据表的配置，创建表
//            admin.createTable(descriptor);
//            System.out.println("createTable: "+tableName+" succeeded");
//        }
//    }
//    /**
//     * @Author:CJ
//     * @Description:删除Hbase表
//     * @throws
//     */
//    public static void dropTable(String tableName) throws Exception{
//        if(tableExists(tableName)){
//            admin.disableTable(tableName);
//            admin.deleteTable(tableName);
//            System.out.println("dropTable: succeeded");
//        }else{
//            System.out.println("dropTable: failed");
//        }
//    }
//    /**
//     * @Author:CJ
//     * @Description:向Hbase表插入数据
//     * @throws
//     */
//    public static void addRowData(String tableName,String rowKey,String columnFamily,String column,String value) throws Exception{
//        //创建HTable表对象
//        HTable hTable = new HTable(conf,tableName);
//        //Put代表client要保存的数据
//        Put put = new Put(Bytes.toBytes(rowKey));
//        put.add(Bytes.toBytes(columnFamily),Bytes.toBytes(column),Bytes.toBytes(value));
//        hTable.put(put);
//        hTable.close();
//        System.out.println("addRowData: succeeded");
//    }
//    /**
//     * @Author:CJ
//     * @Description:删除多行数据
//     * @throws
//     */
//    public static void deleteMultiRow(String tableName,String... rows) throws Exception{
//        HTable hTable = new HTable(conf,tableName);
//        List<Delete> deleteList = new ArrayList<Delete>();
//        for(String row:rows){
//            Delete delete = new Delete(Bytes.toBytes(row));
//            deleteList.add(delete);
//        }
//        hTable.delete(deleteList);
//        hTable.close();
//    }
//    /**
//     * @Author:CJ
//     * @Description:遍历表
//     * @throws
//     */
//    public static void getAllRows(String tableName) throws Exception {
//        HTable hTable = new HTable(conf,tableName);
//        //得到扫描region的对象
//        Scan scan = new Scan();
//        //添加限定列族
//        //scan.addFamily(Bytes.toBytes("columnFamily"));
//        //用HTable取得扫描实现类ResultScanner结果集合对象
//        ResultScanner resultScanner = hTable.getScanner(scan);
//        for(Result result:resultScanner){
//            Cell[] cells = result.rawCells();//取得所有单元格数据
//            for(Cell cell:cells){
//                //输出rowKey
//                System.out.println(Bytes.toString(CellUtil.cloneRow(cell)));
//                //输出columnFamily
//                System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
//                //输出column
//                System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell)));
//                //输出value
//                System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
//            }
//        }
//    }
//}
