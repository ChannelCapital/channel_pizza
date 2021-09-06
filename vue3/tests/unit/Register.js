import Register from "@/components/Register.vue";
import {shallowMount} from "@vue/test-utils"
var mocha = require('mocha')
var describe = mocha.describe
var it = mocha.it
var expect = mocha.expect

describe("Register.vue", () => {
    let wrapper;

    mocha.beforeEach(() => {
        wrapper = shallowMount(Register, {
            methods: { postCodeSearch: () => { }}

        })
        
    })
    it("render", () => {
        expect(wrapper.exists()).toBe(true);
    })
})