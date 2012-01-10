import com.bt.domain.User

class UserTests extends GroovyTestCase {

    void testFindByCredentials() {
        User user = TestFixture.createDefaultUser()
        assertNotNull(User.findByCredentials(user.username, user.password))
    }
}
