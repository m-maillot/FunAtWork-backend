import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.should.shouldMatch
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.funatwork.FunServer
import io.funatwork.database.DatabaseHelper
import io.funatwork.database.OrganizationResource
import io.funatwork.model.Organization
import org.http4k.client.OkHttp
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.util.*

@Tag("fast")
class OrganizationApiTest {

    private val port = Random().nextInt(1000) + 8000
    private val client = OkHttp()
    private val organizationResource: OrganizationResource = mock()
    private val databaseHelper: DatabaseHelper = mock {
        on { organizationResource() } doReturn organizationResource
    }
    private val server = FunServer(port, databaseHelper)
    private val baseUrl = "http://localhost:$port"

    @BeforeEach
    fun setup() {
        server.start()
    }

    @AfterEach
    fun teardown() {
        server.stop()
    }

    @Test
    fun `responds to ping`() {
        client(Request(Method.GET, "$baseUrl/organizations")) shouldMatch hasStatus(Status.OK)
    }

    @Test
    fun `create a new organization`() {
        whenever(organizationResource.create(Organization(name = "MyOrga"))).thenReturn(Organization(id = 1, name = "MyOrga"))
        val request = Request(Method.POST, "$baseUrl/organizations").body("{ \"name\": \"MyOrga\" }")
        val response = client(request)
        response shouldMatch hasStatus(Status.OK)
        response.bodyString() shouldMatch equalTo("{\"id\":1,\"name\":\"MyOrga\"}")
    }

    @Test
    fun `list organization`() {
        whenever(organizationResource.list()).thenReturn(listOf(Organization(1, "Orga1"), Organization(2, "Orga2")))
        val response = client(Request(Method.GET, "$baseUrl/organizations"))
        response shouldMatch hasStatus(Status.OK)
        response.bodyString() shouldMatch equalTo("[{\"id\":1,\"name\":\"Orga1\"},{\"id\":2,\"name\":\"Orga2\"}]")
    }

}