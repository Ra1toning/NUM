import 'package:flutter/material.dart';
import 'package:shoo_app/ShooOrhilt.dart';

void main() {
  runApp(
    const MaterialApp(
      home: Scaffold(
        body: BodyContainer(),
      ),
    ),
  );
}

class BodyContainer extends StatelessWidget {
  const BodyContainer({super.key});

  @override
  Widget build(context) {
    return Container(
      decoration: const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromARGB(255, 5, 109, 60),
            Color.fromARGB(255, 176, 204, 202)
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      ),
      child: const Center(
        child: ShooOrhilt(),
      ),
    );
  }
}

class StyledText extends StatelessWidget {
  const StyledText(this.text, {super.key});

  final String text;

  @override
  Widget build(context) {
    return Text(
      text,
      style: const TextStyle(
        fontSize: 28,
        color: Color.fromARGB(200, 255, 255, 255),
        fontWeight: FontWeight.bold,
      ),
    );
  }
}
