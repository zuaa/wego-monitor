package com.heiji.fenling


class TumblrShengling extends Base implements Shengling {

	 

	@Override
	public boolean isList(String url) {  
		boolean re=false;
		if(url.contains("tumblr")){
			re= true;
		} 
		if(url.contains('?')){
			re= false;
		}
		
		if(url.contains('tagged')){
			re= false;
		}
		
		return re;  
	} 
	@Override
	public boolean isLink(String url) {
		boolean re=false;
		if(url.contains("tumblr")&&url.contains("media")){
			re= true;
		}else{ 
			re= false;
		} 
		return re;
	}  
	static main(args) {
		TumblrShengling _LofterShengling=new TumblrShengling();
		Ling ling =new Ling()
		ling.setLink("http://femalesofdesire.tumblr.com/")
		ling.setLevel(1);
		_LofterShengling.getAllUrl(ling).each{ println it.link +"|"+it.level };
	}

}
