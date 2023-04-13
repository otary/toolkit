package cn.chenzw.toolkit.spring.validation.exception;

import cn.chenzw.toolkit.core.lang.StringKit;
import cn.chenzw.toolkit.spring.http.HttpRequestWrapper;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 默认的违约异常处理器
 *
 * @author chenzw
 */
public class DefaultConstraintViolationExceptionWrapper implements ConstraintViolationExceptionWrapper {

    private List<InvalidField> invalidFields = new ArrayList<>();
    private String methodName;
    private Class<?> beanClass;
    private HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper();

    /**
     * <p>GET + @RequestParam 参数异常</p>
     *
     * @param constraintViolationException
     */
    public DefaultConstraintViolationExceptionWrapper(ConstraintViolationException constraintViolationException) {
        for (ConstraintViolation<?> cv : constraintViolationException.getConstraintViolations()) {
            String fieldName = StringKit.subStringAfterFirst(cv.getPropertyPath().toString(), ".");
            this.invalidFields.add(
                    new InvalidField(fieldName, cv.getMessage(), cv.getInvalidValue(), cv.getMessageTemplate())
            );
            this.beanClass = cv.getRootBeanClass();
            this.methodName = StringKit.subStringBeforeFirst(
                    cv.getPropertyPath().toString(), "."
            );
        }
    }

    /**
     * <p>@RequestBody + @Validated 参数异常</p>
     *
     * @param methodArgumentNotValidException
     */
    public DefaultConstraintViolationExceptionWrapper(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            this.invalidFields.add(
                    new InvalidField(
                            fieldError.getField(),
                            fieldError.getDefaultMessage(),
                            fieldError.getRejectedValue(),
                            fieldError.getDefaultMessage()
                    )
            );
        }
        this.beanClass = methodArgumentNotValidException.getParameter().getContainingClass();
        this.methodName = methodArgumentNotValidException.getParameter().getMethod().getName();
    }

    /**
     * <p>@Validated 对象参数异常</p>
     *
     * @param bindException
     */
    public DefaultConstraintViolationExceptionWrapper(BindException bindException) {
        List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            this.invalidFields.add(
                    new InvalidField(
                            fieldError.getField(),
                            fieldError.getDefaultMessage(),
                            fieldError.getRejectedValue(),
                            fieldError.getDefaultMessage()
                    )
            );
        }
    }

    @Override
    public List<InvalidField> getInvalidFields() {
        return invalidFields;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public HttpRequestWrapper getHttpRequestWrapper() {
        return httpRequestWrapper;
    }

    @Override
    public String toHumanString() {
        StringBuilder msgBuilder = new StringBuilder();
        for (InvalidField invalidField : invalidFields) {
            msgBuilder.append(
                    String.format("参数[%s=%s]校验不通过:[%s]; ", invalidField.getFieldName(), invalidField.getInvalidValue(), invalidField.getMessage()));
        }
        return msgBuilder.toString();
    }

    @Override
    public String getMessages() {
        return invalidFields.stream().map(invalidField -> invalidField.getMessage()).collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return "DefaultConstraintViolationExceptionWrapper{" + "invalidFields=" + invalidFields + ", methodName='"
                + methodName + '\'' + ", beanClass=" + beanClass + ", httpRequestWrapper=" + httpRequestWrapper + '}';
    }
}
