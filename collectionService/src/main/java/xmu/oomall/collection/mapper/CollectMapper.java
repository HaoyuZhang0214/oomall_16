package xmu.oomall.collection.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xmu.oomall.collection.domain.CollectItemPo;
import xmu.oomall.collection.domain.GoodsPo;

import java.util.List;


@Mapper
@Repository
public interface CollectMapper {


    /**
     * create a brand
     *
     * @param
     * @return id
     */
    void addCollect(CollectItemPo collectItem);

    Integer deleteCollectById(Integer id);

    List<CollectItemPo> listCollectByCodition(Integer userId);

    GoodsPo Goods(Integer id);
}