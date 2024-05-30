import 'package:flutter/material.dart';
import 'package:registration_app/sign_up.dart';
import 'package:registration_app/login.dart';

class MainScreen extends StatelessWidget {
  const MainScreen({super.key});

  @override
  Widget build(context) {
    return Container(
      padding: const EdgeInsets.all(20),
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'Юу байна даа!',
              style: TextStyle(
                fontSize: 48,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 20),
            const Text(
              'Автомат нэвтрэлт нь таныг хэн болохыг баталгаажуулах боломжийг олгоно',
              textAlign: TextAlign.center,
              style: TextStyle(
                fontSize: 14,
                fontWeight: FontWeight.normal,
                color: Colors.grey,
              ),
            ),
            const SizedBox(height: 50),
            Image.asset('assets/images/verification.png', width: 200),
            const SizedBox(height: 50),
            SizedBox(
              width: 400,
              height: 50,
              child: TextButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => const LoginScreen()),
                  );
                },
                style: TextButton.styleFrom(
                  backgroundColor: const Color.fromARGB(255, 89, 79, 235),
                  foregroundColor: Colors.white,
                ),
                child: const Text('Нэвтрэх'),
              ),
            ),
            const SizedBox(height: 10),
            SizedBox(
              width: 400,
              height: 50,
              child: TextButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => const RegisterScreen()),
                  );
                },
                style: TextButton.styleFrom(
                  backgroundColor: const Color.fromARGB(255, 235, 62, 62),
                  foregroundColor: Colors.black,
                ),
                child: const Text('Бүртгүүлэх'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
