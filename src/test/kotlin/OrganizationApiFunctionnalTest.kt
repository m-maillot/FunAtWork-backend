import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.should.shouldMatch
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.funatwork.FunServer
import io.funatwork.database.DatabaseHelper
import io.funatwork.filter.AdminAuthenticationFilter
import io.funatwork.filter.AuthenticationFilter
import io.funatwork.model.Organization
import org.http4k.client.OkHttp
import org.http4k.core.Filter
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.util.*

@Tag("fast")
class OrganizationApiFunctionnalTest {

    private val port = Random().nextInt(1000) + 8000
    private val client = OkHttp()
    private val databaseHelper = DatabaseHelper("", "mem", "DB_CLOSE_DELAY=-1")
    private val adminFiler: AdminAuthenticationFilter = mock {
        on { invoke() } doReturn Filter { next -> { next(it) } }
    }
    private val authFilter: AuthenticationFilter = mock {
        on { invoke() } doReturn Filter { next -> { next(it) } }
    }
    private val server = FunServer(port = port,
            databaseHelper = databaseHelper,
            adminFilter = adminFiler,
            authFilter = authFilter)
    private val baseUrl = "http://localhost:$port/api/v1"

    @BeforeEach
    fun setup() {
        server.start()

    }

    @AfterEach
    fun teardown() {
        databaseHelper.close()
        server.stop()
    }

    @Test
    fun `responds to ping`() {
        client(Request(Method.GET, "$baseUrl/organizations")) shouldMatch hasStatus(Status.OK)
    }

    @Test
    fun `create a new organization`() {
        val request = Request(Method.POST, "$baseUrl/organizations").body("{ \"name\": \"MyOrga\", \"logo\": \"url\" }")
        val response = client(request)
        response shouldMatch hasStatus(Status.OK).and(hasBody("{\"id\":1,\"name\":\"MyOrga\",\"logo\":\"url\"}"))
    }

    @Test
    fun `list organization`() {
        databaseHelper.organizationResource().create(Organization(name = "Orga1", logo = "url1"))
        databaseHelper.organizationResource().create(Organization(name = "Orga2", logo = "url2"))
        val response = client(Request(Method.GET, "$baseUrl/organizations"))
        response shouldMatch hasStatus(Status.OK).and(hasBody("[{\"id\":1,\"name\":\"Orga1\",\"logo\":\"url1\"},{\"id\":2,\"name\":\"Orga2\",\"logo\":\"url2\"}]"))
    }

}