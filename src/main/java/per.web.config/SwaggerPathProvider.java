package per.web.config;

import springfox.documentation.spring.web.paths.AbstractPathProvider;

import javax.servlet.ServletContext;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * SwaggerPathProvider
 *
 * @author Wilson
 * @date 2018/10/3
 */
public class SwaggerPathProvider extends AbstractPathProvider {
    private final String CONTEXT_PATH;
    private ServletContext servletContext;

    public SwaggerPathProvider(String contextPath) {
        super();
        this.CONTEXT_PATH = contextPath;
    }

    public SwaggerPathProvider(ServletContext servletContext) {
        super();
        this.CONTEXT_PATH = isNullOrEmpty(servletContext.getContextPath()) ? "/" : servletContext.getContextPath();
    }

    @Override
    protected String applicationPath() {
        return CONTEXT_PATH;
    }

    @Override
    protected String getDocumentationPath() {
        return CONTEXT_PATH;
    }

}
