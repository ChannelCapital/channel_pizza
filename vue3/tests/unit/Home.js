import Home from "@/components/Home.vue";
import {shallowMount} from "@vue/test-utils"
var mocha = require('mocha')
var describe = mocha.describe
var it = mocha.it
var expect = mocha.expect

describe("home.vue", () => {
    let wrapper;

    mocha.beforeEach(() => {
        wrapper = shallowMount(Home, {
            methods: { placeOrder: () => {

            }
                
            }

        })
        
    })
    it("render", () => {
        expect(wrapper.exists()).toBe(true);
    })
})