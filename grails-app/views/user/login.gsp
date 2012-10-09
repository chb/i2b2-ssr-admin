<!doctype html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>I2B2SSR Administration</title>
</head>

<head>

<body>
<div class="body">
  <h1>Login</h1>
  <g:form action="auth" method="post">
    <div class="dialog">
      <table>
        <tbody>
        <tr class="prop">
          <td class="name">
            <label for="login">Login:</label>
          </td>
          <td>
            <input type="text" id="login" name="login"/>
          </td>
        </tr>

        <tr class="prop">
          <td class="name">
            <label for="password">Password:</label>
          </td>
          <td>
            <input type="password" id="password" name="password"/>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button">
        <input class="save" type="submit" value="Login"/>
      </span>
    </div>
  </g:form>
</div>
</body>
</html>
