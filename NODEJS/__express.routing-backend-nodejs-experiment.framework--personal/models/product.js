'use strict';

const mongoose = require('mongoose');

var productSchema = new mongoose.Schema({
    name: String,
    picture: String,
    price: {
        type: Number,
        default: 0
    },
    category: {
        type: String,
        enum: [
            'computers',
            'phones',
            'accesories'
        ]
    },
    description: String
});

const Product = mongoose.model('Product', productSchema);

module.exports = Product;