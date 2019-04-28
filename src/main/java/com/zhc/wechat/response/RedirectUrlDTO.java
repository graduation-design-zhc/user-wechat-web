package com.zhc.wechat.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhanghuachang
 * @date 2019-04-28
 */
@Data
public class RedirectUrlDTO implements Serializable {

    private static final long serialVersionUID = -5597486052932085383L;
    private String url;

}
