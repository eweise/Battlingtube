<fieldset >
  <ul height="800px">
    <g:if test="${battleEntry.youtubeId}">
    <li>
      <div id="youtubeContainer" style="width:300px;margin-left:130px; " >
        <g:youtubeLarge youtubeId="${battleEntry.youtubeId}"/>
      </div>
    </li>
    </g:if>
    <li>
      <ul id="battleEntryFields">
        <input type="hidden" name="id" value="${battleEntry.id}"/>
        <g:inputField domainObject="${battleEntry}" field="youtubeUrl" label="YouTube URL">
          <a id="popupAnchor" href="#" title="More Info">More Info</a></g:inputField>
          <div id="popup" title="Youtube URL" >
              <img src="${request.contextPath}/images/youtube_snippet.png"/>
          </div>

        <li>
        <label>Description</label>
        <textarea  rows="4" cols="37" id="comments" name="comments" >${battleEntry.comments}</textArea>
        </li>
      </ul>
    </li>
    <g:if test="${battleEntry.youtubeId}">
    </g:if>
  </ul>
</fieldset>
 