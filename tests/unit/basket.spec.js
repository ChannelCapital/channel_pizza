import { shallowMount } from '@vue/test-utils'
import BasketView from '../../src/views/BasketView.vue'


describe("Testing component metod", () => {

    const wrapper = shallowMount(BasketView);    
        
    it('Should Calculet the sum of the prices', () =>{

        wrapper.setData({ basket: [{"price": 3, "quantity": 5} ]})
            expect(wrapper.vm.calculate()).toBe(15);

       });

});

