package in.ankitparmar.portfolio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;   // e.g. "uploads"

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // make sure path ends with '/'
        String location = uploadDir.endsWith("/") ? uploadDir : uploadDir + "/";

        // /uploads/**  ->  file:uploads/
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + location);
    }
}
