
import com.aliyun.oss.OSSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSSConfig {
    @Bean(destroyMethod = "shutdown")
    public OSSClient OSSClient(DisconfOSS disconfOSS) {
        return new OSSClient(disconfOSS.getEndpoint(), disconfOSS.getAccessKeyId(), disconfOSS.getAccessKeySecret());
    }
}
