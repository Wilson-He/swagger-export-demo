package per.web.config.config;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ParameterValidation
 *
 * @author Wilson
 * @date 18-7-11
 */
@Component
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

  @Resource
  private ReloadableResourceBundleMessageSource messageSource;

  /**
   * post api请求vo属性参数校验错误处理
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object postParamExceptionHandler(MethodArgumentNotValidException exception)
      throws NoSuchFieldException {
    List<String> errors = new ArrayList<>();
    Class voClass = exception.getParameter().getParameterType();
    for (FieldError each : exception.getBindingResult().getFieldErrors()) {
      String defaultMsg = each.getDefaultMessage();
      if (!Objects.equals(defaultMsg,
          messageSource.getMessage(each.getCode(), each.getArguments(), Locale.getDefault()))) {
        errors.add(defaultMsg);
        continue;
      }
      String fieldName = each.getField();
      Field field = voClass.getDeclaredField(fieldName);
      field.setAccessible(true);
      ApiModelProperty modelProperty = field.getAnnotation(ApiModelProperty.class);
      String head;
      if (modelProperty == null || StringUtils.isEmpty(modelProperty.value())) {
        head = fieldName;
      } else {
        String value = modelProperty.value();
        head = value.contains("(") ? value.split("\\(")[0] : value;
      }
      errors.add(StringUtils.join(head, defaultMsg));
    }
    Map<String, Object> map = new HashMap<>(2);
    map.put("code", HttpStatus.BAD_REQUEST.value());
    map.put("msg", errors);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

  /**
   * get api 请求参数校验错误处理
   */
  @ExceptionHandler({ConstraintViolationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object getMappingParamExceptionHandler(ConstraintViolationException exception) {
    List<ConstraintViolation<?>> list = exception.getConstraintViolations()
        .stream()
        .filter(e -> !e.getPropertyPath().toString().contains("arg"))
        .collect(Collectors.toList());
    List<String> errors = new ArrayList<>(list.size());
    Map<String, Object> map = new HashMap<>(2);
    map.put("code", HttpStatus.BAD_REQUEST.value());
    map.put("msg", errors);
    ConstraintViolation first = list.get(0);
    Object[] validParameters = first.getExecutableParameters();
    Class[] paramClasses = new Class[validParameters.length];
    for (int i = 0; i < validParameters.length; i++) {
      paramClasses[i] = validParameters[i].getClass();
    }
    String methodName = first.getPropertyPath().toString().split("\\.")[0];
    Method method = ReflectionUtils
        .findMethod(first.getRootBean().getClass(), methodName, paramClasses);
    Parameter[] parameters = method.getParameters();
    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    List<String> paramNames = Arrays.asList(parameterNameDiscoverer.getParameterNames(method));
    for (ConstraintViolation each : list) {
      String parameterName = each.getPropertyPath().toString().split("\\.")[1];
      Parameter parameter = parameters[paramNames.indexOf(parameterName)];
      ApiParam param = parameter.getDeclaredAnnotation(ApiParam.class);
      String head;
      if (param == null || StringUtils.isEmpty(param.value())) {
        head = parameterName;
      } else {
        String value = param.value();
        head = value.contains("(") ? value.split("\\(")[0] : value;
      }
      errors.add(StringUtils.join(head, each.getMessage()));
    }
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

}
