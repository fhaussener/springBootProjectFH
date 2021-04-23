<#--Component for Rating range input and submit button-->
<li>
    <label for="coffee">Kaffee</label>
    <div class="range-container">
        <input type="range" min="0" max="100" value="0" id="coffee" name="coffee"
               oninput="updateRangeValue(this.value, 'coffeeValue');">
        <span id="coffeeValue">0</span>
    </div>
</li>
<li>
    <label for="powerPlug">Steckdosen</label>
    <div class="range-container">
        <input type="range" min="0" max="100" value="0" id="powerPlug" name="powerPlug"
               oninput="updateRangeValue(this.value, 'powerPlugValue');">
        <span id="powerPlugValue">0</span>
    </div>
</li>
<li>
    <label for="internet">Internet</label>
    <div class="range-container">
        <input type="range" min="0" max="100" value="0" id="internet" name="internet"
               oninput="updateRangeValue(this.value, 'internetValue');">
        <span id="internetValue">0</span>
    </div>
</li>
<li class="form-actions">
    <a id="cancel" href="/">Abbrechen</a>
    <button id="submit" class="button" type="submit">Erfassen</button>
</li>