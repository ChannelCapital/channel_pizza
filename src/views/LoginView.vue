<template>  
    <div class="container">
        <h1>Login</h1>
            <div class="container login-container">
            <div class="row">
                <div class="col-md-3"></div>
                 <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Welcome</a></div>
                    </div>     
                    <div style="padding-top:30px" class="panel-body" >
                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                        <form id="loginform" class="form-horizontal" role="form">
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="email" type="text" class="form-control" name="email" value="" placeholder="email" v-model="email">                                        
                                    </div>
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="password" type="password" class="form-control" name="password" placeholder="password" v-model="password">
                                    </div>  
                            <div class="input-group">
                                      <div class="checkbox">
                                        <label>
                                          <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                        </label>
                                      </div>
                                    </div>

                                <div style="margin-top:10px" class="form-group">
                                    <div class="col-sm-12 controls">
                          <button type="submit" class="btn btn-warning" @click.prevent.stop="login()">Login</button>
                                    </div>
                                </div>
                            </form>     
                        </div>                     
                    </div>  
                <div class="col-md-4"></div>
            </div>
        </div>
        </div>
    </div>
</template>

<script>
export default {
  name: "Login",
   data(){
    return{
    foobar: null,
    email: '',
    password: '', 
    submitted: false
     }
   },

methods: {
  /**
    * Login user by entered email and password
    * After button clicked method checks the related password to the email address and if it is correct
    * directs to the secure profile page.
    * @author: fatih akguc
   */
   login: function(){
     if(this.email != '' && this.password != ''){
      const axios = require("axios");
       axios.get(`http://localhost:8080/email/${this.email}`)
         
       .then(x => {

        if(x.data[0] == null){
          alert('No matching email address. Try again');
        }
        else if(this.password === x.data[0].password){
        alert('Correct. You are logged in.');
          this.email = '';
          this.password = '';
          this.$emit("authenticated", true);
          this.$router.push({path: "/secure/"+x.data[0].id}); 
       }else{
          alert('Wrong password. Try again');
       }
        })
        }else{
       alert('Fill All The Empty Fields.');
      
     }
   }
  }
};
</script>

<style scoped>
.float-container {
  padding: 0px;
  align-content: center;
}

.float-child {
  width: 70%;
  float: left;
  margin-left: 2%;
  align-content: center;
  
}
[class*="fa-"] {
  font-size: 190%;
  color: gray;
}

p,h1{
    color: white;
    padding: 2%;
    
}

label {
  color: white;
}
.avatar {
  margin-top: 30%;
  margin-bottom: 10%;
  margin-left: 6%;
  width: 100%;
  text-align: center;
}

#logos{
    text-align: center;
}

#java{
    margin-left: 2%;
}

div#social-links {
  padding: 10px;
  margin-top: 20%;
  text-align: center;
}

#pagenumber {
  text-align: center;
}

</style>
