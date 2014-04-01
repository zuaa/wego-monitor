package com.heiji.fenling


class LofterShengling extends Base implements Shengling {
 
	@Override
	public boolean isList(String url) { 
		if(url.contains("lofter.com")&&url.contains("page=")){
			return true;
		}else{
			return false;
		} 
	}

	@Override
	public boolean isLink(String url) {
		if(url.contains(".lofter.com/post")){
			return true;
		}else{

			return false;
		} 
	} 
	static main(args) {
		LofterShengling _LofterShengling=new LofterShengling();
		Ling ling =new Ling()
		ling.setLink("http://moody1988.lofter.com/")
		ling.setLevel(1);
		_LofterShengling.getAllUrl(ling).each{ println it.link +"|"+it.level };
	}

}
