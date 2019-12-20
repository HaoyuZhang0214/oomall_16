package xmu.oomall.footprint.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xmu.oomall.footprint.domain.FootprintItemPo;


import java.util.List;

/**
 * @author Chaney
 */
@Mapper
@Repository
public interface FootPrintMapper {
    List<FootprintItemPo> findFootPrintItemByUserIdAndGoodsId(@Param("userId") Integer userId,
                                                              @Param("goodsId")Integer goodsId,
                                                              @Param("page")Integer page,
                                                              @Param("limit")Integer limit);

    List<FootprintItemPo> findFootPrintItemByUserId(@Param("userId") Integer userId,
                                                    @Param("page")Integer page,
                                                    @Param("limit")Integer limit);

    boolean deleteFootPrintItemByUserIdAndId(@Param("userId") Integer userId,@Param("id") Integer id);

    boolean addFootPrintItem(FootprintItemPo footprintItemPo);

}
