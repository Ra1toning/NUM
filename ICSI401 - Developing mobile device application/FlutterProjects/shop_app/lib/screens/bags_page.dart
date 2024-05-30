import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/models/product_model.dart';
import 'package:shop_app/provider/globalProvider.dart';
import 'package:shop_app/repository/repository.dart';
import 'package:easy_localization/easy_localization.dart';

class BagsPage extends StatefulWidget {
  const BagsPage({Key? key}) : super(key: key);

  @override
  _BagsPageState createState() => _BagsPageState();
}

class _BagsPageState extends State<BagsPage> {
  Future<List<ProductModel>?> _getCartData() async {
    final provider = Provider.of<Global_provider>(context, listen: false);
    int? userId = provider.getLocalUserId();

    if (userId != null) {
      List<ProductModel>? data =
          await Provider.of<MyRepository>(context, listen: false)
              .fetchCartProducts(userId);
      if (data != null) {
        Provider.of<Global_provider>(context, listen: false).setCartItems(data);
        print('Cart data fetched successfully: $data');
      }
      return data;
    } else {
      return [];
    }
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<List<ProductModel>?>(
      future: _getCartData(),
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return const Center(
            child: CircularProgressIndicator(),
          );
        } else if (snapshot.hasError) {
          return Center(
            child: Text('Error: ${snapshot.error}'),
          );
        } else if (snapshot.hasData) {
          double total = Provider.of<Global_provider>(context)
              .cartItems
              .fold(0, (sum, item) => sum + ((item.price ?? 0) * item.count));

          return Scaffold(
            appBar: AppBar(
              backgroundColor: const Color.fromARGB(223, 253, 253, 253),
              title: Text(
                "cart".tr(),
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                  color: Color.fromARGB(223, 37, 37, 37),
                ),
              ),
            ),
            body: ListView.builder(
              itemCount: Provider.of<Global_provider>(context).cartItems.length,
              itemBuilder: (context, index) {
                var cartItem =
                    Provider.of<Global_provider>(context).cartItems[index];
                return Card(
                  margin: const EdgeInsets.all(8.0),
                  child: SizedBox(
                    height: 150,
                    child: Center(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          ListTile(
                            leading: cartItem.image != null
                                ? Image.network(
                                    cartItem.image!,
                                    width: 50,
                                    height: 50,
                                  )
                                : const Icon(Icons.image_not_supported,
                                    size: 50),
                            title: Text(
                              cartItem.title ?? 'No title',
                              style: const TextStyle(
                                fontSize: 18.0,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            subtitle: Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                Row(
                                  children: [
                                    IconButton(
                                      icon: const Icon(Icons.remove),
                                      onPressed: () {
                                        Provider.of<Global_provider>(context,
                                                listen: false)
                                            .decreaseQuantity(index);
                                      },
                                    ),
                                    Text('${cartItem.count}'),
                                    IconButton(
                                      icon: const Icon(Icons.add),
                                      onPressed: () {
                                        Provider.of<Global_provider>(context,
                                                listen: false)
                                            .incrementQuantity(index);
                                      },
                                    ),
                                  ],
                                ),
                                Text(
                                  '\$${(cartItem.price ?? 0).toStringAsFixed(2)}',
                                  style: const TextStyle(
                                    fontSize: 16.0,
                                    fontWeight: FontWeight.bold,
                                    color: Colors.green,
                                  ),
                                ),
                              ],
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                );
              },
            ),
            bottomNavigationBar: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    "total: \$${total.toStringAsFixed(2)}",
                    style: const TextStyle(
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                      color: Colors.black,
                    ),
                  ),
                  ElevatedButton(
                    onPressed: () async {
                      final cartItems =
                          Provider.of<Global_provider>(context, listen: false)
                              .cartItems;
                      if (cartItems.isNotEmpty) {
                        final List<Map<String, dynamic>> products = cartItems
                            .map((item) => {
                                  "productId": item.id,
                                  "quantity": item.count,
                                })
                            .toList();

                        final userId =
                            1; // Assuming user ID is 1 for simplicity

                        final bool success = await Provider.of<MyRepository>(
                                context,
                                listen: false)
                            .submitCart(userId, products);

                        if (success) {
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(
                              content:
                                  const Text('Худалдан авалт баталгаажлаа'),
                              duration: const Duration(seconds: 3),
                              action: SnackBarAction(
                                label: 'Close',
                                onPressed: () {},
                              ),
                            ),
                          );

                          Provider.of<Global_provider>(context, listen: false)
                              .clearCart();
                        } else {
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(
                              content: Text(
                                  'Failed to complete the purchase. Please try again later.'),
                              duration: const Duration(seconds: 3),
                              action: SnackBarAction(
                                label: 'Close',
                                onPressed: () {},
                              ),
                            ),
                          );
                        }
                      } else {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(
                            content: Text('Your cart is empty!'),
                            duration: Duration(seconds: 2),
                          ),
                        );
                      }
                    },
                    child: Text(
                      'buy'.tr(),
                      style: TextStyle(fontSize: 16),
                    ),
                  ),
                ],
              ),
            ),
          );
        } else {
          return const Center(
            child: Text('No data available'),
          );
        }
      },
    );
  }
}
