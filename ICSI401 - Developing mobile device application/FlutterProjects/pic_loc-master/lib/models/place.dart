import 'dart:io';

import 'package:uuid/uuid.dart';

const puid = Uuid();
class PlaceLocation{
  PlaceLocation({required this.latitude, required this.longitude});

  final double latitude;
  final double longitude;
}
class Place {
  Place({
    required this.title, 
    required this.image, 
    required this.location,
    id,}) : id=id?? puid.v4();
  final String id;
  final String title;
  File image;
  final PlaceLocation location;
}
