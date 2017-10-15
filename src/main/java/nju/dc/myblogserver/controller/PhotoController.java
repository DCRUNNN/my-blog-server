package nju.dc.myblogserver.controller;

import nju.dc.myblogserver.dao.PhotoDao;
import nju.dc.myblogserver.dao.utils.PhotoDaoUtils;
import nju.dc.myblogserver.dto.BaseResult;
import nju.dc.myblogserver.po.AlbumPO;
import nju.dc.myblogserver.po.PhotoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoDao photoDao;

    @Autowired
    private PhotoDaoUtils photoDaoUtils;

    @PostMapping("createAlbum")
    public BaseResult createAlbum(@RequestBody AlbumPO albumPO) {

        albumPO.setCreateDate(photoDaoUtils.setCreateDate());
        int success = photoDao.createAlbum(albumPO);

        if (success == 1) {
            return new BaseResult(0, "create album successfully!");
        }
        return new BaseResult(-1, "fail to create album!");
    }

    @PostMapping("savePhoto")
    public BaseResult savePhotoIntoAlbum(@RequestBody PhotoPO photoPO) {

        photoPO.setUploadDate(photoDaoUtils.setCreateDate());
        photoPO.setPhotoID(photoDaoUtils.createPhtoID());

        int success = photoDao.addPhotoIntoAlbum(photoPO);

        if (success == 1) {
            return new BaseResult(0, "save photo successfully!");
        }
        return new BaseResult(-1, "fail to save photo!");
    }

    @GetMapping("getPhotoByAlbumName")
    public BaseResult getPhotoByAlbumName(@RequestParam String albumName) {

        List<PhotoPO> list = photoDao.getPhotoByAlbumName(albumName);

        return new BaseResult<>(0, list);
    }

    @GetMapping("getAllAlbums")
    public BaseResult getAllAlbums(){

        List<AlbumPO> list = photoDao.getAllAlbums();

        return new BaseResult<>(0, list);
    }
}
