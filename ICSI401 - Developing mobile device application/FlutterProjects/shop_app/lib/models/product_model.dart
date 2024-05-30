import 'package:json_annotation/json_annotation.dart';
part 'product_model.g.dart';

@JsonSerializable(createToJson: false)
class ProductModel {
  final int? id;
  final String? title;
  final double? price;
  final String? description;
  final String? category;
  final String? image;
  final Rating? rating;
  bool isFavorite;
  int count;

  ProductModel({
    this.isFavorite = false,
    this.id,
    this.title,
    this.price,
    this.description,
    this.category,
    this.image,
    this.rating,
    this.count = 1,
  });

 
  ProductModel fromJson(Map<String, dynamic> json) {
    return _$ProductModelFromJson(json);
  }
  

  static List<ProductModel> fromList(List<dynamic> data) => data.map((e) => ProductModel().fromJson(e)).toList();
  
  Map<String, dynamic> toJson() {
    throw UnimplementedError();
  }
}

@JsonSerializable(createToJson: false)
class Rating {
  double? rate;
  int? count;

  Rating({this.rate, this.count});

  factory Rating.fromJson(Map<String, dynamic> json) {
    return _$RatingFromJson(json);
  }
}
