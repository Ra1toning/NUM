import 'dart:io';

import 'package:file_access/models/place.dart';
import 'package:file_access/widgets/image_input.dart';
import 'package:file_access/widgets/location_input.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/user_places.dart';

class AddPlaceScreen extends StatefulWidget {
  const AddPlaceScreen({super.key});

  @override
  State<AddPlaceScreen> createState() => _nameState();
}

class _nameState extends State<AddPlaceScreen> {
  final _titleController = TextEditingController();
  File? image;
  PlaceLocation? location;
  void _savePlace() {
    final enteredTitle = _titleController.text;

    if (enteredTitle.isEmpty) {
      return;
    }
    Provider.of<Provider_Place>(context, listen: false).addPlace(
        new Place(title: enteredTitle, image: image!, location: location!));
    Navigator.of(context).pop();
  }

  @override
  Widget build(BuildContext context) {
    return Consumer<Provider_Place>(builder: (context, provider, child) {
      return Scaffold(
        appBar: AppBar(
          title: const Text('Add new Place'),
        ),
        body: SingleChildScrollView(
          padding: const EdgeInsets.all(12),
          child: Column(
            children: [
              TextField(
                decoration: const InputDecoration(labelText: 'Title'),
                controller: _titleController,
                style: TextStyle(
                  color: Theme.of(context).colorScheme.onBackground,
                ),
              ),
              // image Input
              ImageInput(
                OnTakeImg: (imagex) {
                  image = imagex;
                },
              ),
              const SizedBox(height: 16),
              LocatioInput(
                OnPlaceLoc: (locationx) {
                  location = locationx;
                },
              ),
              ElevatedButton.icon(
                onPressed: _savePlace,
                icon: const Icon(Icons.add),
                label: const Text('Add Place'),
              ),
            ],
          ),
        ),
      );
    });
  }
}
