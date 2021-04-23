<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Workcafe</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/stylesheet.css">
    <script src="js/ratingRange.js"></script>
</head>
<body>

<main>
    <div class="add-form">
        <#if place??>
        <#--Rating form-->
            <form action="/rating?placeId=${place.id}&city=${city}" method="post">
                <h3 class="rating-title">Neues Rating erfassen</h3>
                <ul>
                    <#include "ratingAndSubmit.ftlh">
                </ul>
            </form>
        <#else>
        <#--New Place form-->
            <form action="/" method="post">
                <h3 id="add-title">Neues Café erfassen</h3>
                <ul>
                    <li>
                        <label for="name">Stadt auswählen:</label>
                        <select name="key" id="name">
                            <option value="bern">Bern</option>
                            <option value="zuerich">Zürich</option>
                            <option value="basel">Basel</option>
                        </select>
                    </li>
                    <li>
                        <label for="placeName">Name des Cafe</label>
                        <input required id="placeName" name="placeName">
                    </li>
                    <li>
                        <label for="pictureUrl">Link zum Bild</label>
                        <input type="url" placeholder="https://unsplash/image.com"
                               pattern="https://.*|http://.*" required id="pictureUrl" name="pictureUrl">
                    </li>
                    <#include "ratingAndSubmit.ftlh">
                </ul>
            </form>
        </#if>
    </div>
</main>

</body>
</html>
