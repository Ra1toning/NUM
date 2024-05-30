import 'dart:io';

import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import '../models/place.dart';
import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart' as syspath;
import 'package:path/path.dart' as path;

class Provider_Place extends ChangeNotifier {
  List<Place> userPlaces = [];

  Future<Database> _getDatabase() async {
    final db = await openDatabase(
      join(await getDatabasesPath(), 'use_places.db'),
      onCreate: (db, version) {
        return db.execute(
          'CREATE TABLE places(id TEXT PRIMARY KEY, title TEXT, image TEXT, lat REAL, lon REAL)',
        );
      },
      version: 1,
    );
    return db;
  }

  void loadPlaces() async {
    final db = await _getDatabase();
    final data = await db.query('places');
    final places = data
        .map((row) => Place(
              id: row['id'] as String,
              title: row['title'] as String,
              image: File(row['image'] as String),
              location: PlaceLocation(
                  latitude: row['lat'] as double,
                  longitude: row['lon'] as double),
            ))
        .toList();
    userPlaces = places;
    notifyListeners();
  }

  void addPlace(Place a) async {
    final appDir = await syspath.getApplicationDocumentsDirectory();
    final filename = path.basename(a.image.path);
    final copiedimg = await a.image.copy('${appDir.path}/$filename');

    final db = await _getDatabase();
    db.insert('places', {
      'id': a.id,
      'title': a.title,
      'image': '${appDir.path}/$filename',
      'lat': a.location.latitude,
      'lon': a.location.longitude,
    });
    loadPlaces();
    notifyListeners();
  }

  void deletePlace(String id) async {
    final db = await _getDatabase();

    final placeIndex = userPlaces.indexWhere((place) => place.id == id);
    if (placeIndex >= 0) {
      final place = userPlaces[placeIndex];
      final imagePath = place.image.path;

      final imageFileBeforeDeletion = File(imagePath);
      final doesExistBeforeDeletion = await imageFileBeforeDeletion.exists();

      final imageFile = File(imagePath);
      if (await imageFile.exists()) {
        await imageFile.delete();
        print('Image file deleted successfully.');
      } else {
        print('Image file does not exist.');
      }

      final imageFileAfterDeletion = File(imagePath);
      final doesExistAfterDeletion = await imageFileAfterDeletion.exists();

      if (doesExistBeforeDeletion && !doesExistAfterDeletion) {
        print('Image file deleted successfully.');
      } else {
        print('Failed to delete the image file.');
      }

      await db.delete('places', where: 'id = ?', whereArgs: [id]);
      userPlaces.removeAt(placeIndex);
      notifyListeners();
    }
  }
}
