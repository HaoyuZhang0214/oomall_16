package xmu.oomall.ad.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xmu.oomall.ad.domain.Ad;

import java.util.List;


/**
 * @author zhy
 */
@Mapper
@Repository
public interface AdMapper {

        /**
         * 获得广告
         * @return List<Ad>
         */
        List<Ad> getAds();

        /**
         * 添加广告
         * @param ad
         * @return int
         */
        int addAd(Ad ad);

        /**
         * 获得广告
         * @param id
         * @return Ad
         */
        Ad getAdById(Integer id);


        /**
         * 修改广告
         * @param ad
         * @return int
         */
        int updateAdById(Ad ad);


        /**
         * 删除广告
         * @param id
         * @return int
         */
        int deleteAd(Integer id);


        /**
         * 获得广告
         * @param ad
         * @return List<Ad>
         */
        List<Ad> adGetAds(Ad ad);
}
