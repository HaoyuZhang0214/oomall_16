package xmu.oomall.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.ad.dao.AdDao;
import xmu.oomall.ad.domain.Ad;
import xmu.oomall.ad.util.ResponseUtil;

import java.util.ArrayList;
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
        return ResponseUtil.ok(ad);
    }

    public Object getAdById(Integer id){
        if(adDao.getAdById(id)==null) return ResponseUtil.fail(683,"广告 id 不合法");
        return ResponseUtil.ok(adDao.getAdById(id));
    }

    public Object updateAdByd(Ad ad){
        if(adDao.updateAdById(ad)==0) return ResponseUtil.fail(682,"修改广告失败");
        if(adDao.getAdById(ad.getId())!=null) return ResponseUtil.ok(adDao.getAdById(ad.getId()));
        return ResponseUtil.fail(682,"修改广告失败");
    }

    public  Object DeleteAd(Integer id){
        if(adDao.deleteAd(id)==0) return ResponseUtil.fail(682,"修改广告失败");
        return ResponseUtil.ok();
    }


    public Object adGetAds2(Ad ad,Integer page,Integer limit){
        List<Ad> CollectList=new ArrayList<Ad>();
        CollectList =  adDao.adGetAds2(ad);
        int pagecount = CollectList.size() / limit;
        int remain = CollectList.size() % limit;
        if (remain > 0) {
            pagecount++;
        }
        if(page>pagecount) return ResponseUtil.ok();
        List<Ad> subList = null;
        if (remain == 0) {
            subList = CollectList.subList((page - 1) * limit, page * limit);
        } else {
            if (page == pagecount) {
                subList = CollectList.subList((page - 1) * limit, CollectList.size());
            } else {
                subList = CollectList.subList((page - 1) * limit, page * limit);
            }
        }
        return ResponseUtil.ok(subList);
    }

    public Object adGetAds(Ad ad,Integer page,Integer limit){
        List<Ad> CollectList=new ArrayList<Ad>();
       CollectList =  adDao.adGetAds(ad);
        int pagecount = CollectList.size() / limit;
        int remain = CollectList.size() % limit;
        if (remain > 0) {
            pagecount++;
        }
        if(page>pagecount) return ResponseUtil.ok();
        List<Ad> subList = null;
        if (remain == 0) {
            subList = CollectList.subList((page - 1) * limit, page * limit);
        } else {
            if (page == pagecount) {
                subList = CollectList.subList((page - 1) * limit, CollectList.size());
            } else {
                subList = CollectList.subList((page - 1) * limit, page * limit);
            }
        }
        return ResponseUtil.ok(subList);
    }

    public Object adGetAds3(Ad ad,Integer page,Integer limit){
        List<Ad> CollectList=new ArrayList<Ad>();
        CollectList =  adDao.adGetAds3(ad);
        int pagecount = CollectList.size() / limit;
        int remain = CollectList.size() % limit;
        if (remain > 0) {
            pagecount++;
        }
        if(page>pagecount) return ResponseUtil.ok();
        List<Ad> subList = null;
        if (remain == 0) {
            subList = CollectList.subList((page - 1) * limit, page * limit);
        } else {
            if (page == pagecount) {
                subList = CollectList.subList((page - 1) * limit, CollectList.size());
            } else {
                subList = CollectList.subList((page - 1) * limit, page * limit);
            }
        }
        return ResponseUtil.ok(subList);
    }
}
