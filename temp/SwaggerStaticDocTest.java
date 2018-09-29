package per.web.config;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import per.web.config.Application;
import springfox.documentation.staticdocs.SwaggerResultHandler;
//import springfox.documentation.staticdocs.SwaggerResultHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SwaggerStaticDocTest {
  @Autowired
  private WebApplicationContext context;
  private String snippetDir = "target/generated-snippets";
  private String outputDir = "target/asciidoc";

  private MockMvc mockMvc;

//  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

//  @Test
  public void Test() throws Exception {
    mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
        .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
        .andExpect(status().isOk())
        .andReturn();


    Swagger2MarkupConverter.from(outputDir + "/swagger.json")
        .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
        .withExamples(snippetDir)
        .build()
        .intoFolder(outputDir);
  }


}
