import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/models/product_model.dart';
import 'package:shop_app/provider/globalProvider.dart';
import 'package:shop_app/repository/repository.dart';
import '../widgets/ProductView.dart';
import 'package:easy_localization/easy_localization.dart';

class ShopPage extends StatefulWidget {
  const ShopPage({super.key});

  @override
  State<ShopPage> createState() => _ShopPageState();
}

class _ShopPageState extends State<ShopPage> {
  Future<List<ProductModel>?> _getProductData() async {
    final provider = Provider.of<Global_provider>(context, listen: false);
    if (provider.products.isNotEmpty) {
      return provider.products;
    }
    List<ProductModel>? data =
        await Provider.of<MyRepository>(context, listen: false)
            .fetchProductData();
    if (data != null) {
      provider.setProducts(data);
    }
    return provider.products;
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<List<ProductModel>?>(
      future: _getProductData(),
      builder: ((context, snapshot) {
        if (snapshot.hasData) {
          return SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(height: 10),
                Padding(
                  padding: EdgeInsets.only(left: 10),
                  child: Text(
                    "products".tr(),
                    style: const TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Color.fromARGB(223, 37, 37, 37),
                    ),
                  ),
                ),
                const SizedBox(height: 10),
                Padding(
                  padding: const EdgeInsets.only(left: 10),
                  child: Wrap(
                    spacing: 20,
                    runSpacing: 10,
                    children: List.generate(
                      snapshot.data!.length,
                      (index) => ProductViewShop(snapshot.data![index]),
                    ),
                  ),
                ),
                const SizedBox(height: 20),
              ],
            ),
          );
        } else {
          return const Center(
            child: SizedBox(
              height: 25,
              width: 25,
              child: CircularProgressIndicator(),
            ),
          );
        }
      }),
    );
  }
}
