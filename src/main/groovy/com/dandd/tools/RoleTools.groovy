package com.dandd.tools

/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-22
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 */
class RoleTools {

    static main(agrs){
        println("1")
    }

/**
 * 创建角色/初始化角色
 * @param name
 * @param property
 * @return
 */
    static createRole(def name ,def property){
        def role=[:];
        if(name==null){
            role["name"]=RandomListValue(Name.firstName())+RandomListValue(Name.secondName())
        }else{
            role["name"]=name
        }

        if(property!=null){
            role["property"]=property
        }else{
            role["property"]=createRoleProperty()
        }

        role["alignments"]=RandomListValue(DragonConstant.alignments)
        role["language"]=RandomListValue(DragonConstant.language)
        role["profession"]=RandomListValue(DragonConstant.profession)
        role["race"]=RandomListValue(DragonConstant.race)

        role["姓名"]  = role["name"]
        role["等级"]  = 1
        role["属性值"]= role["property"]
        role["阵营"] =  role["alignments"]
        role["语言"]=  role["language"]
        role["职业"]=   role["profession"]
        role["种族"]= role["race"]
       // role["生命值"]= role["race"]
        role["属性修正值"]=property.collect{
            it=["${it}":modifiedValue(role["属性值"][it])]
        }
        return role;
    }
    /**
     * 在list随机一个值出来
     * @param list
     * @return
     */
    static RandomListValue(def list){
        Random r1=new Random();
        return  list[r1.nextInt(list.size())]
    }

    /**
     * 计算属性修正值
     * @param v
     * @return
     */
    static modifiedValue(int v){
        int n=v/2;
        return n-5;
    }


    /**
     * 创建角色的属性值
     * @return
     */
    static createRoleProperty()
    {
        def a=[:]
        DragonConstant.property.each {
            a[it]= initproperty()
        }
        return a;
    }
    /**
     * 产生一个属性值
     * @return
     */
    static initproperty(){
        //4 次 1D6  三个最大值取和
        Random r1=new Random();
        int temp= r1.nextInt(6)+1;
        // println(temp+"----")
        int re =0;
        3.times {
            int a=r1.nextInt(6)+1
            //    println(a+"----")
            if(a>=temp){
                re=re+a;
            }else{
                re=re+temp;
                temp=a;
            }
        }

        return re;
    }


}
