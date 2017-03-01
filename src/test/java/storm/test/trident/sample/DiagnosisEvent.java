/** 
 * Project Name:stormDemo 
 * File Name:DiagnosisEvent.java 
 * Package Name:stormDemo 
 * Date:2015年9月8日上午11:16:01 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.sample;  

import java.io.Serializable;

/** 
 * ClassName:DiagnosisEvent <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月8日 上午11:16:01 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class DiagnosisEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    public double lat;
    public double lng;
    public long time;
    public String diagnosisCode;

    public DiagnosisEvent(double lat, double lng,
                          long time, String diagnosisCode) {
        super();
        this.time = time;
        this.lat = lat;
        this.lng = lng;
        this.diagnosisCode = diagnosisCode;
    }
}
