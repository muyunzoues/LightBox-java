package com.easylive.admin.controller;

import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.query.CategoryInfoQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.CategoryInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends ABaseController {
    @Resource
    private CategoryInfoService categoryInfoService;
    @RequestMapping("/loadCategory")
    public ResponseVO loadCategory(CategoryInfoQuery query) {
        query.setOrderBy("sort asc");
        query.setConvert2Tree(true);
        List<CategoryInfo> categoryInfoList= categoryInfoService.findListByParam(query);
       return getSuccessResponseVO(categoryInfoList);
    }

    @RequestMapping("/saveCategory")
    public ResponseVO saveCategory(@NotNull Integer pCategoryId,
                                   Integer categoryId,
                                   @NotEmpty String categoryCode,
                                   @NotEmpty String categoryName,
                                   String icon,
                                   String background) {
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setCategoryCode(categoryCode);
        categoryInfo.setCategoryName(categoryName);
        categoryInfo.setpCategoryId(pCategoryId);
        categoryInfo.setIcon(icon);
        categoryInfo.setBackground(background);
        categoryInfo.setCategoryId(categoryId);
        categoryInfoService.saveCategory(categoryInfo);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/delCategory")
    public ResponseVO delCategory(@NotNull Integer categoryId) {
        categoryInfoService.delCategory(categoryId);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/changeSort")
    public ResponseVO changeSort(@NotNull Integer pCategoryId,String categoryIds) {
        categoryInfoService.changeSort(pCategoryId,categoryIds);
        return getSuccessResponseVO(null);
    }
}
