window.onload = function () {
    let vue = new Vue({
        el: "#cart_div",
        data: {
            cart: {}
        },
        methods: {
            getCart: function () {
                axios({
                    method: "POST",
                    url: "user.do",
                    params: {
                        operate: "getCart"
                    }
                })
                    .then(function (value) {
                        console.log(value.data);
                        vue.cart = value.data;
                    })
                    .catch(function (reason) {
                    })
            },
            editCartItem: function (id, buyCount) {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: "editCartItem",
                        id: id,
                        buyCount: buyCount
                    }
                })
                    .then(function (value) {
                        vue.getCart();
                    })
                    .catch(function (reason) {
                    })
            }
        },
        mounted: function () {
            this.getCart();
        }
    })
}
