package nju.dc.myblogserver.po;

public class PhotoPO {

    private String photoID;
    private String photoName;
    private String photoSrc;
    private String albumName;
    private String uploadDate;
    private String photoDescription;

    public PhotoPO() {
    }

    public PhotoPO(String photoID, String photoName, String photoSrc, String albumName, String uploadDate, String photoDescription) {
        this.photoID = photoID;
        this.photoName = photoName;
        this.photoSrc = photoSrc;
        this.albumName = albumName;
        this.uploadDate = uploadDate;
        this.photoDescription = photoDescription;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    @Override
    public String toString() {
        return "PhotoPO{" +
                "photoID='" + photoID + '\'' +
                ", photoName='" + photoName + '\'' +
                ", photoSrc='" + photoSrc + '\'' +
                ", albumName='" + albumName + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", photoDescription='" + photoDescription + '\'' +
                '}';
    }
}
