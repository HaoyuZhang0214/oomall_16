package xmu.oomall.footprint.service;


import org.springframework.stereotype.Service;
import xmu.oomall.footprint.domain.FootprintItem;
import xmu.oomall.footprint.domain.FootprintItemPo;

import java.util.List;

/**
 * @author Chaney
 */
@Service
public interface FootPrintService {
    List<FootprintItem> findFootPrintItemByUserIdAndGoodsId(Integer userId, Integer goodsId, Integer page, Integer limit);

    List<FootprintItem> findFootPrintItemByUserId(Integer userId, Integer page, Integer limit);

    boolean deleteFootPrintItemByUserIdAndId(Integer userId,Integer id);

    FootprintItemPo addFootPrintItem(FootprintItemPo footprintItemPo);
}

