import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/provider/globalProvider.dart';
import 'package:easy_localization/easy_localization.dart';
import 'bags_page.dart';
import 'shop_page.dart';
import 'favorite_page.dart';
import 'profile_page.dart';

class HomePage extends StatelessWidget {
  HomePage({super.key});

  List<Widget> Pages = [
    const ShopPage(),
    const BagsPage(),
    const FavoritePage(),
    const ProfilePage()
  ];
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Consumer<Global_provider>(builder: (context, provider, child) {
      return Scaffold(
        body: Pages[provider.currentIdx],
        bottomNavigationBar: BottomNavigationBar(
            type: BottomNavigationBarType.fixed,
            currentIndex: provider.currentIdx,
            onTap: provider.changeCurrentIdx,
            items: [
              BottomNavigationBarItem(
                  icon: Icon(Icons.shop), label: "shop".tr()),
              BottomNavigationBarItem(
                  icon: Icon(Icons.shopping_basket), label: "bag".tr()),
              BottomNavigationBarItem(
                  icon: Icon(Icons.favorite), label: "favorite".tr()),
              BottomNavigationBarItem(
                  icon: Icon(Icons.person), label: "profile".tr()),
            ]),
      );
    });
  }
}
