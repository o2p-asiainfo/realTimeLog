#!/bin/bash
topologyName="realTimelogSys"
function stopLog(){
	./storm kill $topologyName
}
stopLog
exit 1
