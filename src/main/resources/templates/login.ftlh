<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login to Workcafe</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/stylesheet.css">
</head>
<body>
<main class="login-container">
    <div class="login">
        <div class="info-header">
            <#include "logo.ftlh">
            <h4>Login to Workcafe</h4>
            <#if error>
                <div class="error alert">Die Anmeldung ist fehlgeschlagen.</div>
            </#if>
            <#if logout>
                <div class="logout alert">Sie wurden erfolgreich abgemeldet.</div>
            </#if>
        </div>
        <form action="login" method="POST" class="login-form">
            <div class="input-group">
                <label for="username">Username</label>
                <input required type="text" id="username" name="username"/>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input required type="password" id="password" name="password"/>
            </div>
            <div class="login-btn-container">
                <button type="submit" class="button">Log in</button>
            </div>
        </form>
    </div>
</main>
</body>
</html>
