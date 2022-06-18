<template>
  <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="float-container">
              <div class="float-child" id="form">
                  <div class="col-md-12" >
                      <form class="row g-3" id="inputinfos">
                          <div class="col-md-4">
                          <label for="inputEmail4" class="form-label">Email</label>
                          <input type="email" class="form-control" id="email" v-model="email" />
                          </div>
                          <div class="col-md-4">
                          <label for="inputPassword4" class="form-label">Password</label>
                          <input type="password" class="form-control" id="password" v-model="password" />
                          </div>
                          <div class="col-md-4">
                          <label for="inputPhone" class="form-label">Phone</label>
                          <input type="phone" class="form-control" id="phone" v-model="phone" />
                          </div>
                          <div class="col-12">
                          <label for="inputAddress" class="modal-dialog modal-lg">Address Name</label>
                          <input
                              type="text"
                              class="form-control"
                              id="inputAddress"
                              placeholder="Address Name"
                              v-model="inputAddress"
                              v-on:keyup="fillAddress()"
                              v-on:keyup.delete="deleteInputs()"/>
                          </div>
                          <div class="col-12">
                          <label for="inputAddress2" class="form-label">Address Detail</label>
                          <input
                              type="text"
                              class="form-control"
                              id="inputAddress2"
                              placeholder="Disctrict, Apartment, Number"
                              v-model="inputAddress2"
                          />
                          </div>
                          <div class="col-md-6">
                          <label for="inputRegion" class="form-label">Region</label>
                          <input type="text" class="form-control" id="inputRegion"  v-model="inputRegion"/>
                          </div>
                          <div class="col-md-4">
                          <label for="inputCountry" class="form-label">Country</label><br />
                          <input type="text" class="form-control" id="inputCountry"  v-model="inputCountry"/>
                          </div>
                          <div class="col-md-2">
                          <label for="inputZip" class="form-label">Postcode</label>
                          <input type="text" class="form-control" id="inputZip" v-model="inputZip"/>
                          </div>
                          <div class="col-md-2">
                          <div class="form-check">
                              <br />
                              <input
                              class="form-check-input"
                              type="checkbox"
                              id="gridCheck"
                              />
                              <label class="form-check-label" for="gridCheck">
                              Check me out
                              </label>
                          </div>
                          </div>
                          <div class="col-md-12">
                          <br />
                          <button type="submit" class="btn btn-primary" @click.prevent.stop="addRecord()">Sign Up</button>
                          </div>
                      </form>
                  </div>
              </div>
          </div>
      </div>
    </div> 
  </div>
</template>
<script src="vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script>
export default {
  name: "Signup",

   data(){
    return{
    foobar: null,
    email: '',
    password: '',
    phone:'',
    inputAddress: '',
    inputAddress2: '',
    inputRegion: '',
    inputCountry: '',
    inputZip: '',
    info:[],
    info2:[]
    
     }
   },

  methods: {
    /**
    * Method creates a new user by send it to database to save.
    * @author: fatih akguc
   */
   addRecord: function(){
     if(this.email != '' && this.password != '' && this.inputAddress != ''
     && this.inputAddress2 != '' && this.inputRegion != '' && this.inputState != '' 
     && this.inputZip != ''  && this.phone != ''){

      const axios = require("axios");

       axios.post(`http://localhost:8080/add/${this.email}/${this.password}/${this.phone}/${this.inputAddress}/
                  ${this.inputAddress2}/${this.inputRegion}/${this.inputCountry}/${this.inputZip}`)
       .then(function (response) {
         this.email = '';
         this.password = '';
         this.phone='';
         this.inputAddress = '';
         this.inputAddress2= '';
         this.inputRegion= '';
         this.inputCountry = '';
         this.inputZip = '';
         alert(response.data);
        })
        alert('Saved. Thank You! You are directed to Home Page.');
        this.$router.push({path: "/"});        
        }else{
       alert('Fill All The Empty Fields.');
     }
   },

   /**
    * This method uses postcode API and fill the address input areas with the information fetched 
    * from the service. If the input is deleted all the related areas also be deleted.
    * @author: fatih akguc
   */
   fillAddress:function(){
      setTimeout(() => {  
    const axios = require("axios");
    axios.get(`http://api.postcodes.io/places?q=[${this.inputAddress}]`)
       .then((response)=>{
   
        let info2 = [];
        info2 = response.data;

        if(response.data.result.length > 0){
         this.inputAddress = info2.result[0].name_1;
         this.inputRegion = info2.result[0].region;
         this.inputCountry = info2.result[0].country;
         this.inputAddress2 = info2.result[0].district_borough;
         let longitude = info2.result[0].longitude;
         let latitude = info2.result[0].latitude;

       // Autocomplete Postcode from address informations
       axios.get(`https://api.postcodes.io/postcodes?lon=${longitude}&lat=${latitude}`)
       .then((response) =>{
         let info = [];
         info = response.data;
         if(info.result != null){ 
         console.log(info);
         this.inputZip = info.result[0].postcode;
       }
        })
     }
      })
     }, 2000);
   },

   deleteInputs:function(){
    this.inputRegion = '';
    this.inputCountry = '';
    this.inputAddress2 = '';
    this.inputZip = '';
   }
  }
}

</script>

<style scoped>

[class*="col-"]{
  margin-top: 1%;
}

div.from-check{
position: absolute;
margin-left: 3%;
}

div.float-container {
  padding: 0px;
  
}
 div#mailbox{
   align-content: center;
 }

div#form{
width: 100%;
  float: left;
  margin-left: 0%;
}

div#social-buttons{
    width: 10%;
    padding: 1%;
    margin-left: 10%;

}

div.row {
  margin-top: 5%;
}

div#contact{
  width: 500px;
}

span.mail{
  margin-left: 5%;
  width: 100%;
}

span.plane{
  margin-left: 0%;
  margin-top: 100px;
  width: 100%;
}



[class*="fa-"] {
  font-size: 200%;
  color: gray;
  
}

label {
  color: white;
}
.avatar {
  margin-top: 30%;
  margin-bottom: 10%;
  width: 90%;
  text-align: center;
}
  div#buttons{
    text-align: left;  
}

#mail{
  margin-left: 3%;
  margin-bottom: 3%;
}

div#icons-2 {
  padding: 0%;
  text-align: left;
}

#pagenumber {
  text-align: center;
}

</style>