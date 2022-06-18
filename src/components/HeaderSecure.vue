<template>
  <header>
    <div class="container" id="navbar">
      <nav class="navbar navbar-light" style="background-color: #e3f2fd">
        <div class="col-md-8"></div>
        <div class="col-md-4" id="links">
          <div class="container-fluid">
            <router-link to="/products">Pizza Menu</router-link> |
            <router-link to="/basket">Basket</router-link> |
          </div>
        </div>
      </nav>
    </div>
    <div></div>
  </header>
</template>

<script>
export default {
  name: "HeaderSecure",
  id:''

};
/**
    * If it is authenticated this secure page header will be shown
    * @author: fatih akguc
   */
</script>

<style scoped lang="scss">
header {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 16px;
  margin-bottom: 10px;
  color: black;


  img.logo {
    width: 80px;
  }
}
</style>
