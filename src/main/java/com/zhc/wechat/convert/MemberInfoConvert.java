package com.zhc.wechat.convert;

import com.user.wechat.api.dto.MemberDTO;
import com.zhc.wechat.dal.response.MemberInfoDTO;
import org.springframework.beans.BeanUtils;


/**
 * @author zhanghuachang
 * @date 2019-04-29
 */
public class MemberInfoConvert {

    public static MemberInfoDTO convert(MemberDTO memberDTO) {
        MemberInfoDTO memberInfoDTO = new MemberInfoDTO();
        BeanUtils.copyProperties(memberDTO, memberInfoDTO);
        return memberInfoDTO;
    }
}
