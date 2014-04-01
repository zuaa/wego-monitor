package com.heiji.ciku


class TestSougouScel {

	static main(args) {
		String fileName="C:\\Users\\zu\\git\\maid\\src\\com\\heiji\\ciku\\dianzi.scel";
		File file = new File(fileName);
		SougouScelReader reader=new SougouScelReader();
		SougouScelMdel model=reader.read(file);

		model.wordMap.each { println it }
	}
}
