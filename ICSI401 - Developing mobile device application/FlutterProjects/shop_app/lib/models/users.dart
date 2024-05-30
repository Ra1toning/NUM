import 'package:json_annotation/json_annotation.dart';

part 'user_model.g.dart';

@JsonSerializable(createToJson: false)
class UserModel {
  final int? id;
  final String? email;
  final String? username;
  final String? password;
  final Name? name;

  UserModel({
    this.id,
    this.email,
    this.username,
    this.password,
    this.name,
  });

  factory UserModel.fromJson(Map<String, dynamic> json) =>
      _$UserModelFromJson(json);

  static List<UserModel> fromList(List<dynamic> data) =>
      data.map((e) => UserModel.fromJson(e)).toList();
}

@JsonSerializable(createToJson: false)
class Name {
  final String? firstname;
  final String? lastname;

  Name({
    this.firstname,
    this.lastname,
  });

  factory Name.fromJson(Map<String, dynamic> json) => _$NameFromJson(json);
}
