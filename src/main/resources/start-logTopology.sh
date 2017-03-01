#!/bin/bash
zk_name_space="/cfg/DCN/logServer/2"
zk_connect_string="ppd01:2181,ppd02:2181,ppd03:2181"
mainSrc="com.asiainfo.integration.o2p.log.topology.LogTopology"
jarPath="../app-jar/realTimeLog.jar"
function startLog(){
	./storm jar $jarPath $mainSrc $zk_name_space $zk_connect_string
}
startLog
exit 1
