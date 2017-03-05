# Android Final Project

Projeto final do módulo avançado de desenvolvimento Android, prof Heider Lopes, FIAP.

## Telas

Informações sobre as telas do app.

### SplashScreen

A tela inicial do app, SplashScreen, contém o logo do app, e esse logo
é animado, conforme especificação de funcionalidade 1:

> " [...] Essa tela deverá conter o logotipo da aplicação e uma animação."

Na tela, fazemos a animação no logotipo :)

Ainda nessa tela, fazemos um GET na [nessa API](http://www.mocky.io/v2/58b9b1740f0000b614f09d2f)
e salvamos os dados no SQLite.

### Login

Ao submeter o formulário de login, verificamos se o que foi digitado
são os mesmos dados que estão salvos no SQLite.
