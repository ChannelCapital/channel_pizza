<template>
  <div class="container">
     <div class="page-centered">
            <h1 class="login-h1">Register as a new user</h1>
            <form>
                <div class="form-group">
                    <input type="text" placeholder="Username" v-model="username" class="form-control form-input" required>
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" v-model="password" class="form-control form-input" required>
                </div>
                <div class="form-group">
                    <input type="text" placeholder="Post Code" v-model="postcode" class="form-control form-input" required>
                </div>
                
                  <div><button type="button" @click="searchForPostCodes()">Search for Postcode</button></div>
                  
        <div>
<label for="postcodes">Choose a Postcode:</label>
    <select v-model="selected" name="post_id" @change="onChange($event)" class="form-select form-control" >
        
        <option v-bind="postcd" v-for="(postcd, i) in postCodes" v-bind:key = i++>{{postcd}}</option>
        
    </select>
  
  </div>         
                <div><button type="button" @click="Register()">Register</button></div>
                <p v-if="isUsernamePasswordnull">Please fill username, password and postcodes fields.</p>
                <p v-if="canRegistered">User successfully added.</p>
                <a class="nav-link" v-if="canRegistered" href="/login">Go to login page</a>                
            </form>
        </div>
  </div>
</template>

<script>

import axios from 'axios'


export default {
name: "Register",
    data(){
      return{
        username: "",
        password: "",
        address: "",
        postcode: "",
        postCodes: null,        
        canRegistered: false,
        isUsernamePasswordnull: false,
         onChange(e) {
              console.log(e.target.value);
              this.address = this.selected;
              
          }
      };
    },
        methods: {
        async Register() {
        if(this.username !="" && this.password !="" && this.address !=""){
        axios.post("http://localhost:8080/users/addpizzauser", {username: this.username, password: this.password, userAdress: this.address});
        //this.$router.push("/");
        this.canRegistered = true;
        this.isUsernamePasswordnull=false;}
        else{
          this.isUsernamePasswordnull = true;
        }           
        
        
    },
      
          searchForPostCodes(){
          
          axios.get("https://api.postcodes.io/postcodes/" + this.postcode + "/autocomplete").then(Response=>{this.postCodes=Response.data.result}
          );
          
          //location.reload();
          
        },

        
      
        
        

  },
 
 
}
</script>

<style>

</style>