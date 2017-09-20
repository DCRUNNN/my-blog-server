package nju.dc.myblogserver.controller;

import nju.dc.myblogserver.dao.ArticleDao;
import nju.dc.myblogserver.dto.BaseResult;
import nju.dc.myblogserver.po.ArticlePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @GetMapping("/articleInfo")
    public BaseResult getArticleInfo() {

        List<ArticlePO> poList = articleDao.getArticleInfo();

        return new BaseResult<>(0, poList);
    }

    @GetMapping("/articleContent")
    public BaseResult getArticleContent(@RequestParam String date) {

        ArticlePO po = articleDao.getArticleContent(date);
        return new BaseResult<>(0, po);
    }
}
