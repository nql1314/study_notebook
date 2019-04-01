
import com.aliyun.oss.ClientConfiguration
import com.aliyun.oss.ClientException
import com.aliyun.oss.HttpMethod
import com.aliyun.oss.OSSClient
import com.aliyun.oss.common.auth.DefaultCredentials
import com.aliyun.oss.common.auth.ServiceSignature
import com.aliyun.oss.common.comm.RequestMessage
import com.aliyun.oss.common.utils.HttpHeaders
import com.aliyun.oss.common.utils.HttpUtil
import com.aliyun.oss.internal.OSSHeaders
import com.aliyun.oss.internal.OSSUtils
import com.aliyun.oss.internal.OSSUtils.*
import com.aliyun.oss.internal.RequestParameters
import com.aliyun.oss.internal.RequestParameters.*
import com.aliyun.oss.internal.SignUtils
import com.aliyun.oss.model.*
import javafx.fxml.FXMLLoader.DEFAULT_CHARSET_NAME
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URI
import java.net.URISyntaxException
import java.net.URL
import java.util.*
import java.util.regex.Pattern


@Service
class ObjectStorageServiceImpl : ObjectStorageService {

    @Autowired
    private lateinit var ossDisconfConfig: DisconfOSS

    @Autowired
    private lateinit var client: OSSClient

    /**
     * 上传对象
     */
    override fun putObject(key: String, `in`: InputStream, isPublic: Boolean) {
        val request = PutObjectRequest(ossDisconfConfig.bucket, key, `in`)
        client.putObject(request)
        if (isPublic) {
            val setObjectAclRequest = SetObjectAclRequest(ossDisconfConfig.bucket, key, CannedAccessControlList.PublicRead)
            client.setObjectAcl(setObjectAclRequest)
        }
        `in`.close()
    }

    /**
     * 上传带md5对象
     */
    override fun putObjectWithMd5(key: String, `in`: InputStream, isPublic: Boolean, contentType: String?): String {
        val md5 = EncodeUtl.getMd5ByFile(`in`.readBytes())
        val metadata = ObjectMetadata()
        metadata.addUserMetadata("md5", md5)
        if (contentType != null) {
            metadata.contentType = contentType
        }
        val request = PutObjectRequest(ossDisconfConfig.bucket, key, `in`, metadata)
        val client = client
        client.putObject(request)
        `in`.close()
        if (isPublic) {
            val setObjectAclRequest = SetObjectAclRequest(ossDisconfConfig.bucket, key, CannedAccessControlList.PublicRead)
            client.setObjectAcl(setObjectAclRequest)
        }
        return md5
    }

    /**
     * 删除对象
     */
    override fun deleteObjects(vararg keys: String) {
        val request = DeleteObjectsRequest(ossDisconfConfig.bucket)
        request.withKeys(keys.asList())
        client.deleteObjects(request)
    }

    /**
     * 生成授权访问url
     */
    override fun generateUrl(key: String, expireDay: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, expireDay)
        val expireDate = calendar.time
        val url = generatePresignedUrl(ossDisconfConfig.bucket, key, expireDate, HttpMethod.GET, ossDisconfConfig.endpoint)
        if (isPicture(key)) {
            return url.toString().replace(url.host, ossDisconfConfig.imgCdn)
        } else {
            return url.toString().replace(url.host, ossDisconfConfig.contentCdn)
        }
    }

    /**
     * 生成限时的授权访问url
     */
    override fun generateUrl(key: String, date: Date, style: Boolean): String {
        val request = GeneratePresignedUrlRequest(ossDisconfConfig.bucket, key)
        request.expiration = date
        request.method = HttpMethod.GET
        if (style) {
            request.process = ossDisconfConfig.starIconStyle
        }
        val url = generatePresignedUrl(request, ossDisconfConfig.endpoint)
        if (isPicture(key)) {
            return url.toString().replace(url.host, ossDisconfConfig.imgCdn)
        } else {
            return url.toString().replace(url.host, ossDisconfConfig.contentCdn).replace("http://", "https://")
        }
    }

    /**
     * 生成公开访问url
     */
    override fun generatePublicUrl(key: String): String {
        if (isPicture(key)) {
            return "https://${ossDisconfConfig.imgCdn}/$key"
        } else {
            return "https://${ossDisconfConfig.contentCdn}/$key"
        }
    }

    @Throws(ClientException::class)
    fun generatePresignedUrl(bucketName: String, key: String, expiration: Date, method: HttpMethod, endpoint: String): URL {
        val request = GeneratePresignedUrlRequest(bucketName, key)
        request.expiration = expiration
        request.method = method

        return generatePresignedUrl(request, endpoint)
    }


    @Throws(ClientException::class)
    fun generatePresignedUrl(request: GeneratePresignedUrlRequest, endpoint: String): URL {


        val bucketName = request.bucketName
        if (request.bucketName == null) {
            throw IllegalArgumentException(OSS_RESOURCE_MANAGER.getString("MustSetBucketName"))
        }
        ensureBucketNameValid(request.bucketName)

        if (request.expiration == null) {
            throw IllegalArgumentException(OSS_RESOURCE_MANAGER.getString("MustSetExpiration"))
        }

        val currentCreds = DefaultCredentials(ossDisconfConfig.accessKeyId, ossDisconfConfig.accessKeySecret)
        val accessId = currentCreds.getAccessKeyId()
        val accessKey = currentCreds.getSecretAccessKey()
        val useSecurityToken = currentCreds.useSecurityToken()
        val method = if (request.method != null) request.method else HttpMethod.GET

        val expires = (request.expiration.time / 1000L).toString()
        val key = request.key
        val config = ClientConfiguration()
        val resourcePath = OSSUtils.determineResourcePath(bucketName, key, config.isSLDEnabled())

        val requestMessage = RequestMessage()

        val uri = toURI(endpoint)
        requestMessage.endpoint = OSSUtils.determineFinalEndpoint(uri, bucketName, config)
        requestMessage.method = method
        requestMessage.resourcePath = resourcePath
        requestMessage.headers = request.headers

        requestMessage.addHeader(HttpHeaders.DATE, expires)
        if (request.contentType != null && request.contentType.trim { it <= ' ' } != "") {
            requestMessage.addHeader(HttpHeaders.CONTENT_TYPE, request.contentType)
        }
        if (request.contentMD5 != null && request.contentMD5.trim { it <= ' ' } == "") {
            requestMessage.addHeader(HttpHeaders.CONTENT_MD5, request.contentMD5)
        }
        for ((key1, value) in request.userMetadata) {
            requestMessage.addHeader(OSSHeaders.OSS_USER_METADATA_PREFIX + key1, value)
        }

        val responseHeaderParams = HashMap<String, String>()
        populateResponseHeaderParameters(responseHeaderParams, request.responseHeaders)
        if (responseHeaderParams.size > 0) {
            requestMessage.parameters = responseHeaderParams
        }

        if (request.queryParameter != null && request.queryParameter.size > 0) {
            for ((key1, value) in request.queryParameter) {
                requestMessage.addParameter(key1, value)
            }
        }

        if (request.process != null && request.process.trim { it <= ' ' } != "") {
            requestMessage.addParameter(RequestParameters.SUBRESOURCE_PROCESS, request.process)
        }

        if (useSecurityToken) {
            requestMessage.addParameter(SECURITY_TOKEN, currentCreds.getSecurityToken())
        }

        val canonicalResource = ("/" + (bucketName ?: "")
                + if (key != null) "/$key" else "")
        val canonicalString = SignUtils.buildCanonicalString(method.toString(), canonicalResource,
                requestMessage, expires)
        val signature = ServiceSignature.create().computeSignature(accessKey, canonicalString)

        val params = LinkedHashMap<String, String>()
        params[HttpHeaders.EXPIRES] = expires
        params[OSS_ACCESS_KEY_ID] = accessId
        params[SIGNATURE] = signature
        params.putAll(requestMessage.parameters)

        val queryString = HttpUtil.paramToQueryString(params, DEFAULT_CHARSET_NAME)

        /* Compse HTTP request uri. */
        var url = requestMessage.endpoint.toString()
        if (!url.endsWith("/")) {
            url += "/"
        }
        url += "$resourcePath?$queryString"

        try {
            return URL(url)
        } catch (e: MalformedURLException) {
            throw ClientException(e)
        }

    }

    @Throws(IllegalArgumentException::class)
    private fun toURI(endpoint: String): URI {
        var endpoint = endpoint
        if (!endpoint.contains("://")) {
            endpoint = "http://" + endpoint
        }

        try {
            return URI(endpoint)
        } catch (e: URISyntaxException) {
            throw IllegalArgumentException(e)
        }

    }

    private fun isPicture(key: String): Boolean {
        val reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$"
        val pattern = Pattern.compile(reg)
        val matcher = pattern.matcher(key.toLowerCase())
        return matcher.find()
    }
}