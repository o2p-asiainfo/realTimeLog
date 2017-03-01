/** 
 * Project Name:stormDemo 
 * File Name:ZkWather.java 
 * Package Name:com.ai.mine.stormDemo 
 * Date:2015年8月19日下午10:49:55 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.sample.demo;  

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/** 
 * ClassName:ZkWather <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年8月19日 下午10:49:55 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class ZkWatherDemo implements Watcher {

    private static final int SESSION_TIMEOUT = 10000; 
    private static final String ZK_HOST_CLUSTER = "hadoop.master:2181,hadoop.slave1:2181,hadoop.slave2:2181"; 
    private static final String ZK_PATH = "/zkleader"; 
    private ZooKeeper zk = null; 
    
    //private static final String ZK_HOST_CLUSTER = "192.168.56.100:2181";
    private CountDownLatch connectedSemaphore = new CountDownLatch(1); 
    
    
    /**
     * 创建zookeeper连接
     * @param connectString zookeeper服务器地址列表
     * @param sessionTimeout session超时时间
     */
   public void createConnection( String connectString, int sessionTimeout )  { 
       this.releaseConnection(); 
       try { 
           zk = new ZooKeeper( connectString, sessionTimeout, this ); 
           System.out.println( " Finished starting ZK:  "   +  zk.getState());
           connectedSemaphore.await(); 
       } catch (IOException e ) { 
           System.out.println( "连接创建失败，发生 IOException" ); 
           e.printStackTrace(); 
       } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
   } 
    
   /** 
    * 关闭ZK连接 
    */ 
   public void releaseConnection() { 
       if ( this.zk != null) { 
           try { 
               this.zk.close(); 
           } catch ( InterruptedException e ) { 
               e.printStackTrace(); 
           } 
       } 
   } 
   
   
   
   /** 
    * 创建节点 
    * @param path 节点path 
    * @param data 初始数据内容 
    * @return 
    */ 
   public boolean createPath(String path, String data ) { 
       try { 
           System.out.println( "节点创建成功, Path: " 
                   + this.zk.create( path, // 节点路径
                                     data.getBytes(), // 节点数据
                                     Ids.OPEN_ACL_UNSAFE, // 是否acl控制
                                     CreateMode.EPHEMERAL // 永久节点还是临时节点
                                   ) 
                   + ", content: " + data ); 
       } catch ( KeeperException e ) { 
           System.out.println( "节点创建失败，发生KeeperException" ); 
           e.printStackTrace(); 
       } catch ( InterruptedException e ) { 
           System.out.println( "节点创建失败，发生 InterruptedException" ); 
           e.printStackTrace(); 
       } 
       return true; 
   } 
   
   /** 
    * 读取指定节点数据内容 
    * @param path 节点path 
    * @return 
    */ 
   public String readData( String path ) { 
       try { 
           System.out.println( "获取数据成功，path：" + path ); 
           return new String( this.zk.getData( path, false, null ) ); 
       } catch ( KeeperException e ) { 
           System.out.println( "读取数据失败，发生KeeperException，path: " + path  ); 
           e.printStackTrace(); 
           return ""; 
       } catch ( InterruptedException e ) { 
           System.out.println( "读取数据失败，发生 InterruptedException，path: " + path  ); 
           e.printStackTrace(); 
           return ""; 
       } 
   } 
   
   /** 
    * 更新指定节点数据内容 
    * @param path 节点path 
    * @param data  数据内容 
    * @return 
    */ 
   public boolean writeData( String path, String data ) { 
       try { 
           System.out.println( "更新数据成功，path：" + path + ", stat: " + 
                                                       this.zk.setData( path, data.getBytes(), -1 ) ); //-1无视节点时间版本
       } catch ( KeeperException e ) { 
           System.out.println( "更新数据失败，发生KeeperException，path: " + path  ); 
           e.printStackTrace(); 
       } catch ( InterruptedException e ) { 
           System.out.println( "更新数据失败，发生 InterruptedException，path: " + path  ); 
           e.printStackTrace(); 
       } 
       return false; 
   } 
   
   /** 
    * 删除指定节点 
    * @param path 节点path 
    */ 
   public void deleteNode( String path ) { 
       try { 
           this.zk.delete( path, -1 ); 
           System.out.println( "删除节点成功，path：" + path ); 
       } catch ( KeeperException e ) { 
           System.out.println( "删除节点失败，发生KeeperException，path: " + path  ); 
           e.printStackTrace(); 
       } catch ( InterruptedException e ) { 
           System.out.println( "删除节点失败，发生 InterruptedException，path: " + path  ); 
           e.printStackTrace(); 
       } 
   } 
    public void process(WatchedEvent event) {
        System.out.println( "收到事件通知：" + event.getState() +"\n"  ); 
        if ( KeeperState.SyncConnected == event.getState() ) { 
            connectedSemaphore.countDown(); 
        } 
    }
    
    public static void main( String[] args ) { 
        
        ZkWatherDemo watchTest = new ZkWatherDemo(); 
        watchTest.createConnection( ZK_HOST_CLUSTER, SESSION_TIMEOUT ); 
        if ( watchTest.createPath( ZK_PATH, "我是节点初始内容" ) ) { 
            System.out.println(); 
            System.out.println( "数据内容: " + watchTest.readData( ZK_PATH ) + "\n" ); 
            watchTest.writeData( ZK_PATH, "更新后的数据" ); 
            System.out.println( "数据内容: " + watchTest.readData( ZK_PATH ) + "\n" ); 
            //watchTest.deleteNode( ZK_PATH ); 
        } 
 
        watchTest.releaseConnection(); 
    } 
    
    

}
