<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Workcafe</title>
    <link rel="stylesheet" href="css/stylesheet.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <script src="https://unpkg.com/axios@0.19.2/dist/axios.js"></script>
    <script src="js/deletePlace.js"></script>
</head>
<body>

<header>
    <#include "logo.ftlh">
    <#--Top right actions-->
    <div class="right-actions">
        <a id="new-place" href="/add" class="button">
            <img src="images/plus.svg" alt="add new place"/>
            <span>Café erfassen</span>
        </a>
        <div>
            <a href="/logout" class="logout-link">Logout?</a>
        </div>
    </div>
</header>
<main>
    <#--Selector for selecting city-->
    <div class="selector-container">
        <div>
            <h2>Wähle eine Stadt aus</h2>
            <nav class="selector">
                <ul>
                    <li>
                        <a class="selector-link ${(selected?? && selected.key == "bern")?then("selected-link","")}"
                           href="?city=bern">Bern</a>
                    </li>
                    <li>
                        <a class="selector-link ${(selected?? && selected.key == "zuerich")?then("selected-link","")}"
                           href="?city=zuerich">Zürich</a>
                    </li>
                    <li>
                        <a class="selector-link ${(selected?? && selected.key == "basel")?then("selected-link","")}"
                           href="?city=basel">Basel</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <#--Places list-->
    <div class="list-section">
        <#if selected??>
            <#list selected.places>
                <ul>
                    <#items as place>
                        <#--Single place card-->
                        <li class="place-container">
                            <div class="action-badge">
                                <#--action button on place card-->
                                <#if isAdmin>
                                    <a class="delete-link" onclick="return deletePlace(${place.id});">
                                        <img src="images/trash.svg" alt="trash"/>
                                    </a>
                                <#else>
                                    <a class="add-rating-link" href="/rating?placeId=${place.id}&city=${selected.key}">
                                        <img src="images/plus.svg" alt="add Rating"/><span>Rating erfassen</span>
                                    </a>
                                </#if>
                            </div>
                            <#--Place information-->
                            <img class="place-image" src="${place.pictureUrl}" alt="place picture"/>
                            <div class="title-container">
                                <h3 class="title-place">${place.placeName}</h3>
                                <span class="rating-place">${place.ratings?size}
                                    <#if place.ratings?size==1>
                                        Rating
                                    <#else >
                                        Ratings
                                    </#if>
                                </span>
                            </div>
                            <#if place.ratings?has_content>
                                <#--Ratings-->
                                <ul class="rating-list">
                                    <li>
                                        <span>Kaffee</span>
                                        <div class="rating-container">
                                            <div class="rating-bar">
                                                <div class="progress" style="width:${place.avgCoffee}%"></div>
                                            </div>
                                            <span class="rating-number">${place.avgCoffee}%</span>
                                        </div>
                                    </li>
                                    <li>
                                        <span>Steckdosen</span>
                                        <div class="rating-container">
                                            <div class="rating-bar">
                                                <div class="progress"
                                                     style="width:${place.avgPowerPlug}%"></div>
                                            </div>
                                            <span class="rating-number">${place.avgPowerPlug}%</span>
                                        </div>
                                    </li>
                                    <li>
                                        <span>Internet</span>
                                        <div class="rating-container">
                                            <div class="rating-bar">
                                                <div class="progress" style="width:${place.avgInternet}%"></div>
                                            </div>
                                            <span class="rating-number">${place.avgInternet}%</span>
                                        </div>
                                    </li>
                                </ul>
                            <#else>
                                <span>Not rated yet</span>
                            </#if>
                        </li>
                    </#items>
                </ul>
            </#list>
        <#else>
            <#--No selection state-->
            <div class="no-selection">
                <h3>
                    Keine Stadt ausgewählt
                </h3>
            </div>
        </#if>
    </div>
</main>
</body>
</html>