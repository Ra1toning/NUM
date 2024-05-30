import 'package:flutter/material.dart';
import 'package:provider/provider.dart'; // Import Provider package
import 'package:shop_app/screens/product_detail.dart';
import '../models/product_model.dart';
import 'package:shop_app/provider/globalProvider.dart'; // Import your Global_provider

class ProductViewShop extends StatelessWidget {
  final ProductModel data;

  const ProductViewShop(this.data, {super.key});

  _onTap(BuildContext context) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (_) => Product_detail(data)),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Consumer<Global_provider>(
      builder: (context, provider, child) {
        return InkWell(
          onTap: () => _onTap(context),
          child: Card(
            elevation: 4.0,
            margin: const EdgeInsets.all(8.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                // Image
                Container(
                  height: 150.0,
                  decoration: BoxDecoration(
                    image: DecorationImage(
                      image: NetworkImage(data.image!),
                      fit: BoxFit.fitHeight,
                    ),
                  ),
                ),
                // Product details
                Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Flexible(
                            child: Text(
                              data.title!,
                              style: const TextStyle(
                                fontSize: 18.0,
                                fontWeight: FontWeight.bold,
                              ),
                              overflow: TextOverflow.ellipsis,
                              maxLines:
                                  2, // Adjust the number of lines as needed
                            ),
                          ),
                          IconButton(
                            icon: Icon(
                              provider.favoriteItems.contains(data)
                                  ? Icons.favorite
                                  : Icons.favorite_border,
                              color: Colors.red,
                            ),
                            onPressed: () {
                              provider.addfavItems(data, context);
                            },
                          ),
                        ],
                      ),
                      const SizedBox(height: 8.0),
                      Text(
                        '\$${data.price!.toStringAsFixed(2)}',
                        style: const TextStyle(
                          fontSize: 16.0,
                          color: Colors.green,
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        );
      },
    );
  }
}
