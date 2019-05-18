package com.zhc.wechat.service;

import com.user.wechat.api.dto.ProductCategoryDTO;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-14
 */
public interface ProductCategoryService {

    List<ProductCategoryDTO> getAllCategoryList();

}
