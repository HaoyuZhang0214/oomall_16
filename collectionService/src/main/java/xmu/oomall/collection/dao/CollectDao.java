package xmu.oomall.collection.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.collection.domain.CollectItem;
import xmu.oomall.collection.domain.CollectItemPo;
import xmu.oomall.collection.domain.GoodsPo;
import xmu.oomall.collection.mapper.CollectMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectDao {
    @Autowired
    private CollectMapper collectMapper;

    public CollectItemPo addCollect(CollectItemPo collectItem){
         collectMapper.addCollect(collectItem);
         return collectItem;
    }

    public  List<CollectItem> listcollect(Integer userId){
        List<GoodsPo> goods=null ;
        List<CollectItem>  collectItems=new ArrayList<CollectItem>();
               for(CollectItemPo collect:collectMapper.listCollectByCodition(userId)){
                   Integer goodsid=collect.getGoodsId();
                   CollectItem A=new CollectItem();
                   //goods.add(collectMapper.Goods(goodsid));
                   A.setGoodsPo(collectMapper.Goods(goodsid));
                   A.setGmtCreate(collect.getGmtCreate());
                   A.setGmtModified(collect.getGmtModified());
                   A.setGoodsId(collect.getGoodsId());
                   A.setId(collect.getId());
                   A.setUserId(collect.getUserId());
                   if(A!=null) {
                       collectItems.add(A);
                   }
               }
               return collectItems;
    }

    public Integer deleteCollectById(Integer id){
        collectMapper.deleteCollectById(id);
        return id;
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