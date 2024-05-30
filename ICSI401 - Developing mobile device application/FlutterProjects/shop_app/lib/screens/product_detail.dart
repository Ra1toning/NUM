import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/models/product_model.dart';
import 'package:shop_app/provider/globalProvider.dart';

// ignore: camel_case_types
class Product_detail extends StatelessWidget {
  final ProductModel product;
  const Product_detail(this.product, {super.key});

  @override
  Widget build(BuildContext context) {
    return Consumer<Global_provider>(builder: (context, provider, child) {
      return Scaffold(
        appBar: AppBar(),
        body: Column(
          children: [
            Image.network(
              product.image!,
              height: 200,
            ),
            Text(
              product.title!,
              style: const TextStyle(fontSize: 25, fontWeight: FontWeight.bold),
            ),
            Text(
              product.description!,
              style: const TextStyle(fontSize: 16),
            ),
            Text(
              'PRICE: \$${product.price}',
              style: const TextStyle(fontSize: 25, fontWeight: FontWeight.bold),
            ),
          ],
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            provider.addCartItems(product, context);
          },
          child: const Icon(Icons.shopping_cart),
        ),
      );
    });
  }
}
