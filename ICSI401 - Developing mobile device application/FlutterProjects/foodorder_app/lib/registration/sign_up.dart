import 'package:flutter/material.dart';
import 'package:foodorder_app/registration/login.dart';

class RegisterScreen extends StatefulWidget {
  const RegisterScreen({super.key});

  @override
  State<RegisterScreen> createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _confirmPasswordController =
      TextEditingController();

  void _register(context) {
    final String password = _passwordController.text;
    final String confirmPassword = _confirmPasswordController.text;

    if (password.length < 6) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Нууц үг багадаа 6 оронтой байна шүү дээ!!!!'),
        ),
      );
      return;
    }

    if (password != confirmPassword) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Нууц үг таарахгүй байна шүү дээ!!!!'),
        ),
      );
      return;
    }

    if (password == confirmPassword) {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => const LoginScreen()),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('')),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'Бүртгүүлэх',
              style: TextStyle(
                fontSize: 30,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 10),
            const Text(
              'Шинэ хэрэглэгчийн мэдээллийг оруулна уу',
              textAlign: TextAlign.center,
              style: TextStyle(
                fontSize: 14,
                fontWeight: FontWeight.normal,
                color: Colors.grey,
              ),
            ),
            const SizedBox(height: 50),
            Container(
              decoration: BoxDecoration(
                border: Border.all(
                  color: Colors.black,
                  width: 1.0,
                ),
                borderRadius: BorderRadius.circular(4.0),
              ),
              child: TextField(
                controller: _usernameController,
                decoration: const InputDecoration(
                  labelText: 'И-Мэйл',
                  contentPadding: EdgeInsets.symmetric(horizontal: 16.0),
                ),
              ),
            ),
            const SizedBox(height: 15),
            Container(
              margin: const EdgeInsets.only(top: 8.0),
              decoration: BoxDecoration(
                border: Border.all(
                  color: Colors.black,
                  width: 1.0,
                ),
                borderRadius: BorderRadius.circular(4.0),
              ),
              child: TextField(
                controller: _passwordController,
                decoration: const InputDecoration(
                  labelText: 'Нууц үг',
                  contentPadding: EdgeInsets.symmetric(horizontal: 16.0),
                ),
                obscureText: true,
              ),
            ),
            const SizedBox(height: 15),
            Container(
              margin: const EdgeInsets.only(top: 8.0),
              decoration: BoxDecoration(
                border: Border.all(
                  color: Colors.black,
                  width: 1.0,
                ),
                borderRadius: BorderRadius.circular(4.0),
              ),
              child: TextField(
                controller: _confirmPasswordController,
                decoration: const InputDecoration(
                  labelText: 'Нууц үг баталгаажуулах',
                  contentPadding: EdgeInsets.symmetric(horizontal: 16.0),
                ),
                obscureText: true,
              ),
            ),
            const SizedBox(height: 30),
            SizedBox(
              width: 400,
              height: 50,
              child: TextButton(
                onPressed: () => _register(context),
                style: TextButton.styleFrom(
                  backgroundColor: const Color.fromARGB(255, 235, 62, 62),
                  foregroundColor: Colors.black,
                ),
                child: const Text('Бүртгүүлэх'),
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Text(
                  'Аль хэдийн бүртгүүлчихээ юу? Тэгвэл',
                  textAlign: TextAlign.center,
                ),
                TextButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => const LoginScreen()),
                    );
                  },
                  child: const Text('Нэвтрэх'),
                ),
              ],
            ),
            const SizedBox(height: 200),
          ],
        ),
      ),
    );
  }
}
