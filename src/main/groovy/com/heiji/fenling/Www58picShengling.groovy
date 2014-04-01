package com.heiji.fenling


class Www58picShengling extends Base implements Shengling { 
	@Override
	public boolean isList(String url) {  
		boolean re=false;
		if(url.contains("shiliangtu")){
			re= true;
		} 
		if(url.contains('id-')){
			re= true;
		} 
		return re;  
	} 
	@Override
	public boolean isLink(String url) {
		boolean re=false;
		if(url.contains("http://www.58pic.com/shiliangtu/")){
			re= true;
		} 
		if(url.contains("/0/")){
			re= false;
		}
		return re;
	}  
	
	public int level() {
		return 3;
	}
	static main(args) {
		Www58picShengling _LofterShengling=new Www58picShengling();
		Ling ling =new Ling()
		ling.setLink("http://femalesofdesire.tumblr.com/")
		ling.setLevel(1);
		_LofterShengling.getAllUrl(ling).each{ println it.link +"|"+it.level };
	}

}
