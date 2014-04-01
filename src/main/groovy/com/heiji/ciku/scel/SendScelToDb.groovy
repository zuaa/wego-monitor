package com.heiji.ciku.scel

import com.gmongo.GMongo
import com.heiji.ciku.SougouScelMdel
import com.heiji.ciku.SougouScelReader

class SendScelToDb {
	static def   mongo = new GMongo("192.168.9.123:27017")
	static def    db = mongo.getDB("scel")
	static main(args) {
		new File('C:/Users/zu/git/maid/src/com/heiji/ciku/scel').eachFile{
			if(it.name.endsWith('.scel')){
				//				println it.name
				SougouScelReader reader=new SougouScelReader();
				SougouScelMdel model=reader.read(it);

				//java.util.LinkedHashMap.Entry
				model.wordMap .each {w->
//					println w.getKey()  + w.getValue()
					w.getValue().each{word->
						def a =["key":w.getKey(),word:word]
						db."sougou".insert a
					}
					
				}

				//
			}
		}
	}
}
