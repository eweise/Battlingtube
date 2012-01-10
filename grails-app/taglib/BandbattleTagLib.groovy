import com.bt.domain.Battle
import com.bt.domain.BattleEntry
import com.battlingtube.domain.ImageKey
import java.text.SimpleDateFormat
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.bt.domain.Battle
import com.bt.domain.BattleEntry

class BandbattleTagLib {


  def dateFormat = {attrs ->
    if (attrs.value == null) {
      return
    }
    out << new java.text.SimpleDateFormat(attrs.format).format(attrs.value)
  }

  def title = {attrs ->

    out << "Welcome to ${ConfigurationHolder.config.bt.name}"
  }

  def loggedIn = {attrs, body ->
    if (session.user != null) {
      out << body()
    }
  }

  def pagingControl = { attrs, body ->
   def pagingResult  = attrs.pagingResult;

   out << """<div class='pagingControl'>
             Previous  Page ${pagingResult.currentPage} Next 
             </div>
  """
  }

  def battleLink = { attrs, body ->
    out << """<a href='${request.contextPath}/battle/details/${attrs.battle.id}'>${attrs.battle.name}</a> """

  }

    
  def youtubeSmall = {attrs, body ->
    out << """<div class="youtubeSmall"><object width="170" height="120"><param name="movie"
                value="http://www.youtube.com/v/${attrs.youtubeId}"></param>
                <param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param>
                <embed src="http://www.youtube.com/v/${attrs.youtubeId}" type="application/x-shockwave-flash"
                allowscriptaccess="always" allowfullscreen="true" width="170" height="120"></embed></object></div>"""
  }

  def youtubeLarge = {attrs, body ->
    out << """<div class="youtubeLarge"><object width="100%" height="100%"><param name="movie"
              value="http://www.youtube.com/v/${attrs.youtubeId}&autoplay=1"></param>
              <param name="autoplay" value="1"></param>
              <param name="allowFullScreen" value="false"></param><param name="allowscriptaccess" value="always"></param>
              <embed src="http://www.youtube.com/v/${attrs.youtubeId}&autoplay=1" type="application/x-shockwave-flash"
              allowscriptaccess="always" " allowfullscreen="false" width="100%" height="100%"></embed></object></div>"""
  }

  def savebutton = {attrs, body ->

    out << """<img id="savebutton" name="createimage" src="${request.contextPath}/images/savebutton.png" alt="Vote"
                    onmousedown="document.createimage.src = '${request.contextPath}/images/savebutton_on.png;'"
                    onmouseup="document.createimage.src = '${request.contextPath}/images/savebutton.png;'" onclick="document.${attrs.formName}.submit()"/>
                """
  }

  def voteSmallButton = {attrs, body ->
    out << """<img id="votebutton" name="createimage" src="${request.contextPath}/images/votesmallbutton.png" alt="Vote"
                    onmousedown="document.createimage.src = '${request.contextPath}/images/votesmallbutton_on.png;'"
                    onmouseup="document.createimage.src = '${request.contextPath}/images/votesmallbutton.png;'" onclick="document.location.href='${request.contextPath}/vote/next/${attrs.battleId}'"/>
                """
  }

  def enterButton = {attrs, body ->
    out << """<img id="enterbutton${attrs.battleId}" class="enterButton" name="enterbutton" src="${request.contextPath}/images/enterbutton.png" alt="Enter Battle"
                    onmousedown="document.enterbutton${attrs.battleId}.src = '${request.contextPath}/images/enterbutton_on.png;'"
                    onmouseup="document.enterbutton${attrs.battleId}.src = '${request.contextPath}/images/enterbutton.png;'" onclick="document.location.href='${request.contextPath}/vote/next/${attrs.battleId}'"/>
                """
  }

  def joinButton = {attrs, body ->
    out << """<img id="createimage${attrs.battleId}" name="createimage" src="${request.contextPath}/images/joinbutton.png" alt="Join"
                    onmousedown="document.createimage${attrs.battleId}.src = '${request.contextPath}/images/joinbutton_on.png;'"
                    onmouseup="document.createimage${attrs.battleId}.src = '${request.contextPath}/images/joinbutton.png;'" onclick="document.location.href='${request.contextPath}/battleEntry/create/${attrs.battleId}'"/>
                """
  }

  def printNumberOfRounds = {attrs, body ->
    if (attrs.numberOfRounds == 1) {
      out << "${attrs.numberOfRounds} round"
    }
    else {
      out << "${attrs.numberOfRounds} rounds"
    }
  }


  def inputField = {attrs, body ->
    out << """<li><label class="value ${hasErrors(bean: attrs.domainObject, field: attrs.field, 'errors')}" for="${attrs.field}">${attrs.label}:</label>
    <input type="text" id="${attrs.field}" name="${attrs.field}" value="${fieldValue(bean: attrs.domainObject, field: attrs.field)}"/>"""
    out << body()
    out << "</li>"
  }

  def inputTextArea = {attrs, body ->
    out << """<li><label  ${hasErrors(bean: attrs.domainObject, field: attrs.field, 'errors')}" for="${attrs.field}">${attrs.label}:</label>
    <textarea type="text" rows="'4' cols='34'" id="${attrs.field}" name="${attrs.field}" >${fieldValue(bean: attrs.domainObject, field: attrs.field)}</textArea></li>"""
  }


}
