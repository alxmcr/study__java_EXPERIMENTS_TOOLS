'use strict';

// Models
const Product = require('../models/product');

function getProduct(req, res) {

	console.log('GET /api/product/:productId');
	console.log(req.params);


	let productId = req.params.productId;

	Product.findById(productId, (err, product) => {
		if (err) {
			return res.status(500).send({
				message: `Error al realizar la peticion: ${err}`
			})
		} else if (!product) {
			return res.status(404).send({
				message: 'El producto no existe'
			})
		}

		return res.status(200).send({
			product
			// or product: product
		});
	})
}

function getProducts(req, res) {
	Product.find({}, (err, products) => {
		if (err) {
			return res.status(500).send({
				message: `Error al realizar la peticion: ${err}`
			})
		} else if (!products) {
			return res.status(404).send({
				message: 'No existen productos'
			})
		}

		res.status(200).send({
			products
		});
	});
}

function saveProduct(req, res) {
	console.log('POST /api/product');
	console.log(req.body);

	// MongoDB: Product
	let product = new Product();
	product.name = req.body.name;
	product.picture = req.body.picture;
	product.price = req.body.price;
	product.category = req.body.category;
	product.description = req.body.description;

	product.save((err, productStored) => {
		if (err) {
			res.status(500).send({
				message: `Error al guardar en la BD ${err}`
			})
		} else {
			res.status(200).send({
				product: productStored
			})
		}
	});
}

function updateProduct(req, res) {
	console.log('PUT /api/product/:productId');
	console.log(req.params);
	console.log(req.body);

	let productId = req.params.productId;

	var conditions = {
			_id: productId
		},
		productUpdated = req.body,
		options = {
			multi: true
		};

	Product.updateOne(conditions, productUpdated, options, (err, numAffected) => {
		// numAffected is the number of updated documents
		console.log(`${numAffected.n} product(s) updated...`);

		if (err) {
			return res.status(500).send({
				message: `Error al realizar la actualización: ${err}`
			})
		}

		return res.status(200).send({
			product: productUpdated
		});
	});

}

function deleteProduct(req, res) {
	console.log('DELETE /api/product/:productId');
	console.log(req.params);

	let productId = req.params.productId;

	Product.findById(productId, (err, product) => {
		if (err) {
			return res.status(500).send({
				message: `Error al realizar la peticion: ${err}`
			})
		} else if (!product) {
			return res.status(404).send({
				message: 'El producto no existe'
			})
		}

		product.remove(err => {
			if (err) {
				return res.status(500).send({
					message: `Error al borrar el producto: ${err}`
				})
			}
		})

		return res.status(200).send({
			message: 'El prooducto ha sido eliminado'
		});
	})
}

module.exports = {
	getProduct,
	getProducts,
	saveProduct,
	updateProduct,
	deleteProduct
}