package com.zhc.wechat.service;

import com.user.wechat.api.dto.ProductDTO;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-14
 */
public interface ProductService {

    List<ProductDTO> getListByCategoryType(Integer categoryType);

    ProductDTO findByProductId(String productId);

}
