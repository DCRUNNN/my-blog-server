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
import java.util.ArrayList;
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
        //成功的话返回1
        return success ? 1 : 0;
    }

    @Override
    public int deleteArticle(String date) {
        String sql = "delete from article_Info where date=" + '"' + date + '"';
        String sql2 = "delete from article where date=" + '"' + date + '"';

        String[] sqls = new String[2];
        sqls[0] = sql;
        sqls[1] = sql2;

        int[] check = jdbcTemplate.batchUpdate(sqls);

        //如果包括0则这两条插入语句至少有一句不成功，success为false
        boolean success = !Arrays.asList(check).contains(0);
        //成功的话返回1
        return success ? 1 : 0;
    }

    @Override
    public int updateArticle(String date, ArticlePO articlePO) {
        String sql = "update article_Info set title =" + '"' + articlePO.getTitle() + '"' + "," + "date=" + '"' + articlePO.getDate() + '"' + " where date=" + '"' + date + '"';
        String sql2 = "update article set date=" + '"' + articlePO.getDate() + '"' + "," + "content=" + '"' + articlePO.getContent() + '"' + " where date=" + '"' + date + '"';

        String[] sqls = new String[2];
        sqls[0] = sql;
        sqls[1] = sql2;

        int[] check = jdbcTemplate.batchUpdate(sqls);

        //如果包括0则这两条插入语句至少有一句不成功，success为false
        boolean success = !Arrays.asList(check).contains(0);
        //成功的话返回1
        return success ? 1 : 0;
    }

    @Override
    public List<ArticlePO> getArticleInfo() {

        String sql = "select * from article_Info";
        List<ArticlePO> list = jdbcTemplate.query(sql, getArticleInfoMapper());
        return list.size() == 0 ? new ArrayList<>() : list;
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
