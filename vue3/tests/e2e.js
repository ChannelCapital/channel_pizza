// https://nightwatchjs.org/
module.exports = {
    'ChannelPizza login, add pizzas, and checkout demo' : function(browser) {
      browser
        .url('http://localhost:8082/home')
        .waitForElementVisible('body')
        .assert.titleContains('Channel Pizza')
        .assert.visible('button.loginToOrder')
        .click('button.loginToOrder')
        .assert.visible('input[name=username]')
        .setValue('input[name=username]', 'user')
        .setValue('input[name=password]', '12345678')
        .click('button.btn-primary')
        .assert.containsText('p','Your cart is empty!')
        .assert.visible('button.pizzaIndex1')
        .assert.visible('button.pizzaIndex2')
        .click('button.pizzaIndex1')
        .click('button.pizzaIndex1')
        .click('button.pizzaIndex1')
        .click('button.pizzaIndex2')
        .click('button.pizzaIndex2')        
        .click('button.checkout-btn')
        .assert.visible('span.totalCost')
        .assert.containsText('span.totalCost','$13.5')
        .assert.containsText('button.placeOrder','Looks Good')
        .click('button.placeOrder')
        .assert.visible('h5.confirmation')
        .assert.containsText('h5.confirmation','We have received your order. It should be on its way to you within 30 minutes.')    
        .click('button.returnHome')
        .assert.containsText('div.cart p','Your cart is empty!')
        .end();
    }
  };