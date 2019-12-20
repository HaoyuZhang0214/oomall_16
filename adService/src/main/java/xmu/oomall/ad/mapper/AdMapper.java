package xmu.oomall.ad.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xmu.oomall.ad.domain.Ad;

import java.util.List;


@Mapper
@Repository
public interface AdMapper {

        List<Ad> getAds();

        int addAd(Ad ad);

        Ad getAdById(Integer id);

        int updateAdById(Ad ad);

        int deleteAd(Integer id);

        List<Ad> adGetAds(Ad ad);
}
