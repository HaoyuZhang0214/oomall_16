package xmu.oomall.collection.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.collection.domain.CollectItem;
import xmu.oomall.collection.domain.CollectItemPo;
import xmu.oomall.collection.domain.GoodsPo;
import xmu.oomall.collection.mapper.CollectMapper;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhy
 */
@Repository
public class CollectDao {
    @Autowired
    private CollectMapper collectMapper;

    public CollectItemPo addCollect(CollectItemPo collectItem){
         if(collectMapper.addCollect(collectItem)>0) {
             return collectItem;
         }
         else {
             return null;
         }
    }

    public  List<CollectItem> listcollect(Integer userId){
        List<GoodsPo> goods= new ArrayList<GoodsPo>() ;
        List<CollectItem>  collectItems=new ArrayList<CollectItem>();
               for(CollectItemPo collect:collectMapper.listCollectByCodition(userId)){
                   Integer goodsid=collect.getGoodsId();
                   CollectItem a =new CollectItem();
                   a.setGoodsPo(collectMapper.goods(goodsid));
                   a.setGmtCreate(collect.getGmtCreate());
                   a.setGmtModified(collect.getGmtModified());
                   a.setGoodsId(collect.getGoodsId());
                   a.setId(collect.getId());
                   a.setUserId(collect.getUserId());
                   if(a!=null) {
                       collectItems.add(a);
                   }
               }
               return collectItems;
    }

    public Integer deleteCollectById(Integer id){
        return collectMapper.deleteCollectById(id);
    }
   /* public List<CollectItemPo> listCollectByCodition()
    {
        return collectMapper.listCollectByCodition();
    }*/
   /* public Integer addBrand(Brand brand)
    {
        brandMapper.addBrand(brand);
        int id=brand.getId();
        return id;
    }*/

}