package nju.dc.myblogserver.controller;

import nju.dc.myblogserver.dao.ArticleDao;
import nju.dc.myblogserver.dao.utils.ArticleDaoUtils;
import nju.dc.myblogserver.dto.BaseResult;
import nju.dc.myblogserver.po.ArticlePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleDaoUtils articleDaoUtils;

    @PostMapping("saveArticle")
    public BaseResult saveArticle(@RequestBody ArticlePO articlePO) {

        articlePO.setDate(articleDaoUtils.setCreateDate());
        articlePO.setArticleID(articleDaoUtils.createArticleID());

        int success = articleDao.addArticle(articlePO);

        if (success == 1) {
            return new BaseResult(0,"save article successfully!");
        }
        return new BaseResult(-1, "fail to save article!");
    }

    @PutMapping("/updateArticle")
    public BaseResult updateArticle(@RequestBody ArticlePO articlePO) {

        String newDate = articleDaoUtils.setCreateDate();
        articlePO.setDate(newDate);

        int success = articleDao.updateArticle(newDate, articlePO);

        if (success == 1) {
            return new BaseResult(0,"update article successfully!");
        }
        return new BaseResult(-1, "fail to update article!");
    }

    @DeleteMapping("/deteleArticle")
    public BaseResult deleteArticle(@RequestBody String date) {

        System.out.println(date);

        int success = articleDao.deleteArticle(date);

        if (success == 1) {
            return new BaseResult(0,"delete article successfully!");
        }
        return new BaseResult(-1, "fail to delete article!");

    }


    @GetMapping("/articleInfo")
    public BaseResult getArticleInfo() {

        List<ArticlePO> poList = articleDao.getArticleInfo();

        return new BaseResult<>(0, poList);
    }

    @GetMapping("/articleContent")
    public BaseResult getArticleContent(@RequestParam String date) {

        ArticlePO po = articleDao.getArticleContent(date);
        if (po.getContent().equals("") || po.getContent() == null) {
            po.setContent("对不起，好像出现了一些问题！");
        }
        return new BaseResult<>(0, po);
    }


}
