/** 
 * Project Name:realTimeLog 
 * File Name:DrpcTest.java 
 * Package Name:storm.test.drpc 
 * Date:2015年10月7日上午10:12:46 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.drpc;  

import org.apache.storm.thrift.TException;
import org.apache.storm.thrift.transport.TTransportException;
import org.apache.storm.generated.DRPCExecutionException;
import org.apache.storm.utils.DRPCClient;


/** 
 * ClassName:DrpcTest <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年10月7日 上午10:12:46 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class DrpcTestDemo {

    public static void main(String[] args) throws TTransportException  {
        DRPCClient client = new DRPCClient(null, "10.6.0.12", 3772);
        String result;
        try {
            result = client.execute("queryLog", "querys");
            System.out.println(result);
        } catch (TException e) {
            e.printStackTrace();
        }
    }
    
}
