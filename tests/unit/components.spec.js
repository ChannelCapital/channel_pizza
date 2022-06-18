import { shallowMount } from '@vue/test-utils'
import Header from '@/components/Header.vue'
import HeaderSecure from '@/components/HeaderSecure.vue'

describe('Header.vue', () => {
  it('renders header with router links', () => {
    const routerLinkTag = '<router-link to'
    const wrapper = shallowMount(Header);
    expect(wrapper.html()).toContain(routerLinkTag);
  })
}),

describe('HeaderSecure.vue', () => {
  it('renders header with router links', () => {
    const routerLinkTag = '<router-link to'
    const wrapper = shallowMount(HeaderSecure);
    expect(wrapper.html()).toContain(routerLinkTag);
  })
})
