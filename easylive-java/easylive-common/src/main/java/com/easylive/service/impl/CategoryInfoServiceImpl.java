package com.easylive.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.exception.BusinessException;
import com.easylive.service.VideoInfoService;
import org.springframework.stereotype.Service;

import com.easylive.entity.enums.PageSize;
import com.easylive.entity.query.CategoryInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.query.SimplePage;
import com.easylive.mappers.CategoryInfoMapper;
import com.easylive.service.CategoryInfoService;
import com.easylive.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("categoryInfoService")
public class CategoryInfoServiceImpl implements CategoryInfoService {

	@Resource
	private CategoryInfoMapper<CategoryInfo, CategoryInfoQuery> categoryInfoMapper;

	@Resource
	private VideoInfoService videoInfoService;

	@Resource
	private RedisComponent redisComponent;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<CategoryInfo> findListByParam(CategoryInfoQuery param) {
		List<CategoryInfo> categoryInfoList= this.categoryInfoMapper.selectList(param);
		if(param.getConvert2Tree() !=null && param.getConvert2Tree()){
			categoryInfoList= convertLine2Tree(categoryInfoList, Constants.ZERO);
		}
		return categoryInfoList;
	}

	private List<CategoryInfo> convertLine2Tree(List<CategoryInfo> dataList,Integer pid) {
		List<CategoryInfo> children = new ArrayList<>();
		for (CategoryInfo categoryInfo : dataList) {
			if(categoryInfo.getCategoryId() != null && categoryInfo.getpCategoryId() != null && categoryInfo.getpCategoryId().equals(pid)){
				categoryInfo.setChildren(convertLine2Tree(dataList,categoryInfo.getCategoryId()));
				children.add(categoryInfo);
			}
		}
		return children;
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(CategoryInfoQuery param) {
		return this.categoryInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<CategoryInfo> findListByPage(CategoryInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<CategoryInfo> list = this.findListByParam(param);
		PaginationResultVO<CategoryInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(CategoryInfo bean) {
		return this.categoryInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<CategoryInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.categoryInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<CategoryInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.categoryInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(CategoryInfo bean, CategoryInfoQuery param) {
		StringTools.checkParam(param);
		return this.categoryInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(CategoryInfoQuery param) {
		StringTools.checkParam(param);
		return this.categoryInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据CategoryId获取对象
	 */
	@Override
	public CategoryInfo getCategoryInfoByCategoryId(Integer categoryId) {
		return this.categoryInfoMapper.selectByCategoryId(categoryId);
	}

	/**
	 * 根据CategoryId修改
	 */
	@Override
	public Integer updateCategoryInfoByCategoryId(CategoryInfo bean, Integer categoryId) {
		return this.categoryInfoMapper.updateByCategoryId(bean, categoryId);
	}

	/**
	 * 根据CategoryId删除
	 */
	@Override
	public Integer deleteCategoryInfoByCategoryId(Integer categoryId) {
		return this.categoryInfoMapper.deleteByCategoryId(categoryId);
	}

	/**
	 * 根据CategoryCode获取对象
	 */
	@Override
	public CategoryInfo getCategoryInfoByCategoryCode(String categoryCode) {
		return this.categoryInfoMapper.selectByCategoryCode(categoryCode);
	}

	/**
	 * 根据CategoryCode修改
	 */
	@Override
	public Integer updateCategoryInfoByCategoryCode(CategoryInfo bean, String categoryCode) {
		return this.categoryInfoMapper.updateByCategoryCode(bean, categoryCode);
	}

	/**
	 * 根据CategoryCode删除
	 */
	@Override
	public Integer deleteCategoryInfoByCategoryCode(String categoryCode) {
		return this.categoryInfoMapper.deleteByCategoryCode(categoryCode);
	}

	@Override
	public void saveCategory(CategoryInfo categoryInfo) {
		CategoryInfo dbBean= this.categoryInfoMapper.selectByCategoryCode(categoryInfo.getCategoryCode());
		if(categoryInfo.getCategoryId() == null && dbBean != null|| categoryInfo.getCategoryId() != null && dbBean !=null
		&& !categoryInfo.getCategoryId().equals(dbBean.getCategoryId())){
			throw new BusinessException("分类编号已存在");
		}
		if(categoryInfo.getCategoryId() == null){
			Integer maxSort =this.categoryInfoMapper.selectMaxSort(categoryInfo.getpCategoryId());
			categoryInfo.setSort(maxSort+1);
			this.categoryInfoMapper.insert(categoryInfo);
		}else{
			this.categoryInfoMapper.updateByCategoryId(categoryInfo, categoryInfo.getCategoryId());
		}
		save2Redis();
	}

	@Override
	public void delCategory(Integer categoryId) {
		//TODO 查询分类下是否有视频
		VideoInfoQuery videoInfoQuery= new VideoInfoQuery();
		videoInfoQuery.setCategoryIdOrPCategoryId(categoryId);
		Integer count=videoInfoService.findCountByParam(videoInfoQuery);
		if(count>0){
			throw new BusinessException("分类下有视频信息，无法删除");
		}
		CategoryInfoQuery categoryInfoQuery= new CategoryInfoQuery();
		categoryInfoQuery.setCategoryIdOrPCategoryId(categoryId);
		categoryInfoMapper.deleteByParam(categoryInfoQuery);
		//刷新缓存
		save2Redis();
	}

	@Override
	public void changeSort(Integer pCategoryId, String categoryIds) {
		String[] categoryIdArray = categoryIds.split(",");
		List<CategoryInfo> categoryInfoList= new ArrayList<>();
		Integer sort=0;
		for(String categoryId:categoryIdArray){
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setCategoryId(Integer.parseInt(categoryId));
			categoryInfo.setpCategoryId(pCategoryId);
			categoryInfo.setSort(++sort);
			categoryInfoList.add(categoryInfo);
		}
		categoryInfoMapper.updateSortBatch(categoryInfoList);
		//刷新缓存
		save2Redis();
	}

	@Override
	public List<CategoryInfo> getAllCategoryList() {
		List<CategoryInfo> categoryInfoList= redisComponent.getCategoryList();
		if(categoryInfoList.isEmpty()){
			save2Redis();
		}
		return redisComponent.getCategoryList();
	}

	private void save2Redis() {
		CategoryInfoQuery query= new CategoryInfoQuery();
		query.setOrderBy("sort asc");
		query.setConvert2Tree(true);
		List<CategoryInfo> categoryInfoList = findListByParam(query);
		redisComponent.saveCategoryList(categoryInfoList);
	}
}