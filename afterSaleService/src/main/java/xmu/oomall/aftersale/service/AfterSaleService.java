package xmu.oomall.aftersale.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 水木子
 */
@Service
public interface AfterSaleService {

    /**
     * 获取所有的afterSale
     * @return
     */
    public List<xmu.oomall.aftersale.domain.AfterSaleService> findAfterSales(Integer userId);

    public xmu.oomall.aftersale.domain.AfterSaleService findAfterSaleById(Integer id);

    public List<xmu.oomall.aftersale.domain.AfterSaleService> findAfterSalesByUserId(Integer userId);

    public xmu.oomall.aftersale.domain.AfterSaleService adminUpdateAfterSale(xmu.oomall.aftersale.domain.AfterSaleService afterSaleService);

    public xmu.oomall.aftersale.domain.AfterSaleService updateAfterSale(xmu.oomall.aftersale.domain.AfterSaleService afterSaleService);

    public xmu.oomall.aftersale.domain.AfterSaleService insertAfterSale(xmu.oomall.aftersale.domain.AfterSaleService afterSaleService);

    public int deleteAfterSale(Integer id);

}
