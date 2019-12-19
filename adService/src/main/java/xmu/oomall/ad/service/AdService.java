package xmu.oomall.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.ad.dao.AdDao;
import xmu.oomall.ad.domain.Ad;
import xmu.oomall.ad.util.ResponseUtil;

import java.util.List;

@Service
public class AdService {
    @Autowired
    private AdDao adDao;

    public Object getAds(){
        return ResponseUtil.ok(adDao.getAds());
    }

    public Object addAd(Ad ad){
        if(adDao.addAd(ad)==0) return ResponseUtil.fail(681,"创建广告失败");
        if(adDao.getAdById(ad.getId())!=null)
        return ResponseUtil.ok(adDao.getAdById(ad.getId()));
        return ResponseUtil.fail(681,"创建广告失败");
    }

    public Object getAdById(Integer id){
        if(adDao.getAdById(id)==null) return ResponseUtil.fail(680,"获取广告失败");
        return ResponseUtil.ok(adDao.getAdById(id));
    }

    public Object updateAdByd(Ad ad){
        if(adDao.updateAdById(ad)==0) return ResponseUtil.fail(682,"修改广告失败");
        if(adDao.getAdById(ad.getId())!=null) return ResponseUtil.ok(adDao.getAdById(ad.getId()));
        return ResponseUtil.fail(682,"修改广告失败");
    }

    public  Object DeleteAd(Integer id){
        if(adDao.deleteAd(id)==0) return ResponseUtil.fail(683,"删除广告失败");
        return ResponseUtil.ok();
    }

    public Object adGetAds(Ad ad){
        if(adDao.adGetAds(ad)==null) return ResponseUtil.ok();
        return  ResponseUtil.ok(adDao.adGetAds(ad));
    }
}
