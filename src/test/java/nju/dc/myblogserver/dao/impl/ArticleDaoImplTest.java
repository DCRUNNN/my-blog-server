package nju.dc.myblogserver.dao.impl;

import nju.dc.myblogserver.dao.ArticleDao;
import nju.dc.myblogserver.dao.utils.ArticleDaoUtils;
import nju.dc.myblogserver.po.ArticlePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleDaoImplTest {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleDaoUtils articleDaoUtils;

    @Test
    public void addArticle() throws Exception {
        ArticlePO po = new ArticlePO();
        po.setTitle("我的博客开放啦2！");
        po.setContent("<p>这是测试一下能不能插入<strong>相同的内容</strong>啊！</p>");
        po.setDate(articleDaoUtils.setCreateDate());
        po.setArticleID(articleDaoUtils.createArticleID());

        System.out.println(articleDao.addArticle(po));
    }

    @Test
    public void getArticleInfo() throws Exception {
        List<ArticlePO> list = articleDao.getArticleInfo();
        for (ArticlePO po : list) {
            System.out.println(po.toString());
        }
    }

    @Test
    public void getArticleContent() throws Exception {
        String date = "2017-09-20 19:42:18";
        ArticlePO po = articleDao.getArticleContent(date);
        System.out.println(po.toString());
    }

}