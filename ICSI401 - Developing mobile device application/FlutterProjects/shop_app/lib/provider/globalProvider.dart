import 'package:flutter/material.dart';
import 'package:shop_app/models/product_model.dart';
import 'package:shop_app/models/users.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class Global_provider extends ChangeNotifier {
  List<ProductModel> products = [];
  List<ProductModel> cartItems = [];
  List<ProductModel> favoriteItems = [];
  List<UserModel> users = [];
  int currentIdx = 0;
  UserModel? _currentUser;
  int? _localUserId;

  void setProducts(List<ProductModel> data) {
    products = data;
    notifyListeners();
  }

  void setCartItems(List<ProductModel> data) {
    cartItems = data;
    notifyListeners();
  }

  void setUsers(List<UserModel> item) {
    users = item;
    notifyListeners();
  }

  void setCurrentUser(UserModel? user) {
    _currentUser = user;
    notifyListeners();
  }

  UserModel? get currentUser => _currentUser;

  void addCartItems(ProductModel item, BuildContext context) {
    if (_currentUser != null) {
      if (cartItems.any((e) => e.id == item.id)) {
        cartItems.removeWhere((e) => e.id == item.id);
      } else {
        cartItems.add(item);
      }
      notifyListeners();
    } else {
      // Show SnackBar to prompt user to log in
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text(
              'Та эхлээд нэвтэрснээр дурдсан бараануудыг хадгалах боломжтой болно'),
        ),
      );
    }
  }

  void addfavItems(ProductModel item, BuildContext context) {
    if (_currentUser != null) {
      if (favoriteItems.any((e) => e.id == item.id)) {
        favoriteItems.removeWhere((e) => e.id == item.id);
      } else {
        favoriteItems.add(item);
      }
      notifyListeners();
    } else {
      // Show SnackBar to prompt user to log in
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text(
              'Та эхлээд нэвтэрснээр дурдсан бараануудыг хадгалах боломжтой болно'),
        ),
      );
    }
  }

  void clearCart() {
    cartItems.clear();
    notifyListeners();
  }

  void removeFavorite(int index) {
    favoriteItems.removeAt(index);
    notifyListeners();
  }

  void changeCurrentIdx(int idx) {
    currentIdx = idx;
    notifyListeners();
  }

  void incrementQuantity(int index) {
    cartItems[index].count++;
    notifyListeners();
  }

  void decreaseQuantity(int index) {
    if (cartItems[index].count > 1) {
      cartItems[index].count--;
    } else {
      cartItems.removeAt(index);
    }
    notifyListeners();
  }

  Future<void> saveToken(String token) async {
    final storage = FlutterSecureStorage();
    await storage.write(key: 'token', value: token);
  }

  void setLocalUserId(int? userId) {
    _localUserId = userId;
    notifyListeners();
  }

  int? getLocalUserId() {
    return _localUserId;
  }

  Future<String?> getToken() async {
    final storage = FlutterSecureStorage();
    return await storage.read(key: 'token');
  }
}
