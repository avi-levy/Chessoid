package tekdays

class LoggingFilters {

    def filters = {
        logEvent(controller:'*', action:'*') {  // log some stuff that happens for all actions for all controllers
            before = {
				if (request) {
					 writeToLog("Server processing request: ${request}\nParams: ${params}") 
				}
			}
			after = {
				if (response) {
					writeToLog("Server sending response ${response}\nParams: ${params}")
				}
			}
        }
    }
		
	void writeToLog(stuffToLog) {
		println "========================== START ========================="
		println stuffToLog
		println "========================== STOP =========================\n"
	}
}
