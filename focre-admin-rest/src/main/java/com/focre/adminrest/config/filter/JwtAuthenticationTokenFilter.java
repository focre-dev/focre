package com.focre.adminrest.config.filter;

import com.focre.adminrest.config.RestProperties;
import com.focre.base.config.filter.TokenAuthBaseFilter;
import com.focre.base.entity.dto.ResultDto;
import com.focre.base.exception.BizExceptionEnum;
import com.focre.base.i18n.MessageService;
import com.focre.base.i18n.consts.CommonMessage;
import com.focre.utlis.enums.ClientTypeEnum;
import com.focre.utlis.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends TokenAuthBaseFilter {

    @Autowired
    private RestProperties properties;

    @Autowired
    private MessageService messageServiceImpl;

    @Override
    protected boolean checkClientType(String clientType) {
        return null != ClientTypeEnum.codeOf(clientType);
    }

    @Override
    protected List<String> getNotAuthPath() {
        return properties.getNotAuthPath();
    }

    @Override
    protected List<String> getNotClientAuthPath() {
        return properties.getNotClientAuthPath();
    }

    @Override
    protected void renderJson(HttpServletResponse response, BizExceptionEnum bizError, CommonMessage messageError)
            throws ServletException, IOException {
        ResponseUtil.renderJson(response, new ResultDto(bizError, messageServiceImpl.getMessage(messageError)));
    }
}
