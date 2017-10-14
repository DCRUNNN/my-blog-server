package nju.dc.myblogserver.dao.impl;

import nju.dc.myblogserver.dao.PhotoDao;
import nju.dc.myblogserver.po.AlbumPO;
import nju.dc.myblogserver.po.PhotoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PhotoDaoImpl implements PhotoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addPhotoIntoAlbum(PhotoPO photoPO) {

        String sql = "insert into photo(photoID,photoName,photoSrc,albumName,uploadDate,photoDescription) values "
                + "("
                + '"' + photoPO.getPhotoID() + '"' + "," + '"' + photoPO.getPhotoName() + '"' + "," + '"' + photoPO.getPhotoSrc() + '"' + ","
                + '"' + photoPO.getAlbumName() + '"' + "," + '"' + photoPO.getUploadDate() + '"' + "," + '"' + photoPO.getPhotoDescription() + '"'
                + ")";

        return jdbcTemplate.update(sql);
    }

    @Override
    public List<PhotoPO> getPhotoByAlbumName(String albumName) {

        String sql = "select * from photo where albumName = " + '"' + albumName + '"';

        List<PhotoPO> photoPOList = jdbcTemplate.query(sql, getPhotoMapper());

        return photoPOList.size() == 0 ? new ArrayList<>() : photoPOList;
    }

    @Override
    public int createAlbum(AlbumPO albumPO) {

        String sql = "insert into album(albumName,albumDescription,createDate) values "
                + "("
                + '"' + albumPO.getAlbumName() + '"' + "," + '"' + albumPO.getAlbumDescription() + '"' + "," + '"' + albumPO.getCreateDate() + '"'
                + ")";

        return jdbcTemplate.update(sql);
    }


    private RowMapper<PhotoPO> getPhotoMapper() {
        return (resultSet, i) -> {
            PhotoPO po = new PhotoPO();
            po.setPhotoID(resultSet.getString("photoID"));
            po.setPhotoName(resultSet.getString("photoName"));
            po.setPhotoSrc(resultSet.getString("photoSrc"));
            po.setAlbumName(resultSet.getString("albumName"));
            String date = resultSet.getString("date");
            po.setUploadDate(date.substring(0, date.length() - 2));
            po.setPhotoDescription(resultSet.getString("photoDescription"));
            return po;
        };
    }

}
