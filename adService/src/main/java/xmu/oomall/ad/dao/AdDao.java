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

    public Integer addAd(Ad ad){
        return adMapper.addAd(ad);
    }

    public Ad getAdById(Integer id){
        return adMapper.getAdById(id);
    }

    public Integer updateAdById(Ad ad){
        return adMapper.updateAdById(ad);
    }

    public int deleteAd(Integer id){
        return adMapper.deleteAd(id);
    }

    public List<Ad> adGetAds(Ad ad){
        return adMapper.adGetAds(ad);
    }
}
