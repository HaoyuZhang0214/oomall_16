package xmu.oomall.footprint.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.footprint.domain.FootprintItemPo;
import xmu.oomall.footprint.mapper.FootPrintMapper;

import java.util.List;

@Repository
public class FootPrintDao {
    @Autowired
    private FootPrintMapper footPrintMapper;

    //AtomicInteger id=new AtomicInteger(1);

    public List<FootprintItemPo> findFootPrintItemByUserIdAndGoodsId(Integer userId, Integer goodsId, Integer page, Integer limit)
    {
        return footPrintMapper.findFootPrintItemByUserIdAndGoodsId(userId,goodsId,page,limit);
    }

    public List<FootprintItemPo> findFootPrintItemByUserId(Integer userId, Integer page, Integer limit)
    {
        return footPrintMapper.findFootPrintItemByUserId(userId,page,limit);
    }

    public boolean deleteFootPrintItemByUserIdAndId(Integer userId,Integer id)
    {
        return footPrintMapper.deleteFootPrintItemByUserIdAndId(userId, id);
    }

    public  FootprintItemPo addFootPrintItem(FootprintItemPo footprintItemPo)
    {
        boolean ok=footPrintMapper.addFootPrintItem(footprintItemPo);

        //数据库操作失败则将id设为0
        if(ok==false) {
            footprintItemPo.setId(0);
        }

        //id.incrementAndGet();
        //footprintItemPo.setId(id.get());

        Integer id=footprintItemPo.getId();
        System.out.println(id);
        return footprintItemPo;//footPrintMapper.addFootPrintItem(footprintItemPo);
    }


}

