package io.github.liuruinian.sentinel.autoconfig;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author hk417
 * @version Created in 2022/4/27 23:47
 * <p>
 *      全局Sentinel熔断处理
 * </p>
 */
@Component
@Slf4j
public class GlobalSentinelBlockerHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response, BlockException e) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("[BlockExceptionHandler] -> block exception: {}", e.getMessage());
            log.info("[BlockExceptionHandler] -> rule: {}", e.getRule());
        }

        JSONObject result = new JSONObject();

        if (e instanceof FlowException) {
            result.put("code", HttpStatus.TOO_MANY_REQUESTS.value());
            result.put("msg", HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
        }

        if (e instanceof DegradeException) {
            result.put("code", HttpStatus.FORBIDDEN.value());
            result.put("msg", "service degraded!");
        }

        if (e instanceof ParamFlowException) {
            result.put("code", HttpStatus.TOO_MANY_REQUESTS.value());
            result.put("msg", HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
        }

        if (e instanceof SystemBlockException) {
            result.put("code", HttpStatus.FORBIDDEN.value());
            result.put("msg", "trigger system protect rules!");
        }

        if (e instanceof AuthorityException) {
            result.put("code", HttpStatus.UNAUTHORIZED.value());
            result.put("msg", "authority rules reject!");
        }

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), result);
    }
}
