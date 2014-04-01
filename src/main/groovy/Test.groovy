/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-22
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */

//println("hello world")

def a = "";
//println(Strings.isNullOrEmpty(a))




















//这里是获取所有分类，并且生产更新产品的命令
gettext().eachLine{
    if(it.trim().startsWith("<option")){
      //  println(it.trim()[(it.trim().indexOf("value=\"")+7)..(it.trim().indexOf("\">")-1)])


        println("${getV(it.trim().toString(), '">', '</').replace("&nbsp;","")}|${getV(it.trim().toString(), 'value="', '">')}|${getV(it.trim().toString(), '">', '</').replace("&nbsp;","")}")
    }


}

def getV(  str,  s,  e){
   return  (str[(str.indexOf(s)+s.length())..(str.indexOf(e)-1)])
}

def gettext () {
    return """
<select name="catalog_id" id="catalog_id">
        <option value="">==选择分类==</option>

        <option value=",61,">创意产品</option>

        <option value=",61,63,">&nbsp;&nbsp;&nbsp;&nbsp;生日礼物</option>

        <option value=",61,62,">&nbsp;&nbsp;&nbsp;&nbsp;创意礼物</option>

        <option value=",57,">限时促销</option>

        <option value=",57,59,">&nbsp;&nbsp;&nbsp;&nbsp;卫衣</option>

        <option value=",57,58,">&nbsp;&nbsp;&nbsp;&nbsp;日用品</option>

        <option value=",54,">母婴用品</option>

        <option value=",54,56,">&nbsp;&nbsp;&nbsp;&nbsp;尿片/洗护/喂哺/推车床</option>

        <option value=",54,55,">&nbsp;&nbsp;&nbsp;&nbsp;背袋/背带</option>

        <option value=",2,">品牌女装</option>

        <option value=",2,64,">&nbsp;&nbsp;&nbsp;&nbsp;秋装</option>

        <option value=",2,60,">&nbsp;&nbsp;&nbsp;&nbsp;打底裤</option>

        <option value=",2,53,">&nbsp;&nbsp;&nbsp;&nbsp;丝袜</option>

        <option value=",2,20,">&nbsp;&nbsp;&nbsp;&nbsp;连衣裙</option>

        <option value=",2,19,">&nbsp;&nbsp;&nbsp;&nbsp;针织衫</option>

        <option value=",2,18,">&nbsp;&nbsp;&nbsp;&nbsp;雪纺衫</option>

        <option value=",2,17,">&nbsp;&nbsp;&nbsp;&nbsp;风衣</option>

        <option value=",2,16,">&nbsp;&nbsp;&nbsp;&nbsp;半身裙</option>

        <option value=",2,15,">&nbsp;&nbsp;&nbsp;&nbsp;牛仔裤</option>

        <option value=",2,14,">&nbsp;&nbsp;&nbsp;&nbsp;衬衫</option>

        <option value=",2,13,">&nbsp;&nbsp;&nbsp;&nbsp;裤子</option>

        <option value=",2,12,">&nbsp;&nbsp;&nbsp;&nbsp;T恤</option>

        <option value=",1,">精致内衣</option>

        <option value=",1,25,">&nbsp;&nbsp;&nbsp;&nbsp;全棉内裤</option>

        <option value=",1,24,">&nbsp;&nbsp;&nbsp;&nbsp;塑身上衣</option>

        <option value=",1,23,">&nbsp;&nbsp;&nbsp;&nbsp;女士内裤</option>

        <option value=",1,22,">&nbsp;&nbsp;&nbsp;&nbsp;男士内裤</option>

        <option value=",1,21,">&nbsp;&nbsp;&nbsp;&nbsp;精品文胸</option>

        <option value=",4,">时尚女鞋</option>

        <option value=",4,39,">&nbsp;&nbsp;&nbsp;&nbsp;短靴</option>

        <option value=",4,38,">&nbsp;&nbsp;&nbsp;&nbsp;高帮鞋</option>

        <option value=",4,37,">&nbsp;&nbsp;&nbsp;&nbsp;凉鞋</option>

        <option value=",4,36,">&nbsp;&nbsp;&nbsp;&nbsp;靴子</option>

        <option value=",4,35,">&nbsp;&nbsp;&nbsp;&nbsp;帆布鞋</option>

        <option value=",4,34,">&nbsp;&nbsp;&nbsp;&nbsp;单鞋</option>

        <option value=",6,">潮流女包</option>

        <option value=",6,45,">&nbsp;&nbsp;&nbsp;&nbsp;女士双肩包</option>

        <option value=",6,44,">&nbsp;&nbsp;&nbsp;&nbsp;女士手拿</option>

        <option value=",6,43,">&nbsp;&nbsp;&nbsp;&nbsp;女士手提</option>

        <option value=",6,42,">&nbsp;&nbsp;&nbsp;&nbsp;女士单肩</option>

        <option value=",8,">美容护肤</option>

        <option value=",8,51,">&nbsp;&nbsp;&nbsp;&nbsp;眉笔/眉粉</option>

        <option value=",8,50,">&nbsp;&nbsp;&nbsp;&nbsp;复方精油</option>

        <option value=",8,49,">&nbsp;&nbsp;&nbsp;&nbsp;单方精油</option>

        <option value=",8,48,">&nbsp;&nbsp;&nbsp;&nbsp;精华液</option>

        <option value=",8,47,">&nbsp;&nbsp;&nbsp;&nbsp;香水</option>

        <option value=",8,46,">&nbsp;&nbsp;&nbsp;&nbsp;面膜</option>

        <option value=",7,">时尚饰品</option>

        <option value=",9,">户外运动</option>

        <option value=",52,">男士用品</option>

        <option value=",52,3,">&nbsp;&nbsp;&nbsp;&nbsp;精品男装</option>

        <option value=",52,3,33,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;皮衣</option>

        <option value=",52,3,32,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风衣</option>

        <option value=",52,3,31,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Polo衫</option>

        <option value=",52,3,30,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牛仔裤</option>

        <option value=",52,3,29,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;夹克</option>

        <option value=",52,3,28,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;休闲裤</option>

        <option value=",52,3,27,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;衬衫</option>

        <option value=",52,3,26,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;T恤</option>

        <option value=",52,3,5,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;流行男鞋</option>

        <option value=",52,3,5,41,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帆布鞋</option>

        <option value=",52,3,5,40,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;低帮鞋</option>

        <option value=",10,">手机数码</option>

        <option value=",65,">电子产品</option>

        <option value=",65,67,">&nbsp;&nbsp;&nbsp;&nbsp;硬盘</option>

        <option value=",65,67,68,">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;混合硬盘</option>

        <option value=",65,66,">&nbsp;&nbsp;&nbsp;&nbsp;硬盘</option>

        <option value=",11,">母婴玩具</option>
                </select>
    """
}

