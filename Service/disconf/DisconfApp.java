
import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * disconf 配置信息 app.properties
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "app.properties")
public class DisconfApp {

    private Boolean enable;

    @DisconfFileItem(name = "app.enable")
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
