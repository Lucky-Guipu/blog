package com.evan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.evan.entity.Blog;
import com.evan.entity.Category;
import com.evan.entity.Tag;
import com.evan.model.vo.BlogInfo;
import com.evan.model.vo.NewBlog;
import com.evan.model.vo.RandomBlog;
import com.evan.model.vo.SearchBlog;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    List<BlogInfo> getBlogInfoListByIsPublished();

    List<Blog> getListByTitleAndCategoryId(@Param("title") String title, @Param("categoryId") Integer categoryId);

    List<SearchBlog> getSearchBlogListByQueryAndIsPublished(@Param("query") String query);

    List<Blog> getIdAndTitleList();

    List<NewBlog> getNewBlogListByIsPublished();

    List<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(@Param("categoryName") String categoryName);

    List<BlogInfo> getBlogInfoListByTagNameAndIsPublished(@Param("tagName") String tagName);

    List<String> getGroupYearMonthByIsPublished();

    List<Blog> getBlogListByYearMonthAndIsPublished(@Param("yearMonth") String yearMonth);

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();

    int deleteBlogById(Long id);

    int deleteBlogTagByBlogId(Long blogId);

    int saveBlog(com.evan.model.dto.Blog blog);

    int saveBlogTag(@Param("blogId") Long blogId, @Param("tagId") Long tagId);

    int updateBlogRecommendById(@Param("blogId") Long blogId, @Param("recommend") Boolean recommend);

    int updateBlogVisibilityById(@Param("blogId") Long blogId, @Param("blogVisibility") com.evan.model.dto.BlogVisibility blogVisibility);

    int updateBlogTopById(@Param("blogId") Long blogId, @Param("top") Boolean top);

    int updateViews(@Param("blogId") Long blogId, @Param("views") Integer views);

    Blog getBlogById(Long id);

    String getTitleByBlogId(Long id);

    String getBlogPassword(Long blogId);

    int updateBlog(com.evan.model.dto.Blog blog);

    int countBlog();

    int countBlogByIsPublished();

    int countBlogByCategoryId(Long categoryId);

    int countBlogByTagId(Long tagId);

    Boolean getCommentEnabledByBlogId(Long blogId);

    Boolean getPublishedByBlogId(Long blogId);

    Category getCategoryById(Long categoryId);

    Category getCategoryByName(String name);

    List<Tag> getTagListByBlogId(Long blogId);

    Long getCategoryIdByBlogId(Long blogId);
}
