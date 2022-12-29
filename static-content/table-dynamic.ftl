<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/static/css/1.css" rel="stylesheet">
    <title>Document</title>
    <head>
<body>
<img src="/static/images/BlackCat.jpg">

<p>Hello, ${name}!

<table border="2">
    <thead>
        <td>Id</td>
        <td>Name</td>
        <td>Price, USD</td>
    </thead>
    <tbody>
    <#list price as item>
    <tr>
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>$${item.price}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>