package xmu.oomall.collection.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xmu.oomall.collection.domain.CollectItemPo;
import xmu.oomall.collection.domain.GoodsPo;

import java.util.List;


/**
 * @author zhy
 */
@Mapper
@Repository
public interface CollectMapper {


    /**
     * create a brand
     *
     * @param collectItem
     * @return id
     */
    Integer addCollect(CollectItemPo collectItem);

    /**
     *
     * @param id
     * @return
     */
    Integer deleteCollectById(Integer id);

    /**
     *
     * @param userId
     * @return
     */
    List<CollectItemPo> listCollectByCodition(Integer userId);

    /**
     *
     * @param id
     * @return
     */
    GoodsPo goods(Integer id);
}