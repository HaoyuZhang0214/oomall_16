package xmu.oomall.ad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.ad.domain.Ad;
import xmu.oomall.ad.mapper.AdMapper;
import xmu.oomall.ad.util.ResponseUtil;

import java.util.List;

@Repository
public class AdDao {
    @Autowired
    private AdMapper adMapper;

    public List<Ad> getAds(){
        return adMapper.getAds();
    }

    public int addAd(Ad ad){
        return adMapper.addAd(ad);
    }

    public Ad getAdById(Integer id){
        return adMapper.getAdById(id);
    }

    public int updateAdById(Ad ad){
        return adMapper.updateAdById(ad);
    }

    public int deleteAd(Integer id){
        return adMapper.deleteAd(id);
    }

    public List<Ad> adGetAds(Ad ad){
        return adMapper.adGetAds(ad);
    }

    public List<Ad> adGetAds2(Ad ad){
        return adMapper.adGetAds2(ad);
    }

    public List<Ad> adGetAds3(Ad ad){
        return adMapper.adGetAds3(ad);
    }
}
