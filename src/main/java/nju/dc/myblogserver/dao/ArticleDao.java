package nju.dc.myblogserver.dao;

import nju.dc.myblogserver.po.ArticlePO;

import java.sql.Blob;
import java.util.List;

public interface ArticleDao {

    /**
     * 向数据库中插入文章
     * 文章标题、创建日期、文章ID保存在article_Info表中
     * 创建日期、文章内容保存在article表中
     * @param articlePO
     * @return 二者都插入成功返回1，失败返回0
     */
    int addArticle(ArticlePO articlePO);

    /**
     * 从数据库中得到所有文章标题、发表日期
     * 用以在前端目录中展示
     * @return ArticlePO的list
     */
    List<ArticlePO> getArticleInfo();

    /**
     * 根据文章的发表日期，得到文章的内容
     * @param date 格式：YYYY-mm-dd HH:mm:ss
     * @return ArticlePO
     */
    ArticlePO getArticleContent(String date);

    /**
     * 根据文章创建日期从数据库中删除该文章
     * 注意在两个表（article_Info和article）中都要删除
     * @param date 文章创建日期
     * @return 二者均删除成功返回1，失败返回0
     */
    int deleteArticle(String date);

    /**
     * 修改已发表的文章
     * @param date 旧的发表日期，根据此从表中找到该条记录
     * @param articlePO 修改后的文章，可修改的：标题、内容，发表时间为新的保存时间
     * @return 修改成功返回1，失败返回0
     */
    int updateArticle(String date, ArticlePO articlePO);

}
