class BattleControllerTests extends GroovyTestCase {

    void testSearch() {
        def controller = new BattleController()
        controller.search
        this.assertEquals("", controller.response.contentAsString)

    }
}
