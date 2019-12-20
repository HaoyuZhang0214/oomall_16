package xmu.oomall.footprint.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:足迹明细对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class FootprintItem extends FootprintItemPo {

    public FootprintItem(FootprintItemPo footprintItemPo)
    {
        this.setId(footprintItemPo.getId());
        this.setGoodsId(footprintItemPo.getGoodsId());
        this.setBirthTime(footprintItemPo.getBirthTime());
        this.setUserId(footprintItemPo.getUserId());
        this.setGmtCreate(footprintItemPo.getGmtCreate());
    }
    private GoodsPo goodsPo;
}
