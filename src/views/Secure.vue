<template>
    <div>
        <img alt="welcome" src="../pictures/welcomePage.jpg" id="avatar" /><br><br>
            <div class="col-md-12">
                <p >Select our best pizzas from the menu and add to the basket. It is easy to order and pay at the door!</p>
                <p >To communicate call 888-11-19 or send an email to pizza_fusion@gmail.com</p>

            </div><br>
    </div>
</template>

<script>
import HeaderSecure from '../components/HeaderSecure.vue'

    export default {
    components: {
       HeaderSecure,
    },
        name: 'Secure',
       
        data() {
            return {
            };
        },
    /**
     * This method show a secure profile page of the user logged in. Also create a session storage to 
     * use throught the session.
     * @author: fatih akguc
    */
        created(){
            const userId = this.$route.params.id;
            sessionStorage.setItem("userId",userId);
        }
    }
</script>

<style scoped>
    #secure {
        background-color: #FFFFFF;
        border: 1px solid #CCCCCC;
        padding: 20px;
        margin-top: 10px;
    }

    .col-md-12{
        text-align: center;
    }
</style>