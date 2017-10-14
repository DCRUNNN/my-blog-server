package nju.dc.myblogserver.dao;

import nju.dc.myblogserver.po.AlbumPO;
import nju.dc.myblogserver.po.PhotoPO;

import java.util.List;

public interface PhotoDao {

    int addPhotoIntoAlbum(PhotoPO photoPO);

    List<PhotoPO> getPhotoByAlbumName(String albumName);

    int createAlbum(AlbumPO albumPO);

}
