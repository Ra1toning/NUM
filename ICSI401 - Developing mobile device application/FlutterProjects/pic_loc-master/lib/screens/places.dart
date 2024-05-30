import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../screens/add_place.dart';
import '../widgets/places_list.dart';
import '../providers/user_places.dart';

class PlacesScreen extends StatelessWidget {
  const PlacesScreen({super.key});
  

  @override
  Widget build(BuildContext context) {

    return Consumer<Provider_Place>(
              builder: (context, provider, child) {
                return Scaffold(
                  appBar: AppBar(
                    title: const Text('Your Places'),
                    actions: [
                      IconButton(
                        icon: const Icon(Icons.add),
                        onPressed: () {
                          Navigator.of(context).push(
                            MaterialPageRoute(
                              builder: (ctx) => const AddPlaceScreen(),
                            ),
                          );
                        },
                      ),
                    ],
                  ),
                  body: PlacesList(
                    places: provider.userPlaces,
                  ),
                );
              }
            );
}
}