package xmu.oomall.aftersale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.aftersale.dao.AfterSaleServiceDao;
import xmu.oomall.aftersale.domain.AfterSaleService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author
 */
@Service
public class AfterSaleServiceImpl implements xmu.oomall.aftersale.service.AfterSaleService {

    @Autowired
    private AfterSaleServiceDao afterSaleServiceDao;

    @Override
    public List<AfterSaleService> findAfterSales(Integer userId) {
        return afterSaleServiceDao.findAfterSales(userId);
    }

    @Override
    public AfterSaleService findAfterSaleById(Integer id) {
        return afterSaleServiceDao.findAfterSaleById(id);
    }

    @Override
    public List<AfterSaleService> findAfterSalesByUserId(Integer userId) {
        return afterSaleServiceDao.findAfterSalesByUserId(userId);
    }

    /**
     * 管理员修改售后记录，应该只能修改审核状态
     * 首先根据id从数据库中读取售后记录，然后修改对象的相关属性，再修改到数据库中
     * @param afterSaleService
     * @return 返回修改后的对象
     */
    @Override
    public AfterSaleService adminUpdateAfterSale(AfterSaleService afterSaleService) {
        AfterSaleService afterSaleService1 = afterSaleServiceDao.findAfterSaleById(afterSaleService.getId());
        //只有根据id能找到这个售后记录，并且用户没有取消，管理员才审核
        if(afterSaleService1 !=null && afterSaleService1.getBeApplied()){
            int i= afterSaleService.getStatusCode();
            //只有管理员没审核过的售后记录，才进行审核，不能进行二次审核
            if(afterSaleService1.getStatusCode()==0 && (i==1 ||i==2)) {
                //管理员修改审核状态
                afterSaleService1.setStatusCode(afterSaleService.getStatusCode());
                //设置最近修改时间
                afterSaleService1.setGmtModified(LocalDateTime.now());
                //要设置结束时间吗？
                afterSaleService1.setEndTime(LocalDateTime.now());
                //返回修改后的售后记录
                return afterSaleServiceDao.updateAfterSale(afterSaleService1);
            }else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    @Override
    public AfterSaleService updateAfterSale(AfterSaleService afterSaleService) {
        //首先根据id获取数据库中的售后记录
        AfterSaleService afterSaleService1 = afterSaleServiceDao.findAfterSaleById(afterSaleService.getId());
        //比较afterSale1 中的userId 和 afterSale 中的userId是否相同
        //不同表示A用户修改了B用户的售后记录 ，此时修改 afterSale 的userId，然后返回afterSale
        //数据库中有相应的记录
        if (afterSaleService1 != null) {
            //管理员还没有审核并且该售后没有被删除\被取消
            if(afterSaleService1.getStatusCode()==0 && !afterSaleService1.getBeDeleted() && afterSaleService1.getBeApplied()) {
                //用户可以修改number，applyReason,type,beApplied，goodsType
                //设置最近修改时间
                afterSaleService1.setGmtModified(LocalDateTime.now());
                //用户可以取消售后
                if(afterSaleService.getBeApplied()!=null && !afterSaleService.getBeApplied()) {
                    afterSaleService1.setBeApplied(afterSaleService.getBeApplied());
                }
                //用户可以增加申请理由
                if(afterSaleService.getApplyReason()!=null){
                    afterSaleService1.setApplyReason(afterSaleService1.getApplyReason()+' '+ afterSaleService.getApplyReason());
                }
                //用户可以更改售后类型
                if(afterSaleService.getType()!=null && (afterSaleService.getType()==0 || afterSaleService.getType()==1)){
                    afterSaleService1.setType(afterSaleService.getType());
                }
                //用户可以修改售后数量
                if(afterSaleService.getNumber()!=null && afterSaleService.getNumber()>0){
                    afterSaleService1.setNumber(afterSaleService.getNumber());
                }
                //用户可以修改售后商品类型
                if(afterSaleService.getGoodsType()!=null) {
                    int goodsType = afterSaleService.getGoodsType();
                    if (goodsType >= 1 && goodsType <= 4) {
                        afterSaleService1.setGoodsType(goodsType);
                    }
                }
                //返回更新后的售后记录
                return afterSaleServiceDao.updateAfterSale(afterSaleService1);
            } else if(afterSaleService1.getStatusCode()!=0 && !afterSaleService1.getBeDeleted() && afterSaleService1.getBeApplied()){
                //管理员审核后的售后记录、并且该订单还没有被删除、取消
                //用户只能取消售后
                if(afterSaleService.getBeApplied()==false){
                    afterSaleService1.setBeApplied(afterSaleService.getBeApplied());
                    return afterSaleServiceDao.updateAfterSale(afterSaleService1);
                }else{
                    return null;
                }
            }else{
                //售后记录被删除、被取消，用户都不能再修改 售后记录了
                return null;
            }
        }else{
            //数据库中没有相应的售后记录
            return null;
        }
    }

    @Override
    public AfterSaleService insertAfterSale(AfterSaleService afterSaleService) {
        //首先通过orderItemId,调取order服务的接口，返回该orderItem
        //初步判断是否接受该订单
        LocalDateTime orderCreateTime = LocalDateTime.now();
        LocalDateTime now=LocalDateTime.now();
        Duration duration = Duration.between(orderCreateTime,now);
        //售后申请在订单有效期内（7天）
        if(duration.toNanos()<7*24*60*60*1000){
            //设置管理员审核状态，0代表未审核
            afterSaleService.setStatusCode(0);
            //设置是否申请，true代表是
            afterSaleService.setBeApplied(true);
            //设置售后记录是否删除
            afterSaleService.setBeDeleted(false);
            //设置申请时间
            afterSaleService.setApplyTime(LocalDateTime.now());
            //设置售后记录创建时间，标准组回复与上面的一样
            afterSaleService.setGmtCreate(LocalDateTime.now());
            //设置最近一次修改时间
            afterSaleService.setGmtModified(LocalDateTime.now());
            //设置结束时间应该不在这里，当管理员审核后才设置
            //返回创建后的售后订单
            return afterSaleServiceDao.insertAfterSale(afterSaleService);
        }
        else {       //订单超出7天，无法申请
            return null;
        }
    }

    @Override
    public int deleteAfterSale(Integer id) {
        return afterSaleServiceDao.deleteAfterSale(id);
    }
}
