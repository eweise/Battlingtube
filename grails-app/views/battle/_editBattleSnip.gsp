<style type="text/css">
  div.dialog {
    width:200px;
  }
</style>

<div id="editBattle">
  <h3>Create a Battle</h3>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="dialog">
    <g:hasErrors bean="${battle}">
      <div class="errors">
        <g:renderErrors bean="${battle}" as="list"/>
      </div>
    </g:hasErrors>
<div id="updateMe">this div is updated by the form</div>
    <g:formRemote name="myform" update="updateMe"
                  url="[ controller: 'battle', action: 'save']">
      <div id="input-form">
        <input type="hidden" name="id" value="${battle?.id}"/>
        <fieldset>
          <ul id="battle">
            <li><label for="name">Name</label>
              <input type="text" id="name" name="name" value="${fieldValue(bean: battle, field: 'name')}"/>
            </li>
            <li>
              <label for="category.id">Category</label>
              <g:select id="category.id" optionKey="id" from="${com.bt.domain.reference.Category.list()}" name="category.id" value="${battle?.category?.id}"></g:select>
            <g:savebutton formName="myform"/>
            </li>
          </ul>

        </fieldset>
      </div>
    </g:formRemote>
  </div>
</div>

