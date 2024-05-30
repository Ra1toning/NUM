import 'package:file_access/models/place.dart';
import 'package:flutter/material.dart';
import 'package:location/location.dart';

class LocatioInput extends StatefulWidget {
  const LocatioInput({super.key, required this.OnPlaceLoc});
  final void Function(PlaceLocation location) OnPlaceLoc;
  @override
  State<LocatioInput> createState() => _LocatioInputState();
}

class _LocatioInputState extends State<LocatioInput> {
  String data ="";
  PlaceLocation? locationP;
  void _getLocation() async {
    Location location = new Location();

    bool serviceEnabled;
    PermissionStatus permissionGranted;
    LocationData locationData;

    serviceEnabled = await location.serviceEnabled();
    if (!serviceEnabled) {
      serviceEnabled = await location.requestService();
      if (!serviceEnabled) {
        return;
      }
    }
    permissionGranted = await location.hasPermission();
    if (permissionGranted == PermissionStatus.denied) {
      permissionGranted = await location.requestPermission();
      if (permissionGranted != PermissionStatus.granted) {
        return;
      }
    }
    locationData = await location.getLocation();
    debugPrint('Lat: ${locationData.latitude}, Lon: ${locationData.longitude}');
    setState(() {
      locationP = PlaceLocation(latitude: locationData.latitude!, longitude: locationData.longitude!);
      data = 'Lat: ${locationData.latitude}, Lon: ${locationData.longitude}';
    });
    widget.OnPlaceLoc(locationP!);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 80,
      width: double.infinity,
      alignment: Alignment.center,
      decoration: BoxDecoration(
        border: Border.all(
          width: 1,
          color: Theme.of(context).colorScheme.primary.withOpacity(0.2),
        ),
      ),
      child: Column(
        children: [
          TextButton.icon(onPressed: _getLocation, icon: const Icon(Icons.location_on), label: const Text('Get Location')),
          Text(data,
          style: TextStyle(
            fontSize: 20,
            fontWeight: FontWeight.bold,
            color: Theme.of(context).colorScheme.primary,
          ),)
        ],
      ),
    );
  }
}