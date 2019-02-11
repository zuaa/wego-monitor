 import groovy.sql.Sql

/**
 * Created by zuaa on 16-7-28.
 */
def sql = Sql.newInstance("jdbc:postgresql://192.168.3.230:5432/pm", "pm","aaaaaaaa", "org.postgresql.Driver");
def src=[]
//sql.eachRow("select * from pm_product_a limit 100 offset 0" , {row->
//     def item ={}
//    item.pn=it.pn;
//    src<<item
//}

sql.rows("select * from pm_product_a_pn limit 10").each {
    check( sql.rows("select * from pm_product_a where pn= ?",[it.pn]));
}




def check(rows){
    def re=[]
    rows.each {
        Map item =[:]
        item.pn=it.pn+""
        item.id=it.id
        item.supplierPn=it.supplier_pn+""
        item.mfs=it.mfs+""
        item.mfsId=it.mfs_id+""
        item.catalog=it.catalog+""
        item.description=it.description+""
        item.param=it.param+""
        re<< item
    }
    def rmap= ProductSimilarity.findSimilarity(re,null)
}
