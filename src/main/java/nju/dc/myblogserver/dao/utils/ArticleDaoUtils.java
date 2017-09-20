package nju.dc.myblogserver.dao.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ArticleDaoUtils {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建文章ID
     * @return
     */
    public String createArticleID() {

        String sql = "select count(*) as total from article_info";
        int total = jdbcTemplate.query(sql, resultSet -> resultSet.next() ? resultSet.getInt("total") : 0);
        return "article-" + formatInteger((total + 1), 4);
    }

    public String setCreateDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 格式化数字
     * @param i
     * @param length
     * @return
     */
    private String formatInteger(int i, int length) {

        return String.format("%0" + length + "d", i);
    }


}
