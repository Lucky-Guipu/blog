package com.evan.service.impl;

import com.evan.constant.RedisKeyConstants;
import com.evan.entity.Blog;
import com.evan.mapper.BlogMapper;
import com.evan.model.dto.BlogVisibility;
import com.evan.model.vo.BlogDetail;
import com.evan.model.vo.BlogInfo;
import com.evan.model.vo.NewBlog;
import com.evan.model.vo.PageResult;
import com.evan.model.vo.RandomBlog;
import com.evan.model.vo.SearchBlog;
import com.evan.service.BlogService;
import com.evan.service.RedisService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    private static final Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedisService redisService;

    @PostConstruct
    public void init() {
        log.info("===== BlogServiceImpl loaded =====");
    }

    private static final int PAGE_SIZE = 5;

    @Override
    public PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum) {
        List<BlogInfo> allList = blogMapper.getBlogInfoListByIsPublished();
        // 手动填充 category 和 tags（MyBatis association/collection 映射不可靠，Service 层兜底）
        for (BlogInfo blogInfo : allList) {
            if (blogInfo.getCategory() == null) {
                Long categoryId = blogMapper.getCategoryIdByBlogId(blogInfo.getId());
                if (categoryId != null) {
                    blogInfo.setCategory(blogMapper.getCategoryById(categoryId));
                }
            }
            if (blogInfo.getTags() == null || blogInfo.getTags().isEmpty()) {
                blogInfo.setTags(blogMapper.getTagListByBlogId(blogInfo.getId()));
            }
        }
        int totalPage = (int) Math.ceil((double) allList.size() / PAGE_SIZE);
        int fromIndex = (pageNum - 1) * PAGE_SIZE;
        int toIndex = Math.min(fromIndex + PAGE_SIZE, allList.size());
        List<BlogInfo> pageList = allList.subList(Math.min(fromIndex, allList.size()), toIndex);
        return new PageResult<>(totalPage, pageList);
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName, Integer pageNum) {
        List<BlogInfo> allList = blogMapper.getBlogInfoListByCategoryNameAndIsPublished(categoryName);
        for (BlogInfo blogInfo : allList) {
            if (blogInfo.getCategory() == null) {
                Long categoryId = blogMapper.getCategoryIdByBlogId(blogInfo.getId());
                if (categoryId != null) {
                    blogInfo.setCategory(blogMapper.getCategoryById(categoryId));
                }
            }
            if (blogInfo.getTags() == null || blogInfo.getTags().isEmpty()) {
                blogInfo.setTags(blogMapper.getTagListByBlogId(blogInfo.getId()));
            }
        }
        int totalPage = (int) Math.ceil((double) allList.size() / PAGE_SIZE);
        int fromIndex = (pageNum - 1) * PAGE_SIZE;
        int toIndex = Math.min(fromIndex + PAGE_SIZE, allList.size());
        List<BlogInfo> pageList = allList.subList(Math.min(fromIndex, allList.size()), toIndex);
        return new PageResult<>(totalPage, pageList);
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByTagNameAndIsPublished(String tagName, Integer pageNum) {
        List<BlogInfo> allList = blogMapper.getBlogInfoListByTagNameAndIsPublished(tagName);
        for (BlogInfo blogInfo : allList) {
            if (blogInfo.getCategory() == null) {
                Long categoryId = blogMapper.getCategoryIdByBlogId(blogInfo.getId());
                if (categoryId != null) {
                    blogInfo.setCategory(blogMapper.getCategoryById(categoryId));
                }
            }
            if (blogInfo.getTags() == null || blogInfo.getTags().isEmpty()) {
                blogInfo.setTags(blogMapper.getTagListByBlogId(blogInfo.getId()));
            }
        }
        int totalPage = (int) Math.ceil((double) allList.size() / PAGE_SIZE);
        int fromIndex = (pageNum - 1) * PAGE_SIZE;
        int toIndex = Math.min(fromIndex + PAGE_SIZE, allList.size());
        List<BlogInfo> pageList = allList.subList(Math.min(fromIndex, allList.size()), toIndex);
        return new PageResult<>(totalPage, pageList);
    }

    @Override
    public BlogDetail getBlogByIdAndIsPublished(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        if (blog == null) {
            return null;
        }
        BlogDetail detail = new BlogDetail();
        detail.setId(blog.getId());
        detail.setTitle(blog.getTitle());
        detail.setContent(blog.getContent());
        detail.setCreateTime(blog.getCreateTime());
        detail.setUpdateTime(blog.getUpdateTime());
        detail.setViews(blog.getViews());
        detail.setWords(blog.getWords());
        detail.setReadTime(blog.getReadTime());
        detail.setTop(blog.getTop());
        detail.setPassword(blog.getPassword() != null ? blog.getPassword() : "");
        detail.setAppreciation(blog.getAppreciation());
        detail.setCommentEnabled(blog.getCommentEnabled());
        detail.setTags(blogMapper.getTagListByBlogId(id));
        return detail;
    }

    @Override
    public List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query) {
        return blogMapper.getSearchBlogListByQueryAndIsPublished(query);
    }

    @Override
    public List<NewBlog> getNewBlogListByIsPublished() {
        return blogMapper.getNewBlogListByIsPublished();
    }

    @Override
    public List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend() {
        return blogMapper.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
    }

    @Override
    public Map<String, Object> getArchiveBlogAndCountByIsPublished() {
        List<String> yearMonths = blogMapper.getGroupYearMonthByIsPublished();
        Map<String, List<Blog>> archiveMap = new HashMap<>();
        for (String yearMonth : yearMonths) {
            archiveMap.put(yearMonth, blogMapper.getBlogListByYearMonthAndIsPublished(yearMonth));
        }
        int count = blogMapper.countBlogByIsPublished();
        Map<String, Object> result = new HashMap<>();
        result.put("archiveMap", archiveMap);
        result.put("count", count);
        return result;
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public String getTitleByBlogId(Long id) {
        return blogMapper.getTitleByBlogId(id);
    }

    @Override
    public String getBlogPassword(Long blogId) {
        return blogMapper.getBlogPassword(blogId);
    }

    @Override
    public List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId) {
        return blogMapper.getListByTitleAndCategoryId(title, categoryId);
    }

    @Override
    public List<Blog> getIdAndTitleList() {
        return blogMapper.getIdAndTitleList();
    }

    @Override
    public void deleteBlogById(Long id) {
        blogMapper.deleteBlogTagByBlogId(id);
        blogMapper.deleteBlogById(id);
    }

    @Override
    public void deleteBlogTagByBlogId(Long blogId) {
        blogMapper.deleteBlogTagByBlogId(blogId);
    }

    @Override
    public void saveBlog(com.evan.model.dto.Blog blog) {
        blogMapper.saveBlog(blog);
    }

    @Override
    public void saveBlogTag(Long blogId, Long tagId) {
        blogMapper.saveBlogTag(blogId, tagId);
    }

    @Override
    public void updateBlogRecommendById(Long blogId, Boolean recommend) {
        blogMapper.updateBlogRecommendById(blogId, recommend);
    }

    @Override
    public void updateBlogVisibilityById(Long blogId, BlogVisibility blogVisibility) {
        blogMapper.updateBlogVisibilityById(blogId, blogVisibility);
    }

    @Override
    public void updateBlogTopById(Long blogId, Boolean top) {
        blogMapper.updateBlogTopById(blogId, top);
    }

    @Override
    public void updateViewsToRedis(Long blogId) {
        redisService.incrementByHashKey(RedisKeyConstants.BLOG_VIEWS_MAP, blogId, 1);
    }

    @Override
    public void updateViews(Long blogId, Integer views) {
        blogMapper.updateViews(blogId, views);
    }

    @Override
    public void updateBlog(com.evan.model.dto.Blog blog) {
        blogMapper.updateBlog(blog);
    }

    @Override
    public int countBlogByIsPublished() {
        return blogMapper.countBlogByIsPublished();
    }

    @Override
    public int countBlogByCategoryId(Long categoryId) {
        return blogMapper.countBlogByCategoryId(categoryId);
    }

    @Override
    public int countBlogByTagId(Long tagId) {
        return blogMapper.countBlogByTagId(tagId);
    }

    @Override
    public Boolean getCommentEnabledByBlogId(Long blogId) {
        return blogMapper.getCommentEnabledByBlogId(blogId);
    }

    @Override
    public Boolean getPublishedByBlogId(Long blogId) {
        return blogMapper.getPublishedByBlogId(blogId);
    }
}
