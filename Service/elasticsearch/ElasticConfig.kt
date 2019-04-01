import com.handarui.novel.server.manager.config.DisconfElastic
import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestClientBuilder
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ElasticConfig {

    @Autowired
    private lateinit var disconfElastic: DisconfElastic

    @Bean
    fun restClientBuilder(): RestClientBuilder {
        val credentialsProvider = BasicCredentialsProvider()
        credentialsProvider.setCredentials(AuthScope.ANY,
                UsernamePasswordCredentials(disconfElastic.user, disconfElastic.pass))

        val builder = RestClient.builder(HttpHost(disconfElastic.host!!, disconfElastic.port!!))
                .setHttpClientConfigCallback { httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider) }

        return builder
    }

    @Bean(destroyMethod = "close")
    fun restHighLevelClient(): RestHighLevelClient {
        return RestHighLevelClient(restClientBuilder())
    }

}