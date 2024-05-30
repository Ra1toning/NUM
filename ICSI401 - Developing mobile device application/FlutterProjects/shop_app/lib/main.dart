import 'package:easy_localization/easy_localization.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:flutter/material.dart';
import 'package:shop_app/global_keys.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/provider/globalProvider.dart';
import 'package:shop_app/repository/repository.dart';
import 'package:shop_app/services/httpservices.dart';
import '../../../screens/home_page.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await EasyLocalization.ensureInitialized();
  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider<Global_provider>(
            create: (context) => Global_provider()),
        Provider<HttpService>(
          create: (_) => HttpService(baseUrl: "https://fakestoreapi.com"),
        ),
        ProxyProvider<HttpService, MyRepository>(
            update: (_, httpService, __) =>
                MyRepository(httpService: httpService))
      ],
      child: Builder(
        builder: (context) {
          _initLanguage(context);
          return EasyLocalization(
            supportedLocales: const [Locale('en', 'US'), Locale('mn', 'MN')],
            path: 'assets/translations',
            fallbackLocale: const Locale('en', 'US'),
            child: MyApp(),
          );
        },
      ),
    ),
  );
}

Future<void> _initLanguage(BuildContext context) async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  String? savedLanguage = prefs.getString('language');

  if (savedLanguage != null && savedLanguage.contains('_')) {
    List<String> languageParts = savedLanguage.split('_');
    if (languageParts.length == 2) {
      context.setLocale(Locale(languageParts[0], languageParts[1]));
    } else {
      // Handle unexpected format
      print('Unexpected language format: $savedLanguage');
    }
  } else {
    // Handle case where savedLanguage is null or does not contain '_'
    print('No saved language found or invalid format.');
  }
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      localizationsDelegates: context.localizationDelegates,
      supportedLocales: context.supportedLocales,
      locale: context.locale,
      navigatorKey: GlobalKeys.navigatorKey,
      theme: ThemeData(
        useMaterial3: false,
      ),
      home: HomePage(),
    );
  }
}
