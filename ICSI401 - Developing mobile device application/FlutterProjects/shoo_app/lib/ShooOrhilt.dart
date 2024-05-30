import 'package:flutter/material.dart';
import 'dart:math';

final randomizer = Random();

class ShooOrhilt extends StatefulWidget {
  const ShooOrhilt({Key? key});

  @override
  State<ShooOrhilt> createState() {
    return _ShooOrhiltState();
  }
}

class _ShooOrhiltState extends State<ShooOrhilt> {
  var currentDiceRoll = 1;
  var player1Guess;
  var player2Guess;
  var winner;

  void rollDice() {
    setState(() {
      currentDiceRoll = randomizer.nextInt(6) + 1;
      if (player1Guess == currentDiceRoll) {
        winner = 'Тоглогч 1';
      } else if (player2Guess == currentDiceRoll) {
        winner = 'Тоглогч 2';
      } else {
        winner = null;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Transform.rotate(
          angle: 3.141592,
          child: Column(
            children: [
              StyledText('Тоглогч 1-н сонгосон шоо: $player1Guess'),
              const SizedBox(height: 10),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  for (var i = 1; i <= 6; i++)
                    TextButton(
                      onPressed: () {
                        setState(() {
                          player1Guess = i;
                        });
                      },
                      style: TextButton.styleFrom(
                        backgroundColor:
                            player1Guess == i ? Colors.green : Colors.blue,
                        foregroundColor: Colors.white,
                      ),
                      child: Text(i.toString()),
                    ),
                ],
              )
            ],
          ),
        ),
        Transform.rotate(
          angle: 3.141592,
          child: TextButton(
            onPressed: rollDice,
            style: TextButton.styleFrom(
              foregroundColor: Colors.white,
              textStyle: const TextStyle(
                fontSize: 28,
              ),
            ),
            child: const Text('Шоо хаях'),
          ),
        ),
        const SizedBox(height: 20),
        Image.asset(
          'assets/images/dice-$currentDiceRoll.png',
          width: 200,
        ),
        const SizedBox(
          height: 20,
        ),
        TextButton(
          onPressed: rollDice,
          style: TextButton.styleFrom(
            foregroundColor: Colors.white,
            textStyle: const TextStyle(
              fontSize: 28,
            ),
          ),
          child: const Text('Шоо хаях'),
        ),
        Column(
          children: [
            StyledText('Тоглогч 1-н сонгосон шоо: $player2Guess'),
            const SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                for (var i = 1; i <= 6; i++)
                  TextButton(
                    onPressed: () {
                      setState(() {
                        player2Guess = i;
                      });
                    },
                    style: TextButton.styleFrom(
                      backgroundColor:
                          player2Guess == i ? Colors.green : Colors.blue,
                      foregroundColor: Colors.white,
                    ),
                    child: Text(i.toString()),
                  ),
              ],
            )
          ],
        ),
        if (winner != null) StyledText('ЯЛАГЧ: $winner'),
      ],
    );
  }
}

class StyledText extends StatelessWidget {
  const StyledText(this.text, {Key? key}) : super(key: key);

  final String text;

  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      style: const TextStyle(
        fontSize: 18,
        color: Color.fromARGB(200, 255, 255, 255),
        fontWeight: FontWeight.bold,
      ),
    );
  }
}
