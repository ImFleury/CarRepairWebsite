<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
body {
  background-image: url("https://mcdn.wallpapersafari.com/medium/51/78/dltnIv.jpg");
  margin-bottom: 75px;
}

.styled-box {
  font-family: "Assistant", Arial, Helvetica, sans-serif;
  background: rgb(255, 255, 255);
  border: 0px solid rgb(184, 176, 176);
  border-radius: 15px 35px;
  text-align: center;
  width: 400px;
  height: auto;
  overflow: hidden;
  margin: auto;
  padding: 25px;
  margin-top: 25px;
  padding-bottom: 35px;
  text-decoration: none;
}

a {
  text-decoration: none;
  color: rgb(5, 5, 206);
}

a:hover {
  color: hsl(240, 100%, 27%);
}

h1, h2 {
    color: hsl(240, 100%, 27%);
}


.buttonstyle {
  margin-left: 10px;
  margin-top: 10px;
  text-align: center;
  padding: 9px 14px 9px 14px;
  font-weight: bold;
  font-family: "Assistant", Arial, Helvetica, sans-serif;
  border-radius: 10px;
  border: 1.5px solid silver;
  border-block: 2px;
}
</style>
