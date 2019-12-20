package xmu.oomall.ad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.ad.domain.Ad;
import xmu.oomall.ad.service.AdService;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/adService")
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping("/ads")
    public Object getAds(){
        return adService.getAds();
    }

    @GetMapping("/admin/ads")
    public Object adGetAds(@RequestParam String name,@RequestParam String adContent,@RequestParam(defaultValue = "1") Integer page,
                    @RequestParam(defaultValue = "10") Integer limit){
            Ad ad=new Ad();
            ad.setName(name);
            ad.setContent(adContent);
            return adService.adGetAds(ad);
    }

    @PostMapping("/ads")
    public Object addAd(@RequestBody Ad ad){
        return adService.addAd(ad);
    }

    @GetMapping("/ads/{id}")
    public Object getAdById(@PathVariable Integer id){
            return adService.getAdById(id);
    }

    @PutMapping("/ads/{id}")
    public Object updateAdById(@PathVariable Integer id,@RequestBody Ad ad){
        LocalDateTime time=LocalDateTime.now();
        ad.setGmtModified(time);
        ad.setId(id);
        return adService.updateAdByd(ad);
    }

   @DeleteMapping("/ads/{id}")
    public Object deleteAdById(@PathVariable Integer id){
        return adService.DeleteAd(id);
    }
}
