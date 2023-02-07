<!DOCTYPE html>
<html>   
    <head>
        <title>List of users</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1>Users List:</h1>
        <table>
            <thead>
                <tr>
                    <th>Login:</th>
                    <th>Status:</th>
                    <th>Profile picture:</th>
                </tr>
            </thead>
            <tbody>
                <#list userInfo?keys as key>
                    <#list key?keys as key2>
                        <th>${key2}</th>
                        <th>${key[key2]}</th>
                    <#/list>
                    <th>${userInfo[key]}</th>
                <#/list>
            </tbody>
        </table>
    </body>
</html>