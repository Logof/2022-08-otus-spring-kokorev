package ru.otus.collectorio.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import ru.otus.collectorio.payload.response.AnswerCode;
import ru.otus.collectorio.payload.response.EntityResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CollectorioDeniedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write(EntityResponse.error(AnswerCode.FORBIDDEN_CODE).toString());
        response.getWriter().flush();
    }
}
