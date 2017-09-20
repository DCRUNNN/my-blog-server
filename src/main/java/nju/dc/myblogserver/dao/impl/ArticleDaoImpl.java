package nju.dc.myblogserver.dao.impl;

import nju.dc.myblogserver.dao.ArticleDao;
import nju.dc.myblogserver.dao.utils.ArticleDaoUtils;
import nju.dc.myblogserver.po.ArticlePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.serial.SerialBlob;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Arrays;
import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ArticleDaoUtils articleDaoUtils;

    @Override
    public int addArticle(ArticlePO articlePO) {

        String sql = "insert into article_Info(articleID,title,date) values "
                + "("
                + '"' + articlePO.getArticleID() + '"' + "," + '"' + articlePO.getTitle() + '"' + "," + '"' + articlePO.getDate() + '"'
                + ")";

        String sqlContent = "insert into article(date,content) values "
                + "("
                + '"' + articlePO.getDate() + '"' + "," + '"' + articlePO.getContent() + '"'
                + ")";

        String[] sqls = new String[2];
        sqls[0] = sql;
        sqls[1] = sqlContent;

        int[] check = jdbcTemplate.batchUpdate(sqls);

        //如果包括0则这两条插入语句至少有一句不成功，success为false
        boolean success = !Arrays.asList(check).contains(0);
        return success ? 1 : 0;
    }

    @Override
    public List<ArticlePO> getArticleInfo() {

        String sql = "select * from article_Info";
        return jdbcTemplate.query(sql, getArticleInfoMapper());
    }


    @Override
    public ArticlePO getArticleContent(String date) {
        String sql = "Select content from article where date=" + '"' + date + '"';
        ArticlePO po = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            ArticlePO tempPO = new ArticlePO();
            tempPO.setContent(resultSet.getString("content"));
            tempPO.setTitle("");
            return tempPO;
        });
        return po;
    }

//    private String getArticleIDByTitle()

    private RowMapper<ArticlePO> getArticleInfoMapper() {
        return (resultSet, i) -> {
            ArticlePO po = new ArticlePO();
            po.setArticleID(resultSet.getString("articleID"));
            String date = resultSet.getString("date");
            po.setDate(date.substring(0, date.length() - 2));
            po.setTitle(resultSet.getString("title"));
            return po;
        };
    }

}
