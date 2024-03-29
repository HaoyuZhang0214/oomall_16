package xmu.oomall.aftersale.service;



import org.springframework.stereotype.Service;

import xmu.oomall.aftersale.domain.AfterSale;



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

    public List<AfterSale> findAfterSales(Integer userId);



    public AfterSale findAfterSaleById(Integer id);



    public List<AfterSale> findAfterSalesByUserId(Integer userId);



    public AfterSale adminUpdateAfterSale(AfterSale afterSale);



    public AfterSale updateAfterSale(AfterSale afterSale);



    public AfterSale insertAfterSale(AfterSale afterSale);



    public Integer deleteAfterSale(Integer id);



}