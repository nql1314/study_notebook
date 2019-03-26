

import com.baidu.disconf.client.common.annotations.DisconfFile
import com.baidu.disconf.client.common.annotations.DisconfFileItem
import org.springframework.stereotype.Component

@Component
@DisconfFile(filename = "elastic.properties")
class DisconfElastic {

    @get:DisconfFileItem(name = "elastic.server.host")
    var host: String? = null

    @get:DisconfFileItem(name = "elastic.server.port")
    var port: Int? = null

    @get:DisconfFileItem(name = "elastic.client.user")
    var user: String? = null

    @get:DisconfFileItem(name = "elastic.client.pass")
    var pass: String? = null
}