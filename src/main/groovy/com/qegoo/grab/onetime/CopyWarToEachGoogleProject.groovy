package com.qegoo.grab.onetime

class CopyWarToEachGoogleProject {

	static main(args) {

		24.times {
			String command=" cp -rf  /home/xuping/workspace_gae/war/* /home/xuping/workspace_gae/heiji${it}/war/"
			Process p="${command}".execute()
			println "${command}  "
			println "${p.text}"
		}
	}
}
