import 'package:shop_app/models/cart_model.dart';
import 'package:shop_app/models/product_model.dart';
import 'package:shop_app/services/httpservices.dart';
import 'package:shop_app/models/users.dart';
import 'package:flutter/services.dart';
import 'dart:convert';

class MyRepository {
  final HttpService httpService;
  MyRepository({required this.httpService});
  Future<List<ProductModel>?> fetchProductData() async {
    try {
      dynamic jsonData = await httpService.getData('products', null);
      List<ProductModel> data = ProductModel.fromList(jsonData);
      return data;
    } catch (e) {
      throw Exception('Failed to load data');
    }
  }

  Future<List<ProductModel>?> fetchCartProducts(int userId) async {
    try {
      dynamic jsonData = await httpService.getData('carts/user/$userId', null);
      List<CartModel> allCarts = CartModel.fromList(jsonData);

      dynamic productsData = await httpService.getData('products', null);
      List<ProductModel> products = ProductModel.fromList(productsData);

      CartModel userCart = allCarts.firstWhere(
        (cart) => cart.userId == userId,
      );

      List<int> productIds =
          userCart.products.map((product) => product.productId).toList();

      List<ProductModel> cartProducts =
          products.where((product) => productIds.contains(product.id)).toList();

      return cartProducts;
    } catch (e) {
      throw Exception('Failed to load data');
    }
  }

  Future<String?> login(String username, String password) async {
    try {
      dynamic data = {"username": username, "password": password};
      dynamic jsonData = await httpService.postData('auth/login', null, data);
      return jsonData.body;
    } catch (e) {
      return Future.error(e.toString());
    }
  }

  Future<List<UserModel>> fetchUserDatafromLocal() async {
    String res = await rootBundle.loadString("assets/users.json");
    List<UserModel> data = UserModel.fromList(jsonDecode(res));
    return data;
  }

  Future<bool> submitCart(
      int userId, List<Map<String, dynamic>> products) async {
    final Map<String, dynamic> requestData = {
      "userId": userId,
      "products": products,
    };

    try {
      final response = await httpService.postData('carts', null, requestData);

      print('Request data: $requestData');
      print('Response status code: ${response.statusCode}');
      print('Response body: ${response.body}');

      if (response.statusCode == 200) {
        return true;
      } else {
        return false;
      }
    } catch (e) {
      print('Error: $e');
      return false;
    }
  }
}
