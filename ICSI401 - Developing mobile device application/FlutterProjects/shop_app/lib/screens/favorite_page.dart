import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/provider/globalProvider.dart';
import 'package:shop_app/models/product_model.dart';
import 'package:easy_localization/easy_localization.dart';

class FavoritePage extends StatelessWidget {
  const FavoritePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Consumer<Global_provider>(builder: (context, provider, child) {
      return Scaffold(
        appBar: AppBar(
          backgroundColor: const Color.fromARGB(223, 253, 253, 253),
          title: Text(
            "favorite".tr(),
            style: TextStyle(
              fontSize: 24,
              fontWeight: FontWeight.bold,
              color: Color.fromARGB(223, 37, 37, 37),
            ),
          ),
        ),
        body: ListView.builder(
          itemCount: provider.favoriteItems.length,
          itemBuilder: (context, index) {
            return Card(
              margin: const EdgeInsets.all(8.0),
              child: SizedBox(
                height: 150,
                child: Center(
                  child: ListTile(
                    leading: Image.network(
                      provider.favoriteItems[index].image!,
                      width: 50,
                      height: 50,
                    ),
                    title: Row(
                      children: [
                        Flexible(
                          child: Text(
                            provider.favoriteItems[index].title!,
                            style: const TextStyle(
                              fontSize: 18.0,
                              fontWeight: FontWeight.bold,
                            ),
                            overflow: TextOverflow.ellipsis,
                            maxLines: 2,
                          ),
                        ),
                        IconButton(
                          icon: const Icon(Icons.close),
                          onPressed: () {
                            provider.removeFavorite(index);
                          },
                        ),
                      ],
                    ),
                    subtitle: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          '\$${provider.favoriteItems[index].price!.toStringAsFixed(2)}',
                          style: const TextStyle(
                            fontSize: 16.0,
                            color: Colors.green,
                          ),
                        ),
                        Container(
                          decoration: const BoxDecoration(
                            color: Colors.red,
                            shape: BoxShape.circle,
                          ),
                          child: IconButton(
                            onPressed: () {
                              ProductModel product =
                                  provider.favoriteItems[index];
                              provider.addCartItems(product, context);
                            },
                            icon: const Icon(Icons.shopping_cart),
                            color: Colors.white,
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            );
          },
        ),
      );
    });
  }
}
