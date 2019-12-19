package xmu.oomall.aftersale.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xmu.oomall.aftersale.domain.AfterSaleService;

import java.util.List;

/**
 * @author
 */
@Mapper
@Repository
public interface AfterSaleServiceMapper {

    /**
     * 管理员查询某一用户所有售后服务列表
     *
     * @return
     */
    public List<AfterSaleService> findAfterSales(Integer userId);

    /**
     * 通过id查询某一个售后服务具体信息
     * @param id
     * @return
     */
    public AfterSaleService findAfterSaleById(Integer id);

    /**
     * 用户查询该用户下所有售后服务列表，需要userId
     * @param userId
     * @return
     */
    public List<AfterSaleService> findAfterSalesByUserId(Integer userId);

    /**
     * 修改售后服务的信息
     * @param afterSaleService
     * @return
     */
    public int updateAfterSale(AfterSaleService afterSaleService);

    /**
     * 申请售后服务,即新建售后服务
     * @param afterSaleService
     * @return
     */
    public int insertAfterSale(AfterSaleService afterSaleService);

    /**
     * 删除某一个售后服务
     * @param id
     * @return
     */
    public int deleteAfterSale(Integer id);
}
