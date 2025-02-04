package core.mvc.tobe.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.annotation.web.RequestBody;
import core.mvc.ObjectMapperException;
import core.mvc.tobe.MethodParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestBodyArgumentResolver extends AbstractAnnotationArgumentResolver {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public boolean supports(MethodParameter methodParameter) {
        return supportAnnotation(methodParameter, RequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, HttpServletRequest request, HttpServletResponse response) {
        try {
            return objectMapper.readValue(request.getReader(), methodParameter.getType());
        } catch (IOException e) {
            throw new ObjectMapperException(e);
        }
    }
}
