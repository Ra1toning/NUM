part of 'users.dart';

UserModel _$UserModelFromJson(Map<String, dynamic> json) {
  return UserModel(
    id: json['id'] as int?,
    email: json['email'] as String?,
    username: json['username'] as String?,
    password: json['password'] as String?,
    name: json['name'] == null
        ? null
        : Name.fromJson(json['name'] as Map<String, dynamic>),
  );
}

Name _$NameFromJson(Map<String, dynamic> json) {
  return Name(
    firstname: json['firstname'] as String?,
    lastname: json['lastname'] as String?,
  );
}
