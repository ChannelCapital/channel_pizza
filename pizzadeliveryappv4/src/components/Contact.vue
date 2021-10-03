<template>
  <div class="container">
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">Your Pizza Shop</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-link active" aria-current="page" href="/">Home</a>
        <a class="nav-link" v-if=isLoggedin href="/orders">Orders</a>
        <a class="nav-link" v-if ="!isLoggedin" href="/login">Login</a>
        <a class="nav-link" v-if ="!isLoggedin" href="/register">Register</a>        
        <a class="nav-link" href="/contact">Contact</a>        
        <a class="nav-item nav-link disabled" v-if="isLoggedin" href="#" tabindex="-1" aria-disabled="true">Welcome {{userName}}</a>
        <a class="nav-link" v-if="isLoggedin" @click="logOut()" href="#">Logout</a>
      </div>
    </div>
  </div>
</nav>


<section class="section pb-5">

  
  <h2 class="section-heading h1 pt-4">Contact us</h2>
  
  <p class="section-description pb-4"></p>

  <div class="row">

    
    <div class="col-lg-5 mb-4">

      
      <div class="card">

        <div class="card-body">
         
          <div class="md-form">
            <i class="fas fa-user prefix grey-text"></i>
            
            <label for="form-name" id="name">Your name</label>
          </div>

          <div class="md-form">
            <i class="fas fa-envelope prefix grey-text"></i>
            <input type="text" id="name" class="form-control" v-model="name">
            <label for="form-email">Your email</label>
          </div>

          <div class="md-form">
            <i class="fas fa-tag prefix grey-text"></i>
            <input type="text" id="email" class="form-control" v-model="email">
            <label for="form-Subject" id="subject">Subject</label>
          </div>

          <div class="md-form">
            <i class="fas fa-pencil-alt prefix grey-text"></i>
            <textarea id="subject" class="form-control md-textarea" rows="3" v-model="subject"></textarea>
            
          </div>

          <div class="text-center mt-4">
            <button @click="submit()" >Submit</button>
          </div>

        </div>

      </div>
      

    </div>
    

    
    <div class="col-lg-7">

      
      <div id="map-container-google-11" class="z-depth-1-half map-container-6" style="height: 400px">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d754.4937039508591!2d14.265460077818277!3d40.850476722141174!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x133b083fcda6225d%3A0x536d940d135b4e04!2sReggi%20Maurizio!5e0!3m2!1str!2str!4v1632592821814!5m2!1str!2str" 
        width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
      </div>

      <br>
      
      <div class="row text-center">
        <div class="col-md-4">
          <a class="btn-floating blue accent-1"><i class="fas fa-map-marker-alt"></i></a>
          <p>Corso Umberto I, 80138 Napoli NA</p>
          <p>Italy</p>
        </div>

        <div class="col-md-4">
          <a class="btn-floating blue accent-1"><i class="fas fa-phone"></i></a>
          <p>+ 01 234 567 89</p>
          <p>Mon - Fri, 8:00-22:00</p>
        </div>

        <div class="col-md-4">
          <a class="btn-floating blue accent-1"><i class="fas fa-envelope"></i></a>
          <p>info@gmail.com</p>
          <p>sale@gmail.com</p>
        </div>
      </div>

    </div>
    

  </div>

</section>

<p v-if="isSubjectSent">Your suggestion is successfully sent. Thank you!</p>

  
  </div>
</template>

<script>
import axios from 'axios';

export default {
name: "Contact",
data(){
    return{
        name:"",
        email:"",
        subject:"",
        isLoggedIn: false,
        isSubjectSent: false
    };
},

methods: {
    logOut(){
              this.$store.commit("setAuthentication", true);
              sessionStorage.removeItem("id");
              sessionStorage.removeItem("username");
              sessionStorage.clear();
              this.isLoggedin=false;
              this.pizzaUserCart = [];
              
              
            },
            submit(){
                
                axios.post("http://localhost:8080/suggestions/addsuggestion", {name:this.name, email:this.email, subject: this.subject});
                this.isSubjectSent = true;
                this.name = "";
                this.email = "";
                this.subject = "";
                
            }

},

created(){

    this.$store.commit("setAuthentication", true);
    const Responseid = sessionStorage.getItem("id");
    const Responseusername = sessionStorage.getItem("username");
    
    if (Responseid != null && Responseusername != null){
      this.pizzaUserID = Responseid;
      this.userName = Responseusername;
      this.isLoggedin = true;
    }
}


}
</script>

<style>

.map-container-6{
overflow:hidden;
padding-bottom:56.25%;
position:relative;
height:0;
}
.map-container-6 iframe{
left:0;
top:0;
height:100%;
width:100%;
position:absolute;
}

</style>