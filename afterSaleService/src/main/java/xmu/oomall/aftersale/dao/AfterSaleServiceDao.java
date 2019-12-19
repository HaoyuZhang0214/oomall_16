package xmu.oomall.aftersale.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.aftersale.domain.AfterSaleService;
import xmu.oomall.aftersale.mapper.AfterSaleServiceMapper;

import java.util.List;

/**
 * @author
 */
@Repository
public class AfterSaleServiceDao {

    @Autowired
    private AfterSaleServiceMapper afterSaleServiceMapper;

    public List<AfterSaleService> findAfterSales(Integer userId){
        return afterSaleServiceMapper.findAfterSales(userId);
    }

    public AfterSaleService findAfterSaleById(Integer id){
        return afterSaleServiceMapper.findAfterSaleById(id);
    }

    public List<AfterSaleService> findAfterSalesByUserId(Integer userId){
        return afterSaleServiceMapper.findAfterSalesByUserId(userId);
    }

    public AfterSaleService updateAfterSale(AfterSaleService afterSaleService){
        afterSaleServiceMapper.updateAfterSale(afterSaleService);
        return afterSaleServiceMapper.findAfterSaleById(afterSaleService.getId());
    }

    public AfterSaleService insertAfterSale(AfterSaleService afterSaleService){
        afterSaleServiceMapper.insertAfterSale(afterSaleService);
        return afterSaleServiceMapper.findAfterSaleById(afterSaleService.getId());
    }

    public int deleteAfterSale(Integer id){
        return afterSaleServiceMapper.deleteAfterSale(id);
    }
}
