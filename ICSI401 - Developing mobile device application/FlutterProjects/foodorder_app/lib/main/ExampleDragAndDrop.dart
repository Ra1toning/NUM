import 'package:flutter/material.dart';
import 'package:foodorder_app/main/orderList.dart';
import 'customer.dart';
import 'package:foodorder_app/main/Item.dart';
import 'MenuListItem.dart';
import 'DraggingListItem.dart';

const List<Item> _items = [
  Item(
    name: 'Мантуун бууз',
    totalPriceCents: 2000,
    uid: '1',
    imageProvider: NetworkImage(
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlwgj2WEk4OFlQLfogbf7Kd6dPD0HoYNJTRXLU0WrcXw&s'),
  ),
  Item(
    name: 'Хуушуур',
    totalPriceCents: 2200,
    uid: '2',
    imageProvider: NetworkImage(
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlUUHzPUg1vcv02ifHPKLX8mitM6OHoDab_pgliPp1Aw&s'),
  ),
  Item(
    name: 'Пирожки',
    totalPriceCents: 2000,
    uid: '3',
    imageProvider: NetworkImage(
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNKNS2DEH1BR36gJeUW3wTZj5aHgRBsBav06mV-_sDYxwN0abKV0z2FRWquTb1KFfbhIs&usqp=CAU'),
  ),
  Item(
    name: 'Ороомог',
    totalPriceCents: 2200,
    uid: '4',
    imageProvider: NetworkImage(
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8uJWxozdYsNW2yvuvOUNJg-ySohMvRxL71Syg2GCW3Q&s'),
  ),
  Item(
    name: 'Кимбаб',
    totalPriceCents: 4200,
    uid: '5',
    imageProvider: NetworkImage(
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6tA2sqtYbTumxrp51xKk_ATiseTGNHgyE7zzzaWSpaw&s'),
  ),
  Item(
    name: 'Хотдог',
    totalPriceCents: 3200,
    uid: '6',
    imageProvider: NetworkImage(
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSdBQOss1x_AS4H4K11naEeW9S-E7UnofulvGQ2MtmPw&s'),
  ),
  Item(
    name: 'Онигири',
    totalPriceCents: 2600,
    uid: '7',
    imageProvider: NetworkImage(
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5FLyjBMZuLXkpTKU6k1yaobmbjlqePcFRqgCeWUZ5Tw&s'),
  ),
];

@immutable
class ExampleDragAndDrop extends StatefulWidget {
  const ExampleDragAndDrop({super.key});

  @override
  State<ExampleDragAndDrop> createState() => _ExampleDragAndDropState();
}

class _ExampleDragAndDropState extends State<ExampleDragAndDrop>
    with TickerProviderStateMixin {
  final List<Item> _selectedItems = [];
  final List<Customer> _people = [
    Customer(
      name: 'Балдандорж',
      imageProvider: const NetworkImage(
          'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNiOEHjzA4oYiOOBH9fRJ1Hvch6G9_Xo95BgrD4ZJqZg&s'),
    ),
  ];

  final GlobalKey _draggableKey = GlobalKey();

  void _itemDroppedOnCustomerCart({
    required Item item,
    required Customer customer,
  }) {
    setState(() {
      customer.items.add(item);
      _selectedItems.add(item);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF7F7F7),
      appBar: AppBar(
        title: const Text('Захиалга'),
      ),
      body: _buildContent(),
    );
  }

  Widget _buildContent() {
    return Stack(
      children: [
        SafeArea(
          child: Column(
            children: [
              Expanded(
                child: _buildMenuList(),
              ),
              _buildPeopleRow(),
            ],
          ),
        ),
      ],
    );
  }

  Widget _buildMenuList() {
    return ListView.separated(
      padding: const EdgeInsets.all(16),
      itemCount: _items.length,
      separatorBuilder: (context, index) {
        return const SizedBox(
          height: 12,
        );
      },
      itemBuilder: (context, index) {
        final item = _items[index];
        return _buildMenuItem(
          item: item,
        );
      },
    );
  }

  Widget _buildMenuItem({
    required Item item,
  }) {
    return LongPressDraggable<Item>(
      data: item,
      dragAnchorStrategy: pointerDragAnchorStrategy,
      feedback: DraggingListItem(
        dragKey: _draggableKey,
        photoProvider: item.imageProvider,
      ),
      child: MenuListItem(
        name: item.name,
        price: item.formattedTotalItemPrice,
        photoProvider: item.imageProvider,
      ),
    );
  }

  Widget _buildPeopleRow() {
    return Container(
      padding: const EdgeInsets.symmetric(
        horizontal: 8,
        vertical: 20,
      ),
      child: TextButton(
        onPressed: () {
          Navigator.of(context).push(
            MaterialPageRoute(
              builder: (context) {
                return OrderList(
                  selectedItems: _selectedItems,
                );
              },
            ),
          );
        },
        child: Row(
          children: _people
              .map((customer) => _buildPersonWithDropZone(customer))
              .toList(),
        ),
      ),
    );
  }

  Widget _buildPersonWithDropZone(Customer customer) {
    return Expanded(
      child: Padding(
        padding: const EdgeInsets.symmetric(
          horizontal: 6,
        ),
        child: DragTarget<Item>(
          builder: (context, candidateItems, rejectedItems) {
            return CustomerCart(
              hasItems: customer.items.isNotEmpty,
              highlighted: candidateItems.isNotEmpty,
              customer: customer,
            );
          },
          onAcceptWithDetails: (details) {
            _itemDroppedOnCustomerCart(
              item: details.data,
              customer: customer,
            );
          },
        ),
      ),
    );
  }
}
