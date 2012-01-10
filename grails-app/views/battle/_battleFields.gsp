<div id="input-form">
  <input type="hidden" name="id" value="${battle.id}"/>
  <fieldset>
    <ul id="battle">
      <li><label for="name">Name</label>
        <input type="text" id="name" name="name" value="${fieldValue(bean: battle, field: 'name')}"/>
      </li>
      <li>
        <label for="description">Description</label>
        <textarea rows="4" cols="30" name="description">${battle.description}</textarea>
      </li>
      <li>
        <label for="numberOfRounds">Number of Rounds</label>
        <g:select name="numberOfRounds" id="numberOfRounds" from="${1..5}" value="${battle.numberOfRounds}"/>
      </li>
      <li>
        <label for="duration">Round Duration (days)</label>
        <g:select name="roundDuration" id="duration" from="${1..10}" value="${battle.roundDuration}"/>
      </li>
      <li>
        <label for="advance">% to advance to next round</label>
        <g:select name="percentageToNextRound" id="advance" from="${[10,20,30,40,50,60,70,80,90]}" value="${battle.percentageToNextRound}"/>%
      </li>
      <li>
        <label for="category.id">Category</label>
        <g:select id="category.id" optionKey="id" from="${categories}" name="category.id" value="${battle?.category?.id}"></g:select>
      </li>
      <li>
        <label for="battle.emails">Email Addresses of people who might be interested in this battle<br/>(One address per line)</label>
        <textarea rows="4" cols="30" name="emails">${battle.emails}</textarea>
      </li>
    </ul>

  </fieldset>
</div>
