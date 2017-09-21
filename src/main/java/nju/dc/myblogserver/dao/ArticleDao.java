package nju.dc.myblogserver.dao;

import nju.dc.myblogserver.po.ArticlePO;

import java.sql.Blob;
import java.util.List;

public interface ArticleDao {

    int addArticle(ArticlePO articlePO);

    List<ArticlePO> getArticleInfo();

    ArticlePO getArticleContent(String date);

    int deleteArticle(String date);

    int updateArticle(String date, ArticlePO articlePO);


}
