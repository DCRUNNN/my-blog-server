package nju.dc.myblogserver.po;

import java.util.List;

public class AlbumPO {

    private String albumName;
    private String createDate;
    private List<PhotoPO> photoPOList;
    private String albumDescription;
    private String coverSrc;

    public AlbumPO() {
    }

    public AlbumPO(String albumName, String createDate, List<PhotoPO> photoPOList, String albumDescription, String coverSrc) {
        this.albumName = albumName;
        this.createDate = createDate;
        this.photoPOList = photoPOList;
        this.albumDescription = albumDescription;
        this.coverSrc = coverSrc;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<PhotoPO> getPhotoPOList() {
        return photoPOList;
    }

    public void setPhotoPOList(List<PhotoPO> photoPOList) {
        this.photoPOList = photoPOList;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public String getCoverSrc() {
        return coverSrc;
    }

    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc;
    }

    @Override
    public String toString() {
        return "AlbumPO{" +
                "albumName='" + albumName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", photoPOList=" + photoPOList +
                ", albumDescription='" + albumDescription + '\'' +
                ", coverSrc='" + coverSrc + '\'' +
                '}';
    }
}
