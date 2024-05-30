import 'package:json_annotation/json_annotation.dart';

part 'cart_model.g.dart';

@JsonSerializable()
class CartModel {
  final int id;
  final int userId;
  final List<Product> products;

  CartModel({
    required this.id,
    required this.userId,
    required this.products,
  });

  factory CartModel.fromJson(Map<String, dynamic> json) =>
      _$CartModelFromJson(json);

  Map<String, dynamic> toJson() => _$CartModelToJson(this);

  static List<CartModel> fromList(List<dynamic> data) =>
      data.map((e) => CartModel.fromJson(e)).toList();
}

@JsonSerializable()
class Product {
  final int productId;
  final int quantity;

  Product({
    required this.productId,
    required this.quantity,
  });

  factory Product.fromJson(Map<String, dynamic> json) =>
      _$ProductFromJson(json);

  Map<String, dynamic> toJson() => _$ProductToJson(this);
}
