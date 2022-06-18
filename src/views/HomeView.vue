<template>
  <div id="app">
    <div class="container">
      <div class="col-12-md" id="pizzas">
        <div class="container">
          <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
              <div class="carousel-item active">
                <img class="d-block w-100" src="../pictures/pizza.jpg" alt="First slide">
              </div>
              <div class="carousel-item">
                <img class="d-block w-100" src="../pictures/pizza7.png" alt="Second slide">
              </div>
              <div class="carousel-item">
                <img class="d-block w-100" src="../pictures/pizza8.jpg" alt="Third slide">
              </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev" data-interval="100">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div><br>
        <div class="col-md-12">
            <p style="text-align:justify">
             Capital Pizza is a new take on UK's favorite food. We proudly serve up delicious, gourmet pizza in its purest form - untainted by 
            artificial additives, like preservatives, growth hormones, pesticides, nitrates and trans fats (to name a few). While we’re famous for our 
             pizza, our 75% organic menu features an eclectic variety of gourmet sandwiches, salads, desserts, beer and wine. Additionally, we proudly offer
             health conscious alternatives for our friends with selective diets and food allergies, such as our delicious gluten-free pizza, brownies and 
             beer and our tasty vegan selections. Born from a desire to make a difference, every detail of our operations is continuously evaluated from 
             an environmental perspective in an effort to further minimize our ecological footprint. From delivering our food in company owned hybrid 
             vehicles and offsetting 100% of our power consumption with the purchase of renewable wind energy certificates to building restaurants to 
             LEED certified standards, we are committed to being a leader in not only the pizza industry, but in a better quality of life. Through our 
             spirited philosophy and principled leadership, Capital Pizza will redefine the fast casual restaurant industry. <br><br>    
            </p>
             <p class="note"> To order pizza please Signup and Login first! Have a nice meal...</p>
          </div><br>
          <div class="footer">
      <Footer></Footer>
    </div>
      </div>
    </div>
  </div>
</template>

<script>
import Footer from "../components/Footer.vue";


export default {
  name: "App",

  components: {
    Footer,
  
  },
  data() {
    return {
   
    };
  },
  methods: {
   
  },

  created() {
  
  },
};
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 1%;
  
}

.col-12-md{
  width: 100%;
  height: 800px;
  text-align: center;
}

img#avatar{
  width: 80%;
  height: 60%;
  align-content: center;
}

p{
  color: white;
}


img.d-block.w-100{
  height: 400px;
}
</style>

