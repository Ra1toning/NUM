import 'package:flutter/material.dart';
import 'package:foodorder_app/registration/main_screen.dart';

void main() {
  runApp(
    const MaterialApp(
      home: Scaffold(
        body: MainScreen(),
      ),
      debugShowCheckedModeBanner: false,
    ),
  );
}
