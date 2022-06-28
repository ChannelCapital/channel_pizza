/* eslint-disable vue/multi-word-component-names */
<template>
<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="../assets/img/carousel1.jpg" class="d-block w-100" alt="img">
    </div>
    <div class="carousel-item">
      <img src="../assets/img/carousel2.jpg" class="d-block w-100" alt="img2">
    </div>
    <div class="carousel-item">
      <img src="../assets/img/carousel3.jpg" class="d-block w-100" alt="img3">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</template>

<script>

export default {
    data() {
        return {
            slide: 0,
            sliding: null
        }
    },
    methods: {

    }
}
</script>

<style>
.carousel {
   background-repeat: no-repeat;
    background-size: cover;
    background-position: center center;
    width: 100%;
    position: relative;
    overflow: hidden;
    z-index: 0;
    border-radius: 15px 15px 15px 15px;
}
.item{ height:200px;}
.item img {
width: 100%;
height: 100% !important;
object-fit: cover;}
</style>
