'use strict';

/// Controller
const UserController = require('../controllers/userController');
const ProductController = require('../controllers/productController');

const express = require('express');
const auth = require('../middlewares/auth');
const api = express.Router();

api.get('/product', auth, ProductController.getProducts);
// Pass a parameter ':productId'
api.get('/product/:productId', ProductController.getProduct);
api.post('/product', auth, ProductController.saveProduct);
api.put('/product/:productId', auth,  ProductController.updateProduct);
api.delete('/product/:productId', auth,  ProductController.deleteProduct);

// Token
api.post('/signup', UserController.signUp);
api.post('/signin', UserController.signIn);

// Private
api.get('/private', auth, (req, res) => {
  res.status(200).send({ 
  	message: 'Tienes acceso' 
  })
})

module.exports = api;