import 'package:flutter/material.dart';
import 'package:shop_app/models/users.dart';
import 'package:shop_app/provider/globalProvider.dart';
import 'package:shop_app/repository/repository.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/global_keys.dart';
import 'package:easy_localization/easy_localization.dart';
import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({super.key});

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  Future<List<UserModel>> _getData(BuildContext context) async {
    try {
      List<UserModel> data =
          await Provider.of<MyRepository>(context, listen: false)
              .fetchUserDatafromLocal();
      Provider.of<Global_provider>(context, listen: false).setUsers(data);
      return Provider.of<Global_provider>(context, listen: false).users;
    } catch (e) {
      throw Exception('Failed to get data: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("profile".tr()),
        actions: [
          IconButton(
            icon: const Icon(Icons.language),
            onPressed: _changeLanguage,
          ),
        ],
      ),
      body: FutureBuilder<List<UserModel>>(
        future: _getData(context),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else {
            return Consumer<Global_provider>(
              builder: (context, provider, child) {
                UserModel? user = provider.currentUser;
                if (user == null) {
                  return _buildLoginUI();
                } else {
                  return _buildUserProfile(user);
                }
              },
            );
          }
        },
      ),
    );
  }

  void _changeLanguage() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    if (context.locale.languageCode == Locale('en', 'US').languageCode) {
      context.setLocale(const Locale('mn', 'MN'));
      await prefs.setString('language', 'mn_MN');
    } else {
      context.setLocale(const Locale('en', 'US'));
      await prefs.setString('language', 'en_US');
    }
  }

  Widget _buildLoginUI() {
    return Center(
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          _buildTextField(_emailController, "username".tr(), false),
          const SizedBox(height: 15),
          _buildTextField(_passwordController, "password".tr(), true),
          const SizedBox(height: 15),
          SizedBox(
            width: 400,
            height: 50,
            child: ElevatedButton(
              onPressed: () async {
                String username = _emailController.text;
                String password = _passwordController.text;
                bool isLoggedIn = await _login(username, password, context);
                if (!isLoggedIn) {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(
                      content: Text('Нууц үг эсвэл имэйл буруу байна!'),
                    ),
                  );
                }
              },
              child: Text("login".tr()),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildTextField(
      TextEditingController controller, String labelText, bool obscureText) {
    return Container(
      width: 400,
      height: 50,
      decoration: BoxDecoration(
        border: Border.all(
          color: Colors.black,
          width: 1.0,
        ),
        borderRadius: BorderRadius.circular(4.0),
      ),
      child: TextField(
        controller: controller,
        decoration: InputDecoration(
          labelText: labelText,
          contentPadding: const EdgeInsets.symmetric(horizontal: 16.0),
        ),
        obscureText: obscureText,
      ),
    );
  }

  Widget _buildUserProfile(UserModel user) {
    return Center(
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          const CircleAvatar(
            backgroundImage: NetworkImage(
                'https://static.wikia.nocookie.net/isekai-ojisan/images/5/50/Yousuke_%28Anime%29.png/revision/latest?cb=20220706214655'),
            radius: 50,
          ),
          const SizedBox(height: 20),
          Text(
            '${user.name?.firstname ?? ''} ${user.name?.lastname ?? ''}',
            style: const TextStyle(fontWeight: FontWeight.bold),
          ),
          Text('Email: ${user.email}'),
          const SizedBox(height: 20),
          ElevatedButton(
            onPressed: () {
              final provider =
                  Provider.of<Global_provider>(context, listen: false);
              provider.setCurrentUser(null);
              provider.setLocalUserId(null);
            },
            child: Text("logout".tr()),
          ),
        ],
      ),
    );
  }

  Future<bool> _login(
      String username, String password, BuildContext context) async {
    final provider = Provider.of<Global_provider>(context, listen: false);
    try {
      String? token = await Provider.of<MyRepository>(context, listen: false)
          .login(username, password);

      if (token != null) {
        Map<String, dynamic> tokenMap = json.decode(token);
        UserModel user = UserModel.fromJson(tokenMap);
        provider.saveToken(tokenMap["token"]);
        provider.setCurrentUser(user);
        print(await provider.getToken());

        final localUser = provider.users.firstWhere(
          (localUser) =>
              localUser.username == username && localUser.password == password,
        );
        provider.setLocalUserId(localUser.id!);
        return true;
      } else {
        return false;
      }
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Failed to login: $e'),
        ),
      );
      return false;
    }
  }
}
