import com.bt.domain.Battle

class SearchController {

    def index = {

      String text = params["text"]
      def battles = Battle.findByText(text, 100)
       [battles: battles, text:text]
    }
}
