package xmu.oomall.ad.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.ad.dao.AdDao;
import xmu.oomall.ad.domain.Ad;
import xmu.oomall.ad.util.ResponseUtil;

import java.util.List;


@Service
public class AdService {

    private static final Logger logger = LoggerFactory.getLogger(AdService.class);

    @Autowired
    private AdDao adDao;

    public Object getAds(){
        return ResponseUtil.ok(adDao.getAds());
    }

    public Object addAd(Ad ad){
        if(adDao.addAd(ad)==0) {
            logger.debug("创建广告失败");
            return ResponseUtil.addFail();
        }
        if(adDao.getAdById(ad.getId())!=null) {
            return ResponseUtil.ok(adDao.getAdById(ad.getId()));
        }
        else {
            logger.debug("创建广告失败");
            return ResponseUtil.addFail();
        }
    }

    public Object getAdById(Integer id){
        if(adDao.getAdById(id)==null) {
            logger.debug("获取广告失败");
            return ResponseUtil.getFail();
        }
        return ResponseUtil.ok(adDao.getAdById(id));
    }

    public Object updateAdByd(Ad ad){
        if(adDao.updateAdById(ad)==0) {
            logger.debug("修改广告失败");
            return ResponseUtil.updateFail();
        }
        if(adDao.getAdById(ad.getId())!=null) {
            return ResponseUtil.ok(adDao.getAdById(ad.getId()));
        }
        else {
            logger.debug("修改广告失败");
            return ResponseUtil.updateFail();
        }
    }

    public  Object DeleteAd(Integer id){
        if(adDao.deleteAd(id)==0) {
            logger.debug("删除广告失败");
            return ResponseUtil.deleteFail();
        }
        return ResponseUtil.ok();
    }

    public Object adGetAds(Ad ad){
        if(adDao.adGetAds(ad)==null) {
            logger.debug("获取广告失败");
            return ResponseUtil.getFail();
        }
        return  ResponseUtil.ok(adDao.adGetAds(ad));
    }
}
