package xmu.oomall.ad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.ad.domain.Ad;
import xmu.oomall.ad.service.AdService;
import xmu.oomall.ad.util.ResponseUtil;

import java.time.LocalDateTime;

@RestController
public class AdController {
    @Autowired
    private AdService adService;

    @GetMapping("/ads")
    Object getAds(){
        return adService.getAds();
    }

    @GetMapping("/admin/ads")
    Object adGetAds(@RequestParam String name,@RequestParam String adContent,@RequestParam(defaultValue = "1") Integer page,
                    @RequestParam(defaultValue = "10") Integer limit) {
        if(name==""&&adContent=="") return ResponseUtil.ok();
        Ad ad = new Ad();
        ad.setName(name);
        ad.setContent(adContent);
        if(name!=""&&adContent!="") return adService.adGetAds(ad,page,limit);
        if(name!=""&&adContent=="") return adService.adGetAds2(ad,page,limit);
        return adService.adGetAds3(ad,page,limit);
    }

    @PostMapping("/ads")
    Object addAd(@RequestBody Ad ad){
        LocalDateTime time=LocalDateTime.now();
        ad.setGmtModified(time);
        ad.setGmtCreate(time);
        return adService.addAd(ad);
    }

    @GetMapping("/ads/{id}")
    Object getAdById(@PathVariable Integer id){
            return adService.getAdById(id);
    }

    @PutMapping("/ads/{id}")
    Object updateAdById(@PathVariable Integer id,@RequestBody Ad ad){
        LocalDateTime time=LocalDateTime.now();
        ad.setGmtModified(time);
        ad.setId(id);
        return adService.updateAdByd(ad);
    }

   @DeleteMapping("/ads/{id}")
    Object deleteAdById(@PathVariable Integer id){
        return adService.DeleteAd(id);
    }
}
