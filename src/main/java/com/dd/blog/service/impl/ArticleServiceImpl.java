package com.dd.blog.service.impl;

import com.dd.blog.dao.BlogMapper;
import com.dd.blog.dao.CommentMapper;
import com.dd.blog.pojo.Blog;
import com.dd.blog.pojo.BlogExample;
import com.dd.blog.pojo.Comment;
import com.dd.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    CommentMapper commentMapper;

    public List getComments(Integer blogid){
        List<Comment> comments = new ArrayList<Comment>();
        comments = commentMapper.selectBybIdWithUser(blogid);
        return comments;
    }

    @Override
    public int updateArtl(Blog blog) {
        int cont = blogMapper.updateByPrimaryKeySelective(blog);
        return cont;
    }
}
